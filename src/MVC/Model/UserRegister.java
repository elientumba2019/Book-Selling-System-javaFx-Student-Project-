package MVC.Model;



/**
 * the user register model is meant to serve as a model for users that a re about to register into
 * ths system a few informations a needed
 * and a special method call valid will serve as a validator for user informations
 */


import java.io.Serializable;

/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class UserRegister  implements Serializable{


    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String password2;



    public UserRegister(){}


    public UserRegister(String firstName , String lastName , String username ,
                        String password , String password2){

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRegister)) return false;

        UserRegister that = (UserRegister) o;

        if (!getUsername().equals(that.getUsername())) return false;
        return getPassword().equals(that.getPassword());
    }




    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }



    @Override
    public String toString() {
        return "UserRegister{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';

    }

    public boolean valid(){

        boolean flag = true;

        if(username.length() < 6 || username.isEmpty()){
            flag = false;
        }


        if(firstName.isEmpty()){
            flag = false;
        }

        if (lastName.isEmpty()){
            flag = false;
        }


        if(password.length() < 6){
            flag = false;
        }

        if(password2.length() < 6){
            flag = false;
        }



        if(!password.equals(password2)){
            flag = false;
        }

        return  flag;
    }
}
