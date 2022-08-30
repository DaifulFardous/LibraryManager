package libraryManager.ui.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class login extends Application {
     private double x =0;
     private double y =0;
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
       Scene scene = new Scene(root);
       root.setOnMousePressed((MouseEvent e)->{
           x = e.getSceneX();
           y = e.getSceneY();
       });
       root.setOnMouseDragged((MouseEvent e)->{
           stage.setX(e.getScreenX()-x);
           stage.setY(e.getScreenY()-y);
       });
       stage.initStyle(StageStyle.TRANSPARENT);
       stage.setTitle("Login!");
       stage.setScene(scene);
       stage.show();   
    }
       public static void main(String[] args) {
        launch(args);
    }

}
