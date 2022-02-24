package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.presistance.daos.GroupChatUsersDao;
import gov.iti.jets.service.services.ServerControlService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerGroupChatMessageImpl extends UnicastRemoteObject implements ServerGroupChatMessageInt {

    GroupChatUsersDao groupChatUsersDao = new GroupChatUsersDao();
    Map<Integer, ClientGroupChatMessageInt> clients = new HashMap<>();
    List<Integer> userIds = new ArrayList<>();

    public ServerGroupChatMessageImpl() throws RemoteException {
    }

    @Override
    public boolean sendGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException {
        if(ServerControlService.flag) {
            userIds = groupChatUsersDao.getAllUsersIdFromGroupId(messageGroupDto.getGroupId());
            for (int userId : userIds) {
                try {
                    if (userId != messageGroupDto.getSenderId()) {
                        if (clients.containsKey(userId)) {
                            sendMessage(messageGroupDto, userId);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(userId + " user is offline");
                    e.printStackTrace();
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean register(ClientGroupChatMessageInt clientGroupChatMessageInt, int userId) throws RemoteException {
            clients.put(userId, clientGroupChatMessageInt);
        return true;
    }

    @Override
    public boolean unregister(ClientGroupChatMessageInt clientGroupChatMessageInt) throws RemoteException {
        return false;
    }


    private void sendMessage(MessageGroupDto messageGroupDto ,int userID) {
        try {
            clients.get(userID).receiveGroupChatMessage(messageGroupDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
