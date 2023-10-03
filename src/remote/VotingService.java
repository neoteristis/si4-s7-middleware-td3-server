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
        return candidates.stream().map(Candidate::toStringWithPitch).toList();
    }

    @Override
    public synchronized void sendVotes(List<Vote> votes, ClientInterface client) throws RemoteException {
        this.clientVotes.addAll(votes);
        new Result(candidates, clientVotes).getResult(); // TODO : when vote ended
    }

    @Override
    public synchronized String getUserOTP(String studentID) throws RemoteException, HasAlreadyVotedException {
        try {
            return users.getOTP(studentID);
        } catch (HasAlreadyVotedException e) {
            System.out.println("User " + studentID + " has already voted.");
            throw new HasAlreadyVotedException();
        }
    }

    @Override
    public synchronized String updateUserOTP(String studentID, String oldOTP) throws RemoteException {
        return users.updateOTP(studentID, oldOTP);
    }

    @Override
    public synchronized boolean authenticate(String studentID, String otp) throws RemoteException {
        return users.authenticate(studentID, otp);
    }

    @Override
    public boolean isStudentIDValid(String studentID) throws RemoteException {
        return users.isStudentIDValid(studentID);
    }
}
