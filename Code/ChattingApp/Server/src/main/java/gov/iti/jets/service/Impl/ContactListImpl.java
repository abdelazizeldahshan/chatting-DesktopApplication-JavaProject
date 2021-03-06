package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.daos.ContactDao;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.common.interfaces.ContactListInt;
import gov.iti.jets.common.dtos.ContactDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ContactListImpl extends UnicastRemoteObject implements ContactListInt {
    ContactDao contactDao=  new ContactDao();
    public ContactListImpl() throws RemoteException {
    }


    @Override
    public List<ContactDto> getListContact(int id) throws RuntimeException {
       return mappingUserDtoToContactDto(contactDao.getAllUserFriendsById(id));
    }

    private List<ContactDto> mappingUserDtoToContactDto(List<UserDto> allUserFriendsById) {
        List<ContactDto> contactDtoList = new ArrayList<>();
        for(UserDto user : allUserFriendsById){
            ContactDto contactDto = new ContactDto();
            contactDto.setId(user.getUserID());
            contactDto.setFriendName(user.getName());
            try {
                contactDto.setPicture(encodeImage(user.getPicture()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            contactDto.setStatus(user.getStatus().toString());
            contactDtoList.add(contactDto);
        }
        return contactDtoList;
    }
    public String encodeImage(String imgPath) throws IOException {
        FileInputStream stream = new FileInputStream(imgPath);
        byte[] imageData = stream.readAllBytes();
        String imagePath = Base64.getEncoder().encodeToString(imageData);
        stream.close();
        return  imagePath;
    }
}
