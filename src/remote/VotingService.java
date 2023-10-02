package remote;

import client.ClientInterface;
import client.Vote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class VotingService extends UnicastRemoteObject implements Service {

    private List<Vote> clientVotes;
    private List<Candidate> candidates;
    private List<User> users;

    public VotingService() throws RemoteException {
        super();
        this.clientVotes = new ArrayList<>();
        candidates = new ArrayList<>();
        String csvFilePath = "src/data/candidates.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int rank = 1;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String firstName = parts[0];
                    String lastName = parts[1];

                    Candidate candidate = new Candidate(rank, firstName, lastName);
                    candidates.add(candidate);

                    rank++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(candidates);
        candidates.forEach( candidate -> candidate.toString());
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
