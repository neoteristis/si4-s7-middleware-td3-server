package remote;

public class Candidate  {
    private int rank;
    private String firstName;
    private String lastName;
    private String pitch;
    private int score;

    public Candidate(int rank, String firstName, String lastName, String pitch) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pitch = pitch;
        this.score = 0;
    }

    public int getRank() {
        return this.rank;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int value){
        this.score = score;
    }

    @Override
    public String toString() {
        return this.rank + ": " + this.firstName + " " + this.lastName + " \"" + this.pitch + "\"";
    }
}

