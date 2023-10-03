package remote;

import client.Vote;
import exceptions.HasAlreadyVotedException;
import helper.Authentication;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String studentId;
    private String oneTimePassword;
    private List<Vote> votes;
    private boolean hasAlreadyVoted;

    public User(String studentId) {
        this.studentId = studentId;
        this.oneTimePassword = Authentication.generateOTP();
        this.votes = new ArrayList<>();
        this.hasAlreadyVoted = false;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Boolean cast(Vote vote) throws RemoteException {
        return null;
    }

    public String getOneTimePassword() throws HasAlreadyVotedException {
        if (this.hasAlreadyVoted) {
            throw new HasAlreadyVotedException();
        }
        this.hasAlreadyVoted = true;
        return this.oneTimePassword;
    }

    public void resetHasAlreadyVoted() {
        this.hasAlreadyVoted = false;
    }

    public boolean verifyOneTimePassword(String password) {
        return this.oneTimePassword.equals(password);
    }

    @Override
    public String toString() {
        return this.studentId;
    }

    public String getStudentID() {
        return this.studentId;
    }

    public void updateOneTimePassword() {
        this.oneTimePassword = Authentication.generateOTP();
    }
}
