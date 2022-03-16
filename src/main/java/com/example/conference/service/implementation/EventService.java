package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IEventDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IEventService;

import java.util.List;

public class EventService implements IEventService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IEventDao eventDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            eventDao = daoFactory.getEventDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateEventNumber() throws DBException {
        return eventDao.calculateEventNumber();
    }

    @Override
    public Integer calculateRegistered(Integer event_id) throws DBException {
        return eventDao.calculateRegistered(event_id);
    }

    @Override
    public Integer calculatePresent(Integer event_id) throws DBException {
        return eventDao.calculatePresent(event_id);
    }

    @Override
    public List<Reports> findAllReports(Integer event_id) throws DBException {
        return eventDao.findAllReports(event_id);
    }

    @Override
    public List<Events> findAllEventsInDB() throws DBException {
        return eventDao.findAllEventsInDB();
    }

    @Override
    public List<Events> findEvents(Integer first, Integer offset) throws DBException {
        return eventDao.findEvents(first, offset);
    }

    @Override
    public List<Events> findEventsByPlaceUa(String event_place_ua) throws DBException {
        return eventDao.findEventsByPlaceUa(event_place_ua);
    }

    @Override
    public List<Events> findEventsByPlaceEn(String event_place_en) throws DBException {
        return eventDao.findEventsByPlaceEn(event_place_en);
    }

    @Override
    public boolean addEventsToDB(Events events) {
        return eventDao.addEventsToDB(events);
    }

    @Override
    public boolean updateEventsInDB(Events events) {
        return eventDao.updateEventsInDB(events);
    }

    @Override
    public boolean deleteEventsFromDB(Events events) {
        return eventDao.deleteEventsFromDB(events);
    }

    @Override
    public boolean updateMeetingEditableData(Events events) {
        return eventDao.updateMeetingEditableData(events);
    }
}
