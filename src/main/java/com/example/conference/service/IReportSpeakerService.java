package com.example.conference.service;

import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *    Defines methods to process {@link Report_speakers} data
 */
public interface IReportSpeakerService {


    /**
     * Calculates total report_speakers number available in DB
     * @return count of report_speakers in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateReportSpeakerNumber() throws DBException;

    /**
     * Finds all report_speakers in DB
     * @return List of all report_speakers
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Report_speakers> findAllReportSpeakersInDB() throws DBException;

    /**
     * Finds report_speakers in DB from
     * @param first first row number
     * @param offset offset
     * @return List report_speakers
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Report_speakers> findReportSpeaker(Integer first, Integer offset) throws DBException;

    /**
     * Finds Report by id
     * @param id - Report_id
     * @return Report by id
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Report_speakers findReportById(Integer id) throws DBException;

    /**
     * Adds new report_speakers to DB
     * @param report_speakers - report_speakers to add in DB
     * @return true if operation success and false if fails
     */
    boolean addReportSpeakersToDB(Report_speakers report_speakers);

    /**
     * Updats report_speakers in DB
     * @param report_speakers - Report_speakers to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateReportSpeakersInDB(Report_speakers report_speakers);

    /**
     * Deletes report_skeakers from DB
     * @param report_speakers - Report_speakers to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteReportSpeakersFromDB(Report_speakers report_speakers);

    /**
     *     Saves {@link Report_speakers} to database and deletes all prepositions for assigned speaker
     * @param reportTopicSpeaker data which have to be saved
     * @return true if {@link Report_speakers} was successfully saved, false otherwise
     */
    boolean saveWithProposalsDeletion(Report_speakers reportTopicSpeaker);
}
