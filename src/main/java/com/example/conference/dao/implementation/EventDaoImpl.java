package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IEventDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDaoImpl extends GenericAbstractDao<Events> implements IEventDao {
    private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from events order by event_id;";
    public static final String SQL_SELECT_ALL = "select * from events order by event_id;";
    public static final String SQL_SELECT_BY_PLACE_UA = "select * from events where event_place_ua=?";
    public static final String SQL_SELECT_BY_ID = "select * from events where event_id=?";
    public static final String SQL_SELECT_BY_PLACE_EN = "select * from events where event_place_en=?";
    public static final String SQL_ADD_NEW = "INSERT INTO events VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE events set event_id=?, event_name_ua=?, event_name_en=?, event_place_ua=?, event_place_en=?, event_description_ua=?, event_description_en=?, event_date=?, event_photo_url=? where event_id=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM events where event_id=?;";
    public static final String SQL_FIND_COUNT_REGISTERED = "select count(*) from events join event_users on events.event_id= event_users.event_id where present=0 and events.event_id=?;";
    public static final String SQL_FIND_COUNT_PRESENT = "select count(*) from events join event_users on events.event_id= event_users.event_id where present=1 and events.event_id=?;";
    public static final String SQL_FIND_REPORTS = "select r.* from reports r join events on events.event_id= r.event_id where events.event_id=?;";


    private Mapper<Events, PreparedStatement> mapperToDB = (Events events, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1,events.getEvent_id());
        preparedStatement.setString(2, events.getEvent_name_ua());
        preparedStatement.setString(3, events.getEvent_name_en());
        preparedStatement.setString(4, events.getEvent_place_ua());
        preparedStatement.setString(5, events.getEvent_place_en());
        preparedStatement.setString(6, events.getEvent_description_ua());
        preparedStatement.setString(7, events.getEvent_description_en());
        preparedStatement.setObject(8, events.getEvent_date());
        preparedStatement.setString(9, events.getEvent_photo_url());
    };

    private Mapper<ResultSet, Events> mapperFromDB = (ResultSet resultSet, Events events) -> {
        events.setEvent_id(resultSet.getInt("event_id"));
        events.setEvent_name_ua(resultSet.getString("event_name_ua"));
        events.setEvent_name_en(resultSet.getString("event_name_en"));
        events.setEvent_place_ua(resultSet.getString("event_place_ua"));
        events.setEvent_place_en(resultSet.getString("event_place_en"));
        events.setEvent_description_ua(resultSet.getString("event_description_ua"));
        events.setEvent_description_en(resultSet.getString("event_description_en"));
        events.setEvent_date((LocalDateTime)resultSet.getObject("event_date"));
        events.setEvent_photo_url(resultSet.getString("event_photo_url"));
        try {
            events.setPresentCount(calculatePresent(resultSet.getInt("event_id")));
            events.setRegisteredCount(calculateRegistered(resultSet.getInt("event_id")));
            events.setReports(findAllReports(resultSet.getInt("event_id")));
        } catch (DBException e) {
            e.printStackTrace();
        }
    };

    public EventDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }


    @Override
    public Integer calculateEventNumber() throws DBException {
        return calculateRowCounts(connection, "events", "event_id");
    }

    @Override
    public Integer calculateRegistered(Integer event_id) throws DBException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_COUNT_REGISTERED);
            preparedStatement.setInt(1, event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int countRegistered = resultSet.getInt(1);
                return countRegistered;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer calculatePresent(Integer event_id) throws DBException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_COUNT_PRESENT);
            preparedStatement.setInt(1, event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int countPresent = resultSet.getInt(1);
                return countPresent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reports> findAllReports(Integer event_id) throws DBException {
        List<Reports> reportsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_REPORTS);
            preparedStatement.setInt(1, event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reports reports = new Reports();
                reports.setReport_id(resultSet.getInt("report_id"));
                reports.setEvent_id(resultSet.getInt("event_id"));
                reports.setReport_name_ua(resultSet.getString("report_name_ua"));
                reports.setReport_name_en(resultSet.getString("report_name_en"));
                reportsList.add(reports);
            }
            return reportsList;
        } catch (SQLException e) {
           // e.printStackTrace();
            //log
        }
        return null;
    }

    @Override
    public List<Events> findAllEventsInDB() throws DBException {
        return findAll(connection, Events.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Events> findEvents(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, Events.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public List<Events> findEventsByPlaceUa(String event_place_ua) throws DBException {
        List<Events> eventsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_PLACE_UA);
            preparedStatement.setString(1, event_place_ua);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Events event = new Events();
                event.setEvent_id(resultSet.getInt("event_id"));
                event.setEvent_name_ua(resultSet.getString("event_name_ua"));
                event.setEvent_name_en(resultSet.getString("event_name_en"));
                event.setEvent_place_ua(resultSet.getString("event_place_ua"));
                event.setEvent_place_en(resultSet.getString("event_place_en"));
                event.setEvent_description_ua(resultSet.getString("event_description_ua"));
                event.setEvent_description_en(resultSet.getString("event_description_en"));
                event.setEvent_date((LocalDateTime)resultSet.getObject("event_date"));
                event.setEvent_photo_url(resultSet.getString("event_photo_url"));
                eventsList.add(event);
            }
            return eventsList;
        } catch (SQLException e) {
            eventsList.clear();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Events> findEventsByPlaceEn(String event_place_en) throws DBException {
        List<Events> eventsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_PLACE_EN);
            preparedStatement.setString(1, event_place_en);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Events event = new Events();
                event.setEvent_id(resultSet.getInt("event_id"));
                event.setEvent_name_ua(resultSet.getString("event_name_ua"));
                event.setEvent_name_en(resultSet.getString("event_name_en"));
                event.setEvent_place_ua(resultSet.getString("event_place_ua"));
                event.setEvent_place_en(resultSet.getString("event_place_en"));
                event.setEvent_description_ua(resultSet.getString("event_description_ua"));
                event.setEvent_description_en(resultSet.getString("event_description_en"));
                event.setEvent_date((LocalDateTime)resultSet.getObject("event_date"));
                event.setEvent_photo_url(resultSet.getString("event_photo_url"));
                eventsList.add(event);
            }
            return eventsList;
        } catch (SQLException e) {
            eventsList.clear();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Events findEventsById(Integer id) throws DBException {
        return findBy(connection, Events.class, SQL_SELECT_BY_ID, id);
    }


    @Override
    public boolean addEventsToDB(Events events) {
        return addToDB(connection, events, SQL_ADD_NEW);
    }

    @Override
    public boolean updateEventsInDB(Events events) {
        return updateInDB(connection, events, SQL_UPDATE_BY_ID, 10, events.getEvent_id());
    }

    @Override
    public boolean deleteEventsByIdFromDB(Integer id) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, id);
    }


    @Override
    public boolean updateMeetingEditableData(Events events) {
        return false;
    }
}
