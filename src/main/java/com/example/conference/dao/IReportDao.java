package com.example.conference.dao;

import com.example.conference.entity.Report_preposition;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *     Defines methods to process Reports table
 *
 */
public interface IReportDao {

    /**
     * Calculates total reports number available in DB
     * @return count of reports in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateReportsNumber() throws DBException;

    /**
     * Finds all reports in DB
     * @return List of all reports
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Reports> findAllReportsInDB() throws DBException;

    /**
     * Finds reports in DB from
     * @param first first row number
     * @param offset offset
     * @return List reports
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Reports> findReport(Integer first, Integer offset) throws DBException;

    /**
     * Adds new reports to DB
     * @param reports - reports to add in DB
     * @return true if operation success and false if fails
     */
    boolean addReportToDB(Reports reports);

    /**
     * Updats reports in DB
     * @param reports - reports to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateReportInDB(Reports reports);

    /**
     * Deletes reports from DB
     * @param reports - reports to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteReportFromDB(Reports reports);

}
