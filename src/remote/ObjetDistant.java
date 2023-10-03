package remote;

import client.Vote;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class ObjetDistant extends UnicastRemoteObject implements Distant {

    private final Service service;

    public ObjetDistant(int numPort) throws IOException {
        super(numPort);
        this.service = new VotingService();
    }

    public Service getNewService() throws RemoteException {
        return this.service;
    }

    public int getVote() throws RemoteException {
        return this.service.getVote(); }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Distant objectDistant = new ObjetDistant(10);
            registry.rebind("objetDistant", objectDistant);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
