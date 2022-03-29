package dao;

import com.example.conference.dao.implementation.ReportPrepositionDaoImpl;
import com.example.conference.dao.implementation.ReportsDaoImpl;
import com.example.conference.entity.Report_preposition;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.implementation.ReportPrepositionService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReportPrepositionTest {
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
    public void ReportPrepositionDaoTest() throws DBException {
        ReportPrepositionDaoImpl reportPrepositionDao = new ReportPrepositionDaoImpl(connection);
        Report_preposition rep = new Report_preposition();
        Integer cntRow = reportPrepositionDao.calculateReportPrepositionNumber()+1;
        rep.setId(reportPrepositionDao.calculateReportPrepositionNumber()+1);
        rep.setEvent_id(3);
        rep.setSpeaker_id(3);
        rep.setReport_name_en("test");
        rep.setReport_name_ua("test");
        boolean result = reportPrepositionDao.addReportPrepositionToDB(rep);
        Assert.assertTrue(result);

        Integer cnt = reportPrepositionDao.calculateReportPrepositionNumber();
        Assert.assertEquals(cntRow, cnt);

        List<Report_preposition> all = reportPrepositionDao.findAllReportPrepositionInDB();
        Assert.assertTrue(all.size()>0);


        result = reportPrepositionDao.deleteReportPrepositionFromDB(rep);
        Assert.assertTrue(result);

        reportPrepositionDao.deleteReportPrepositionFromDB(rep);
    }
}
