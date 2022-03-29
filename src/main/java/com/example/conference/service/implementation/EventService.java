package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IEventDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.listener.AppContextAttributeListener;
import com.example.conference.service.IEventService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EventService implements IEventService {
    private static final Logger log = Logger.getLogger(EventService.class);
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IEventDao eventDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            eventDao = daoFactory.getEventDao();
        } catch (DBException e) {
            log.error(e);
        }
    }

    @Override
    public Integer calculateEventNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.calculateEventNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public Integer calculateRegistered(Integer event_id) throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.calculateRegistered(event_id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public Integer calculatePresent(Integer event_id) throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.calculatePresent(event_id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public List<Reports> findAllReports(Integer event_id) throws DBException {
        List<Reports> reports = new ArrayList<>();
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            reports = new ArrayList<>();
            reports = eventDao.findAllReports(event_id);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return reports;
    }

    @Override
    public List<Events> findAllEventsInDB() throws DBException {
        List<Events> events = new ArrayList<>();
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = new ArrayList<>();
            events = eventDao.findAllEventsInDB();
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return events;
    }

    @Override
    public List<Events> findEvents(Integer first, Integer offset) {
        List<Events> events = new ArrayList<>();
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = new ArrayList<>();
            events = eventDao.findEvents(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return events;
    }

    @Override
    public List<Events> findEventsByPlaceUa(String event_place_ua) {
        List<Events> events = new ArrayList<>();
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = new ArrayList<>();
            events = eventDao.findEventsByPlaceUa(event_place_ua);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return events;
    }

    @Override
    public List<Events> findEventsByPlaceEn(String event_place_en) {
        List<Events> events = new ArrayList<>();
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = new ArrayList<>();
            events = eventDao.findEventsByPlaceEn(event_place_en);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return events;
    }

    @Override
    public Events findEventsById(Integer id) {
        Events events = null;
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = eventDao.findEventsById(id);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return events;
    }

    @Override
    public synchronized boolean addEventsToDB(Events events)
    {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.addEventsToDB(events);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateEventsInDB(Events events)
    {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.updateEventsInDB(events);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteEventsByIdFromDB(Integer id) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.deleteEventsByIdFromDB(id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateMeetingEditableData(Events events) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.updateMeetingEditableData(events);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }
}
