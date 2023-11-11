/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.storing.system;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author hp
 */
public class BookStore implements Initializable {
   
    @FXML
    private Label bookname;
    @FXML
    private Label labelname,labelprice,labelerror;
    @FXML
    private Label by;
    @FXML
    private Button Add;

    @FXML
    private Circle circle;
    
    @FXML
    private Rectangle rectangle;
    @FXML
    private TableColumn<Book,String> colprice;

    @FXML
    private TableColumn<Book,String> colyear;

    @FXML
    private TableColumn<Book,String> colAuthour;

    @FXML
    private TableColumn<Book,String> colid;

    @FXML
    private TableColumn<Book,String> colname;

    @FXML
    private TableColumn<Book,String> coltype;

    @FXML
    private Button delete;

    @FXML
    private TableView<Book> table;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtbAuthour;

    @FXML
    private TextField txtbname;
    @FXML
    private TextField txtbtype;

    @FXML
    private Button update;

    @FXML
    private Button Exit;
            boolean validation=true;
            String count;
    @FXML
    void AddBook(ActionEvent event) throws SQLException{ 
        formvalidation();
        if( formvalidation()){
        String  bname,btype,bauthour,price;
        bname=txtbname.getText();
        btype=txtbtype.getText();
        bauthour=txtbAuthour.getText();
        DatePicker date=new DatePicker();
        price=txtPrice.getText();
    try{
        

             pst=con.prepareStatement("insert into book_store(bname,btype,bauthour, publishedDate,bprice)values(?,?,?,?,?)");

            pst.setString(1, bname);
            pst.setString(2, btype);
            pst.setString(3, bauthour);
            pst.setString(4, ((TextField)txtDate.getEditor()).getText());
            pst.setString(5, price);
            pst.executeUpdate();
            
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Book storing System");
           alert.setHeaderText("book storing System");
           alert.setContentText("Successfully Added");
           alert.showAndWait();
            labelerror.setText("");
             table();
                     Add.setDisable(false);

         
         txtbname.setText("");
         txtbtype.setText("");
         txtbAuthour.setText("");
         txtPrice.setText("");
         ((TextField)txtDate.getEditor()).setText("");
         txtbname.requestFocus();
    } catch (SQLException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } 
               
// con.close();
         
    }
//    String count1="Select * from book_store";
   
   public boolean formvalidation(){
      
       
        if(txtbname.getText().isEmpty())
       {
            labelerror.setText("please fill The Book Authour name correctly ");
             txtbname.requestFocus();
           return false;
       }
        else if(txtbtype.getText().isEmpty())
       {    
            labelerror.setText("please fill The Book Type In correctly Catagory");
             txtbtype.requestFocus();
           return false;
       }
        else if(txtbAuthour.getText().isEmpty())
       {
             labelerror.setText("please fill The Book Authour name correctly ");
             txtbAuthour.requestFocus();
           return false;
       }
        else  if(((TextField)txtDate.getEditor()).getText().isEmpty())
          {
            labelerror.setText("please Set Date of boook Written with in the given format of calender!");
               txtDate.requestFocus();
           return false;
           
          }       
        else if(txtPrice.getText().isEmpty())
       {
            labelerror.setText("please Set price of boook with approprite Amount!");
             txtPrice.requestFocus();
           return false;
       }
       return true;
   } 
    public void table(){
       try {
           Connect();
       } catch (SQLException ex) {
           Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
       }
        ObservableList<Book> books=FXCollections.observableArrayList();
        
       try {
           pst =con.prepareStatement("select * from book_store");
           ResultSet rs=pst.executeQuery();
           {
         while(rs.next()){
             Book book=new Book();
             book.setId(rs.getString("id"));
             book.setName(rs.getString("bname"));
             book.setType(rs.getString("btype"));
             book.setAuthour(rs.getString("bauthour"));
             book.setPublishedDate(rs.getString("publishedDate"));
             book.setPrice(rs.getString("bprice"));
             books.add(book);
         } 
           }
            colid.setCellValueFactory(f ->f.getValue().IdProperty());
            colname.setCellValueFactory(f ->f.getValue().bnameProperty());
            coltype.setCellValueFactory(f ->f.getValue().TypeProperty());           table.setItems(books);

            colAuthour.setCellValueFactory(f ->f.getValue().AuthourProperty());
            colyear.setCellValueFactory(f ->f.getValue().PublishedDateProperty());
            colprice.setCellValueFactory(f ->f.getValue().PriceProperty());
            
       } catch (SQLException ex) {
           Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
       }
           table.setRowFactory(tv ->{
               TableRow<Book> myrow= new TableRow<>();
    
               myrow.setOnMouseClicked(event ->
               {
               if(event.getClickCount()==1 && (!myrow.isEmpty()))
               {
                   myIndex=table.getSelectionModel().getSelectedIndex();
                   txtbname.setText(table.getItems().get(myIndex).getName());
                   txtbtype.setText(table.getItems().get(myIndex).getype());
                   txtbAuthour.setText(table.getItems().get(myIndex).getAuthour());
                   ((TextField)txtDate.getEditor()).setText(table.getItems().get(myIndex).getPublishedDate());           
                   txtPrice.setText(table.getItems().get(myIndex).getPrice());

                     bookname.setText(table.getItems().get(myIndex).getName());
                     by.setText(table.getItems().get(myIndex).getAuthour());   
                     labelname.setText(table.getItems().get(myIndex).getype());
                     labelprice.setText(table.getItems().get(myIndex).getPrice());
                     Add.setDisable(true);
//                     Add.setStyle("-fx-background-color:white;");
                       }
           });
           
        return myrow;    
       });
      
    
    }

    @FXML
    void DeleteBook(ActionEvent event) {
     myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    
 
        try
        {
            pst = con.prepareStatement("delete from book_store where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
             txtbname.setText("");
         txtbtype.setText("");
         txtbAuthour.setText("");
         txtPrice.setText("");
            ((TextField)txtDate.getEditor()).setText("");
         txtbname.requestFocus();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Book Storing System");
                alert.setContentText("Selected Book Deleted!");
                alert.showAndWait();
                  table();
                                  Add.setDisable(false);
 
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    @FXML
    void UpdateBook(ActionEvent event) {
             formvalidation();
        if( formvalidation()){
 myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
          
            String  bname,btype,bauthour,price;
        bname=txtbname.getText();
        btype=txtbtype.getText();
        bauthour=txtbAuthour.getText();
        DatePicker date=new DatePicker();
         price=txtPrice.getText();
        try
        {
            pst = con.prepareStatement("update book_store set bname=?,btype=?,bauthour=?, publishedDate=?,bprice=? where id = ? ");
            pst.setString(1, bname);
            pst.setString(2, btype);
            pst.setString(3, bauthour);
            pst.setString(4, ((TextField)txtDate.getEditor()).getText());
            pst.setString(5, price);
             pst.setInt(6, id);
             pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Storing System");

            alert.setContentText("Updated successfully!");

            alert.showAndWait();
                    labelerror.setText("");
                    table();
                 Add.setDisable(false);
                 txtbname.setText("");
         txtbtype.setText("");
         txtbAuthour.setText("");
         txtPrice.setText("");
         ((TextField)txtDate.getEditor()).setText("");
         txtbname.requestFocus();
      
        }
        catch (SQLException ex)
        {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    void Exit(ActionEvent event) {
       System.exit(0);
   
    }
   
      

  Connection con;
  
  PreparedStatement pst;
  Statement stmt;
   ResultSet rs;
  int myIndex;
  int id;
  public void Connect() throws SQLException{
      try{
           
           Class.forName("com.mysql.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost/booking","root","");
           
      }
      catch(ClassNotFoundException ex){
          
      }   catch (SQLException ex){
          ex.printStackTrace();
      }  
      
  }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connect();
            table();
        } catch (SQLException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }

      
    }    
    
}
