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
    private Result result;

    public VotingService() throws RemoteException {
        super();
        clientVotes = new ArrayList<>();
        users = new UserList("src/data/users.csv");
        candidates = new CandidateList("src/data/candidates.csv");
    }

    @Override
    public List<String> getCandidates() throws RemoteException {
        return candidates.stream().map(Candidate::toStringWithPitch).toList();
    }

    @Override
    public String getResults() throws RemoteException {
        return result.toString();
    }


    @Override
    public synchronized void sendVotes(List<Vote> votes, ClientInterface client) throws RemoteException {
        User user = users.getByID(client.getStudentID());
        System.out.println(user + " a voté");
        user.setVotes(votes);
        clientVotes.addAll(votes);
        if (isOver()) {
            System.out.println("Tout le monde a voté");
            result = new Result(candidates, clientVotes);
            System.out.println(result);
        }
    }

    public Boolean isOver(){
        return users.stream().filter(user -> user.getVotes().isEmpty()).count() == 0;
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
