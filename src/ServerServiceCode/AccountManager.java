package ServerServiceCode;

import DTOS.Profile;
import ClientCode.ChatClientCallback;
import DTOS.ChatMessage;
import Dao.UserDaoInterface;
import Dao.userDao;
import Exceptions.DaoException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grahamm
 */
public class AccountManager extends UnicastRemoteObject implements UserCommandsInInterface {

    private final List<ChatClientCallback> activeClients = new ArrayList<>();

    private final List<Profile> accounts = new ArrayList<>();

    private ArrayList<ChatMessage> pmMessageList;

        private ArrayList<ChatClientCallback> clients = new ArrayList();

    public AccountManager() throws RemoteException {
        super();
    }
    private UserDaoInterface dao = new userDao();

    @Override
    public boolean register(String firstName, String lastName, String screenName, String password) throws RemoteException {
        synchronized (accounts) {
            Profile b = new Profile(firstName, lastName, screenName, password);

            try {
                if (!dao.findUserByUserName(b.getScreenName()).equals("empty")) {
                    accounts.add(b);
                    dao.register(b);

                    return true;
                } else {
                    return false;
                }
            } catch (DaoException ex) {
                Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public boolean login(String screenName, String password) throws RemoteException {
        Profile b = new Profile();
        try {
            b = dao.login(screenName, password);
        } catch (DaoException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!b.getScreenName().equals("empty")) {
            b.setLoggedIn(true);
            System.out.println(screenName + " has logged in");
            return true;
        } else {
            return false;
        }
    }

//     @Override
//    public String sendPmMessage(String userToSend, String message) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    // CALLBACK METHODS
    @Override
    public boolean registerForCallbacks(ChatClientCallback newClient) throws RemoteException {
        synchronized (activeClients) {
            if (!activeClients.contains(newClient)) {
                activeClients.add(newClient);

                // Notify everyone
                doCallbacks("A new client registered for callbacks");
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean unregisterForCallbacks(ChatClientCallback exitingClient) throws RemoteException {
        synchronized (activeClients) {
            if (activeClients.contains(exitingClient)) {
                activeClients.remove(exitingClient);

                // Notify everyone
                doCallbacks("A client unregistered for callbacks");
                return true;
            }
            return false;
        }
    }

    public void doCallbacks(String message) throws RemoteException {
        synchronized (activeClients) {
            for (ChatClientCallback client : activeClients) {
                client.displayNotification(message);
            }
        }
    }

    public void callbackClient(String firstName, String secondName, String message) throws RemoteException {
        synchronized (activeClients) {
            for (ChatClientCallback client : activeClients) {
                if (client.getName().equals(firstName + " " + secondName)) {
                    client.displayNotification(message);
                    break;
                }
            }
        }
    }

    @Override
    public boolean sendPmMail(ChatMessage mail) throws RemoteException {
        if (!pmMessageList.contains(mail)) {
            pmMessageList.add(mail);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<ChatMessage> getPmSentEmails(String sender) throws RemoteException {
        ArrayList<ChatMessage> mails = new ArrayList();
        for (ChatMessage e : pmMessageList) {
            if (e.getFrom().equalsIgnoreCase(sender)) {
                mails.add(e);
            }
        }
        return mails;
    }

    @Override
    public ArrayList<ChatMessage> getPmReceivedEmails(String receiver) throws RemoteException {
        ArrayList<ChatMessage> mails = new ArrayList();
        for (ChatMessage e : pmMessageList) {
            if (e.getTo().equalsIgnoreCase(receiver)) {
                mails.add(e);
            }
        }
        return mails;
    }
    
        
    
   // Method to call back to EVERY client registered
    // and push updated voting results
    public synchronized void pushUpdatedVoteInfo(String results) throws RemoteException
    {
        for(ChatClientCallback client : clients)
        {
            client.displayNotification(results);
        }
    }

}
