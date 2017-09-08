

/**
 * book controller control all kind of event trigered in the book layout as well as updates performed
 * on the book tables
 */


/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */

package MVC.Controller;

import Data_Access_Object.DAO_implementations.BookDaoImplementation;
import Data_Access_Object.DAO_implementations.CategoryDaoImplementation;
import Data_Access_Object.DAO_implementations.PublisherDAOImplementation;
import Data_Access_Object.DAO_implementations.StorageDaoImplementation;
import Data_Access_Object.DAO_interfaces.BookDataAccessObject;
import Data_Access_Object.DAO_interfaces.CategoryDataAccessObject;
import Data_Access_Object.DAO_interfaces.PublisherDataAccessObject;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by elie on 17-5-9.
 */
public class ManagementSystem3Book_managementController implements MenuHandler , Initializable , Serializable{



    @FXML
    private VBox mainBox;

    @FXML //search field
    private TextField searchTextField;

    @FXML
    private TableView<Book> detailTableView;

    @FXML //book name
    private TextField bookNameField;

    @FXML // book price
    private TextField bookPrice;

    @FXML // author field
    private TextField author;

    @FXML // category combo box
    private ComboBox<String> categoryComboBox;

    @FXML //publisher combo box
    private ComboBox<String> publishingHouseComboBox;

    @FXML // book cover
    private ImageView bookCoverImage;

    @FXML
    private TextArea bookDescriptionArea;


    private Parent root;
    private Stage stage;
    private String updateString = null;


    CategoryDataAccessObject categoryDataAccessObject = new CategoryDaoImplementation();
    PublisherDataAccessObject publisherDataAccessObject = new PublisherDAOImplementation();


