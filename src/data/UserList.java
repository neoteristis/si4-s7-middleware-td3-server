package data;

import client.ClientInterface;
import exceptions.HasAlreadyVotedException;
import remote.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserList extends ArrayList<User> {

    public UserList(String csvFilePath){

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.add(new User(line));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOTP(String studentID) throws HasAlreadyVotedException {
        for (User user : this) {
            if (user.getStudentID().equals(studentID)) {
                return user.getOneTimePassword();
            }
        }
        return null;
    }

    public boolean authenticate(String studentID, String otp) {
        for (User user : this) {
            if (user.getStudentID().equals(studentID)) {
                return user.verifyOneTimePassword(otp);
            }
        }
        return false;
    }
}
