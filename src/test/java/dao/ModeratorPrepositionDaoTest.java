package dao;

import com.example.conference.dao.implementation.ModeratorPrepositionDaoImpl;
import com.example.conference.dao.implementation.ReportPrepositionDaoImpl;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.Report_preposition;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ModeratorPrepositionDaoTest {
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
    public void ModeratorPrepositionDaoTest() throws DBException {
        ModeratorPrepositionDaoImpl moderatorPrepositionDao = new ModeratorPrepositionDaoImpl(connection);
        Moderator_preposition mod = new Moderator_preposition();
        Integer cntRow = moderatorPrepositionDao.calculateModeratorPrepositionNumber()+1;
        mod.setId(cntRow);
        mod.setReport_id(14);
        mod.setSpeaker_id(1);
        boolean result = moderatorPrepositionDao.addModeratorPrepositionToDB(mod);
        Assert.assertTrue(result);

        Integer cnt = moderatorPrepositionDao.calculateModeratorPrepositionNumber();
        Assert.assertEquals(cntRow, cnt);

        List<Moderator_preposition> all = moderatorPrepositionDao.findAllModeratorPrepositionInDB();
        Assert.assertTrue(all.size()>0);

        Assert.assertTrue(moderatorPrepositionDao.updateModeratorPrepositionInDB(mod));

        result = moderatorPrepositionDao.deleteModeratorPrepositionFromDB(14);
        Assert.assertTrue(result);


    }
}
