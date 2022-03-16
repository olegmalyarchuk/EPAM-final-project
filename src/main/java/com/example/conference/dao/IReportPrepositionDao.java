package com.example.conference.dao;

import com.example.conference.entity.Report_preposition;
import com.example.conference.entity.Reports;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *     Defines methods to process Report_preposition table
 *
 */
public interface IReportPrepositionDao  {

    /**
     * Calculates total report_preposition number available in DB
     * @return count of report_preposition in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateReportPrepositionNumber() throws DBException;

    /**
     * Finds all report_preposition in DB
     * @return List of all report_preposition
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Report_preposition> findAllReportPrepositionInDB() throws DBException;

    /**
     * Finds report_preposition in DB from
     * @param first first row number
     * @param offset offset
     * @return List report_preposition
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Report_preposition> findReportPreposition(Integer first, Integer offset) throws DBException;

    /**
     * Adds new report_preposition to DB
     * @param report_preposition - report_preposition to add in DB
     * @return true if operation success and false if fails
     */
    boolean addReportPrepositionToDB(Report_preposition report_preposition);

    /**
     * Updats report_preposition in DB
     * @param report_preposition - report_preposition to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateReportPrepositionInDB(Report_preposition report_preposition);

    /**
     * Deletes report_preposition from DB
     * @param report_preposition - report_preposition to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteReportPrepositionFromDB(Report_preposition report_preposition);

    /**
     *     Creates {@link com.example.conference.entity.Reports} based on {@link Report_preposition}
     * @param id of {@link Report_preposition}
     * @return true if {@link Reports} was successfully created, false otherwise
     */
    boolean createReportWithPropositionDeletion(int id);
}
