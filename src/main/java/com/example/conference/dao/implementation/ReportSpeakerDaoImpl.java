package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IReportSpeakerDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportSpeakerDaoImpl extends GenericAbstractDao<Report_speakers> implements IReportSpeakerDao {
    private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from reports_speakers order by id;";
    public static final String SQL_SELECT_ALL = "select * from reports_speakers order by id;";
    public static final String SQL_ADD_NEW = "INSERT INTO reports_speakers VALUES(?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE reports_speakers set id=?, report_id=?, speaker_id=?, where id=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM reports_speakers where id=?;";
    String speakerPrepositionDeleteQuery = "DELETE FROM speaker_preposition WHERE speaker_id=? AND report_id=?";
    String moderatorPrepositionDeleteQuery = "DELETE FROM moderator_preposition WHERE speaker_id=? AND report_id=?";
    String insertStatement = "INSERT INTO reports_speakers(report_id, speaker_id) VALUES(?, ?);";

    private Mapper<Report_speakers, PreparedStatement> mapperToDB = (Report_speakers reports_speakers, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, reports_speakers.getId());
        preparedStatement.setInt(2, reports_speakers.getReport_id());
        preparedStatement.setInt(3, reports_speakers.getSpeaker_id());
    };

    private Mapper<ResultSet, Report_speakers> mapperFromDB = (ResultSet resultSet, Report_speakers reports_speakers) -> {
        reports_speakers.setId(resultSet.getInt("id"));
        reports_speakers.setReport_id(resultSet.getInt("report_id"));
        reports_speakers.setSpeaker_id(resultSet.getInt("speaker_id"));
    };

    public ReportSpeakerDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }


    @Override
    public Integer calculateReportSpeakerNumber() throws DBException {
        return calculateRowCounts(connection, "report_speakers");
    }

    @Override
    public List<Report_speakers> findAllReportSpeakersInDB() throws DBException {
        return findAll(connection, Report_speakers.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Report_speakers> findReportSpeaker(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, Report_speakers.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public boolean addReportSpeakersToDB(Report_speakers report_speakers) {
        return addToDB(connection, report_speakers, SQL_ADD_NEW);
    }

    @Override
    public boolean updateReportSpeakersInDB(Report_speakers report_speakers) {
        return updateInDB(connection, report_speakers, SQL_UPDATE_BY_ID, 1, report_speakers.getId());
    }

    @Override
    public boolean deleteReportSpeakersFromDB(Report_speakers report_speakers) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, report_speakers.getId());
    }

    @Override
    public boolean saveWithProposalsDeletion(Report_speakers reportTopicSpeaker) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertSt = connection.prepareStatement(insertStatement);
            insertSt.executeUpdate();

            PreparedStatement speakerPreposition = connection.prepareStatement(speakerPrepositionDeleteQuery);
            speakerPreposition.setInt(1, reportTopicSpeaker.getSpeaker_id());
            speakerPreposition.setInt(2, reportTopicSpeaker.getReport_id());
            speakerPreposition.executeUpdate();

            PreparedStatement moderatorPreposition = connection.prepareStatement(moderatorPrepositionDeleteQuery);
            moderatorPreposition.setInt(1, reportTopicSpeaker.getSpeaker_id());
            moderatorPreposition.setInt(2, reportTopicSpeaker.getReport_id());
            moderatorPreposition.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            //log
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
