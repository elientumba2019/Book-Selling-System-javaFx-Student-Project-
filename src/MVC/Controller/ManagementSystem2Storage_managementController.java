/**
 * the storage controller controls event clikc input
 * searches and many others  user actions performed on the storage layout
 */


/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
package MVC.Controller;
import Data_Access_Object.DAO_implementations.BookDaoImplementation;
import Data_Access_Object.DAO_implementations.StorageDaoImplementation;
import Data_Access_Object.DAO_interfaces.BookDataAccessObject;
import Data_Access_Object.DAO_interfaces.StorageDataAccessObject;
import MVC.Model.Book;
import MVC.Model.BookStorage;
import MVC.Model.BookStorage2;
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
public class ManagementSystem2Storage_managementController implements MenuHandler , Initializable , Serializable{

    @FXML
    private VBox mainBox;

    @FXML // search field
    private TextField searchTextField;

    @FXML // middle table view
    private TableView<BookStorage> middleTable;

    @FXML
    private TableView<BookStorage2> bookTable;

    @FXML // add in storage field
    private TextField datePutInStorage2;

    @FXML //
    private TextField totalQuantity;


    @FXML // book combo box
    private ComboBox<String> bookComboBox;

    @FXML // quantity
    private TextField quantityField;

    @FXML // actual quantity
    private Label actualQuantityChanginLabel;


    private Parent root = null;
    private Stage stage = null;
    Book b = null;






