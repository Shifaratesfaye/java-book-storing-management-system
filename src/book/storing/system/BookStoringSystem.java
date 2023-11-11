/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.storing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class BookStoringSystem extends Application {
    String password="1234";
           String Username="elan";
    @Override
    public void start(Stage stage) throws Exception {
            Stage stagelogin =new Stage();
            Label labelUsername=new Label("username");
            TextField textUsername=new TextField();
            Label labelPassword=new Label("password");
            PasswordField textpassword=new PasswordField();
                Button btnLogin=new Button("login");
                Button btnCancel=new Button("Cancel");
            GridPane gp=new GridPane();
               gp.add(labelUsername, 0, 0);
               gp.add(textUsername, 4, 0);
               gp.add(labelPassword, 0, 4);
               gp.add(textpassword, 4, 4);                   
               gp.add(btnLogin, 0, 12);
               gp.add(btnCancel, 4, 12);
               gp.setStyle("-fx-border-color:blue;-fx-background-color:slategrey;");
                       gp.setPadding(new Insets(25));
                        btnLogin.setFont(Font.font(20)); 
                        btnCancel.setFont(Font.font(20));                             
                        labelUsername.setFont(Font.font(18));
                        labelPassword.setFont(Font.font(18));
                        btnLogin.setStyle("-fx-color:black;-fx-background-color:cyan;");
//                        btnCancel.setStyle("-fx-color:red;-fx-background-color:white;");
                        labelUsername.setStyle("-fx-color:blue;-fx-background-color:white;");
                        labelPassword.setStyle("-fx-color:blue;-fx-background-color:white;");
                gp.setHgap(9);
                gp.setVgap(9);
              Scene sc=new Scene(gp,600,430);
            stagelogin.setTitle("login into page");
             stagelogin.setScene(sc);
               stagelogin.show();
           
             btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               System.exit(0);
            }      
              });
             btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Connect();
             String Username = textUsername.getText();
             String Password = textpassword.getText();
             
             if(textUsername.getText().isEmpty() || textpassword.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Are you joking");
           alert.setContentText("Username or Password Field is Empty!!");
           alert.showAndWait();
      }else{
          try {
              pst = con.prepareStatement("select * from user where Username=? and Password=?");
              pst.setString(1, Username);
              pst.setString(2, Password);
              rs = pst.executeQuery();
              
              if(rs.next()){
                  stage.show();
              }
              else{
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Login Page");
                  alert.setContentText("PLEASE ENTER USERNAME WE DONT HAVE     "+Username+" AS ADMIN EVEN AS WORKER with this password");
                  alert.showAndWait();
                  textUsername.setText("");
                  textpassword.setText("");
                  textUsername.requestFocus();
              }
              
          } catch (SQLException ex) {
              ex.printStackTrace();
          }
          
      }
                
             
             
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("BookStore.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
//        stage.show();
        stage.hide();
    }

    /**
     * @param args the command line arguments
     */
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/booking","root","");
        } catch (ClassNotFoundException ex) {
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public boolean pascheck(String un,String ps){
        return (un.equals(Username))&&(ps.equals(password));
    }
       public static void main(String[] args) {
        launch(args);
    }
    
}
