package com.example.conference.dao;

import com.example.conference.dao.implementation.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.exceptions.Messages;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDaoFactory extends DaoFactory {
    public static  DataSource dataSource;
    public static final Logger log = Logger.getLogger(MySQLDaoFactory.class);
    private Connection connection;

     public MySQLDaoFactory() throws DBException {
         try {
             Context initContext = new InitialContext();
             Context envContext = (Context) initContext.lookup("java:/comp/env");
             // conferences - the name of data source
             dataSource = (DataSource) envContext.lookup("jdbc/conferences");
              log.trace("Data source ==> " + dataSource);
         } catch (NamingException ex) {
             log.error(ex);
         }
    }

    private static Connection getConnection() throws DBException {
        try {
           return dataSource.getConnection();
        } catch (SQLException sqle) {
           sqle.printStackTrace();
             log.error(sqle);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        }
    }

    /** Transaction methods */
    public void beginTransaction() throws DBException {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(Messages.ERR_BEGIN_TRANSACTION, sqle);
        }
    }

    public void commitTransaction() throws DBException {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(Messages.ERR_CANNOT_COMMIT, sqle);
        }
    }

    public void rollbackTransaction() throws DBException {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(Messages.ERR_CANNOT_ROLL_BACK, sqle);
        }
    }

    /** Connection open and closing methods */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            log.error(sqle);
        }
    }

    @Override
    public void open() throws DBException {
        connection = getConnection();
    }

    @Deprecated
    public static void closeConnection(Connection connection) throws DBException {
        try {
            connection.close();
        } catch (SQLException sqle) {
            log.error(sqle);
        } catch (NullPointerException npe) {
            log.error(npe);
        }
    }

    @Override
    void closeConnection() throws DBException {
        try {
            connection.close();
        } catch (SQLException sqle) {
             log.error(sqle);
        } catch (NullPointerException npe) {
             log.error(npe);
        }
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
