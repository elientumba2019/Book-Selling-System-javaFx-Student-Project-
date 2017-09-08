package Test;

import constants.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by elie on 17-5-9.
 */
public  class  Main  extends Application{

    static Parent box = null;
    static Scene scene;
    

    @Override
    public void start(Stage stage){


        try {

             box = (VBox)FXMLLoader.load(getClass().getResource(Constants.LOGIN));
        }catch (Exception e){
            e.printStackTrace();
        }


        scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args){Application.launch(args);}


}
