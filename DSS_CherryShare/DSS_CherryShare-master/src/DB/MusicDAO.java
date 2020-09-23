package DB;

import APP.Music;
import APP.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDAO {

    public List<Music> list(String email) {
        try {

            Connection c = Connect.connect();

            List<Music> res = new ArrayList<Music>();

            if (c != null) {
                PreparedStatement psMusic = c.prepareStatement("SELECT M.idMusic, M.Album, M.Author, M.Title, M.Genre, M.Owner, M.Path FROM Music AS M;");
                ResultSet rsMusic = psMusic.executeQuery();
                while (rsMusic.next()) {
                    Music m = new Music(rsMusic.getInt("idMusic"), rsMusic.getString("Album"), rsMusic.getString("Author"), rsMusic.getString("Title"), rsMusic.getString("Genre"), rsMusic.getString("Owner"), rsMusic.getString("Path"));

                    PreparedStatement psMusicUD = c.prepareStatement("SELECT M.title,M.genre FROM MusicUserData AS M WHERE M.idMusic=? AND M.UserEmail=?;");
                    psMusicUD.setInt(1, m.getId());
                    psMusicUD.setString(2,email);
                    ResultSet rsMusicUD = psMusicUD.executeQuery();

                    if (rsMusicUD.next()) {
                        m.setGenre(rsMusicUD.getString("Genre"));
                        m.setTitle(rsMusicUD.getString("Title"));
                    }

                    res.add(m);
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

    public Music get(Integer idMusic,String email) {
        try {
            Connection c = Connect.connect();

            if (c != null) {
                PreparedStatement psMusic = c.prepareStatement("SELECT * FROM Music AS M WHERE M.idMusic=?;");
                psMusic.setInt(1, idMusic);
                ResultSet rsMusic = psMusic.executeQuery();

                if (rsMusic.next()) {
                    Music m = new Music(idMusic, rsMusic.getString("Album"), rsMusic.getString("Author"), rsMusic.getString("Title"), rsMusic.getString("Genre"), rsMusic.getString("Owner"), rsMusic.getString("Path"));

                    PreparedStatement psMusicUD = c.prepareStatement("SELECT M.title,M.genre FROM MusicUserData AS M WHERE M.idMusic=? AND M.UserEmail=?;");
                    psMusicUD.setInt(1, m.getId());
                    psMusicUD.setString(2,email);
                    ResultSet rsMusicUD = psMusicUD.executeQuery();

                    if (rsMusicUD.next()) {
                        m.setGenre(rsMusicUD.getString("Genre"));
                        m.setTitle(rsMusicUD.getString("Title"));
                    }


                    c.close();
                    return m;
                }
                c.close();
            }
        } catch (Exception e) {
            System.err.println("Got an exception at musicDAO.get!");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean containsKey(Integer id) {
        try {
            Connection c = Connect.connect();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT * FROM `Music` WHERE `idMusic`=?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Got an exception at musicDAO.containsKey!");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void put(Music m) {
        try {
            Connection c = Connect.connect();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("INSERT INTO Music (Album, Author, Title, Genre, Owner, Path) VALUES (?,?,?,?,?,?);");
            ps.setString(1, m.getAlbum());
            ps.setString(2, m.getAuthor());
            ps.setString(3, m.getTitle());
            ps.setString(4, m.getGenre());
            ps.setString(5, m.getOwner());
            ps.setString(6, m.getPath());
            ps.executeUpdate();

            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception at musicDAO.put!");
            System.err.println(e.getMessage()+'1');
        }
    }

    public void remove(Integer key) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            PreparedStatement stm1 = c.prepareStatement("Delete from Music where `idMusic`=?");
            stm1.setInt(1, key);
            stm1.executeUpdate();

            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception at musicDAO.remove!");
            System.err.println(e.getMessage());
        }
    }

    public void update(Music m) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            PreparedStatement ps = c.prepareStatement("UPDATE Music SET Title=?, Genre=?, Album=?, Author=? WHERE idMusic=?;");
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getGenre());
            ps.setString(3,m.getAlbum());
            ps.setString(4, m.getAuthor());
            ps.setInt(5, m.getId());
            ps.executeUpdate();

            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception at musicDAO.update!");
            System.err.println(e.getMessage());
        }
    }

    public void updateNotOwner(Music m, String email) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            /*Remover alterações anteriores!*/
            PreparedStatement rs = c.prepareStatement("DELETE FROM MusicUserData WHERE idMusic=? AND UserEmail=?;");
            rs.setInt(1,m.getId());
            rs.setString(2,email);;
            rs.executeUpdate();

            /*Inserir novas alterações*/
            PreparedStatement ps = c.prepareStatement("INSERT INTO MusicUserData (idMusic,userEmail,Title,Genre,Album,Author) VALUES (?,?,?,?,?,?);");
            ps.setInt(1, m.getId());
            ps.setString(2, email);
            ps.setString(3, m.getTitle());
            ps.setString(4, m.getGenre());
            ps.setString(5,m.getAlbum());
            ps.setString(6,m.getAuthor());
            ps.executeUpdate();

            c.close();
        } catch (Exception e) {
            System.err.println("Got an exception at musicDAO.updateNotOwner!");
            System.err.println(e.getMessage());
        }
    }

    public List<Music> listByAlbum(String album, String email) {
        try {
            Connection c = Connect.connect();
            List<Music> res = new ArrayList<Music>();

            if (c != null) {
                PreparedStatement psMusic = c.prepareStatement("SELECT M.idMusic, M.Album, M.Author, M.Title, M.Genre, M.Owner, M.Path FROM Music AS M WHERE M.Album=?;");
                psMusic.setString(1,album);
                ResultSet rsMusic = psMusic.executeQuery();
                while (rsMusic.next()) {
                    Music m = new Music(rsMusic.getInt("idMusic"), rsMusic.getString("Album"), rsMusic.getString("Author"), rsMusic.getString("Title"), rsMusic.getString("Genre"), rsMusic.getString("Owner"), rsMusic.getString("Path"));

                    PreparedStatement psMusicUD = c.prepareStatement("SELECT M.title,M.genre,M.Album,M.Author FROM MusicUserData AS M WHERE M.idMusic=? AND M.UserEmail=?;");
                    psMusicUD.setInt(1, m.getId());
                    psMusicUD.setString(2,email);
                    ResultSet rsMusicUD = psMusicUD.executeQuery();

                    if (rsMusicUD.next()) {
                        m.setGenre(rsMusicUD.getString("Genre"));
                        m.setTitle(rsMusicUD.getString("Title"));
                        m.setAlbum(rsMusicUD.getString("Album"));
                        m.setAuthor(rsMusicUD.getString("Author"));
                    }
                    res.add(m);
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


    public List<Music> listByAuthor(String author, String email) {
        try {
            Connection c = Connect.connect();
            List<Music> res = new ArrayList<Music>();

            if (c != null) {
                PreparedStatement psMusic = c.prepareStatement("SELECT M.idMusic, M.Album, M.Author, M.Title, M.Genre, M.Owner, M.Path FROM Music AS M WHERE M.Author=?;");
                psMusic.setString(1,author);
                ResultSet rsMusic = psMusic.executeQuery();
                while (rsMusic.next()) {
                    Music m = new Music(rsMusic.getInt("idMusic"), rsMusic.getString("Album"), rsMusic.getString("Author"), rsMusic.getString("Title"), rsMusic.getString("Genre"), rsMusic.getString("Owner"), rsMusic.getString("Path"));

                    PreparedStatement psMusicUD = c.prepareStatement("SELECT M.title,M.genre,M.Album,M.Author FROM MusicUserData AS M WHERE M.idMusic=? AND M.UserEmail=?;");
                    psMusicUD.setInt(1, m.getId());
                    psMusicUD.setString(2,email);
                    ResultSet rsMusicUD = psMusicUD.executeQuery();

                    if (rsMusicUD.next()) {
                        m.setGenre(rsMusicUD.getString("Genre"));
                        m.setTitle(rsMusicUD.getString("Title"));
                        m.setAlbum(rsMusicUD.getString("Album"));
                        m.setAuthor(rsMusicUD.getString("Author"));
                    }
                    res.add(m);
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

}

