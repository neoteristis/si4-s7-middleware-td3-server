package remote;

import client.Vote;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result implements Serializable {

    private List<Candidate> candidates;
    private List<Vote> clientVotes;

    public Result(List<Candidate> candidates, List<Vote> clientVotes) {
        this.candidates = candidates;
        this.clientVotes = clientVotes;
    }

    public void getResult() {
        Map<Integer, Integer> rankSums = clientVotes.stream()
                .collect(Collectors.groupingBy(Vote::getRank,
                        Collectors.summingInt(Vote::getValue)));

        List<Map.Entry<Integer, Integer>> sortedRankSums = rankSums.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        System.out.println("Résultats :");
        sortedRankSums.forEach(entry -> {
            String label = candidates.get(entry.getKey()-1).toString().substring(3);
            System.out.println("\t" + label + ": " + entry.getValue());
        });
    }
}
