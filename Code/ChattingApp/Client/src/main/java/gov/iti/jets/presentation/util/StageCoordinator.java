package gov.iti.jets.presentation.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gov.iti.jets.presentation.controllers.MessageController;
import gov.iti.jets.service.daos.MessageDao;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
    private Stage primaryStage;
    private GridPane homepage;

    private final Map<String, Scene> sceneMap = new HashMap<>();
    private final Map<String, Node> nodeMap = new HashMap<>();

    private StageCoordinator() {
    }

    public static StageCoordinator getInstance() {
        return stageCoordinator;
    }

    public void initStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public  void setHomepage(GridPane gridPane){
        homepage = gridPane;
    }
    public  GridPane getHomepage(){
        return homepage;
    }

    public String openFile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pic Chooser");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            return file.getPath();
        } else {
            return null;
        }
    }

    public void switchToWelcomScreen() {
        Scene welcomScene = sceneMap.get("welcomScene");
        if (welcomScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/WelcomePage/LoginView.fxml"));
                welcomScene = new Scene(root);
                sceneMap.put("welcomScene", welcomScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(welcomScene);
    }

    public void switchToLoginScreen() {
        Scene loginScene = sceneMap.get("loginScene");
        if (loginScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/LoginSection1/LoginView2.fxml"));
                loginScene = new Scene(root);
                sceneMap.put("loginScene", loginScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(loginScene);
    }

    public void switchToPasswordScreen() {
        Scene passwordScene = sceneMap.get("passwordScene");
        if (passwordScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/passwordsection/passwordView.fxml"));
                passwordScene = new Scene(root);
                sceneMap.put("passwordScene", passwordScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(passwordScene);
    }

    public void switchToGHomePageScreen() {
        Scene homePageScene = null;
        try {
            GridPane root = FXMLLoader.load(getClass().getResource("/views/HomePageStructure/homePage.fxml"));
            homePageScene = new Scene(root);
//            sceneMap.put("homePageScene", homePageScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(homePageScene);
    }

    public void switchToProfileScreen() {
        Scene profileScene = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/profile/editProfileSection.fxml"));
            profileScene = new Scene(root);
//            sceneMap.put("homePageScene", homePageScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(profileScene);
    }

    public void switchToRegistrationScreen() {
        Scene registrationScene = sceneMap.get("registrationScene");
        if (registrationScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/RegisterSection/RegisterView.fxml"));
                registrationScene = new Scene(root);
                sceneMap.put("registrationScene", registrationScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(registrationScene);
    }

    public Node loadContacts() {
        Node contactList = nodeMap.get("contactList");
        if (contactList == null) {
            try {
                contactList = FXMLLoader.load(getClass().getResource("/views/contactSection/contactList1.fxml"));
                nodeMap.put("contactList", contactList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return contactList;
    }

    public Node loadSidebar() {
        Node sidebar = null;
        try {
            sidebar = FXMLLoader.load(getClass().getResource("/views/SidebarSection/sideBar.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sidebar;
    }

    public Node loadDefault(){
        Node defaultbar = null;
        try {
            defaultbar = FXMLLoader.load(getClass().getResource("/views/HomePageSection1/defaultHomeScreen1.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultbar;
    }
    public Node loadChatSection(){
        Node chatSection = null;
        try {
            chatSection = FXMLLoader.load(getClass().getResource("/views/HomePageSection2/homePageSection2.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatSection;
    }

    public void loadAddContact() {
        Stage addNewContact = new Stage();
        Pane addContact = null;
        try {
            addContact = FXMLLoader.load(getClass().getResource("/views/contactSection/addContact.fxml"));
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        Scene scene = new Scene(addContact);
        addNewContact.setScene(scene);
        addNewContact.setTitle("Add New Contact");
        addNewContact.show();
    }

    public Node loadSettings() {
        Node settings = nodeMap.get("settings");
        if (settings == null) {
            try {
                settings = FXMLLoader.load(getClass().getResource("/views/settings/settingScreen.fxml"));
                nodeMap.put("settings", settings);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return settings;
    }

    public void loadhelp() {
        Stage helpStage = new Stage();
        Pane help = null;
        try {
            help = FXMLLoader.load(getClass().getResource("/views/settings/helpPane.fxml"));
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        Scene scene = new Scene(help);
        helpStage.setScene(scene);
        helpStage.setTitle("Help Me");
        helpStage.show();
    }

    public void loadTheme() {
        Stage themeStage = new Stage();
        Pane theme = null;
        try {
            theme = FXMLLoader.load(getClass().getResource("/views/settings/editTheme.fxml"));
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        Scene scene = new Scene(theme);
        themeStage.setScene(scene);
        themeStage.setTitle("Choose Theme");
        themeStage.show();

    }

    public AnchorPane loadMyChat() {
        AnchorPane chatSectiodn = null;
        try {
            chatSectiodn = FXMLLoader.load(getClass().getResource("/views/chattingSection/chatSection.fxml"));
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        return chatSectiodn;
    }

    // public Pane loadUserPane(){
    // try {
    // Pane userPane =
    // FXMLLoader.load(getClass().getResource("/views/contactSection/userPane.fxml"));
    // return userPane;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }

    // return null ;
    // }

     public HBox loadMessage( MessageDao messageDao) {


         try {

//             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/message/messageView.fxml"));
//
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(getClass().getResource("/views/message/messageView.fxml")); // Your .fxml File

             HBox message = loader.load();
             MessageController messageController = (MessageController) loader.getController();
//             Platform.runLater(new Runnable() {
//                 @Override
//                 public void run() {
                     messageController.displayMessage(messageDao.getMessageContent(), messageDao.getMessageUserName());
//                 }
//             });


             return message;
         } catch (Exception e) {
             System.out.println("File Not Found Exception");
         }
         return null;

     }}
