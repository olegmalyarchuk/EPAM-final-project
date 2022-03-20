package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IUserService;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IUserDao userDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            userDao = daoFactory.getUserDao();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer calculateUsersNumber() {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            userDao = daoFactory.getUserDao();
            result = userDao.calculateUsersNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer calculateRowsBy(String param, String value) {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            userDao = daoFactory.getUserDao();
            result = userDao.calculateRowsBy(param, value);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAllUsersInDB(){
        List<User> users = new ArrayList<>();
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            users = new ArrayList<>();
            users = userDao.findAllUsersInDB();
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> findUsers(Integer first, Integer offset) {
        List<User> users = new ArrayList<>();
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            users = new ArrayList<>();
            users = userDao.findUsers(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> findUserByRole(User_roles user_roles) {
        List<User> users = new ArrayList<>();
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            users = new ArrayList<>();
            users = userDao.findUserByRole(user_roles);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserById(Integer id) {
        User user = null;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            user = userDao.findUserById(id);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            user = userDao.findUserByEmail(email);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public synchronized boolean addUserToDB(User user) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userDao = daoFactory.getUserDao();
            result = userDao.addUserToDB(user);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateUserInDB(User user) {

        boolean result;
        try {
            daoFactory.beginTransaction();
            userDao = daoFactory.getUserDao();
            result = userDao.updateUserInDB(user);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteUserFromDB(User user) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userDao = daoFactory.getUserDao();
            result = userDao.deleteUserFromDB(user);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public List<User> findAvailableSpeakersForPrepositionByReport(int reportId) {
        List<User> users = new ArrayList<>();
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            users = new ArrayList<>();
            users = userDao.findAvailableSpeakersForPrepositionByReport(reportId);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public synchronized boolean updateUserImagePath(User user) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userDao = daoFactory.getUserDao();
            result = userDao.updateUserImagePath(user);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }
}
