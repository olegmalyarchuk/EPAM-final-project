package com.example.conference.service;

import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *    Defines functions to interact with user data
 *
 */
public interface IUserService {

    /**
     * Calculates total users number available in DB
     * @return count of users in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateUsersNumber();


    /**
     * Calculates row number available in DB
     * @return count of rows in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateRowsBy(String param, String value) throws DBException;

    /**
     * Finds all users in DB
     * @return List of all users
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<User> findAllUsersInDB();

    /**
     * Finds users in DB from
     * @param first first row number
     * @param offset offset
     * @return List users
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<User> findUsers(Integer first, Integer offset);

    /**
     * Finds all users by user role
     * @param user_roles - User`s role
     * @return List of
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<User> findUserByRole(User_roles user_roles);

    /**
     * Finds user by user id number
     * @param id - User`s id number
     * @return User
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    User findUserById(Integer id);

    /**
     * Finds User by email
     * @param email - User email
     * @return User by email
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    User findUserByEmail(String email);

    /**
     * Finds Users and speakers by event_id
     * @param event_id - Event ID
     * @return  Users and speakers by event_id
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<User> findByEventId(Integer event_id) throws DBException;


    /**
     * Adds new user to DB
     * @param user - user to add in DB
     * @return true if operation success and false if fails
     */
     boolean addUserToDB(User user);

    /**
     * Updats user in DB
     * @param user - User to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateUserInDB(User user);

    /**
     * Deletes user from DB
     * @param user - User to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteUserFromDB(User user);

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *     Finds all speakers which are available to propose for specific report
     * @param reportId id of report
     * @return list of speakers
     */
    List<User> findAvailableSpeakersForPrepositionByReport(int reportId);

    /**
     *     Updates user's image_path
     * @param user user with updated image_path
     * @return true if image_path was successfully updates, false otherwise
     */
    boolean updateUserImagePath(User user);
}
