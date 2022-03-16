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
            //log
        }
    }

    @Override
    public Integer calculateUsersNumber() throws DBException {
        Integer result = userDao.calculateUsersNumber();
        return result;
    }

    @Override
    public List<User> findAllUsersInDB() throws DBException {
        List<User> users = new ArrayList<>();
        users = userDao.findAllUsersInDB();
        return users;
    }

    @Override
    public List<User> findUsers(Integer first, Integer offset) throws DBException {
        return userDao.findUsers(first, offset);
    }

    @Override
    public List<User> findUserByRole(User_roles user_roles) throws DBException {
        return userDao.findUserByRole(user_roles);
    }

    @Override
    public User findUserById(Integer id) throws DBException {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) throws DBException {
        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean addUserToDB(User user) {
        return userDao.addUserToDB(user);
    }

    @Override
    public boolean updateUserInDB(User user) {
        return userDao.updateUserInDB(user);
    }

    @Override
    public boolean deleteUserFromDB(User user) {
        return userDao.deleteUserFromDB(user);
    }

    @Override
    public List<User> findAvailableSpeakersForPrepositionByReport(int reportId) {
        return userDao.findAvailableSpeakersForPrepositionByReport(reportId);
    }

    @Override
    public boolean updateUserImagePath(User user) {
        return userDao.updateUserImagePath(user);
    }
}
