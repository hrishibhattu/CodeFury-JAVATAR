package com.toyodo.controller;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.toyodo.model.Products;
import com.toyodo.service.EmployeeService;
import com.toyodo.service.impl.EmployeeServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet("/EmployeeImportProducts")
public class EmployeeImportProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeImportProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		HashMap<String, Integer> counter = new HashMap<String, Integer>();
		int failed = 0, succeeded = 0;
		RequestDispatcher rd;
		Part filePart = request.getPart("importFile");
		String fileName = filePart.getSubmittedFileName();
		for (Part part : request.getParts()) {
			part.write("C:\\Users\\HP\\Desktop\\Sample\\" + fileName);
		}
		try {
			JSONParser parser = new JSONParser();
			JSONArray products;
//			path
			products = (JSONArray) parser
					.parse(new FileReader("C:\\Users\\HP\\Desktop\\Sample\\" + fileName));

			// Create a map with frequency counts of product_id's
			for (Object o : products) {
				JSONObject product = (JSONObject) o;

				String product_id = (String) product.get("product_id");

				if (counter.containsKey(product_id)) {
					counter.put(product_id, counter.get(product_id) + 1);
				} else {
					counter.put(product_id, 1);
				}

			}
			List<Products> listOfValidProducts = new ArrayList<Products>();

			for (Object o : products) {
				JSONObject jsonProduct = (JSONObject) o;

				String product_id = (String) jsonProduct.get("product_id");

				// If duplicate product present in JSON file, ignore that product
				if (counter.get(product_id) > 1) {
					failed++;
					continue;
				}

				String name = (String) jsonProduct.get("name");

				String category = (String) jsonProduct.get("category");

//				Long p = jsonProduct.get("price");
				Long p = (Long) jsonProduct.get("price");
				double price = (double) p;

				Products product = new Products(product_id, name, price, category);
				listOfValidProducts.add(product);
				succeeded++;
			}
			employeeService.importProducts(listOfValidProducts);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println(
					"Number of products successfully added = " + succeeded + "\nNumber of failures = " + failed);
			request.setAttribute("succeeded", succeeded);
			request.setAttribute("failed", failed);
			rd = request.getRequestDispatcher("/JSP/employeeImportProducts.jsp");
			rd.forward(request, response);
		}

	}

}
