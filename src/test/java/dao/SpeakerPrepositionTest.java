package dao;

import com.example.conference.dao.implementation.SpeakerPrepositionDaoImpl;
import com.example.conference.dao.implementation.UserRolesDaoImpl;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SpeakerPrepositionTest {
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
    public void SpeakerPrepositionTest() throws DBException {
        SpeakerPrepositionDaoImpl speakerPrepositionDao = new SpeakerPrepositionDaoImpl(connection);
        Speaker_preposition sp = new Speaker_preposition();
       Integer id = speakerPrepositionDao.calculateSpeakerPrepositionNumber()+1;
       sp.setId(id);
       sp.setReport_id(1);
       sp.setSpeaker_id(1);
       boolean result = speakerPrepositionDao.addSpeakerPrepositionToDB(sp);
       Assert.assertTrue(result);

       Integer cnt = speakerPrepositionDao.calculateSpeakerPrepositionNumber();
       Assert.assertEquals(id, cnt);

       List<Speaker_preposition> all = speakerPrepositionDao.findAllSpeakerPrepositionInDB();
       Assert.assertTrue(all.size()>0);

       result = speakerPrepositionDao.updateSpeakerPrepositionInDB(sp);
       Assert.assertTrue(result);


       List<Speaker_preposition> findByRepId = speakerPrepositionDao.findAllByReportIdWithSpeaker(1);
       Assert.assertTrue(findByRepId.size()>0);



        result = speakerPrepositionDao.deleteSpeakerPrepositionFromDB(id);
        Assert.assertTrue(result);
    }
}

