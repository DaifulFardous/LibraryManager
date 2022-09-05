package libraryManager.ui.main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class dashboardController implements Initializable {

     @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_image;

    @FXML
    private TableView<AvailableBooks> availableBooks_table;

    @FXML
    private Label availableBooks_title;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_author;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_booksTitle;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_booksType;

    @FXML
    private TableColumn<AvailableBooks, String> col_ab_publishedDate;

    @FXML
    private Button edit_btn;

    @FXML
    private Button issueBooks_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button returnBooks_btn;

    @FXML
    private Button save_btn;

    @FXML
    private Button savedBooks_btn;

    @FXML
    private Label studentId_label;

    @FXML
    private Button take_btn;
    
    
    @FXML
    private Button minimize;
    
    
    @FXML
    private Button close;
    
    @FXML
    private Button arrow_btn;
    
    @FXML
    private Button bars_btn;
    
    
    @FXML
    private AnchorPane nav_form;
    
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private Button halfNav_saveBtn;

    @FXML
    private Circle small_circle;
    
    @FXML
    private Button halfNav_availbaleBtn;

    @FXML
    private AnchorPane halfNav_form;

    @FXML
    private Button halfNav_returnBtn;

    @FXML
    private Button halfNav_takeBtn;
    @FXML
    private Label availableBooks_author;
    @FXML
    private AnchorPane issue_form;
    @FXML
    private Label currentForm_label;
    @FXML
    private AnchorPane savedBook_from;
    @FXML
    private AnchorPane returnBook_from;
    @FXML
    private Label take_author;

    @FXML
    private TextField take_bookTitle;

    @FXML
    private Button take_ckearBtn;

    @FXML
    private Label take_date;

    @FXML
    private TextField take_firstName;

    @FXML
    private ComboBox<?> take_gender;

    @FXML
    private Label take_genre;

    @FXML
    private ImageView take_imageView;

    @FXML
    private Label take_issuedDate;

    @FXML
    private TextField take_lastName;

    @FXML
    private Label take_studentId;

    @FXML
    private Button take_takeBtn;

    @FXML
    private Label take_title;
    
    
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private String comboBox[] = {"Male", "Female", "Others"}; 
    
    public void gender(){
        List<String> combo = new ArrayList();
       
        for(String data: comboBox){
            combo.add(data);
        }
         ObservableList list = FXCollections.observableArrayList(combo);
        take_gender.setItems(list);
    }
    
    public void findBook(ActionEvent event){
        clearFindData();
        String sql = "SELECT * FROM book WHERE bookTitle = '"+ take_bookTitle.getText() +"'";
        
        connect = Database.connectDB();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            boolean check = false;
            Alert alert;
          if(take_bookTitle.getText().isEmpty()){
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Admin Message!!");
              alert.setHeaderText(null);
              alert.setContentText("Please select a book");
              alert.showAndWait();
              
          }
          /*else if(take_firstName.getText().isEmpty() || take_lastName.getText().isEmpty() || take_gender.getSelectionModel().isEmpty()){
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Admin Message!!");
              alert.setHeaderText(null);
              alert.setContentText("Please Insert the details");
              alert.showAndWait();
          }*/
          else{
              while(result.next()){
                  take_title.setText(result.getString("bookTitle"));
                  take_author.setText(result.getString("author"));
                  take_genre.setText(result.getString("bookType"));
                  take_date.setText(result.getString("date"));
                  GetData.path = result.getString("image");
                  String uri = "file:"+GetData.path;
                  image = new Image(uri, 119,146,false,false,true);
                  take_imageView.setImage(image);
                  check = true;
              }
              
              if(!check){
                  take_title.setText("No book found!!");
              }
          }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void clearFindData(){
         take_title.setText("");
         take_author.setText("");
         take_genre.setText("");
         take_date.setText("");
         take_imageView.setImage(null);
    }
    
    public ObservableList<AvailableBooks> dataList(){
        ObservableList<AvailableBooks> listBooks = FXCollections.observableArrayList();
        String sql = "SELECT * FROM book";
        connect = Database.connectDB();
        try{
            
            AvailableBooks abBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()){
                abBooks = new AvailableBooks(result.getString("bookTitle"),
                        result.getString("author"),
                        result.getString("bookType"),
                        result.getString("image"),
                        result.getDate("date"));
                listBooks.add(abBooks);
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return listBooks;
    }
     private ObservableList<AvailableBooks> listBook;
     
     public void showAvailableBooks(){
         listBook = dataList();
         col_ab_booksTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
         col_ab_booksType.setCellValueFactory(new PropertyValueFactory<>("genre"));
         col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         availableBooks_table.setItems(listBook);
     }
     public void selectAvailableBooks(){
         AvailableBooks bookData = availableBooks_table.getSelectionModel().getSelectedItem();
         int n = availableBooks_table.getSelectionModel().getFocusedIndex();
         if((n-1) < -1){
            return; 
         }
         availableBooks_title.setText(bookData.getTitle());
         availableBooks_author.setText(bookData.getAuthor());
         String uri = "file:" + bookData.getImage();
         image = new Image(uri, 138, 171, false, true);
         availableBooks_image.setImage(image);
     }
      public void abTakeButton(ActionEvent e){
         if(e.getSource() == take_btn){
             issue_form.setVisible(true);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(false);
             currentForm_label.setText("Issue Books");
             
             issueBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_takeBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         }
     }
     public void studentId(){
         studentId_label.setText(GetData.studentId);
     }
     public void sideNavButtonDesign(ActionEvent e){
         if(e.getSource() == halfNav_availbaleBtn){
             
             issue_form.setVisible(false);
             availableBooks_form.setVisible(true);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(false);
             currentForm_label.setText("Available Books");
             
             availableBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_availbaleBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
         }else if(e.getSource() == halfNav_takeBtn){
             
             issue_form.setVisible(true);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(false);
             currentForm_label.setText("Issue Books");
             
             issueBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_takeBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         
         }else if(e.getSource() == halfNav_returnBtn){
             
             issue_form.setVisible(false);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(true);
             currentForm_label.setText("Return Books");
             
             issueBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_takeBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         
         }else if(e.getSource() == halfNav_saveBtn){
             
             issue_form.setVisible(false);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(true);
             returnBook_from.setVisible(false);
             
             currentForm_label.setText("Saved Books");
             
             savedBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_saveBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         }
     }
     public void navButtonDesign(ActionEvent e){
         if(e.getSource() == availableBooks_btn){
             
             issue_form.setVisible(false);
             availableBooks_form.setVisible(true);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(false);
             currentForm_label.setText("Available Books");
             
             availableBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_availbaleBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         }else if(e.getSource() == issueBooks_btn){
             
             issue_form.setVisible(true);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(false);
             currentForm_label.setText("Issue Books");
             
             issueBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_takeBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         
         }else if(e.getSource() == returnBooks_btn){
             
             issue_form.setVisible(false);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(false);
             returnBook_from.setVisible(true);
             currentForm_label.setText("Return Books");
             
             returnBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_returnBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         
         }else if(e.getSource() == savedBooks_btn){
             
             issue_form.setVisible(false);
             availableBooks_form.setVisible(false);
             savedBook_from.setVisible(true);
             returnBook_from.setVisible(false);
             
             currentForm_label.setText("Saved Books");
             
             savedBooks_btn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             
             halfNav_saveBtn.setStyle(" -fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
             halfNav_availbaleBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
             halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
         }
     }
  
    private double x = 0;
    private double y = 0;
    
    @FXML
    public void sliderArrow(){
        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(.5));
        slider.setNode(nav_form);
        slider.setToX(-243.2);
        TranslateTransition sliderM = new TranslateTransition();
        sliderM.setDuration(Duration.seconds(.5));
        sliderM.setNode(main_form);
        sliderM.setToX(-243.2+90);
        
        TranslateTransition sliderH = new TranslateTransition();
        sliderH.setDuration(Duration.seconds(.5));
        sliderH.setNode(halfNav_form);
        sliderH.setToX(0);
        
        slider.setOnFinished((ActionEvent e)->{
            arrow_btn.setVisible(false);
            bars_btn.setVisible(true);
        });
        sliderH.play();
        sliderM.play();
        slider.play();
    }
    
    @FXML
    public void sliderBars(){
        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(.5));
        slider.setNode(nav_form);
        slider.setToX(0);
        TranslateTransition sliderM = new TranslateTransition();
        sliderM.setDuration(Duration.seconds(.5));
        sliderM.setNode(main_form);
        sliderM.setToX(0);
        TranslateTransition sliderH = new TranslateTransition();
        sliderH.setDuration(Duration.seconds(.5));
        sliderH.setNode(halfNav_form);
        sliderH.setToX(-68);
        slider.setOnFinished((ActionEvent e)->{
            arrow_btn.setVisible(true);
            bars_btn.setVisible(false);
        });
        sliderH.play();
        sliderM.play();
        slider.play();
    }
    
    @FXML
    public void logout(ActionEvent e) throws IOException{
        
            if(e.getSource() == logout_btn){
             Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
             Stage stage = new Stage();
             Scene scene = new Scene(root);
             root.setOnMousePressed((MouseEvent event)->{
                 x = event.getSceneX();
                 y = event.getSceneY();
             });
             root.setOnMouseDragged((MouseEvent event)->{
                stage.setX(event.getScreenX()-x);
                stage.setY(event.getScreenY()-y);
             });
             stage.initStyle(StageStyle.TRANSPARENT);
             stage.setScene(scene);
             stage.show();
             logout_btn.getScene().getWindow().hide();
            }
        } 
    @FXML
    public void exit(){
        System.exit(0);
    }
    @FXML
    public void minimize(){
          Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showAvailableBooks();
      studentId();
      gender();
    }
    
}
