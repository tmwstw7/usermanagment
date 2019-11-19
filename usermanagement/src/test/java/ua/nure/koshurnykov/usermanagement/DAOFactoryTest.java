package ua.nure.koshurnykov.usermanagement;

import junit.framework.TestCase;
import ua.nure.koshurnykov.usermanagement.db.DAO;
import ua.nure.koshurnykov.usermanagement.db.DAOFactory;
import ua.nure.koshurnykov.usermanagement.entity.SystemUser;

public class DAOFactoryTest extends TestCase {
    public void testGetUserDAO(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        assertNotNull("DAOFactory instance is null", daoFactory);
        DAO<SystemUser> result = daoFactory.getUserDao();
        assertNotNull("UserDao instance is null", result);
    }
}
