package com.example.conference.dao;

import com.example.conference.entity.Event_users;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *     Defines methods to process Events table
 *
 */
public interface IEventDao {

    /**
     * Calculates total events number available in DB
     * @return count of events in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateEventNumber() throws DBException;


    /**
     * Calculates total registered users in DB
     * @return count of registered in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateRegistered(Integer event_id) throws DBException;

    /**
     * Calculates total present users in DB
     * @return count of present in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculatePresent(Integer event_id) throws DBException;


    /**
     * Finds all reports for event by event_id in DB
     * @return List of all reports by event_id
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Reports> findAllReports(Integer event_id) throws DBException;

    /**
     * Finds all events in DB
     * @return List of all events
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Events> findAllEventsInDB() throws DBException;

    /**
     * Finds events in DB from
     * @param first first row number
     * @param offset offset
     * @return List events
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Events> findEvents(Integer first, Integer offset) throws DBException;


    /**
     * Finds Events by event_place_ua
     * @param event_place_ua - Event event_place_ua
     * @return Event by event_place_ua
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Events> findEventsByPlaceUa(String event_place_ua) throws DBException;

    /**
     * Finds Events by event_place_en
     * @param event_place_en - Event event_place_en
     * @return Event by event_place_en
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Events> findEventsByPlaceEn(String event_place_en) throws DBException;

    /**
     * Finds Events by event_id
     * @param event_id - Event id
     * @return Event by event_id
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Events findEventsById(Integer event_id) throws DBException;

    /**
     * Adds new events to DB
     * @param events - events to add in DB
     * @return true if operation success and false if fails
     */
    boolean addEventsToDB(Events events);

    /**
     * Updats events in DB
     * @param events - events to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateEventsInDB(Events events);

    /**
     * Deletes events by id from DB
     * @param id - event id to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteEventsByIdFromDB(Integer id);



    /**
     * <p>
     * </p>
     *     Updates event data that moderator can edit
     * @param events entity which contains edited data
     * @return true if data was successfully updated, false otherwise
     */
    boolean updateMeetingEditableData(Events events);

}
