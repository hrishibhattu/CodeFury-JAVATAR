package com.toyodo.factory;

import com.toyodo.dao.impl.CustomerDAOImpl;

public class CustomerDAOFactory {
	public static CustomerDAOImpl getCustomerDAO() {
		return new CustomerDAOImpl();
	}
}
