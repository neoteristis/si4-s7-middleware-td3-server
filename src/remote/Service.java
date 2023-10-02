package remote;

import client.ClientInterface;
import client.Vote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Service extends Remote {
    public int getVote() throws RemoteException;
    public List<String> getCandidates() throws RemoteException;
    public void sendVotes(List<Vote> votes, ClientInterface client) throws RemoteException;
}
