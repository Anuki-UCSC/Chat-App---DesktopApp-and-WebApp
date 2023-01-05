package com.example.chatapp.views;

import com.example.chatapp.ChatApplication;
import com.example.chatapp.model.entity.User;
import com.example.chatapp.model.repo.Chat1MessageRepo;
import com.example.chatapp.model.service.MessageService;
import com.example.chatapp.utils.Menu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.example.chatapp.views.Login.getLoggedUser;

@Controller
public class MainFrame  {

    @FXML
    private HBox imageView;
    @FXML
    private Label recentChatLabel1;
    @FXML
    private Label recentChatLabel2;
    @FXML
    private Label recentChatLabel3;
    @FXML
    private VBox Chat1Global;
    @FXML
    private VBox Chat2;
    @FXML
    private VBox Chat3;
    @FXML
    private VBox sideBar;
    @FXML
    private StackPane contentView;
    @Autowired
    private Chat1MessageRepo chat1MessageRepo;

    @Autowired
    private MessageService messageService;
    public User loginUser=new User();


    public void show(User loginUser) {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(ChatApplication.class.getResource("MainFrame.fxml"));
            stage.setScene((new Scene(root)));
            this.loginUser=loginUser;
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickMenu(MouseEvent mouseEvent) {
        try{
            Node node =(Node) mouseEvent.getSource();
            if(node.getId().equals("Exit")){
                sideBar.getScene().getWindow().hide();
            }else{
                Menu menu = Menu.valueOf(node.getId());
                changeMenuStyle(menu.getName());
                contentView.getChildren().clear();
                FXMLLoader loader=new FXMLLoader((ChatApplication.class.getResource(menu.getFxml())));
                loader.setControllerFactory(ChatApplication.getApplicationContext()::getBean);
                Parent view=loader.load();
                contentView.getChildren().add(view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
        @FXML
    private void initialize(){
        MessageService service=new MessageService();

        User user =getLoggedUser();
//            Optional<Chat1Message> message =chat1MessageRepo.findFirstByOrderByCreatedTimeDesc();
//            if(message.isPresent()){
//                recentChatLabel2.setText(message.get().getMessage());
//            }else {
//                recentChatLabel2.setText("");
//            }
//            recentChatLabel2.setText(messageService.getLastMessageChat1());

            Image image = new Image("E:\\javaFx1\\ChatApp-git\\src\\images\\image1.jpg");

            // simple displays ImageView the image as is
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            iv1.setFitHeight(350);
            iv1.setPreserveRatio(true);
            imageView.getChildren().add(iv1);

            if(!user.isChat1()){
                Chat2.getChildren().clear();
                Chat2.getStyleClass().add("side-bar-menu-no-styles");

            }
            if (!user.isChat2()){
                Chat3.getChildren().clear();
                Chat3.getStyleClass().add("side-bar-menu-no-styles");
            }
    }

//    @Override
//    public void start(Stage stage) {
//        // load the image
//        Image image = new Image("E:\\javaFx1\\ChatApp-git\\src\\images\\image1.jpg");
//
//        // simple displays ImageView the image as is
//        ImageView iv1 = new ImageView();
//        iv1.setImage(image);
//        imageView.getChildren().add(iv1);
//
//    }


    private void changeMenuStyle(String name){
        Chat1Global.getStyleClass().clear();
        Chat1Global.getStyleClass().add("side-bar-menu");
        Chat2.getStyleClass().clear();
        Chat2.getStyleClass().add("side-bar-menu");
        Chat3.getStyleClass().clear();
        Chat3.getStyleClass().add("side-bar-menu");

        if(name.equals("Chat1Global")){
            Chat1Global.getStyleClass().add("side-bar-menu-clicked");
        }else if(name.equals("Chat2")){
            Chat2.getStyleClass().add("side-bar-menu-clicked");
        }else if(name.equals("Chat3")){
            Chat3.getStyleClass().add("side-bar-menu-clicked");
        }
    }


//            System.out.println("find chat :"+chat1MessageRepo.findFirstByOrderByCreatedTimeDesc().get().getMessage());

}
