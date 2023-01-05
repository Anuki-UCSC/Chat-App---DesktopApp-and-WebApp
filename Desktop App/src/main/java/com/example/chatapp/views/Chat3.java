package com.example.chatapp.views;

import com.example.chatapp.ChatApplication;
import com.example.chatapp.model.entity.*;
import com.example.chatapp.model.service.MessageService;
import com.example.chatapp.model.service.UserService;
import com.example.chatapp.utils.EventHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class Chat3 {


    @FXML
    private TextField messageBoxTf;
    @FXML
    private Button sendBtn;
    @FXML
    private StackPane contentView;
    @FXML
    private VBox addUserToChatBtn;

    @FXML
    private VBox chatContainer;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private AddUserToChat addUserToChat;

    @Autowired
    private RemoveUserFromChat removeUserFromChat;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private EventHandler eventHandler;
    private String currentChat="chat2";
    private ScheduledExecutorService executorService;


    @FXML
    public void addUserToChat() {
        List<User> users =userService.findNotChatUsersList(currentChat);
        addUserToChat.showView(users,currentChat);
    }

    @FXML
    public void removeUserFromChat() {
        List<User> users =userService.findChatUsersList(currentChat);
        removeUserFromChat.showView(users,currentChat);
    }



    @FXML
    public void sendMessage() {
        if(messageBoxTf.getText().length()>0){
            MessageBody messageBody= new MessageBody();
            messageBody.setMessage(messageBoxTf.getText());
            messageBody.setType(MessageBody.Type.normal);
            messageBody.setGroup(MessageBody.Group.valueOf(currentChat));
            messageBody.setSender(mainFrame.loginUser.getUsername());
            eventHandler.sendMessage(messageBody);
//            messageService.sendMessage(messageBody);
            messageBoxTf.clear();
            initializeScheduler();
        }


    }

    @FXML
    private void initialize(){
        chatContainer.getChildren().clear();
        messageService.getMessages(MessageBody.Group.valueOf(currentChat)).stream().forEach(msg -> {
            chatContainer.getChildren().add(new Chat3.ChatItem(msg));
        });
    }


    private void initializeScheduler() {
        executorService = Executors.newScheduledThreadPool(5);
        executorService.schedule(this::reload,500, TimeUnit.MILLISECONDS);
    }

    public void reload(){

        Platform.runLater(() -> {
            chatContainer.getChildren().clear();
            messageService.getMessages(MessageBody.Group.valueOf(currentChat)).stream().forEach(msg -> {
                chatContainer.getChildren().add(new Chat3.ChatItem(msg));
            });
        });

    }

    private class ChatItem extends VBox {
        public ChatItem(Object messageObject){
            Chat2Message message= (Chat2Message) messageObject;
            HBox hBox = new HBox();


            if(message.getType().equals(MessageBody.Type.normal)){
                if(message.getSender().toString().equals(mainFrame.loginUser.getUsername().toString())){
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,20,5,5));
                    Text text = new Text(message.getMessage());
                    TextFlow textFlow =new TextFlow(text);
                    textFlow.setStyle("-fx-background-color: #d5087e; -fx-background-radius: 20px");
                    textFlow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.907,0.867, 0.789));
                    hBox.getChildren().add(textFlow);
                    chatContainer.getChildren().add(hBox);
//
                }else {
//                    getStyleClass().add("item");
                    hBox.setAlignment(Pos.CENTER_LEFT);
//                    hBox.setPadding(new Insets(5,20,5,5));
                    Text text = new Text(message.getMessage());
                    Text text2 = new Text(String.valueOf(message.getSender().charAt(0)).toUpperCase());
                    TextFlow textFlow =new TextFlow(text);
                    textFlow.setStyle("-fx-background-color: #af95a5; -fx-background-radius: 20px");
                    textFlow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.207,0.267, 0.289));
                    text2.setFill(Color.color(0.907,0.867, 0.789));
                    text2.setTextAlignment(TextAlignment.CENTER);
                    VBox avatar = new VBox(text2);
                    avatar.setStyle("-fx-background-color: #010179; -fx-background-radius: 40px; -fx-border-radius: 40px; -fx-pref-width: 30px ; -fx-pref-height: 30px");
                    avatar.setPadding(new Insets(5,5,5,5));
                    avatar.setAlignment(Pos.CENTER);
                    hBox.getChildren().add(avatar);
                    hBox.getChildren().add(textFlow);
                    hBox.setSpacing(5);
                    chatContainer.getChildren().add(hBox);
                }
            }else {
                hBox.setAlignment(Pos.CENTER);
                hBox.setPadding(new Insets(5,20,5,5));
                Text text = new Text(message.getMessage());
                text.setStyle("-fx-font-size: 12px");
                TextFlow textFlow =new TextFlow(text);
                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.207,0.267, 0.289));
                hBox.getChildren().add(textFlow);
                chatContainer.getChildren().add(hBox);
//                getStyleClass().add("specialItem");
            }





//            Label name = new Label();
//            name.setText(message.getMessage());
//            getChildren().addAll(name);



//            if(message.getType().equals(MessageBody.Type.normal)){
//                if(message.getSender().toString().equals(mainFrame.loginUser.getUsername().toString())){
//                    getStyleClass().add("myItem");
////
//                }else {
//                    getStyleClass().add("item");
//                }
//            }else {
//
//                getStyleClass().add("specialItem");
//            }

        }
    }
}
