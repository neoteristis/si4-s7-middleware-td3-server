package remote;

import client.Vote;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ObjetDistant extends UnicastRemoteObject implements Distant {

    private Service service;

    public ObjetDistant(int numPort) throws RemoteException {
        super(numPort);
        this.service = new ObjetService(10);
    }

    public Service getNewService() throws RemoteException {
        return this.service;
    }

    @Override
    public Result res(List<Vote> clientVotes) throws RemoteException {
        return new Result(clientVotes);
    }

    public int getVote() throws RemoteException {
        return this.service.getVote(); }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started on port 1099");
            Distant objectDistant = new ObjetDistant(0);
            registry.rebind("objetDistant", objectDistant);
        } catch (RemoteException e) {
            System.out.println("Error with the server side...");
        }
    }
}