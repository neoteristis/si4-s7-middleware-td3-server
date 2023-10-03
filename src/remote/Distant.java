package remote;

import client.Vote;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.List;

public interface Distant extends Remote {
    public Service getNewService() throws RemoteException;
}
