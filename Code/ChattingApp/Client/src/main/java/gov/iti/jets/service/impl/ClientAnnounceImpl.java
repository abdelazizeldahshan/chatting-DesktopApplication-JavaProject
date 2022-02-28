package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.networking.RMIRegister;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientAnnounceImpl extends UnicastRemoteObject implements ClientAnnounceMessageInt {


    RMIRegister rmiRegister=RMIRegister.getInstance();
    ServerMessageAnnouncetInt serverMessageAnnouncetInt;
    public static List<String> messagesFromAdmin=new ArrayList<>();

    public ClientAnnounceImpl() throws RemoteException {
        super();
       serverMessageAnnouncetInt=rmiRegister.serverMessageAnnouncetInt();
        serverMessageAnnouncetInt.register(this);
    }

    @Override
    public void reciveMessage(List<String> messageAnnounceDto) throws RemoteException {
        for (String message:messageAnnounceDto ) {
            messagesFromAdmin.add( message);
        }

    }
}
