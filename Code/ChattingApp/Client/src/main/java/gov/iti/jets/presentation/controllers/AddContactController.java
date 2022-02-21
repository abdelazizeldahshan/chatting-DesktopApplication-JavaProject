package gov.iti.jets.presentation.controllers;


import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.service.services.FriendRequestService;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {

    @FXML
    private TextField AddingFriendTextArea;

    @FXML
    void OnEnterPressed(KeyEvent event) {
        if (AddingFriendTextArea.getText() != null) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println(AddingFriendTextArea.getText());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FriendRequestService friendRequestService = new FriendRequestService();
                            ClientFriendRequestDto clientFriendRequestDto = new ClientFriendRequestDto();
                            clientFriendRequestDto.setFriendPhoneNumber(AddingFriendTextArea.getText());
                            clientFriendRequestDto.setUserId(LoginService.getId());
                            System.out.println(friendRequestService.friendRequest(clientFriendRequestDto));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AddingFriendTextArea.setText("");
            }
        });
    }
}