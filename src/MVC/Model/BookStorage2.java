package MVC.Model;

/**
 * the name book storage two refers to the second table in the layout
 * that table has fewer properties compared to the one above
 * for more details see the Storage management fxml file
 */


import java.io.Serializable;

/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class BookStorage2 implements Serializable{

    private String name;
    private String price;
    private String quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookStorage2)) return false;

        BookStorage2 that = (BookStorage2) o;

        if (!getName().equals(that.getName())) return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) return false;
        return getQuantity() != null ? getQuantity().equals(that.getQuantity()) : that.getQuantity() == null;
    }




    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        return result;
    }
}

