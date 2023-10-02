package remote;

import client.Vote;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result implements Serializable {

    private List<Vote> clientVotes;
    public Result(List<Vote> clientVotes) {
        this.clientVotes = clientVotes;
    }

    public void getResult(){

        Map<Integer, Integer> rankSums = clientVotes.stream()
                .collect(Collectors.groupingBy(Vote::getRank,
                        Collectors.summingInt(Vote::getValue)));

        System.out.println("Result :");
        rankSums.forEach((rank, sum) -> {
            System.out.println("\tCandidate " + rank + ": " + sum);
        });
    }
}
