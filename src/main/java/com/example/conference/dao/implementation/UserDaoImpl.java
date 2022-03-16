package com.example.conference.dao.implementation;


import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends GenericAbstractDao<User> implements IUserDao {
    private Connection connection;

    public static final String SQL_SELECT_base = "SELECT * from users join user_roles on users.role_id=user_roles.role_id order by user_id;";
    public static final String SQL_SELECT_ALL = "SELECT * from users join user_roles on users.role_id=user_roles.role_id order by user_id;";
    public static final String SQL_SELECT_BY_ID = "SELECT * from users join user_roles on users.role_id=user_roles.role_id where user_id=?;";
    public static final String SQL_SELECT_BY_EMAIL = "SELECT * from users join user_roles on users.role_id=user_roles.role_id where user_email=?;";
    public static final String SQL_SELECT_BY_ROLE = "SELECT * from users join user_roles on users.role_id=user_roles.role_id where user_roles.role_description=?;";
    public static final String SQL_ADD_NEW = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String SQL_UPDATE_BY_ID = "UPDATE users set user_id=?, role_id=?, user_name=?, user_surname=?, user_password=?, user_phone=?, user_email=?, user_photo_url=?, user_address=? where user_id=?;";
    public static final String SQL_UPDATE_BY_EMAIL = "UPDATE users set user_id=?, role_id=?, user_name=?, user_surname=?, user_password=?, user_phone=?, user_email=?, user_photo_url=?, user_address=? where user_email=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM users where user_id=?;";
    public static final String SQL_FIND_AVAILABLE_SPEAKER_BY_REPORT = "SELECT users.* FROM users WHERE NOT EXISTS (SELECT NULL FROM moderator_preposition mp WHERE mp.speaker_id=users.user_id AND mp.report_id=?) AND EXISTS (SELECT NULL FROM user_roles r WHERE r.role_description='speaker' AND r.role_id=users.role_id) ORDER BY users.user_id;";
    public static final String SQL_UPDATE_IMAGE_BY_ID = "UPDATE users set user_photo_url=? where user_id=?;";
    public static final String SQL_FIND_ROLE = "select role_description  from user_roles r join users u on r.role_id = u.role_id where u.user_id=?;";


    private Mapper<User, PreparedStatement> mapperToDB = (User user, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, user.getRole_id());
        preparedStatement.setString(3, user.getUser_name());
        preparedStatement.setString(4, user.getUser_surname());
        preparedStatement.setString(5, user.getUser_password());
        preparedStatement.setString(6, user.getUser_phone());
        preparedStatement.setString(7, user.getUser_email());
        preparedStatement.setString(8, user.getUser_photo_url());
        preparedStatement.setString(9, user.getUser_address());

    };

    private Mapper<ResultSet, User> mapperFromDB = (ResultSet resultSet, User user) -> {
        user.setId(resultSet.getInt("user_id"));
        user.setRole_id(resultSet.getInt("role_id"));
        user.setUser_name(resultSet.getString("user_name"));
        user.setUser_surname(resultSet.getString("user_surname"));
        user.setUser_password(resultSet.getString("user_password"));
        user.setUser_phone(resultSet.getString("user_phone"));
        user.setUser_email(resultSet.getString("user_email"));
        user.setUser_photo_url(resultSet.getString("user_photo_url"));
        user.setUser_address(resultSet.getString("user_address"));
    };

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
    }

    @Override
    public Integer calculateUsersNumber() throws DBException {
        return calculateRowCounts(connection, "users");
    }

    @Override
    public List<User> findAllUsersInDB() throws DBException {
       return findAll(connection, User.class, SQL_SELECT_ALL);
    }

    @Override
    public List<User> findUsers(Integer first, Integer offset) throws DBException {
       return findAllFromTo(connection, User.class, first, offset, SQL_SELECT_base);
    }

    @Override
    public List<User> findUserByRole(User_roles user_roles) throws DBException {
       return findAsListBy(connection, User.class, SQL_SELECT_BY_ROLE, user_roles);
    }

    @Override
    public User findUserById(Integer id) throws DBException {
        return findBy(connection, User.class, SQL_SELECT_BY_ID, id);
    }

    @Override
    public User findUserByEmail(String email) throws DBException {
        return findBy(connection, User.class, SQL_SELECT_BY_EMAIL, email);
    }

    @Override
    public boolean addUserToDB(User user) {
       return addToDB(connection, user, SQL_ADD_NEW);
    }

    @Override
    public boolean updateUserInDB(User user) {
        return updateInDB(connection, user, SQL_UPDATE_BY_ID, 1, user.getId());
    }

    @Override
    public boolean deleteUserFromDB(User user) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, user.getId());
    }

    @Override
    public List<User> findAvailableSpeakersForPrepositionByReport(int reportId) {
        List<User> speakers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_AVAILABLE_SPEAKER_BY_REPORT);
            statement.setInt(1, reportId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getInt("user_id"));
                u.setRole_id(resultSet.getInt("role_id"));
                u.setUser_name(resultSet.getString("user_name"));
                u.setUser_surname(resultSet.getString("user_surname"));
                u.setUser_password(resultSet.getString("user_password"));
                u.setUser_phone(resultSet.getString("user_phone"));
                u.setUser_email(resultSet.getString("user_email"));
                u.setUser_photo_url(resultSet.getString("user_photo_url"));
                u.setUser_address(resultSet.getString("user_address"));
                speakers.add(u);
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            //logger
        }
        return speakers;
    }

    @Override
    public boolean updateUserImagePath(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_IMAGE_BY_ID);
            preparedStatement.setString(1, user.getUser_photo_url());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
           // e.printStackTrace();
            //logger
        }
        return false;
    }
}
