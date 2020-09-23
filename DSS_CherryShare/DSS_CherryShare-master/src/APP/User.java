package APP;

import java.util.ArrayList;

public class User {

    private String email;
    private String password;
    private String username;
    private boolean isAdmin;
    private ArrayList<String> friends;

    public User(String mail, String pass, String usern, boolean isAdmin){
        this.email = mail;
        this.password = pass;
        this.username = usern;
        this.isAdmin = isAdmin;
        this.friends = new ArrayList<String>();
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean getAdmin(){
        return this.isAdmin;
    }

    public ArrayList<String> getFriends() { return  this.friends; }

    public void setEmail(String email) { this.email = email;}
    public void setPassword(String pass) { this.password = pass;}
    public void setUsername(String username) { this.username = username;}
    public void setAdmin(boolean adm){ this.isAdmin=adm;}
    public void setFriends(ArrayList<String> friends) { this.friends = friends;}

    public void addFriend(String email) { this.friends.add(email);}

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

}

