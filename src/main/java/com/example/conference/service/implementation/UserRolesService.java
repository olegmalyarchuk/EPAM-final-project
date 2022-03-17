package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IUserDao;
import com.example.conference.dao.IUserRolesDao;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IUserRolesService;

import java.util.ArrayList;
import java.util.List;

public class UserRolesService implements IUserRolesService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IUserRolesDao userRolesDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            userRolesDao = daoFactory.getUserRolesDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateUserRolesNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            userRolesDao = daoFactory.getUserRolesDao();
            result = userRolesDao.calculateUserRolesNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User_roles> findAllUserRolesInDB() throws DBException {
        List<User_roles> user_roles = new ArrayList<>();
        try {
            daoFactory.open();
            userRolesDao = daoFactory.getUserRolesDao();
            user_roles = new ArrayList<>();
            user_roles = userRolesDao.findAllUserRolesInDB();
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user_roles;
    }

    @Override
    public List<User_roles> findUserRoles(Integer first, Integer offset) throws DBException {
        List<User_roles> user_roles = new ArrayList<>();
        try {
            daoFactory.open();
            userRolesDao = daoFactory.getUserRolesDao();
            user_roles = new ArrayList<>();
            user_roles = userRolesDao.findUserRoles(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user_roles;
    }

    @Override
    public synchronized boolean addUserRolesToDB(User_roles user_roles) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userRolesDao = daoFactory.getUserRolesDao();
            result = userRolesDao.addUserRolesToDB(user_roles);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateUserRolesInDB(User_roles user_roles) {

        boolean result;
        try {
            daoFactory.beginTransaction();
            userRolesDao = daoFactory.getUserRolesDao();
            result = userRolesDao.updateUserRolesInDB(user_roles);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteUserRolesFromDB(User_roles user_roles) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userRolesDao = daoFactory.getUserRolesDao();
            result = userRolesDao.deleteUserRolesFromDB(user_roles);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public User_roles findByDescription(String role_description) {
        User_roles user_roles = null;
        try {
            daoFactory.open();
            userRolesDao = daoFactory.getUserRolesDao();
            user_roles = userRolesDao.findByDescription(role_description);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user_roles;
    }
}
