package MVC.Model;

import java.io.Serializable;

/**
 * THE storage class is named this way because it specially desgined
 * to model a book that is to be stored and matches the table that will display it
 * all getters and setters methods are provided
 */


/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class BookStorage implements Serializable {

    private String name;
    private String date;
    private int quantity;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookStorage)) return false;

        BookStorage that = (BookStorage) o;

        if (getQuantity() != that.getQuantity()) return false;
        if (!getName().equals(that.getName())) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }




    @Override
    public String toString() {
        return "BookStorage{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                '}';
    }



    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + getQuantity();
        return result;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
