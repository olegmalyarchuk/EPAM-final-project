package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IEventUsersDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventUsersDaoImpl extends GenericAbstractDao<Event_users> implements IEventUsersDao {
    private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from event_users order by id;";
    public static final String SQL_SELECT_ALL = "select * from event_users order by id;";
    public static final String SQL_ADD_NEW = "INSERT INTO event_users VALUES(?, ?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE event_users set id=?, user_id=?, event_id=?, present=? where id=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM event_users where id=?";
    public static final String SQL_FIND_BY_ALL = "SELECT * FROM event_users WHERE user_id=? AND event_id=?";
    public static final String SQL_UPDATE_ALL  = "UPDATE event_users set present=? where user_id=? AND event_id=?;";
    public static final String SQL_FIND_USER_WITH_ROLE = "SELECT * FROM users u LEFT JOIN event_users um on um.user_id= u.user_id where um.event_id=? order by um.id;";

    private Mapper<Event_users, PreparedStatement> mapperToDB = (Event_users event_users, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, event_users.getId());
        preparedStatement.setInt(2, event_users.getUser_id());
        preparedStatement.setInt(3, event_users.getEvent_id());
        preparedStatement.setBoolean(4, event_users.isPresent());
    };

    private Mapper<ResultSet, Event_users> mapperFromDB = (ResultSet resultSet, Event_users event_users) -> {
       event_users.setId(resultSet.getInt("id"));
       event_users.setUser_id(resultSet.getInt("user_id"));
       event_users.setEvent_id(resultSet.getInt("event_id"));
       event_users.setPresent(resultSet.getBoolean("present"));
    };

    public EventUsersDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }


    @Override
    public Integer calculateEventUsersNumber() throws DBException {
        return calculateRowCounts(connection, "event_users");
    }

    @Override
    public List<Event_users> findAllEventUsersInDB() throws DBException {
        return findAll(connection, Event_users.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Event_users> findEventUsers(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, Event_users.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public boolean addEventUsersToDB(Event_users event_users) {
        return addToDB(connection, event_users, SQL_ADD_NEW);
    }

    @Override
    public boolean updateEventUsersInDB(Event_users event_users) {
        return updateInDB(connection, event_users, SQL_UPDATE_BY_ID, 5, event_users.getId());
    }

    @Override
    public boolean deleteEventUsersFromDB(Event_users event_users) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, event_users.getId()-1);
    }


    @Override
    public Event_users findByUserIdAndEventId(int user_id, int event_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ALL);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Event_users eventUsers = new Event_users();
                eventUsers.setId(resultSet.getInt("id"));
                eventUsers.setUser_id(resultSet.getInt("user_id"));
                eventUsers.setEvent_id(resultSet.getInt("event_id"));
                eventUsers.setPresent(resultSet.getBoolean("present"));
                return eventUsers;

            }
        } catch (SQLException e) {
           // e.printStackTrace();
            //log
        }
        return null;
    }

    @Override
    public List<Event_users> findUsersWithPresenceByEventId(int event_id) {
      List<Event_users> eventUsers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_WITH_ROLE);
            preparedStatement.setInt(1, event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event_users event_user= new Event_users();
                event_user.setId(resultSet.getInt("id"));
                event_user.setUser_id(resultSet.getInt("user_id"));
                event_user.setEvent_id(resultSet.getInt("event_id"));
                event_user.setPresent(resultSet.getBoolean("present"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setRole_id(resultSet.getInt("role_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_surname(resultSet.getString("user_surname"));
                user.setUser_password(resultSet.getString("user_password"));
                user.setUser_phone(resultSet.getString("user_phone"));
                user.setUser_email(resultSet.getString("user_email"));
                user.setUser_photo_url(resultSet.getString("user_photo_url"));
                user.setUser_address(resultSet.getString("user_address"));
                event_user.setUser(user);
                eventUsers.add(event_user);
            }
        } catch (SQLException e) {
            eventUsers.clear();
            e.printStackTrace();
            //logger
        }
        return eventUsers;

    }

    @Override
    public boolean updateUserPresenceByUserIdAndMeetingId(Event_users event_users) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ALL);
            preparedStatement.setBoolean(1, event_users.isPresent());
            preparedStatement.setInt(2,event_users.getUser_id());
            preparedStatement.setInt(3,event_users.getEvent_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            //log
        }
        return false;
    }
}
