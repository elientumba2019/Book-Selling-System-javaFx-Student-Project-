/**
 * controls and provides methods for the register layout
 */




/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */


package MVC.Controller;

import Data_Access_Object.DAO_implementations.UserDaoImplementation;
import Data_Access_Object.DAO_interfaces.UserDataAccessObject;
import MVC.Model.UserRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by elie on 17-5-18.
 */
public class RegisterController {

    @FXML
    private VBox mainBox;

    @FXML // username
    private TextField usernameField;


    @FXML // first name
    private TextField firstName;


    @FXML //last name
    private TextField lastName;

    @FXML // password1
    private TextField passwordField1;

    @FXML //password 2
    private TextField passwordField2;







    public RegisterController(){
    }






    /*
    registers a new user into the system
     */
    @FXML
    public void onClickRegister(ActionEvent e){

        UserDataAccessObject userObject = new UserDaoImplementation();

        String first = firstName.getText();
        String last = lastName.getText();
        String userName = usernameField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        //create a user object
        UserRegister newUser = new UserRegister(first , last , userName , password1 , password2);



        if(userObject.registerUser(newUser)){
            NotificationWindow.displayNotification("Congratulations Dear " + newUser.getFirstName() +"\n" +
                    " You can now Login !");

            Stage stage = (Stage)mainBox.getScene().getWindow();
            stage.close();
        }
        else {

            NotificationWindow.displayNotification("Error were found in the data that you did input.\n"
            + " Read the guide concerning user registration !\n" +
            "For further information please contact the Administrator");
        }

        System.out.println("Register button clicked");
    }
    
    



    
    /*
    clears all the fields input
     */
    public void  clear(){
        
        firstName.setText("");
        lastName.setText("");
        usernameField.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }






    /*
    displayes the menu containing the guide
     */
    @FXML
    public void onClickDisplayGuide(ActionEvent e){
        NotificationWindow.displayNotification("     General information  \n" +
        "Username : not less than and 6 cannot be empty \n" +
        "First name and last name : cannot be empty \n" +
        "The 2 password's length must be greater than 6 and they must be the same !");
    }

}
