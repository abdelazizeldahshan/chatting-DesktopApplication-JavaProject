package gov.iti.jets.service.services;

import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.impl.ClientMessageImpl;
import javafx.scene.layout.HBox;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    static MessageService messageService = new MessageService();
    ClientMessageImpl clientMessage ;
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    MessageDto messageDto = new MessageDto();
    MessageDao messageDao;
    List<HBox> list = new ArrayList<HBox>();
    List<MessageDao> list1 = new ArrayList<>();
    private MessageService(){
        try {
            clientMessage = new ClientMessageImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public ClientMessageImpl getClient(){
        return this.clientMessage;
    }
    public static MessageService getInstance(){
        return messageService;
    }
    public void sendMessageDto(MessageDto messageDto){
        this.messageDto = messageDto;
        clientMessage.sendMessage(this.messageDto);
    }
    public void recieveMessageDto(MessageDto messageDto){
        messageDao = new MessageDao(messageDto);
        list1.add(messageDao);

//        list.add(stageCoordinator.loadMessage(messageDao));
//        System.out.println(list.toString());


    }
//    public List<HBox> getListOfMessage(){
//        return list;
//    }


}
