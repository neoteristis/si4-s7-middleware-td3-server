package remote;

import client.ClientInterface;
import client.Vote;
import data.CandidateList;
import data.UserList;
import exceptions.HasAlreadyVotedException;

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
    public List<String> getCandidates() throws RemoteException {
        return candidates.stream().map(Candidate::toString).toList();
    }

    @Override
    public synchronized void sendVotes(List<Vote> votes, ClientInterface client) throws RemoteException {
        System.out.println("Votes du client " + client + ": ");
        votes.forEach(vote -> System.out.println(vote));
        this.clientVotes.addAll(votes);
        new Result(clientVotes).getResult(); // TODO : when vote ended
    }

    @Override
    public synchronized String getUserOTP(String studentID) throws RemoteException, HasAlreadyVotedException {
        return users.getOTP(studentID);
    }

    @Override
    public synchronized boolean authenticate(String studentID, String otp) throws RemoteException {
        return users.authenticate(studentID, otp);
    }
}
