package dao;

import com.example.conference.dao.implementation.EventDaoImpl;
import com.example.conference.dao.implementation.EventUsersDaoImpl;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class EventDaoTest {
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
    public void EventDaoTest() throws DBException {
        EventDaoImpl eventDao = new EventDaoImpl(connection);
        Events ev = new Events();
        Integer cntRow = eventDao.calculateEventNumber()+1;
        ev.setEvent_id(cntRow);
       ev.setEvent_name_ua("test");
       ev.setEvent_name_en("test");
       ev.setEvent_place_ua("test");
       ev.setEvent_place_en("test");
       ev.setEvent_description_ua("test");
       ev.setEvent_description_en("test");
       ev.setEvent_date(LocalDateTime.now());
       ev.setEvent_photo_url("test.jpg");
        boolean result = eventDao.addEventsToDB(ev);
        Assert.assertTrue(result);

        Integer cnt = eventDao.calculateEventNumber();
        Assert.assertEquals(cntRow, cnt);

        Integer regged = eventDao.calculateRegistered(8);
        Assert.assertTrue(regged>0);
        Integer pres = eventDao.calculatePresent(8);
        Assert.assertTrue(pres>0);

        List<Reports> allreps = eventDao.findAllReports(1);
        Assert.assertTrue(allreps.size()>0);

        List<Events> places1 = eventDao.findEventsByPlaceEn("test");
        Assert.assertTrue(places1.size()>0);

        List<Events> places2 = eventDao.findEventsByPlaceUa("test");
        Assert.assertTrue(places1.size()>0);

        Events event = eventDao.findEventsById(cntRow);
        Assert.assertTrue(event!=null);

        result = eventDao.updateEventsInDB(event);
        Assert.assertTrue(result);

        result = eventDao.updateMeetingEditableData(event);
        Assert.assertFalse(result);

        List<Events> all = eventDao.findAllEventsInDB();
        Assert.assertTrue(all.size()>0);

        result = eventDao.deleteEventsByIdFromDB(cntRow);
        Assert.assertTrue(result);

    }
}
