package com.example.conference.dao;

import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *     Defines methods to process Speaker_preposition table
 */
public interface ISpeakerPrepositionDao {


    /**
     * Calculates total speaker_preposition number available in DB
     * @return count of speaker_preposition in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateSpeakerPrepositionNumber() throws DBException;

    /**
     * Finds all speaker_preposition in DB
     * @return List of all speaker_preposition
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Speaker_preposition> findAllSpeakerPrepositionInDB() throws DBException;

    /**
     * Finds speaker_preposition in DB from
     * @param first first row number
     * @param offset offset
     * @return List speaker_preposition
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Speaker_preposition> findUSpeakerPreposition(Integer first, Integer offset) throws DBException;


    /**
     * Adds new speaker_preposition to DB
     * @param speaker_preposition - speaker_preposition to add in DB
     * @return true if operation success and false if fails
     */
    boolean addSpeakerPrepositionToDB(Speaker_preposition speaker_preposition);

    /**
     * Updats speaker_preposition in DB
     * @param speaker_preposition - Speaker_preposition to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateSpeakerPrepositionInDB(Speaker_preposition speaker_preposition);

    /**
     * Deletes speaker_preposition from DB
     * @param report_id - Speaker_preposition to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteSpeakerPrepositionFromDB(Integer report_id);

    /**
     *      Retrieves all speakers' prepositions for concrete {@link Reports}
     * @param report_id id of {@link Reports}
     * @return list of {@link Speaker_preposition}
     */
    List<Speaker_preposition> findAllByReportIdWithSpeaker(int report_id);

    /**
     *     Returns list of reports ids for specific event for which speaker propose himself
     * @param event_id id of {@link Events}
     * @param speaker_id id of speaker({@link User})
     * @return list of {@link Reports} ids for which speaker proposed himself
     */
    List<Integer> findAllSpeakerProposedReportIdsForEvent(int event_id, int speaker_id);
}
