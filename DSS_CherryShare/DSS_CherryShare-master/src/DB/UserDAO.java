package DB;

import APP.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<User> list() {
        try {

            Connection c = Connect.connect();

            List<User> res = new ArrayList<User>();

            if (c != null) {
                PreparedStatement psUser = c.prepareStatement("SELECT U.Email, U.Username, U.Password, U.isAdmin FROM User AS U;");
                ResultSet rsUser = psUser.executeQuery();

                while (rsUser.next()) {
                    User us = new User(rsUser.getString("Email"),rsUser.getString("Password"),rsUser.getString("Username"),rsUser.getBoolean("Password"));

                    res.add(us);
                }

                c.close();

                return res;
            }

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }

        return null;
    }

    public User get(String Email){
        try{

            Connection c = Connect.connect();

            if(c != null){
                                 
                PreparedStatement psUser = c.prepareStatement("SELECT U.Password, U.Username, U.isAdmin FROM User AS U WHERE U.Email=?;");
                psUser.setString(1, Email);
                ResultSet rsUser = psUser.executeQuery();

                if(rsUser.next()){
                    User u = new User(Email,rsUser.getString("Password"),rsUser.getString("Username"),rsUser.getBoolean("isAdmin"));

                    /* Get amigos da tabela Friends em que user2=Email */
                    PreparedStatement psFriends1 = c.prepareStatement("SELECT user1 FROM Friend WHERE user2=?;");
                    psFriends1.setString(1,Email);
                    ResultSet rsFriends1 = psFriends1.executeQuery();
                    while (rsFriends1.next()) {
                        u.addFriend(rsFriends1.getString("User1"));
                    }

                    /* Get amigos da tabela Friends em que user1=Email */
                    PreparedStatement psFriends2 = c.prepareStatement("SELECT user1 FROM Friend WHERE user1=?;");
                    psFriends2.setString(1,Email);
                    ResultSet rsFriends2 = psFriends2.executeQuery();
                    while (rsFriends2.next()) {
                        u.addFriend(rsFriends2.getString("User2"));
                    }
                    c.close();
                    return u;
                }

                c.close();
            }
        }
        catch(Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }   

        return null;
    }

    public boolean containsKey(String email) {

        try {
            Connection c = Connect.connect();
            if(c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT * FROM `User` WHERE `Email`=?;");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }

        return false;
    }

    /* put preparado apenas para novos users, sem amigos!*/
    public void put(User u) {
        try {
            Connection c = Connect.connect();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("INSERT INTO User (Email, Username, Password, isAdmin) VALUES (?,?,?,?);");
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setBoolean(4, u.getAdmin());
            ps.executeUpdate();

            c.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }
    }

    public void remove(String key) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            User u = (User)get(key);

            //delete friends
            //delete music
            //delete videos

            PreparedStatement stm = c.prepareStatement("DELETE FROM User WHERE `Email`=?");
            stm.setString(1, key);
            stm.executeUpdate();

            c.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void update(String oldEmail, User u) {

        try {
            Connection c = Connect.connect();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("UPDATE User SET email =?, Password=?, Username=?, isAdmin=? WHERE Email=?;");
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getUsername());
            ps.setBoolean(4, u.getAdmin());
            ps.setString(5, oldEmail);
            ps.executeUpdate();
            
            c.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }
    }

    public void requestFriend(String u1, String u2) {
        try {
            Connection c = Connect.connect();
            assert c != null;
            /* Insere na tabela Friend*/
            PreparedStatement psAdd = c.prepareStatement("INSERT INTO FriendRequest (user1,user2) VALUES (?,?);");
            psAdd.setString(1, u1);
            psAdd.setString(2, u2);
            psAdd.executeQuery();

            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void acceptFriend(String u1, String u2) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            /* Insere na tabela Friend*/
            PreparedStatement psAdd = c.prepareStatement("INSERT INTO Friend (user1,user2) VALUES (?,?);");
            psAdd.setString(1, u1);
            psAdd.setString(2, u2);
            psAdd.executeQuery();

            /*Remove da tabela FriendRequest*/
            PreparedStatement psRem = c.prepareStatement("DELETE FROM FriendRequest WHERE ((user1=? || user1=?)&&(user2=? || user2=?));");
            psRem.setString(1, u1);
            psRem.setString(2, u2);
            psRem.setString(3, u1);
            psRem.setString(4, u2);
            psRem.executeQuery();


            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void removeFriend(String u1, String u2) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            PreparedStatement ps = c.prepareStatement("DELETE FROM Friend WHERE (user1=? || user1=?) && (user2=? || user2=?));");
            ps.setString(1, u1);
            ps.setString(2, u2);
            ps.setString(3, u1);
            ps.setString(4, u2);
            ps.executeQuery();

            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