    public ManagementSystem3Book_managementController(){}



    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){

        List<Category> list = categoryDataAccessObject.getAllCategories();
        List<Publisher> list1 = publisherDataAccessObject.getAllPublishers();

        String[] categoriesNames = new String[list.size()];
        String[] publisherNames = new String[list1.size()];

        for(int c = 0 ; c < list.size() ; c++){
            categoriesNames[c] = list.get(c).getName();
        }

        for(int c = 0 ; c < list1.size() ; c++){
            publisherNames[c] = list1.get(c).getName();
        }

        ObservableList<String>  categories = FXCollections.observableArrayList(categoriesNames);
        ObservableList<String>  publishers = FXCollections.observableArrayList(publisherNames);

        //initializes combo boxes
        categoryComboBox.getItems().addAll(categories);
        categoryComboBox.setValue(categories.get(0));
        publishingHouseComboBox.getItems().addAll(publishers);
        publishingHouseComboBox.setValue(publishers.get(0));

        setOnSelected();
        updateBookSStorageInformation();

    }








    /*
    searches for a book in the database
     */
    @FXML
    public void onClickSearch(ActionEvent e){

        configureTableView();
        List<Book> books = null;
        String key = searchTextField.getText();
        BookDataAccessObject search = new BookDaoImplementation();

        if(key.isEmpty()){

             books = search.getAllBooks();

            if(books == null){
                NotificationWindow.displayNotification(" Sorry No books found in the dataBase");
            }


                detailTableView.setItems(FXCollections.observableArrayList(books));

        }else {

            Book book = null;
            book = search.getBookByName(key);

            if(book == null){
                NotificationWindow.displayNotification("Sorry the book was not found in the database");
            }
            Book[] array = {book};

            detailTableView.setItems(FXCollections.observableArrayList(array));
        }

    }







    /*
    chooses a picture for the book cover
     */
    @FXML
    public void onClickChooseFile(ActionEvent e){

        FileChooser chooser = new FileChooser();

        File file = chooser.showOpenDialog((Stage)mainBox.getScene().getWindow());
        bookCoverImage.setImage(new Image("file:" +file.getAbsolutePath()));
        System.out.println("Choose file button Clicked");
    }








    /*
    saves a book into the database
     */
    @FXML
    public void onClickSave(ActionEvent e){
        System.out.println("Save Button Clicked");

        String name = bookNameField.getText();
        String description = bookDescriptionArea.getText();
        String authorName  =  author.getText();
        String category = categoryComboBox.getSelectionModel().getSelectedItem();
        String publisher = publishingHouseComboBox.getSelectionModel().getSelectedItem();
        String price = bookPrice.getText();

        //get stock information
        int inStock = 0;
        StorageDataAccessObject storageDAo = new StorageDaoImplementation();
        BookStorage bookInStorage = storageDAo.getBook(name);

        if(bookInStorage != null){
            inStock = bookInStorage.getQuantity();
        }

        Book book = new Book(name , description , authorName , category, publisher , inStock, price );


        if(contains(book)){
            NotificationWindow.displayNotification(book.getName() + " already exist in the database !!!");
        }
        else {

            BookDataAccessObject save = new BookDaoImplementation();
            save.addBook(book);
            NotificationWindow.displayNotification("Congratulations ! new Book added to the database");
        }
        clear();
    }







    /*
    clears all the input fields
     */
    @FXML
    public void onClickClear(ActionEvent e){
        System.out.println("Clear Button Clicked");
        clear();
    }








    /*
    changes the scene when a menu item is selected
     */
    @FXML
    public void processSystemMenu(ActionEvent e) throws Exception{
        MenuItem item = (MenuItem)e.getSource();

        String s = item.getText();
        if (s.equals("Book Selling Management")) {
            System.out.println("Book management clicked ");
            changeScene(Constants.BOOKSELLING);

        } else if (s.equals("Storage Management")) {
            System.out.println("Storage management clicked ");
            changeScene(Constants.STORAGE_MANAGEMENT);

        } else if (s.equals("Book Management")) {
            System.out.println("Book management clicked ");
            changeScene(Constants.BOOK_MANAGEMENT);

        } else if (s.equals("Category Management")) {
            System.out.println("Category management clicked");
            changeScene(Constants.CATEGORY_MANAGEMENT);

        } else if (s.equals("Publisher Management")) {
            System.out.println("Publisher management clicked ");
            changeScene(Constants.PUBLISHER_MANAGEMENT);

        }
    }







    /*
    update an existing book
     */
    public void onClickUpdate(ActionEvent e){

        /*
        gets the elements contained in the fieldss
         */
        String name = bookNameField.getText();
        String description = bookDescriptionArea.getText();
        String authorName  =  author.getText();
        String category = categoryComboBox.getSelectionModel().getSelectedItem();
        String publisher = publishingHouseComboBox.getSelectionModel().getSelectedItem();
        String price = bookPrice.getText();

        // update string is set to the previous name of the book
        String key = updateString;

        Book book = new Book(name , description , authorName , category, publisher , 1 , price );
        BookDataAccessObject object = new BookDaoImplementation();
        object.update(key , book);
        detailTableView.getItems().clear();
        onClickSearch(new ActionEvent());
        clear();



    }




    /*
    changes scene when different menu option is selected
     */
    @Override
    public void changeScene(String layoutConstant) throws Exception{
        Stage stage = (Stage)mainBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(layoutConstant));
        stage.setScene(new Scene(root));
    }








    /*
    set the required configuration for the table view
     */
    public void configureTableView(){

        //list of columns
        ObservableList<TableColumn<Book, ?>> columns = detailTableView.getColumns();

        TableColumn[] cols = {columns.get(0) , columns.get(1) , columns.get(2) , columns.get(3)  ,
                columns.get(4) , columns.get(5) , columns.get(6)};

        cols[0].setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        cols[1].setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
        cols[2].setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        cols[3].setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        cols[4].setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        cols[5].setCellValueFactory(new PropertyValueFactory<Book, String>("inStock"));
        cols[6].setCellValueFactory(new PropertyValueFactory<Book, String>("price"));
    }







    /*
    special field to be used by the function below
     */
    private String category = null;
    private String publisher = null;
    /*
    return the selected combo box values
     */
    private String[] getChosenComboBox(){

        categoryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                category = categoryComboBox.getSelectionModel().getSelectedItem();
            }
        });

        publishingHouseComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                publisher = publishingHouseComboBox.getSelectionModel().getSelectedItem();
            }
        });


        return new String[]{category , publisher};
    }






    /*
    checks whether a book exist already in the database
     */
    public boolean contains(Book b){
        BookDataAccessObject search = new BookDaoImplementation();
        Book b1 = search.getBookByName(b.getName());

        if(b1 == null){
            return  false;
        }
        return (b1.equals(b));
    }






    /*
    clears all the input field
     */
    public void clear(){
        bookNameField.setText("");
        author.setText("");
        bookPrice.setText("");
    }






    /*
    put the data back into field for a selected item
     */
    public void setOnSelected(){

        detailTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Book book = detailTableView.getSelectionModel().getSelectedItem();

                bookNameField.setText(book.getName());
                bookPrice.setText(book.getPrice());
                author.setText(book.getAuthor());


                /*
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                 */

                ObservableList<String> list = categoryComboBox.getItems();
                int index = list.indexOf(book.getCategory());
                categoryComboBox.setValue(list.get(index));


                list = publishingHouseComboBox.getItems();
                index = list.indexOf(book.getPublisher());
                publishingHouseComboBox.setValue(list.get(index));

                updateString = book.getName();

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







    /*
    get the corresponding storage information from the databse !
     */
    public void updateBookSStorageInformation(){

        BookDataAccessObject bDAO = new BookDaoImplementation();
        StorageDataAccessObject sDAO = new StorageDaoImplementation();

        //creates 2 list of books from storage and from books
        List<Book> books = bDAO.getAllBooks();
        List<BookStorage> storages = sDAO.getBooksInStorage();

        //when a book is contained in both storage and books its storage quantity is updated into books;
        for(int c = 0 ; c < storages.size() ; c++){

            Book book = bDAO.getBookByName(storages.get(c).getName());

           if(book == null){
               continue;
           }
           else {

               Book book1 = book;
               book1.setInStock(storages.get(c).getQuantity());

               bDAO.update(storages.get(c).getName() , book1);

           }
        }

    }
}


