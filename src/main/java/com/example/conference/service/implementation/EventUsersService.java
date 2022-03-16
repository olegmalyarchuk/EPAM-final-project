package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IEventUsersDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Event_users;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IEventUsersService;

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
        return eventUsersDao.calculateEventUsersNumber();
    }

    @Override
    public List<Event_users> findAllEventUsersInDB() throws DBException {
        return eventUsersDao.findAllEventUsersInDB();
    }

    @Override
    public List<Event_users> findEventUsers(Integer first, Integer offset) throws DBException {
        return eventUsersDao.findEventUsers(first, offset);
    }

    @Override
    public boolean addEventUsersToDB(Event_users event_users) {
        return eventUsersDao.addEventUsersToDB(event_users);
    }

    @Override
    public boolean updateEventUsersInDB(Event_users event_users) {
        return eventUsersDao.updateEventUsersInDB(event_users);
    }

    @Override
    public boolean deleteEventUsersFromDB(Event_users event_users) {
        return eventUsersDao.deleteEventUsersFromDB(event_users);
    }

    @Override
    public Event_users findByUserIdAndEventId(int user_id, int event_id) {
        return eventUsersDao.findByUserIdAndEventId(user_id, event_id);
    }

    @Override
    public List<Event_users> findUsersWithPresenceByEventId(int event_id) {
        return eventUsersDao.findUsersWithPresenceByEventId(event_id);
    }

    @Override
    public boolean updateUserPresenceByUserIdAndMeetingId(Event_users event_users) {
        return eventUsersDao.updateUserPresenceByUserIdAndMeetingId(event_users);
    }
}
