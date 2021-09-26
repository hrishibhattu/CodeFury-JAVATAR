package com.toyodo.factory;

import com.toyodo.dao.impl.EmployeeDAOImpl;

public class EmployeeDAOFactory {
	public static EmployeeDAOImpl getEmployeeDAO() {
		return new EmployeeDAOImpl();
	}
}
