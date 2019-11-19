package ua.nure.koshurnykov.usermanagement.db;

import ua.nure.koshurnykov.usermanagement.entity.SystemUser;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

class HsqldbSystemUserDAO implements DAO<SystemUser> {

    private ConnectionFactory connectionFactory;
    private static final String INSERT_QUERY = "INSERT INTO USERS (FIRSTNAME, LASTNAME, DATEOFBIRTH) VALUES (?, ?, ?)";
    private static final String CALL_IDENTITY = "call IDENTITY()";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM USERS";
    private static final String UPDATE_QUERY = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, DATEOFBIRTH = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM USERS WHERE ID= ?";
    private static final String FIND_QUERY = "SELECT * FROM USERS WHERE ID = ?";

    public HsqldbSystemUserDAO() {
    }

    public HsqldbSystemUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public SystemUser create(SystemUser systemUser) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERY);
            statement.setString(1, systemUser.getFirstName());
            statement.setString(2, systemUser.getLastName());
            statement.setDate(3,
                    new Date(systemUser.getDateOfBirth().getTime()));
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseException(
                        "Number of the inserted rows: " + n);
            }
            CallableStatement callableStatement = connection
                    .prepareCall(CALL_IDENTITY);
            ResultSet keys = callableStatement.executeQuery();
            if (keys.next()) {
                systemUser.setId(keys.getLong(1));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();
            return systemUser;
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(SystemUser systemUser) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, systemUser.getFirstName());
            preparedStatement.setString(2, systemUser.getLastName());
            preparedStatement.setDate(3,
                    new Date(systemUser.getDateOfBirth().getTime()));
            preparedStatement.setLong(4, systemUser.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows != 1) {
                throw new DatabaseException(
                        "Number of inserted rows: " + changedRows);
            }

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(SystemUser systemUser) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(DELETE_QUERY);
            statement.setLong(1, systemUser.getId());
            int removedRows = statement.executeUpdate();
            if (removedRows != 1) {
                throw new DatabaseException(
                        "Number of removed rows: " + removedRows);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public SystemUser find(Long id) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        SystemUser user = new SystemUser();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(FIND_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new SystemUser();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return user;
    }

    @Override
    public Collection<SystemUser> findAll() throws DatabaseException {
        Collection<SystemUser> systemUsers = new LinkedList<>();
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                SystemUser user = new SystemUser();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                systemUsers.add(user);
            }
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return systemUsers;
    }
}
