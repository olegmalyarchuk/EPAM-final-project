package dao;

import com.example.conference.dao.implementation.ReportSpeakerDaoImpl;
import com.example.conference.dao.implementation.ReportsDaoImpl;
import com.example.conference.dao.implementation.SpeakerPrepositionDaoImpl;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReportSpeakerDaoTest {
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
    public void ReportSpeakerDaoTest() throws DBException {
        ReportSpeakerDaoImpl reportSpeakerDao = new ReportSpeakerDaoImpl(connection);
        ReportsDaoImpl reportsDao = new ReportsDaoImpl(connection);
        Reports newRep = new Reports();
        newRep.setReport_id(reportsDao.calculateReportsNumber()+1);
        newRep.setEvent_id(1);
        newRep.setReport_name_ua("test");
        newRep.setReport_name_en("test");
        reportsDao.addReportToDB(newRep);
        Report_speakers rp = new Report_speakers();
        Integer id = reportSpeakerDao.calculateReportSpeakerNumber()+1;
        rp.setId(id);
        int report_id = newRep.getReport_id();;
        rp.setReport_id(report_id);
        rp.setSpeaker_id(1);
        boolean result = reportSpeakerDao.addReportSpeakersToDB(rp);
        Assert.assertTrue(result);
        Integer cnt = reportSpeakerDao.calculateReportSpeakerNumber();
        Assert.assertEquals(id, cnt);

        List<Report_speakers> all = reportSpeakerDao.findAllReportSpeakersInDB();
        Assert.assertTrue(all.size()>0);

        result = reportSpeakerDao.updateReportSpeakersInDB(rp);
        Assert.assertTrue(result);



        Report_speakers findByRepId = reportSpeakerDao.findReportById(report_id);
        Assert.assertTrue(findByRepId!=null);



        result = reportSpeakerDao.deleteReportSpeakersFromDB(report_id);
        Assert.assertTrue(result);

        reportsDao.deleteReportFromDB(newRep);
    }
}
