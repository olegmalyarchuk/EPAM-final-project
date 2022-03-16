package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IUserDao;
import com.example.conference.dao.IUserRolesDao;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IUserRolesService;

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
        return userRolesDao.calculateUserRolesNumber();
    }

    @Override
    public List<User_roles> findAllUserRolesInDB() throws DBException {
        return userRolesDao.findAllUserRolesInDB();
    }

    @Override
    public List<User_roles> findUserRoles(Integer first, Integer offset) throws DBException {
        return userRolesDao.findUserRoles(first, offset);
    }

    @Override
    public boolean addUserRolesToDB(User_roles user_roles) {
        return userRolesDao.addUserRolesToDB(user_roles);
    }

    @Override
    public boolean updateUserRolesInDB(User_roles user_roles) {
        return userRolesDao.updateUserRolesInDB(user_roles);
    }

    @Override
    public boolean deleteUserRolesFromDB(User_roles user_roles) {
        return userRolesDao.deleteUserRolesFromDB(user_roles);
    }

    @Override
    public User_roles findByDescription(String role_description) {
        return userRolesDao.findByDescription(role_description);
    }
}
