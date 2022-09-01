package libraryManager.ui.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private Button minimize;

    @FXML
    private PasswordField password;

    @FXML
    private TextField studentId;
    
    //database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    public void login(){
        String sql = "SELECT * FROM student WHERE student_id = ? and password = ?";
        connect = Database.connectDB();
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentId.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(studentId.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message:");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid credentials!");
                alert.showAndWait();
            }else{
                if(result.next()){
                    
                    GetData.studentId = studentId.getText();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Admin Message:");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Logged in!");
                    alert.showAndWait();
                    
                    loginBtn.getScene().getWindow().hide();
                    
                    //dashboard
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
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
                    
                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Admin Message:");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter valid credentials!");
                    alert.showAndWait();
                    
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //validation
    public void numbersOnly(KeyEvent e){
        if(e.getCharacter().matches("[^\\e\t\r\\d+$]")){
            e.consume();
           studentId.setStyle("-fx-border-color:#e04040"); 
        }else{
            studentId.setStyle("-fx-border-color:#fff"); 
        }
    }

    @FXML
    public void minimize(){
        Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
        
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
