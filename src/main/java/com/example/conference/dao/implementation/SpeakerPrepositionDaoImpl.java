package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.ISpeakerPrepositionDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpeakerPrepositionDaoImpl extends GenericAbstractDao<Speaker_preposition> implements ISpeakerPrepositionDao {
   private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from speaker_preposition order by id;";
    public static final String SQL_SELECT_ALL = "select * from speaker_preposition order by id;";
    public static final String SQL_ADD_NEW = "INSERT INTO speaker_preposition VALUES(?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE speaker_preposition set id=?, speaker_id=?, report_id=? where id=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM speaker_preposition where id=?;";
    public static final String SQL_DELETE_BY_REPORT_ID = "DELETE FROM speaker_preposition where report_id=?;";
    public static final String SQL_FIND_BY_REPORT_ID = "SELECT * FROM speaker_preposition LEFT JOIN users u ON speaker_preposition.speaker_id=u.user_id WHERE speaker_preposition.report_id=?";
    public static final String SQL_FIND_REPORT_ID = "SELECT sp.report_id FROM speaker_preposition sp WHERE speaker_id=? AND EXISTS (SELECT NULL FROM reports rt WHERE rt.event_id=? AND rt.report_id=sp.report_id);";
    public static final String SQL_FIND_SPEAKER_BY_SPEAKER_PREPOSITION = "select u.* from users u join speaker_preposition sp on u.user_id=sp.speaker_id where sp.speaker_id=?;";
    public static final String SQL_FIND_ROLE = "select role_description  from user_roles r join users u on r.role_id = u.role_id where u.user_id=?;";

    private Mapper<Speaker_preposition, PreparedStatement> mapperToDB = (Speaker_preposition speaker_preposition, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, speaker_preposition.getId());
        preparedStatement.setInt(2, speaker_preposition.getSpeaker_id());
        preparedStatement.setInt(3, speaker_preposition.getReport_id());
    };

    private Mapper<ResultSet, Speaker_preposition> mapperFromDB = (ResultSet resultSet, Speaker_preposition speaker_preposition) -> {
        speaker_preposition.setId(resultSet.getInt("id"));
        speaker_preposition.setSpeaker_id(resultSet.getInt("speaker_id"));
        speaker_preposition.setReport_id(resultSet.getInt("report_id"));
    };

    public SpeakerPrepositionDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }


    @Override
    public Integer calculateSpeakerPrepositionNumber() throws DBException {
        return calculateRowCounts(connection, "speaker_preposition", "id");
    }

    @Override
    public List<Speaker_preposition> findAllSpeakerPrepositionInDB() throws DBException {
        return findAll(connection, Speaker_preposition.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Speaker_preposition> findUSpeakerPreposition(Integer first, Integer offset) throws DBException {
       return findAllFromTo(connection, Speaker_preposition.class, first, offset, SQL_SELECT_BASE);
    }


    @Override
    public boolean addSpeakerPrepositionToDB(Speaker_preposition speaker_preposition) {
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEW);
           preparedStatement.setInt(1, speaker_preposition.getId());
           preparedStatement.setInt(2, speaker_preposition.getReport_id());
           preparedStatement.setInt(3, speaker_preposition.getSpeaker_id());
           preparedStatement.executeUpdate();
           return true;

       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
        // return addToDB(connection, speaker_preposition, SQL_ADD_NEW);
    }

    @Override
    public boolean updateSpeakerPrepositionInDB(Speaker_preposition speaker_preposition) {
        return updateInDB(connection, speaker_preposition, SQL_UPDATE_BY_ID, 4, speaker_preposition.getId());
    }

    @Override
    public boolean deleteSpeakerPrepositionFromDB(Integer id) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, id);
    }

    @Override
    public boolean deleteSpeakerPrepositionByReportIdFromDB(Integer report_id) {
        return deleteFromDB(connection, SQL_DELETE_BY_REPORT_ID, report_id);
    }

    @Override
    public List<Speaker_preposition> findAllByReportIdWithSpeaker(int report_id) {
        List<Speaker_preposition> speakerPrepositions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_REPORT_ID);
            preparedStatement.setInt(1, report_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Speaker_preposition sp = new Speaker_preposition();
                sp.setId(resultSet.getInt(1));
                sp.setSpeaker_id(resultSet.getInt(2));
                sp.setReport_id(resultSet.getInt(3));
                User speaker = new User();
                speaker.setId(resultSet.getInt("user_id"));
                speaker.setRole_id(resultSet.getInt("role_id"));
                speaker.setUser_name(resultSet.getString("user_name"));
                speaker.setUser_surname(resultSet.getString("user_surname"));
                speaker.setUser_password(resultSet.getString("user_password"));
                speaker.setUser_phone(resultSet.getString("user_phone"));
                speaker.setUser_email(resultSet.getString("user_email"));
                speaker.setUser_photo_url(resultSet.getString("user_photo_url"));
                speaker.setUser_address(resultSet.getString("user_address"));
                sp.setSpeaker(speaker);
                speakerPrepositions.add(sp);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            //logger
        }
        return speakerPrepositions;
    }

    @Override
    public List<Integer> findAllSpeakerProposedReportIdsForEvent(int event_id, int speaker_id) {
        List<Integer> reportIds = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_REPORT_ID);
            preparedStatement.setInt(1, speaker_id);
            preparedStatement.setInt(2, event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportIds.add(resultSet.getInt("report_id"));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            //logger
        }
        return reportIds;
    }
}
