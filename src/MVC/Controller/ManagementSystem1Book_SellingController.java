
/**
 * the book seling controllers serves as a controller for the book selling system
 * it has methods for retrieval search deletion and increment
 * it implements the menu handler interface for ensuring transition between layouts
 */



/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */

package MVC.Controller;

import Data_Access_Object.DAO_implementations.BookDaoImplementation;
import Data_Access_Object.DAO_implementations.SellingDaoImplementation;
import Data_Access_Object.DAO_implementations.StorageDaoImplementation;
import Data_Access_Object.DAO_interfaces.BookDataAccessObject;
import Data_Access_Object.DAO_interfaces.SellingDataAccessObject;
import Data_Access_Object.DAO_interfaces.StorageDataAccessObject;
import MVC.Model.*;
import constants.Constants;
import constants.MenuHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by elie on 17-5-9.
 */
public class ManagementSystem1Book_SellingController implements MenuHandler , Initializable , Serializable{

    @FXML
    private VBox mainBox;

    @FXML
    private MenuBar menuBar;

    @FXML // the search text field
    private TextField searchTextField;

    @FXML //content table view first table
    private TableView<Purchased> contentTable;

    @FXML // total price field below the table
    private TextField totalPriceField;

    @FXML // transaction date field
    private TextField transactionDateField;

    @FXML // total number Field
    private TextField totalNumberField;

    @FXML // second table
    private TableView<BookStorage2> bookTable;

    @FXML // book combo box
    private ComboBox<String> bookComboBox;

    @FXML // book number near combo box
    private TextField quantityField;

    @FXML //label price
    private Label priceSingleChangingLabel;

    @FXML // in stock label
    private Label inStockChangingLabel;

    private Parent root = null;
    private Stage stage = null;






    public ManagementSystem1Book_SellingController(){

    }





    /*
    initialization
     */
    @Override
    public void initialize(URL url , ResourceBundle bundle){
        configureTable();
        setOnItemSelected();
        setOnComboSelected();
        StorageDataAccessObject object = new StorageDaoImplementation();


        //gets book from storage
        List<BookStorage> list = object.getBooksInStorage();


        if(list.size() != 0){

            //adds book names to the combo box
            for(int c = 0 ; c < list.size() ; c++){
                bookComboBox.getItems().add(list.get(c).getName());
            }
            bookComboBox.setValue(list.get(0).getName());
        }
        else {
            bookComboBox.setValue("");
        }



        //getting the number of books in stock
        BookStorage bookStorage = object.getBook(bookComboBox.getSelectionModel().getSelectedItem());

        if(bookStorage != null) {
            inStockChangingLabel.setText(bookStorage.getQuantity() + "");
        }
        else {
            inStockChangingLabel.setText("");
        }



        //getting the bookPrice
        BookDataAccessObject accessObject = new BookDaoImplementation();
        Book book = accessObject.getBookByName(bookComboBox.getSelectionModel().getSelectedItem());



    }







    /*
    looks for a book in the db given its value
     */
    @FXML
    public void onClickSearch(ActionEvent e) throws Exception{

        contentTable.getItems().clear();
        String key = searchTextField.getText();
        List<Purchased> purchasedList = null;
        SellingDataAccessObject object = new SellingDaoImplementation();

        if(key.isEmpty()){

            purchasedList = object.getAllPurchasedBooks();

            if(purchasedList.size() == 0){
                NotificationWindow.displayNotification("No books found !");
            }
            else {
                contentTable.getItems().addAll(FXCollections.observableArrayList(purchasedList));
            }


        }
        else {

            purchasedList = object.getBook(key);

            if(purchasedList.size() == 0){
                NotificationWindow.displayNotification("No books found ! ");
            }
            else {
                contentTable.getItems().addAll(FXCollections.observableArrayList(purchasedList));
            }
        }



        System.out.println("Search button Clicked");
    }








    /*
    adds book to the selling system
     */
    @FXML
    public void onClickAdd(ActionEvent e){


        SellingDataAccessObject object = new SellingDaoImplementation();
        Purchased book = new Purchased();
        String name = bookComboBox.getSelectionModel().getSelectedItem();
        String quantity = quantityField.getText();
        String price = priceSingleChangingLabel.getText();
        int inStock = Integer.parseInt(inStockChangingLabel.getText());


        book.setBookName(name);
        book.setQuantity(Integer.parseInt(quantity));
        book.setPrice(price);


        if(Integer.parseInt(quantity) > inStock){
            NotificationWindow.displayNotification("Transaction impossible the quantity in Stock \n"+
            "is less that the number that you have input !");
        }

        else {

            if(contains(book)){
                NotificationWindow.displayNotification("The book already exist in the database !!! \n" +
                "Content succesfully updated");

                //update number in stock
                Purchased oldBook = object.getBookByName(name);
                book.setQuantity(book.getQuantity() + oldBook.getQuantity());

                //System.out.println(book.toString());

                //deleting the old book
                object.deleteBook(oldBook.getBookName());

                //adding the updated version

                object.addBook(book);

                //updates the quantity back in storage
                updateQuantityInStorage( Integer.parseInt(quantity) , name);


            }
            else {

                object.addBook(book);
                updateQuantityInStorage(Integer.parseInt(quantity) , name);
                NotificationWindow.displayNotification("Congratulations !!!");
            }
        }

        System.out.println("Add button Clicked");
    }






