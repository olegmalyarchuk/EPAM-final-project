package dao;

import com.example.conference.dao.implementation.EventUsersDaoImpl;
import com.example.conference.dao.implementation.ModeratorPrepositionDaoImpl;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class EventUsersDaoTest {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/conference?user=bestuser&password=bestuser";
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void EventUsersDaoTest() throws DBException {
        EventUsersDaoImpl eventUsersDao = new EventUsersDaoImpl(connection);
        Event_users ev = new Event_users();
        ev.setId(200);
        ev.setUser_id(1);
        ev.setEvent_id(1);
        ev.setPresent(false);
        Integer cntRow = eventUsersDao.calculateEventUsersNumber()+1;
        boolean result = eventUsersDao.addEventUsersToDB(ev);
        Assert.assertTrue(result);

        Integer cnt = eventUsersDao.calculateEventUsersNumber();
        Assert.assertEquals(cntRow, cnt);

        List<Event_users> all = eventUsersDao.findAllEventUsersInDB();
        Assert.assertTrue(all.size()>0);

        Assert.assertTrue(eventUsersDao.updateEventUsersInDB(ev));

        Event_users evv = eventUsersDao.findByUserIdAndEventId(1, 1);
        Assert.assertTrue(evv!=null);

        List<Event_users> presenselist = eventUsersDao.findUsersWithPresenceByEventId(1);
        Assert.assertTrue(presenselist.size()>0);

        result = eventUsersDao.updateUserPresenceByUserIdAndMeetingId(ev);
        Assert.assertTrue(result);

        ev.setId(ev.getId()+1);
        result = eventUsersDao.deleteEventUsersFromDB(ev);
        Assert.assertTrue(result);

    }
}
