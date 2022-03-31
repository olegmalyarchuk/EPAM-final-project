package com.example.conference.dao;

import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.exceptions.Messages;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.example.conference.exceptions.Messages.ERR_CANNOT_FIND_BY_KEY;

/**
 *
 *Common actions with db
 *
 */
public class GenericAbstractDao<T> {
    private Mapper<T, PreparedStatement> mapperToDB;
    private Mapper<ResultSet, T> mapperFromDB;

    private static final Logger log = Logger.getLogger(GenericAbstractDao.class);

    protected GenericAbstractDao() {
    }
    /**Converts data into sql query**/
    protected void setMapperToDB(Mapper<T, PreparedStatement> mapperToDB) {
        this.mapperToDB = mapperToDB;
    }
    /**Converts sql query result into data**/
    protected void setMapperFromDB(Mapper<ResultSet, T> mapperFromDB) {
        this.mapperFromDB = mapperFromDB;
    }

    /**Find all query in db**/
    protected List<T> findAll(Connection connection, Class t, String SQL_getAll) throws DBException {
        List<T> items = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = getItemInstance(t);
                mapperFromDB.map(resultSet, item);
                items.add(item);
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(Messages.ERR_CANNOT_FIND_BY_KEY, sqle);
        }
        return items;
    }
    /**Find all with limit query in db**/
    protected List<T> findAllFromTo(Connection connection, Class t, Integer first, Integer offset, String SQL_getAll_base)
            throws DBException {
        List<T> items = new LinkedList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_getAll_base + " limit " + first + ", " + offset + ";");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                T item = getItemInstance(t);
                mapperFromDB.map(resultSet, item);
                items.add(item);
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(Messages.ERR_CANNOT_FIND_BY_KEY, sqle);
        }
        return items;
    }

    /**Find by query in db**/
    protected <V> T findBy(Connection connection, Class t, String SQL_selectByParameter, V value)
            throws DBException {
        T item = getItemInstance(t);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_selectByParameter);
            addParameterToPreparedStatement(preparedStatement, 1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                mapperFromDB.map(resultSet, item);
            else
                return null;
        } catch (SQLException sqle) {
            log.error(sqle);
            return null;
        }
        return item;
    }

    /**Find as list query in db**/
    protected <V> List<T> findAsListBy(Connection connection, Class t, String SQL_selectByParameter, V value)
            throws DBException {
        List<T> items = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_selectByParameter);
            addParameterToPreparedStatement(preparedStatement, 1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = getItemInstance(t);
                mapperFromDB.map(resultSet, item);
                items.add(item);
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(ERR_CANNOT_FIND_BY_KEY, sqle);
        }
        return items;
    }

    /**Add query in db**/
    protected boolean addToDB(Connection connection, T item, String SQL_addNew) {
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_addNew);
            mapperToDB.map(item, preparedStatement);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            log.error(sqle);
            return false;
        }
        return result;
    }

    /**Update query in db**/
    protected <V> boolean updateInDB(Connection connection, T item, String SQL_update, Integer paramNum, V value) {
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_update);
            mapperToDB.map(item, preparedStatement);
            addParameterToPreparedStatement(preparedStatement, paramNum, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return result;
    }

    /**Delete query in db**/
    protected <V> boolean deleteFromDB(Connection connection, String SQL_delete, V value) {
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_delete);
            addParameterToPreparedStatement(preparedStatement, 1, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return result;
    }

    /** Method for table row count calculation. Used for pagination */
    public Integer calculateRowCounts(Connection connection, String tableName, String param) throws DBException {
        Integer result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT " + param + " as ROWCOUNT FROM " + tableName +" order by " + param + " desc limit 1;");
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("ROWCOUNT");
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DBException(ERR_CANNOT_FIND_BY_KEY, sqle);
        }
        return result;
    }

    /** Private method witch returns a concrete instance on entity */
    private T getItemInstance(Class t) {
        T item = null;
        try {
            item = (T) t.newInstance();
        } catch (InstantiationException | IllegalAccessException ie) {
            log.error(ie);
        }
        return item;
    }

    /**Add paramenet to prepared statement**/
    private <V> void addParameterToPreparedStatement(PreparedStatement preparedStatement, Integer paramNum, V value)
            throws SQLException {
        if (value instanceof String)
            preparedStatement.setString(paramNum, (String) value);
        if (value instanceof Integer) {
            preparedStatement.setInt(paramNum, (Integer) value);
        }
        if (value instanceof LocalDateTime)
            preparedStatement.setObject(paramNum, value);
        if (value instanceof Boolean)
            preparedStatement.setBoolean(paramNum, (Boolean)value);
    }

}


