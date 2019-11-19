package ua.nure.koshurnykov.usermanagement.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

    public DatabaseException(Exception e) {
        super(e);
    }

    public DatabaseException(String s) {
        super(s);
    }
}
