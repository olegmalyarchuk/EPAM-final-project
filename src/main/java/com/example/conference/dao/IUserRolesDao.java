package com.example.conference.dao;

import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;
import com.example.conference.exceptions.DBException;

import java.util.List;

/**
 *
 *     Defines methods to process User_roles table
*
 */
public interface IUserRolesDao {

    /**
     * Calculates total user_roles number available in DB
     * @return count of user_roles in DB
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateUserRolesNumber() throws DBException;

    /**
     * Finds all user_roles in DB
     * @return List of all user_roles
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<User_roles> findAllUserRolesInDB() throws DBException;

    /**
     * Finds user_roles in DB from
     * @param first first row number
     * @param offset offset
     * @return List user_roles
     * @throws DBException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<User_roles> findUserRoles(Integer first, Integer offset) throws DBException;

    /**
     * Adds new user_roles to DB
     * @param user_roles - user_roles to add in DB
     * @return true if operation success and false if fails
     */
    boolean addUserRolesToDB(User_roles user_roles);

    /**
     * Updats user_roles in DB
     * @param user_roles - User_roles to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateUserRolesInDB(User_roles user_roles);

    /**
     * Deletes user_roles from DB
     * @param user_roles - User_roles to delete from DB
     * @return true if operation success and false if fails
     */
    boolean deleteUserRolesFromDB(User_roles user_roles);


    /**
     * <p>
     *     Retrieves {@link User_roles} by its role_description
     * </p>
     * @param role_description description to find role by
     * @return found role or null if no role was found
     */
    User_roles findByDescription(String role_description);
}
