package com.example.conference.dao;

import com.example.conference.exceptions.DBException;
import com.example.conference.exceptions.Messages;
import org.apache.log4j.Logger;

public abstract class DaoFactory {

     private static final Logger log = Logger.getLogger(DaoFactory.class);

    /** DAO Factory methods */
    /**
     * Creates User DAO
     * @return User DAO
     */
    public abstract IUserDao getUserDao();

    /**
     * Creates User roles DAO
     * @return User roles DAO
     */
    public abstract IUserRolesDao getUserRolesDao();

    /**
     * Creates Event DAO
     * @return Event DAO
     */
    public abstract IEventDao getEventDao();

    /**
     * Creates Repost DAO
     * @return Repost DAO
     */
    public abstract IReportDao getReportDao();


    /**
     * Creates Report speakers DAO
     * @return Report skeaker DAO
     */
    public abstract IReportSpeakerDao getReportSpeakersDao();

    /**
     * Creates Event users DAO
     * @return Event users DAO
     */
    public abstract IEventUsersDao getEventUsersDao();

    /**
     * Creates Report preposition DAO
     * @return Report preposition DAO
     */
    public abstract IReportPrepositionDao getReportPrepositionDao();

    /**
     * Creates Speaker preposition DAO
     * @return Speaker preposition DAO
     */
    public abstract ISpeakerPrepositionDao getSpeakerPrepositionDao();

    /**
     * Creates Moderator preposition DAO
     * @return Moderator preposition DAO
     */
    public abstract IModeratorPrepositionDao getModeratorPrepositionDao();

    /**
     * Closes connection to Data Source
     * @throws DBException if unable to close connection
     */
    abstract void closeConnection() throws DBException;

    /**
     * Opens connection to Data Source
     * @throws DBException if unable to open connection
     */
    public abstract void open() throws DBException;

    /**
     * Closes connection to Data Source
     */
    public abstract void close();

    /**
     * Opens DB data transaction
     * @throws DBException if unable to open data transaction
     */
    public abstract void beginTransaction() throws DBException;

    /**
     * Commits transaction results and closes transaction
     * @throws DBException if unable to commit data transaction
     */
    public abstract void commitTransaction() throws DBException;

    /**
     * Rollbacks transaction results and closes transaction
     * @throws DBException if unable to rollback transaction
     */
    public abstract void rollbackTransaction() throws DBException;

    public static DaoFactory getDaoFactory(DataBaseSelector dataBase) throws DBException{
        switch (dataBase) {
            case MY_SQL:
                return new MySQLDaoFactory();
            case MS_SQL:
                 log.error("Database " + dataBase + " not supported yet");
                throw new DBException(Messages.ERR_DATA_BASE_NOT_SUPPORTED);
            case ORACLE:
                 log.error("Database " + dataBase + " not supported yet");
                throw new DBException(Messages.ERR_DATA_BASE_NOT_SUPPORTED);
            case POSTGRESS:
                 log.error("Database " + dataBase + " not supported yet");
                throw new DBException(Messages.ERR_DATA_BASE_NOT_SUPPORTED);
            default:
                 log.error("Database type not set");
                throw new DBException("Database type not set");

        }
    }
}