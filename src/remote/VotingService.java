package remote;

import client.ClientInterface;
import client.Vote;
import data.CandidateList;
import data.UserList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class VotingService extends UnicastRemoteObject implements Service {

    private List<Vote> clientVotes;
    private CandidateList candidates;
    private UserList users;

    public VotingService() throws RemoteException {
        super();
        this.clientVotes = new ArrayList<>();
        users = new UserList("src/data/users.csv");
        candidates = new CandidateList("src/data/candidates.csv");
    }

    @Override
    public int getVote() throws RemoteException {
        return 1;
    }

    @Override
    public synchronized int sendVote(Vote vote, ClientInterface client) throws RemoteException {

        try {
            client.voter(vote);
        } catch (java.rmi.RemoteException ignored) {}

        System.out.println("Vote du client: " + vote);
        this.clientVotes.add(vote);
        new Result(clientVotes).getResult();

        return 1;
    }


}
