package ua.nure.koshurnykov.usermanagement.db;

import ua.nure.koshurnykov.usermanagement.entity.SystemUser;

import java.util.Calendar;

public class TestMain {
    public static void main(String[] args) {
        SystemUser systemUser = new SystemUser();
        systemUser.setFirstName("Danyil");
        systemUser.setLastName("koshurnykov");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.OCTOBER, 9);
        systemUser.setDateOfBirth(calendar.getTime());
        DAO<SystemUser> systemUserDAO = DAOFactory.getInstance().getUserDao();
        try {
            SystemUser systemUser1 = systemUserDAO.create(systemUser);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