    /*
    deletes a book from the database
     */
    @FXML
    public void onClickDelete(ActionEvent e) throws Exception{

        Purchased book = contentTable.getSelectionModel().getSelectedItem();
        new SellingDaoImplementation().deleteBook(book.getBookName());

        contentTable.getItems().clear();
        onClickSearch(new ActionEvent());
        System.out.println("Delete button Clicked");
    }






/*
to be coded later
 */
    @FXML
    public void onClickSubmit(ActionEvent e){
        System.out.println("Submit button Clicked");
    }






    /*
    clears all input fields
     */
    @FXML
    public void onClickClear(ActionEvent e){
        searchTextField.setText("");
        transactionDateField.setText("");
        totalNumberField.setText("");
        totalPriceField.setText("");
        quantityField.setText("");

        contentTable.getItems().clear();
        bookTable.getItems().clear();

        System.out.println("Clear button Clicked");
    }





/*
changes menu layout to another selected menu
 */
    @FXML
    public void processSystemMenu(ActionEvent e) throws Exception{
        MenuItem item = (MenuItem)e.getSource();

        switch (item.getText()){
            case "Book Selling Management" :
                 System.out.println("Book management clicked");
                changeScene(Constants.BOOKSELLING);
                break;

            case "Storage Management" :
                System.out.println("Storage management clciked");
                changeScene(Constants.STORAGE_MANAGEMENT);
                break;

            case "Book Management" :
                System.out.println("Book management clicked");
                changeScene(Constants.BOOK_MANAGEMENT);
                break;

            case "Category Management" :
                System.out.println("Category management clicked");
                changeScene(Constants.CATEGORY_MANAGEMENT);
                break;

            case "Publisher Management" :
                System.out.println("Publisher management clicked");
                changeScene(Constants.PUBLISHER_MANAGEMENT);
                break;
        }
    }






    /*
    changes the scene
     */
    @Override
    public void changeScene(String layoutConstant) throws Exception{

        Stage stage = (Stage)mainBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(layoutConstant));
        stage.setScene(new Scene(root));
    }








    /*
    configuration of the table
     */
    public void configureTable(){

        ObservableList<TableColumn<Purchased  , ? >> list = contentTable.getColumns();
        TableColumn[] columns = new TableColumn[list.size()];

        for(int c = 0 ; c < columns.length ; c++){
            columns[c] = list.get(c);
        }

        columns[0].setCellValueFactory(new PropertyValueFactory<Purchased , String>("bookName"));
        columns[1].setCellValueFactory(new PropertyValueFactory<Purchased , String>("price"));
        columns[2].setCellValueFactory(new PropertyValueFactory<Purchased , String>("date"));
        columns[3].setCellValueFactory(new PropertyValueFactory<Purchased , Integer>("quantity"));

        /*
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         */

        ObservableList<TableColumn<BookStorage2 , ?>> tables = bookTable.getColumns();
        TableColumn[] cols = new TableColumn[tables.size()];

        for(int c = 0 ; c < tables.size() ; c++){
            cols[c] = tables.get(c);
        }

        cols[0].setCellValueFactory(new PropertyValueFactory<BookStorage2 , String>("name"));
        cols[1].setCellValueFactory(new PropertyValueFactory<BookStorage2 , String>("price"));
        cols[2].setCellValueFactory(new PropertyValueFactory<BookStorage2 , String>("quantity"));





    }






    /*
    event for clicked item
     */

    public void setOnItemSelected(){

        contentTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Purchased book = contentTable.getSelectionModel().getSelectedItem();

                //create a book instance of BookStorage2
                BookStorage2 storage2 = new BookStorage2();
                storage2.setName(book.getBookName());
                storage2.setPrice(book.getPrice());
                storage2.setQuantity(book.getQuantity()+"");

                /*
                adds the book to the tables
                 */
                bookTable.getItems().clear();
                bookTable.getItems().add(storage2);

                //set the total price , transaction date , and the total number
                totalPriceField.setText( (Integer.parseInt(book.getPrice()) * book.getQuantity()) + "");
                transactionDateField.setText(book.getDate());
                totalNumberField.setText(book.getQuantity() + "");

            }
        });

    }






    /*
    defines the action after selecting a combo box
     */

    public void  setOnComboSelected(){
        bookComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //defines data access objects
                BookDataAccessObject object = new BookDaoImplementation();
                StorageDataAccessObject storageObject = new StorageDaoImplementation();


                String element = bookComboBox.getSelectionModel().getSelectedItem();
                Book book = object.getBookByName(element);

                //setPrice
                priceSingleChangingLabel.setText(book.getPrice());

                //get book quantity from storage
                BookStorage bookStorage = storageObject.getBook(element);
                 //set quantity
                inStockChangingLabel.setText(bookStorage.getQuantity()+"");
            }
        });
    }






    /*
    checks if a book is already in the database
     */

    public boolean contains(Purchased book){

        Purchased book2 = new SellingDaoImplementation().getBookByName(book.getBookName());

        if(book2 == null){
            return false;
        }

        return book.equals(book2);
    }






    /*
    logs the user out
     */

    public void onClickLogOut (ActionEvent e) throws Exception{
        Stage stage = (Stage)mainBox.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(Constants.LOGIN))));
    }






    /*
    when a book is addes to the purchased table the quantity will be
    changed back in the storage
     */
    public void updateQuantityInStorage( int minus , String bookName){

        StorageDataAccessObject accessObject = new StorageDaoImplementation();

        List<BookStorage> bookStorages = accessObject.getBooksInStorage();
        BookStorage book = null;

        for(int c = 0 ; c < bookStorages.size() ; c++){

            book = bookStorages.get(c);

            if(book.getName().equals(bookName)){

                book.setQuantity(book.getQuantity() - minus);

                accessObject.deleteBookFromStorage(bookName);
                accessObject.addBookToStorage(book);

            }
        }


    }

}