    /*
    initializes what need to be initialized
     */
    public void initialize(URL url , ResourceBundle bundle){

        /*
        fetches a book from the DB
         */
        BookDataAccessObject dao = new BookDaoImplementation();
        StorageDataAccessObject object = new StorageDaoImplementation();
        List<Book> books = dao.getAllBooks();

        /*
        copies the content from the list to array
         */
        String[] array = new String[books.size()];
        for(int c = 0 ; c < array.length ; c++){
            array[c] = books.get(c).getName();
        }

        /*
        initializes the combo box
         */
        ObservableList<String> bookNames = FXCollections.observableArrayList(array);
        bookComboBox.getItems().addAll(bookNames);
        bookComboBox.setValue(bookNames.get(0));


        /*
        initializes quantity
         */
         //b = dao.getBookByName(bookComboBox.getSelectionModel().getSelectedItem());
        //actualQuantityChanginLabel.setText(b.getInStock()+"");


        bookComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //b = dao.getBookByName(bookComboBox.getSelectionModel().getSelectedItem());
                //actualQuantityChanginLabel.setText(b.getInStock()+"");

                BookStorage bookStorage = object.getBook(bookComboBox.getSelectionModel().getSelectedItem());

                try{

                    if(contains(bookStorage)){
                        actualQuantityChanginLabel.setText(bookStorage.getQuantity()+"");
                    }



                }catch (NullPointerException e){
                    actualQuantityChanginLabel.setText(0 +"");
                }

            }
        });

        setOnSelectedItem();

    }






    /*
    searches for a book in storage given its name
     */
    @FXML
    public void onClickSearch(ActionEvent e){

        //sets the confiration for the table
        configureTable();
        StorageDataAccessObject object = new StorageDaoImplementation();
        List<BookStorage> list = null;
        BookStorage book = null;

        String key = searchTextField.getText();


        //when the search field is empty
        if(key.isEmpty()){

            //retrieve all books form the database
            list = object.getBooksInStorage();

            if(list == null){
                NotificationWindow.displayNotification("No data found in the database");
            }
            else {
                middleTable.getItems().clear();
                middleTable.setItems(FXCollections.observableArrayList(list));
            }

        }
        else {

            //in case there in an input in the field
            book = object.getBookFromStorage(key);

            if(book == null){
                NotificationWindow.displayNotification("The book was not found !");
                middleTable.getItems().clear();
            }
            else {
                middleTable.getItems().clear();
                middleTable.getItems().addAll(FXCollections.observableArrayList(new BookStorage[]{book}));
            }


        }
        System.out.println("Search button Clicked");
    }









    /*
    adds anew book to the Storage
     */
    @FXML
    public void onClickAdd(ActionEvent e){

        StorageDataAccessObject object = new StorageDaoImplementation();
        BookStorage bookStorage = new BookStorage();

        String name = bookComboBox.getSelectionModel().getSelectedItem();
        String quantity = quantityField.getText();


        bookStorage.setName(name);
        bookStorage.setQuantity(Integer.parseInt(quantity));


        if(contains(bookStorage)){
            NotificationWindow.displayNotification("This book already exist in the database ! \n" +
            "the number has been updated");

            //gets the old book from the database
            BookStorage oldBookValue = object.getBook(name);

            BookStorage newBook = new BookStorage();
            newBook.setName(name);

            //temp variable
            int q = oldBookValue.getQuantity() + Integer.parseInt(quantity);

            newBook.setQuantity(q);


            //deleting old book
            object.deleteBookFromStorage(oldBookValue.getName());

            //adding the update
            object.addBookToStorage(newBook);
            onClickSearch(new ActionEvent());


            System.out.println(oldBookValue.toString());
        }
        else {

            object.addBookToStorage(bookStorage);
            NotificationWindow.displayNotification("Book added to storage !");
        }
        clear();

        System.out.println("Add button Clicked");
    }






    /*
    deletes a book from the storage
     */
    @FXML
    public void onClickDelete(ActionEvent e){
        System.out.println("Delete button Clicked");

        BookStorage book = middleTable.getSelectionModel().getSelectedItem();
        StorageDataAccessObject object = new StorageDaoImplementation();

        object.deleteBookFromStorage(book.getName());
        onClickSearch(new ActionEvent());
        NotificationWindow.displayNotification("Book deleted");

    }




    //to be coded later
    @FXML
    public void onClickAddInStorage(ActionEvent e){
        System.out.println("Add in storage Button Clicked !");
    }






    //clears all the fields
    @FXML
    public void onClickClear(ActionEvent e){
        clear();
        System.out.println("Clear button clicked");
    }





    /*
    changes scenes
     */
    @FXML
    public void processSystemMenu(ActionEvent e) throws Exception{
        MenuItem item2 = (MenuItem)e.getSource();

        String s = item2.getText();
        if (s.equals("Book Selling Management")) {
            System.out.println("Book management clicked");
            changeScene(Constants.BOOKSELLING);

        } else if (s.equals("Storage Management")) {
            System.out.println("Storage management clicked");
            changeScene(Constants.STORAGE_MANAGEMENT);

        } else if (s.equals("Book Management")) {
            System.out.println("Book management clicked");
            changeScene(Constants.BOOK_MANAGEMENT);

        } else if (s.equals("Category Management")) {
            System.out.println("Category management clicked");
            changeScene(Constants.CATEGORY_MANAGEMENT);

        } else if (s.equals("Publisher Management")) {
            System.out.println("Publisher management clicked");
            changeScene(Constants.PUBLISHER_MANAGEMENT);

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
    configures the tables
     */
    public void configureTable(){

        ObservableList<TableColumn<BookStorage , ?>> list = middleTable.getColumns();
        TableColumn[] tableColumns = new TableColumn[list.size()];

        for(int c = 0 ; c < list.size() ; c++){
            tableColumns[c] = list.get(c);
        }

        tableColumns[0].setCellValueFactory(new PropertyValueFactory<BookStorage , String>("name"));
        tableColumns[1].setCellValueFactory(new PropertyValueFactory<BookStorage , String>("date"));
        tableColumns[2].setCellValueFactory(new PropertyValueFactory<BookStorage , Integer>("quantity"));


        ObservableList<TableColumn<BookStorage2 , ?>> list2 = bookTable.getColumns();
        TableColumn[] tableColumns2 = new TableColumn[list.size()];


        for(int c = 0 ; c < list.size() ; c++){
            tableColumns2[c] = list2.get(c);
        }

        tableColumns2[0].setCellValueFactory(new PropertyValueFactory<BookStorage2 , String>("name"));
        tableColumns2[1].setCellValueFactory(new PropertyValueFactory<BookStorage2 , String>("price"));
        tableColumns2[2].setCellValueFactory(new PropertyValueFactory<BookStorage2 , String>("quantity"));
    }







    /*
    check whether a book is contained in the DB
     */

    public boolean contains(BookStorage bookStorage){

        BookStorage book;
        StorageDataAccessObject object = new StorageDaoImplementation();
        book = object.getBook(bookStorage.getName());

        if(book == null){
            return  false;
        }

       return  true;
    }











    /*
    clears all the fields
     */
    public void clear(){
        quantityField.setText("");
    }






    /*
    display the details of a selected item
     */
    public void setOnSelectedItem(){

        middleTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                BookStorage book = middleTable.getSelectionModel().getSelectedItem();
                datePutInStorage2.setText(book.getDate());
                totalQuantity.setText(book.getQuantity()+"");

                //get a book from the DB
                BookDataAccessObject bo = new BookDaoImplementation();
                Book bookObject = bo.getBookByName(book.getName());

                //sets the fields
                BookStorage2 nextBook = new BookStorage2();
                nextBook.setName(book.getName());
                nextBook.setPrice(bookObject.getPrice());
                nextBook.setQuantity(book.getQuantity()+"");

                bookTable.getItems().clear();
                bookTable.getItems().addAll(FXCollections.observableArrayList(new BookStorage2[]{nextBook}));
            }
        });
    }




    /*
    log out
     */
    public void onClickLogOut (ActionEvent e) throws Exception{
        Stage stage = (Stage)mainBox.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(Constants.LOGIN))));
    }
}
