
/**
 * notification class designed to display pop up notification to the user
 */


/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */

package MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.Serializable;

/**
 * Created by elie on 17-5-21.
 */
public class NotificationWindow extends VBox  implements Serializable{





    Label warning = new Label("");
    Button ok = new Button("Ok");





    public NotificationWindow( String  text){



        super.setAlignment(Pos.CENTER);
        super.setSpacing(20);
        super.getChildren().addAll(warning , ok);
        super.setPadding(new Insets(15));
        warning.setTextFill(Color.RED);
        warning.setFont(Font.font("Times new Roman" , FontWeight.BOLD , FontPosture.ITALIC , 18));
        warning.setText(text);

        ok.setPrefSize(80 , 40);
        ok.setTextFill(Color.BLACK);
        ok.setFont(Font.font("Times new Roman" , FontWeight.BOLD , FontPosture.REGULAR , 15));


    }


    public void setWarning(String text){
        this.warning.setText(text);
    }




    public Button getButton(){
        return ok;
    }





    public static void displayNotification(String notification){


        Stage stage = new Stage();
        NotificationWindow notificationWindow = new NotificationWindow(notification);
        Button button = notificationWindow.getButton();

            /*
            when clicked the button will close the window
             */
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });



        //displaying the button on stage
        stage.setScene(new Scene(notificationWindow));
        stage.show();
    }
}
