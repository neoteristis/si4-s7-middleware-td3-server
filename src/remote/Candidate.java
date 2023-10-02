package remote;

public class Candidate  {
    private int rank;
    private String firstName;
    private String lastName;
    private int score;

    public Candidate(int rank, String firstName, String lastName) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
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
        return "Candidate " + this.rank + ": '" + this.firstName + " " + lastName ;
    }
}

