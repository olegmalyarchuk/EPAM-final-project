package com.example.conference.dao.implementation;

import com.example.conference.dao.GenericAbstractDao;
import com.example.conference.dao.IReportDao;
import com.example.conference.dao.Mapper;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsDaoImpl extends GenericAbstractDao<Reports> implements IReportDao {
    private Connection connection;
    public static final String SQL_SELECT_BASE = "select * from reports order by report_id;";
    public static final String SQL_SELECT_ALL = "select * from reports order by report_id;";
    public static final String SQL_ADD_NEW = "INSERT INTO reports VALUES(?, ?, ?, ?)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE reports set report_id=?, event_id=?, report_name_ua=?, where report_name_en=?;";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM reports where report_id=?;";
    public static final String SQL_DELETE =  "DELETE FROM reports_speakers where report_id=?";

    private Mapper<Reports, PreparedStatement> mapperToDB = (Reports reports, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, reports.getReport_id());
        preparedStatement.setInt(2, reports.getEvent_id());
        preparedStatement.setString(3, reports.getReport_name_ua());
        preparedStatement.setString(4, reports.getReport_name_en());
    };

    private Mapper<ResultSet, Reports> mapperFromDB = (ResultSet resultSet, Reports reports) -> {
        reports.setReport_id(resultSet.getInt("report_id"));
        reports.setEvent_id(resultSet.getInt("event_id"));
        reports.setReport_name_ua(resultSet.getString("report_name_ua"));
        reports.setReport_name_en(resultSet.getString("report_name_en"));

    };

    public ReportsDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }


    @Override
    public Integer calculateReportsNumber() throws DBException {
        return calculateRowCounts(connection, "reports");
    }

    @Override
    public List<Reports> findAllReportsInDB() throws DBException {
        return findAll(connection, Reports.class, SQL_SELECT_ALL);
    }

    @Override
    public List<Reports> findReport(Integer first, Integer offset) throws DBException {
        return findAllFromTo(connection, Reports.class, first, offset, SQL_SELECT_BASE);
    }

    @Override
    public boolean addReportToDB(Reports reports) {
        return addToDB(connection, reports, SQL_ADD_NEW);
    }

    @Override
    public boolean updateReportInDB(Reports reports) {
        return updateInDB(connection, reports, SQL_UPDATE_BY_ID, 1, reports.getReport_id());
    }

    @Override
    public boolean deleteReportFromDB(Reports reports) {
        return deleteFromDB(connection, SQL_DELETE_BY_ID, reports.getReport_id());
    }

}
