package dao;

import com.example.conference.dao.implementation.ReportSpeakerDaoImpl;
import com.example.conference.dao.implementation.ReportsDaoImpl;
import com.example.conference.entity.Events;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReportsDaoTest {
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
    public void ReportsDaoTest() throws DBException {
        ReportsDaoImpl reportsDao = new ReportsDaoImpl(connection);
        Reports newRep = new Reports();
        Integer cntRow = reportsDao.calculateReportsNumber()+1;
        newRep.setReport_id(reportsDao.calculateReportsNumber()+1);
        newRep.setEvent_id(1);
        newRep.setReport_name_ua("test");
        newRep.setReport_name_en("test");
        boolean result = reportsDao.addReportToDB(newRep);
        Assert.assertTrue(result);

        Integer cnt = reportsDao.calculateReportsNumber();
        Assert.assertEquals(cntRow, cnt);

        List<Reports> all = reportsDao.findAllReportsInDB();
        Assert.assertTrue(all.size()>0);

        Events events = new Events();
        events.setEvent_id(1);
        List<Reports> byEvent = reportsDao.findReportsByEvent(events);
        Assert.assertTrue(byEvent.size()>0);

        Reports byReportId = reportsDao.findReportsByReportId(1);
        Assert.assertTrue(byReportId!=null);

        result = reportsDao.updateReportInDB(newRep);
        Assert.assertTrue(result);

        Reports findByRepId = reportsDao.findReportsByReportId(cntRow);
        Assert.assertTrue(findByRepId!=null);



        result = reportsDao.deleteReportFromDB(newRep);
        Assert.assertTrue(result);

        reportsDao.deleteReportFromDB(newRep);
    }
}
