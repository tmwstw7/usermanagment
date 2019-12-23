package ua.nure.cs.koshurnikov.usermanagement.db;

import com.mockobjects.dynamic.Mock;

import ua.nure.cs.koshurnikov.usermanagement.domain.User;

public class MockDaoFactory extends DaoFactory {
	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(Dao.class);
	}
	
	public Mock getMockUserDao() {
		return mockUserDao;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Dao<User> getUserDao() {
		// TODO Auto-generated method stub
		return (Dao<User>) mockUserDao.proxy();
	}

}
