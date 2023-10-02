package remote;

import client.Vote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String studentNumber;
    private String password;
    private List<Vote> votes;

    public User(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.votes = new ArrayList<>();
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public Boolean cast(Vote vote) throws RemoteException {
        return null;
    }

}
