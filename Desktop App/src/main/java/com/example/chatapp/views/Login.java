package com.example.chatapp.views;

import com.example.chatapp.ChatApplication;
import com.example.chatapp.model.ChatException;
import com.example.chatapp.model.entity.User;
import com.example.chatapp.model.service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class Login {

    @FXML
    private Button closeBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField loginId;
    @FXML
    private Label message;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private LoginService service;
    public User loginUser;

    public static User user;
    public static User getLoggedUser(){
        return user;
    }

    @FXML
    private PasswordField password;

    private  void attachEvent(){
        loginId.getScene().setOnKeyPressed(event ->{
            if(event.getCode()== KeyCode.ENTER){
                if(closeBtn.isFocused()){
                    close();
                }
                if(loginBtn.isFocused()){
                    login();
                }
            }
        });
    }

    @FXML
    private void close() {
        loginBtn.getScene().getWindow().hide();
    }

    @FXML
    public void login() {
        try {
//            loginUser = service.login(loginId.getText(), password.getText());
            loginUser = service.login(loginId.getText(), password.getText());
            user=service.login(loginId.getText(), password.getText());

            mainFrame.show(loginUser);
            close(); //close login view
        }catch (ChatException e){
            message.setText(e.getMessage());

        }catch (Exception e){
            e.printStackTrace();
            close();
        }
    }

    public static void loadView(Stage stage) {
        try {
            FXMLLoader loader =new FXMLLoader(ChatApplication.class.getResource("Login.fxml"));
            loader.setControllerFactory(ChatApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            stage.setScene(new Scene(view));
            Login controller =loader.getController();
            controller.attachEvent();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
