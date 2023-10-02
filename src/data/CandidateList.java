package data;

import remote.Candidate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CandidateList extends ArrayList<Candidate> {

    public CandidateList(String csvFilePath){

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int rank = 1;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 3) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String[] pitch = new String[parts.length - 2];
                    System.arraycopy(parts, 2, pitch, 0, parts.length - 2);
                    Candidate candidate = new Candidate(rank, firstName, lastName, String.join(" ", pitch));
                    this.add(candidate);
                    rank++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort(Comparator<? super Candidate> c) {
        super.sort(c);
    }

    public void sortByValue() {
        this.sort(Comparator.comparingInt(Candidate::getScore));
    }

    public void print() {
        System.out.println("Candidates : ");
        this.forEach( candidate -> System.out.println(candidate));
    }
}
