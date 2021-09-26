package com.toyodo.factory;

import com.toyodo.dao.impl.ExternalDAOImpl;

public class ExternalDAOFactory {
	public static ExternalDAOImpl getExternalDAO() {
		return new ExternalDAOImpl();
	}
}
