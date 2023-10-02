package remote;

import client.ClientInterface;
import client.Vote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ObjetService extends UnicastRemoteObject implements Service {
    private int number;
    private List<Vote> clientVotes;

    public ObjetService(int numPort,int number) throws RemoteException{
        super(numPort);
        this.number = number;
        this.clientVotes = new ArrayList<>();
    }

    public ObjetService(int number) throws RemoteException {
        super();
        this.number = number;
        this.clientVotes = new ArrayList<>();
    }

    @Override
    public int getVote() throws RemoteException {
        return this.number;
    }

    @Override
    public synchronized int sendVote(Vote vote, ClientInterface client) throws RemoteException {

        try {
            client.voter(vote);
        } catch (java.rmi.RemoteException ignored) {}

        System.out.println("Vote du client: " + vote);
        this.clientVotes.add(vote);

        return this.number;
    }


}
