package com.example.conference.service;

import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.Report_speakers;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *    Defines methods to process {@link Moderator_preposition} data
 */
public interface IModeratorPrepositionService {

    /**
     * Calculates total moderator_preposition number available in DB
     * @return count of moderator_preposition in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateModeratorPrepositionNumber() throws DBException;

    /**
     * Finds all moderator_preposition in DB
     * @return List of all moderator_preposition
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Moderator_preposition> findAllModeratorPrepositionInDB() throws DBException;

    /**
     * Finds moderator_preposition in DB from
     * @param first first row number
     * @param offset offset
     * @return List moderator_preposition
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Moderator_preposition> findModeratorPreposition(Integer first, Integer offset) throws DBException;

    /**
     * Adds new moderator_preposition to DB
     * @param moderator_preposition - moderator_preposition to add in DB
     * @return true if operation success and false if fails
     */
    boolean addModeratorPrepositionToDB(Moderator_preposition moderator_preposition);

    /**
     * Updats moderator_preposition in DB
     * @param moderator_preposition - moderator_preposition to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateModeratorPrepositionInDB(Moderator_preposition moderator_preposition);

    /**
     * Deletes moderator_preposition from DB
     * @param moderator_preposition - moderator_preposition to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteModeratorPrepositionFromDB(Moderator_preposition moderator_preposition);

    /**
     *     Deletes moderator preposition
     * @param moderator_preposition entity which has to be deleted
     * @return true if entity was successfully deleted, false otherwise
     */
    boolean deleteProposal(Moderator_preposition moderator_preposition);
}
