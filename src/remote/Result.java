package remote;

import client.Vote;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result implements Serializable {

    private List<Candidate> candidates;
    List<Map.Entry<Integer, Integer>> sortedRankSums;

    public Result(List<Candidate> candidates, List<Vote> clientVotes) {
        this.candidates = candidates;

        Map<Integer, Integer> rankSums = clientVotes.stream()
                .collect(Collectors.groupingBy(Vote::getRank,
                        Collectors.summingInt(Vote::getValue)));

        sortedRankSums = rankSums.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        String result = "Résultats :\n";
        for (Map.Entry<Integer, Integer> entry : sortedRankSums) {
            String label = candidates.get(entry.getKey()-1).toString().substring(3);
            result += "\t" + label + ": " + entry.getValue() + "\n";
        }
        result += "\nVotre nouveau délégué(e) : "+ candidates.get(sortedRankSums.get(0).getKey()-1).toString().substring(3);
        return result;
    }
}
