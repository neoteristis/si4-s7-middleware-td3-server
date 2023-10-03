package remote;

import helper.Authentication;

public class Candidate  {
    private final String firstName;
    private final String lastName;
    private final String pitch;
    private final int rank;
    private int score;

    public Candidate(int rank, String firstName, String lastName, String pitch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pitch = pitch;
        this.rank = rank;
        this.score = 0;
    }

    public int getRank() {
        return this.rank;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }

    @Override
    public String toString() {
        return this.rank + ": " + this.firstName + " " + this.lastName + " \"" + this.pitch + "\"";
    }
}
