package remote;

import client.Vote;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class ObjetDistant extends UnicastRemoteObject implements Distant {

    private static Service service;

    public ObjetDistant(int numPort) throws IOException {
        super(numPort);
        this.service = new VotingService();
    }

    public Service getNewService() throws RemoteException {
        return this.service;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Distant objectDistant = new ObjetDistant(10);
            registry.rebind("objetDistant", objectDistant);
            System.out.println("Tapez \"Stop\" pour terminer l'élection");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine() == "Stop"){
                    service.getResults();
            }

        } catch (RemoteException e) {
            System.out.println("Error with the server side...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
