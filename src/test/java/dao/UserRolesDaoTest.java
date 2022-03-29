package dao;

import com.example.conference.dao.implementation.UserDaoImpl;
import com.example.conference.dao.implementation.UserRolesDaoImpl;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserRolesDaoTest {
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
    public void UserRolesTest() throws DBException {
        UserRolesDaoImpl userRolesDao = new UserRolesDaoImpl(connection);
        User_roles ur = new User_roles();
        ur.setRole_id(4);
        ur.setRole_description("test");
        int cntRows = userRolesDao.calculateUserRolesNumber();
        boolean result = userRolesDao.addUserRolesToDB(ur);
        Assert.assertEquals(result, true);
        int cnt = userRolesDao.calculateUserRolesNumber();
        Assert.assertEquals(cntRows+1, cnt);
        List<User_roles> all = userRolesDao.findAllUserRolesInDB();
        Assert.assertTrue(all.size()>0);
        User_roles findBy = userRolesDao.findByDescription("test");
        Assert.assertTrue(findBy!=null);
        result = userRolesDao.deleteUserRolesFromDB(ur);
        Assert.assertTrue(true);
    }
}
