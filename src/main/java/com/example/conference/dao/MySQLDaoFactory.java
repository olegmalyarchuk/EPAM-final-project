package com.example.conference.dao;

import com.example.conference.dao.implementation.*;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;

public class MySQLDaoFactory extends DaoFactory{

   // public static final Logger log = Logger.getLogger(MySQLDaoFactory.class);
    private Connection connection;

     public MySQLDaoFactory() throws DBException {
    }



    @Override
    public IUserDao getUserDao() {
        return new UserDaoImpl(connection);
    }

    @Override
    public IUserRolesDao getUserRolesDao() {
        return new UserRolesDaoImpl(connection);
    }

    @Override
    public IEventDao getEventDao() {
        return new EventDaoImpl(connection);
    }

    @Override
    public IReportDao getReportDao() {
        return new ReportsDaoImpl(connection);
    }

    @Override
    public IReportSpeakerDao getReportSpeakersDao() {
        return new ReportSpeakerDaoImpl(connection);
    }

    @Override
    public IEventUsersDao getEventUsersDao() {
        return new EventUsersDaoImpl(connection);
    }

    @Override
    public IReportPrepositionDao getReportPrepositionDao() {
        return new ReportPrepositionDaoImpl(connection);
    }

    @Override
    public ISpeakerPrepositionDao getSpeakerPrepositionDao() {
        return new SpeakerPrepositionDaoImpl(connection);
    }

    @Override
    public IModeratorPrepositionDao getModeratorPrepositionDao() {
        return new ModeratorPrepositionDaoImpl(connection);
    }

}
