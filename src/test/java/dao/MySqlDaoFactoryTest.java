package dao;

import com.example.conference.dao.*;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

public class MySqlDaoFactoryTest {
    @Test
    public void getUserDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IUserDao res = mf.getUserDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getUserRolesDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IUserRolesDao res = mf.getUserRolesDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getEventDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IEventDao res = mf.getEventDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getReportDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IReportDao res = mf.getReportDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getReportSpeakersDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IReportSpeakerDao res = mf.getReportSpeakersDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getEventUsersDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IEventUsersDao res = mf.getEventUsersDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getReportPrepositionDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IReportPrepositionDao res = mf.getReportPrepositionDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getSpeakerPrepositionDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        ISpeakerPrepositionDao res = mf.getSpeakerPrepositionDao();
        Assert.assertTrue(res!=null);
    }
    @Test
    public void getModeratorPrepositionDao() throws DBException {
        MySQLDaoFactory mf = new MySQLDaoFactory();
        IModeratorPrepositionDao res = mf.getModeratorPrepositionDao();
        Assert.assertTrue(res!=null);
    }
}
