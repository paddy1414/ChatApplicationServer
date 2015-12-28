/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Exceptions.DaoException;
import DTOS.Profile;

/**
 *
 * @author Patrick
 */
public interface UserDaoInterface {

    public Profile login(String username, String password) throws DaoException;

    public boolean register(Profile p) throws DaoException;

    public Profile findUserByUserName(String uName) throws DaoException;

}
