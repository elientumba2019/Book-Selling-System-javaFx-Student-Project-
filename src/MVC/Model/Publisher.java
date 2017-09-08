package MVC.Model;


/**
 * each must have a publishing house that is why we model this publisher class
 * that contains three private attributes constructors , getters and setters methods
 */

import java.io.Serializable;

/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class Publisher implements Serializable{

    private String name;
    private String contact;
    private String telephoneNumber;
    private String description;



    public Publisher(){}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;

        Publisher publisher = (Publisher) o;

        return getName().equals(publisher.getName());
    }



    @Override
    public int hashCode() {
        return getName().hashCode();
    }




    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }




    public Publisher(String name , String contact , String telephoneNumber , String description){
        this.name = name;
        this.contact = contact;
        this.telephoneNumber = telephoneNumber;
        this.description = description;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
