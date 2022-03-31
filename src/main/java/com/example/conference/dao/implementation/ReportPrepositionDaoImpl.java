package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IReportPrepositionDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Report_preposition;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportPrepositionDaoImpl extends GenericAbstractDao<Report_preposition> implements IReportPrepositionDao {
    private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from report_preposition order by id;";
    public static final String SQL_SELECT_ALL = "select * from report_preposition order by id;";
    public static final String SQL_ADD_NEW = "INSERT INTO report_preposition VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE report_preposition set id=?, event_id=?, speaker_id=? where id=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM report_preposition where id=?;";
    public static final String SQL_INSERT_TOPIC = "INSERT INTO reports(report_name_ua, report_name_en, event_id) SELECT tp.report_name_ua, tp.report_name_en, tp.event_id FROM report_preposition tp WHERE id=?";

    private Mapper<Report_preposition, PreparedStatement> mapperToDB = (Report_preposition report_preposition, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, report_preposition.getId());
        preparedStatement.setInt(2, report_preposition.getEvent_id());
        preparedStatement.setInt(3, report_preposition.getSpeaker_id());
        preparedStatement.setString(4, report_preposition.getReport_name_ua());
        preparedStatement.setString(5, report_preposition.getReport_name_en());
    };

    private Mapper<ResultSet, Report_preposition> mapperFromDB = (ResultSet resultSet, Report_preposition report_preposition) -> {
        report_preposition.setId(resultSet.getInt("id"));
        report_preposition.setEvent_id(resultSet.getInt("event_id"));
        report_preposition.setSpeaker_id(resultSet.getInt("speaker_id"));
        report_preposition.setReport_name_ua(resultSet.getString("report_name_ua"));
        report_preposition.setReport_name_en(resultSet.getString("report_name_en"));
    };

    public ReportPrepositionDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }


    @Override
    public Integer calculateReportPrepositionNumber() throws DBException {
        return calculateRowCounts(connection, "report_preposition", "id");
    }

    @Override
    public List<Report_preposition> findAllReportPrepositionInDB() throws DBException {
        return findAll(connection, Report_preposition.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Report_preposition> findReportPreposition(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, Report_preposition.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public boolean addReportPrepositionToDB(Report_preposition report_preposition) {
        return addToDB(connection, report_preposition, SQL_ADD_NEW);
    }

    @Override
    public boolean updateReportPrepositionInDB(Report_preposition report_preposition) {
        return updateInDB(connection, report_preposition, SQL_UPDATE_BY_ID, 4, report_preposition.getId());
    }

    @Override
    public boolean deleteReportPrepositionFromDB(Report_preposition report_preposition) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, report_preposition.getId());
    }

    @Override
    public boolean createReportWithPropositionDeletion(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT_TOPIC);
            insertStatement.setInt(1, id);
            insertStatement.executeUpdate();
            PreparedStatement deleteStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
         //   e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
             //   e.printStackTrace();
            }
        }
        return false;
    }
}
