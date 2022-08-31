package libraryManager.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

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
      
    }
    
}
