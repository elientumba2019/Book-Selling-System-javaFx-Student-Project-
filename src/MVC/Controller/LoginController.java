package MVC.Controller;

import Data_Access_Object.DAO_implementations.UserDaoImplementation;
import Data_Access_Object.DAO_interfaces.UserDataAccessObject;
import MVC.Model.UserLogin;
import MVC.Model.UserRegister;
import constants.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.tomcat.util.bcel.Const;

import javax.swing.plaf.nimbus.State;
import java.io.Serializable;

/**
 * the login controller is the very first controller
 * in ou system and one of the most important its main purpose is to
 * ensure that a user safely logs into the system
 * in case the user is not registered a friendly registration is presented
 */



/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class LoginController implements Serializable{


    @FXML //main box
    private VBox mainBox;

    @FXML
    private TextField accountInput;

    @FXML
    private PasswordField passwordInput;




    public LoginController(){
    }






    /*
    logs a user into the system
     */
    @FXML
    public void onClickLogin(ActionEvent event) throws Exception{

        UserDataAccessObject userData = new UserDaoImplementation();

        String username = accountInput.getText();
        String password = passwordInput.getText();

        UserLogin user = new UserLogin(username , password);


        /*
        validating user information
         */
        if(userData.isUser(user)){

            UserRegister userRegister = userData.getUser(user.getUsername());

            if((user.getUsername().equals(userRegister.getUsername())) &&
                    (user.getPassword().equals(userRegister.getPassword()))){

                Stage stage = (Stage)mainBox.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(Constants.BOOKSELLING))));
                NotificationWindow.displayNotification("Welcome dear " + user.getUsername() + "!");
            }
            else {
                NotificationWindow.displayNotification("Username or password not Correct !");
            }

        }
        else {
            NotificationWindow.displayNotification("This user does not exist \n" +
            "Please register !");
        }

        System.out.println("Login button Clicked");

    }






    /*
    when clicked send the user to the registration page
     */
    @FXML
    public void onClickSendToRegistrationWindow(ActionEvent e) throws Exception{

        Stage stage  = new Stage();//= (Stage)mainBox.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(Constants.REGISTER)));
        stage.setScene(scene);
        stage.show();

    }




}








