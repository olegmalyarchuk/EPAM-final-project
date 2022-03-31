package dao;

import com.example.conference.dao.MySQLDaoFactory;
import com.example.conference.dao.implementation.UserDaoImpl;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
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
    public void UserTest() throws DBException {
       UserDaoImpl userDao = new UserDaoImpl(connection);
        User u = new User();
        u.setId(userDao.calculateUsersNumber()+1);
        u.setRole_id(3);
        u.setUser_name("Test");
        u.setUser_surname("Test");
        u.setUser_password("test");
        u.setUser_phone("+555555555");
        u.setUser_email("test@gmail.com");
        u.setUser_photo_url("test.jpg");
        u.setUser_address("test");

        int cntRows = userDao.calculateUsersNumber();
        boolean result = userDao.addUserToDB(u);
        Assert.assertEquals(true, result);
        u.setUser_phone("+666666666");
         result = userDao.updateUserInDB(u);
        Assert.assertEquals(result, true);
        User findByEmail = userDao.findUserByEmail(u.getUser_email());
        Assert.assertEquals(findByEmail.getUser_email(), u.getUser_email());
        User findById = userDao.findUserById(u.getId());
        Assert.assertEquals(findById.getId(), u.getId());
        Assert.assertEquals(cntRows, userDao.calculateUsersNumber()-1);
        List<User> list = userDao.findAllUsersInDB();
        Assert.assertEquals(true, list.contains(u));
        Integer res1 = userDao.calculateRowsBy("user_id", u.getId()+"");
        Assert.assertTrue(res1>0);

        User unexisted = userDao.findUserById(888);
        Assert.assertTrue(unexisted==null);
        unexisted = userDao.findUserByEmail("mail");
        Assert.assertTrue(unexisted==null);

        result = userDao.deleteUserFromDB(u);
        Assert.assertEquals(true, result);
        result = userDao.deleteUserFromDB(u);
        Assert.assertEquals(false, result);
    }
}
