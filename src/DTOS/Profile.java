package DTOS;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author grahamm
 */
public class Profile 
{
    private int uId;
    private String firstName;
    private String lastName;
    private String screenName;
    private String password;
    private ArrayList<String> messages;
    
    private boolean loggedIn = false;

    public Profile(int uId, String firstName, String lastName, String screenName, String password, ArrayList<String> messages) {
        this.uId = uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.password = password;
        this.messages = messages;
    }

    public Profile(int uId, String firstName, String lastName, String screenName, String password) {
        this.uId = uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.password = password;
    }
    
     public Profile(String firstName, String lastName, String screenName, String password) {
        this.uId = uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.password = password;
    }

    public Profile() {
        this.screenName = "empty";
    }

   

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.firstName);
        hash = 19 * hash + Objects.hashCode(this.lastName);
        hash = 19 * hash + Objects.hashCode(this.screenName);
        hash = 19 * hash + Objects.hashCode(this.password);
        hash = 19 * hash + Objects.hashCode(this.messages);
        hash = 19 * hash + (this.loggedIn ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.screenName, other.screenName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.messages, other.messages)) {
            return false;
        }
        if (this.loggedIn != other.loggedIn) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profile{" + "firstName=" + firstName + ", lastName=" + lastName + ", screenName=" + screenName + ", password=" + password + ", messages=" + messages + ", loggedIn=" + loggedIn + '}';
    }
    

  
    

}
