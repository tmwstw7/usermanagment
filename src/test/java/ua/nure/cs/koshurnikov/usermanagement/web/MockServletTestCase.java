package ua.nure.cs.koshurnikov.usermanagement.web;


import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.cs.koshurnikov.usermanagement.db.DaoFactory;
import ua.nure.cs.koshurnikov.usermanagement.db.MockDaoFactory;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;


	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.Factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}

	
	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}
	
	public Mock getMockUserDao() {
		return mockUserDao;
	}


	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}
}
