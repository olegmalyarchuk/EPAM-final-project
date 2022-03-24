package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IModeratorPrepositionDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeratorPrepositionDaoImpl extends GenericAbstractDao<Moderator_preposition> implements IModeratorPrepositionDao {
    private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from moderator_preposition order by id;";
    public static final String SQL_SELECT_ALL = "select * from moderator_preposition order by id;";
    public static final String SQL_ADD_NEW = "INSERT INTO moderator_preposition VALUES(?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE moderator_preposition set id=?, speaker_id=?, report_id=? where id=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM moderator_preposition where report_id=?;";
    public static final String SQL_DELETE = "DELETE FROM moderator_preposition where speaker_id=? and report_id=?;";

    private Mapper<Moderator_preposition, PreparedStatement> mapperToDB = (Moderator_preposition moderator_preposition, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, moderator_preposition.getId());
        preparedStatement.setInt(2, moderator_preposition.getSpeaker_id());
        preparedStatement.setInt(3, moderator_preposition.getReport_id());
    };

    private Mapper<ResultSet, Moderator_preposition> mapperFromDB = (ResultSet resultSet, Moderator_preposition moderator_preposition) -> {
        moderator_preposition.setId(resultSet.getInt("id"));
        moderator_preposition.setSpeaker_id(resultSet.getInt("speaker_id"));
        moderator_preposition.setReport_id(resultSet.getInt("report_id"));
    };

    public ModeratorPrepositionDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }

    @Override
    public Integer calculateModeratorPrepositionNumber() throws DBException {
        return calculateRowCounts(connection, "moderator_preposition");
    }

    @Override
    public List<Moderator_preposition> findAllModeratorPrepositionInDB() throws DBException {
        return findAll(connection, Moderator_preposition.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Moderator_preposition> findModeratorPreposition(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, Moderator_preposition.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public boolean addModeratorPrepositionToDB(Moderator_preposition moderator_preposition) {
        return addToDB(connection, moderator_preposition, SQL_ADD_NEW);
    }

    @Override
    public boolean updateModeratorPrepositionInDB(Moderator_preposition moderator_preposition) {
        return updateInDB(connection, moderator_preposition, SQL_UPDATE_BY_ID, 4, moderator_preposition.getId());
    }

    @Override
    public boolean deleteModeratorPrepositionFromDB(Integer report_id) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, report_id);
    }

    @Override
    public boolean deleteProposal(Moderator_preposition moderator_preposition) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, moderator_preposition.getSpeaker_id());
            preparedStatement.setInt(2, moderator_preposition.getReport_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
