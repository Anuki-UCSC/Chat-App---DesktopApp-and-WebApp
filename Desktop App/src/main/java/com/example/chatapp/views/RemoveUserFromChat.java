package com.example.chatapp.views;


import com.example.chatapp.ChatApplication;
import com.example.chatapp.model.entity.MessageBody;
import com.example.chatapp.model.entity.User;
import com.example.chatapp.model.service.MessageService;
import com.example.chatapp.model.service.UserService;
import com.example.chatapp.utils.EventHandler;
import com.example.chatapp.utils.Intermediate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

@Controller
public class RemoveUserFromChat  {


    @FXML
    private HBox item;
    @FXML
    private Button cancelBtn;
    @FXML
    private VBox vContainer;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private Intermediate intermediate;

    @Autowired
    private EventHandler eventHandler;

    public static List<User> usersList;
    public static String currentChat;
    private User selectedUser;


   public void showView(List<User> usersList, String currentChat){
       try {
           RemoveUserFromChat.usersList =usersList;
           RemoveUserFromChat.currentChat=currentChat;
           Stage stage = new Stage(StageStyle.UNDECORATED);
           stage.initModality(Modality.APPLICATION_MODAL);
           FXMLLoader loader = new FXMLLoader(ChatApplication.class.getResource("RemoveUserFromChat.fxml"));

           loader.setControllerFactory(ChatApplication.getApplicationContext()::getBean);
           Parent view=loader.load();
           stage.setScene(new Scene(view));
           stage.show();
       }catch (Exception e){
           e.printStackTrace();
       }
   }

    @FXML
    private void initialize(){
        vContainer.getChildren().clear();
        this.usersList.stream()
                .forEach(user-> {
                    vContainer.getChildren().add(new RemoveUserFromChat.ItemRemoveUserToFomChat(user));
                });
    }

    @FXML
    public void cancel() {
        selectedUser=null;
        cancelBtn.getScene().getWindow().hide();

    }

    @FXML
    public void remove() {
        if(selectedUser!=null){
            MessageBody messageBody= new MessageBody();
            messageBody.setMessage(selectedUser.getUsername()+" has been removed from the chat.");
            messageBody.setType(MessageBody.Type.special);
            messageBody.setGroup(MessageBody.Group.valueOf(currentChat));
            messageBody.setSender(selectedUser.getUsername());
            eventHandler.sendMessage(messageBody);
            intermediate.reloadChats(currentChat);
            selectedUser = null;
            reload();
            cancelBtn.getScene().getWindow().hide();
        }
    }

    private void reload(){
        vContainer.getChildren().clear();
        userService.findChatUsersList(currentChat).stream()
                .forEach(user-> {
                    vContainer.getChildren().add(new RemoveUserFromChat.ItemRemoveUserToFomChat(user));
                });
    }


    private class ItemRemoveUserToFomChat extends HBox{
        private User user;
        public ItemRemoveUserToFomChat(User user){
            Label name = new Label();
            name.setText(user.getName());
            getChildren().addAll(name);
            getStyleClass().add("item");

            setOnMouseClicked(event -> {
                if(event.getClickCount()==1){
                    if(selectedUser==null) {
                        selectedUser = user;
                        getStyleClass().add("item-selected");
                    }else if(selectedUser.getUsername().equals(user.getUsername()) ){
                        selectedUser = null;
                        getStyleClass().clear();
                        getStyleClass().add("item");
                    }
                }


            });
        }
   }

}
