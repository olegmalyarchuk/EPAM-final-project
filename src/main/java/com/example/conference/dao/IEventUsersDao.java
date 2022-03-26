package com.example.conference.dao;

import com.example.conference.entity.Event_users;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *     Defines methods to process Event_users table
 *
 */
public interface IEventUsersDao {

    /**
     * Calculates total event_users number available in DB
     * @return count of event_users in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateEventUsersNumber() throws DBException;

    /**
     * Finds all event_users in DB
     * @return List of all event_users
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Event_users> findAllEventUsersInDB() throws DBException;

    /**
     * Finds event_users in DB from
     * @param first first row number
     * @param offset offset
     * @return List event_users
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Event_users> findEventUsers(Integer first, Integer offset) throws DBException;


    /**
     * Adds new event_users to DB
     * @param event_users - event_users to add in DB
     * @return true if operation success and false if fails
     */
    boolean addEventUsersToDB(Event_users event_users);

    /**
     * Updats event_users in DB
     * @param event_users - event_users to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateEventUsersInDB(Event_users event_users);

    /**
     * Deletes event_users from DB
     * @param event_users - event_users to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteEventUsersFromDB(Event_users event_users);

    /**
     *     Retrieves {@link Event_users} entity from database by {@code user_id} and {@code event_idd}
     * @param user_id id of user
     * @param event_id id of meeting
     * @return found {@link Event_users} object and {@code null} if no object was found
     */
    Event_users findByUserIdAndEventId(int user_id, int event_id);

    /**
     *     Retrieves list of {@link Event_users} objects with users and their presence for specific event
     * @param event_id of event to retrieve list of {@link Event_users} objects for
     * @return list of {@link Event_users}
     */
    List<Event_users> findUsersWithPresenceByEventId(int event_id);

    /**
     *     Updates user's presence for event
     * @param event_users data about user, event and user's presence
     * @return true if presence was successfully updated, false otherwise
     */
    boolean updateUserPresenceByUserIdAndMeetingId(Event_users event_users);
}
