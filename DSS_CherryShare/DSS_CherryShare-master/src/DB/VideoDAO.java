package DB;

import APP.Video;
import APP.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {

    public List<Video> list() {
        try {

            Connection c = Connect.connect();

            List<Video> res = new ArrayList<Video>();

            if (c != null) {
                PreparedStatement psVideo = c.prepareStatement("SELECT v.idVideo, v.Author, v.Name, v.Genre, v.Owner, v.Path, v.isSerie, v.Season FROM Video AS V;");
                ResultSet rsVideo = psVideo.executeQuery();

                while (rsVideo.next()) {
                        Video m = new Video(rsVideo.getInt("idVideo"),rsVideo.getString("Author"), rsVideo.getString("Name"), rsVideo.getString("Genre"), rsVideo.getString("Owner"), rsVideo.getString("Path"));
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

    public Video get(Integer idVideo) {
        try {

            Connection c = Connect.connect();

            if (c != null) {

                PreparedStatement psVideo = c.prepareStatement("SELECT V.idVideo FROM Video AS V WHERE V.idVideo=?;");
                psVideo.setInt(1, idVideo);
                ResultSet rsVideo = psVideo.executeQuery();

                if (rsVideo.next()) {
                        Video v = new Video(rsVideo.getInt("idVideo"),rsVideo.getString("Author"), rsVideo.getString("Name"), rsVideo.getString("Genre"), rsVideo.getString("Owner"), rsVideo.getString("Path"));
                        c.close();
                        return v;
                }
                c.close();
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public boolean containsKey(Integer id) {

        try {
            Connection c = Connect.connect();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT Name FROM `Video` WHERE `idVideo`=?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }

        return false;
    }

    public void put(Video v) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            PreparedStatement ps = c.prepareStatement("INSERT INTO Video (Author,Name,Genre,Owner,Path,isSerie) VALUES (?,?,?,?,?,?);");
            ps.setString(1, v.getAuthor());
            ps.setString(2, v.getName());
            ps.setString(3, v.getGenre());
            ps.setString(4, v.getOwner());
            ps.setString(5, v.getPath());
            ps.setBoolean(6, false);
            ps.executeUpdate();
            c.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }
    }

    public void remove(Integer key) {
        try {
            Connection c = Connect.connect();
            assert c != null;

            PreparedStatement stm1 = c.prepareStatement("Delete from Video where `idVideo`=?");
            stm1.setInt(1, key);
            stm1.executeUpdate();

            c.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void update(Integer id, String album, String author, String title, String genre, String owner, String path) {

        try {
            Connection c = Connect.connect();
            assert c != null;
            PreparedStatement ps = c.prepareStatement("UPDATE Music SET idMusic =?, Album=?, Author=?, Title=?, Genre=?, Owner=? ,Path=? WHERE Email=?;");
            ps.setInt(1, id);
            ps.setString(2, album);
            ps.setString(3, author);
            ps.setString(4, title);
            ps.setString(5, genre);
            ps.setString(6, owner);
            ps.executeUpdate();

            c.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }
    }
}