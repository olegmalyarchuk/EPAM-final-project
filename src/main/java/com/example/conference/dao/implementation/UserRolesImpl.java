package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IUserRolesDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRolesImpl extends GenericAbstractDao<User_roles> implements IUserRolesDao {
    private Connection connection;
    public static final String SQL_SELECT_BY_ROLE = "SELECT * FROM user_roles WHERE role_description=?";
    public static final String SQL_SELECT_BASE = "SELECT * FROM user_roles ORDER BY role_id";
    public static final String SQL_SELECT_ALL = "SELECT * FROM user_roles ORDER BY role_id";
    public static final String SQL_ADD_NEW = "INSERT INTO user_roles VALUES(?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE user_roles set role_id=?, role_description=? where role_id=?;";
    public static final String SQL_UPDATE_BY_DESCRIPTION = "UPDATE user_roles set role_id=?, role_description=? where role_description=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM user_roles where role_id=?;";

    private Mapper<User_roles, PreparedStatement> mapperToDB = (User_roles user_roles, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, user_roles.getRole_id());
        preparedStatement.setString(2, user_roles.getRole_description());

    };

    private Mapper<ResultSet, User_roles> mapperFromDB = (ResultSet resultSet, User_roles user_roles) -> {
        user_roles.setRole_id(resultSet.getInt("role_id"));
        user_roles.setRole_description(resultSet.getString("role_description"));
    };

    public UserRolesImpl(Connection connection) {
        this.connection = connection;
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
    }


    @Override
    public Integer calculateUserRolesNumber() throws DBException {
        return calculateRowCounts(connection, "user_roles");
    }

    @Override
    public List<User_roles> findAllUserRolesInDB() throws DBException {
        return findAll(connection, User_roles.class, SQL_SELECT_ALL);
    }

    @Override
    public List<User_roles> findUserRoles(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, User_roles.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public boolean addUserRolesToDB(User_roles user_roles) {
        return addToDB(connection, user_roles, SQL_ADD_NEW);
    }

    @Override
    public boolean updateUserRolesInDB(User_roles user_roles) {
        return updateInDB(connection, user_roles, SQL_UPDATE_BY_DESCRIPTION, 3, user_roles.getRole_id());
    }

    @Override
    public boolean deleteUserRolesFromDB(User_roles user_roles) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, user_roles.getRole_id());
    }

    @Override
    public User_roles findByDescription(String role_description) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ROLE);
            statement.setString(1, role_description.toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User_roles ur = new User_roles();
                ur.setRole_id(resultSet.getInt(1));
                ur.setRole_description(resultSet.getString(2));
                return ur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
