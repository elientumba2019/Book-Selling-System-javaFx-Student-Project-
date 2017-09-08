package Data_Access_Object.DAO_interfaces;

import MVC.Model.UserLogin;
import MVC.Model.UserRegister;

/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public interface UserDataAccessObject {

    /*
    checks for a user existance
     */
    boolean isUser(UserLogin login);


    /*
    registers a new user
     */
    boolean registerUser(UserRegister user);


    /*
    gets a user from the db
     */

    UserRegister getUser(String username);
}
