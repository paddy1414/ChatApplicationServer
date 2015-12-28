/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Exceptions.DaoException;
import DTOS.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Patrick
 */
public class userDao extends Dao implements UserDaoInterface {

    @Override
    public Profile login(String urname, String pswd) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profile p = new Profile();

        try {
            con = this.getConnection();

            String query = "SELECT * FROM user WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, urname);
            ps.setString(2, pswd);

            rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("uId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");

                p = new Profile(userId, username, password, firstname, lastname);
            }
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return p;

    }

    @Override
    public boolean register(Profile p) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.getConnection();

            String query = "INSERT INTO user(firstName, lastName, password, userName) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getPassword());
            ps.setString(4, p.getScreenName());

            ps.execute();
        } catch (SQLException e) {
            throw new DaoException("addUser " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("addUser" + e.getMessage());
            }
        }
        return true;
    
}

    @Override
    public Profile findUserByUserName (String uName)  throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profile p = new Profile();
   
        try {
            con = this.getConnection();

            String query = "SELECT * FROM user WHERE USERNAME = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uName);

            rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("uId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");

                p = new Profile(userId, username, password, firstname, lastname);
            }
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return p;
    }
    
}
