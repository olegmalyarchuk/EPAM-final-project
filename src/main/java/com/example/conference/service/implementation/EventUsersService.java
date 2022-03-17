package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IEventUsersDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Events;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IEventUsersService;

import java.util.ArrayList;
import java.util.List;

public class EventUsersService implements IEventUsersService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IEventUsersDao eventUsersDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            eventUsersDao = daoFactory.getEventUsersDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateEventUsersNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            eventUsersDao = daoFactory.getEventUsersDao();
            result = eventUsersDao.calculateEventUsersNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Event_users findByUserIdAndEventId(int user_id, int event_id) {
        Event_users events = null;
        try {
            daoFactory.open();
            eventUsersDao = daoFactory.getEventUsersDao();
            events = eventUsersDao.findByUserIdAndEventId(user_id, event_id);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return events;
    }


    @Override
    public List<Event_users> findAllEventUsersInDB() throws DBException {
        List<Event_users> eventUsers = new ArrayList<>();
        try {
            daoFactory.open();
            eventUsersDao = daoFactory.getEventUsersDao();
            eventUsers = new ArrayList<>();
            eventUsers = eventUsersDao.findAllEventUsersInDB();
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return eventUsers;
    }

    @Override
    public List<Event_users> findEventUsers(Integer first, Integer offset) throws DBException {
        List<Event_users> eventUsers = new ArrayList<>();
        try {
            daoFactory.open();
            eventUsersDao = daoFactory.getEventUsersDao();
            eventUsers = new ArrayList<>();
            eventUsers = eventUsersDao.findEventUsers(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return eventUsers;
    }

    @Override
    public List<Event_users> findUsersWithPresenceByEventId(int event_id) {
        List<Event_users> eventUsers = new ArrayList<>();
        try {
            daoFactory.open();
            eventUsersDao = daoFactory.getEventUsersDao();
            eventUsers = new ArrayList<>();
            eventUsers = eventUsersDao.findUsersWithPresenceByEventId(event_id);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return eventUsers;
    }


    @Override
    public synchronized boolean addEventUsersToDB(Event_users event_users) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventUsersDao = daoFactory.getEventUsersDao();
            result = eventUsersDao.addEventUsersToDB(event_users);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateEventUsersInDB(Event_users event_users) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventUsersDao = daoFactory.getEventUsersDao();
            result = eventUsersDao.updateEventUsersInDB(event_users);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteEventUsersFromDB(Event_users event_users) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventUsersDao = daoFactory.getEventUsersDao();
            result = eventUsersDao.deleteEventUsersFromDB(event_users);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateUserPresenceByUserIdAndMeetingId(Event_users event_users) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventUsersDao = daoFactory.getEventUsersDao();
            result = eventUsersDao.updateUserPresenceByUserIdAndMeetingId(event_users);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }
}
