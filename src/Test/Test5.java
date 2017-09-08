package Test;

import Data_Access_Object.DAO_implementations.UserDaoImplementation;
import Data_Access_Object.DAO_interfaces.UserDataAccessObject;
import MVC.Model.UserLogin;
import MVC.Model.UserRegister;

/**
 * Created by elie on 17-5-24.
 */
public class Test5 {

    public static void  main(String[] args){

        UserDataAccessObject object = new UserDaoImplementation();
        UserRegister user = new UserRegister("elie" , "nsampi" , "elientumba" , "111111" , "111111");


       /* if(object.registerUser(user)){
            System.out.println("Success !!!");
        }
        else {
            System.out.println("oh No !!!!");
        }
        */


       /*UserRegister userRegister = object.getUser("elientumba");
       System.out.println(userRegister.toString());
       */


       if(object.isUser(new UserLogin("elientumba" , "1111"))){
           System.out.println("Welcome elie");
       }
       else {
           System.out.println("this user does not exist !");
       }

    }
}
