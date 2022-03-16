package com.example.conference.dao;

import com.example.conference.dao.implementation.*;
import com.example.conference.exceptions.DBException;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

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
        return new UserRolesImpl(connection);
    }

    @Override
    public IEventDao getEventDao() {
        return new EventImpl(connection);
    }

    @Override
    public IReportDao getReportDao() {
        return new ReportsImpl(connection);
    }

    @Override
    public IReportSpeakerDao getReportSpeakersDao() {
        return new ReportSpeakerImpl(connection);
    }

    @Override
    public IEventUsersDao getEventUsersDao() {
        return new EventUsersImpl(connection);
    }

    @Override
    public IReportPrepositionDao getReportPrepositionDao() {
        return new ReportPrepositionImpl(connection);
    }

    @Override
    public ISpeakerPrepositionDao getSpeakerPrepositionDao() {
        return new SpeakerPrepositionImpl(connection);
    }

    @Override
    public IModeratorPrepositionDao getModeratorPrepositionDao() {
        return new ModeratorPrepositionImpl(connection);
    }

}
