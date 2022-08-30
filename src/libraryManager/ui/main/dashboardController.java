package libraryManager.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable {

     @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_image;

    @FXML
    private TableView<?> availableBooks_table;

    @FXML
    private Label availableBooks_title;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<?, ?> col_ab_author;

    @FXML
    private TableColumn<?, ?> col_ab_booksTitle;

    @FXML
    private TableColumn<?, ?> col_ab_booksType;

    @FXML
    private TableColumn<?, ?> col_ab_publishedDate;

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
    
    private double x = 0;
    private double y = 0;
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
    
}
