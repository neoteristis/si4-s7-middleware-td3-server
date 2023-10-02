package remote;

import client.Vote;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.List;

public interface Distant extends Remote {
    public Result res(List<Vote> clientVotes) throws RemoteException;
    public Service getNewService() throws RemoteException;
}
