package remote;

import client.Vote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String studentId;
    private String password;
    private List<Vote> votes;

    public User(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
        this.votes = new ArrayList<>();
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public Boolean cast(Vote vote) throws RemoteException {
        return null;
    }

    @Override
    public String toString() {
        return "User { " +
                "studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                " }";
    }
}
