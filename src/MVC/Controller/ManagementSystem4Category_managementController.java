
/**
 * category controller control event for the category layout
 * provides methods for addin deleting and updating
 */



/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */


package MVC.Controller;

import Data_Access_Object.DAO_implementations.CategoryDaoImplementation;
import Data_Access_Object.DAO_interfaces.CategoryDataAccessObject;
import MVC.Model.Category;
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
public class ManagementSystem4Category_managementController implements MenuHandler , Initializable , Serializable{

    @FXML
    private VBox mainBox;

    @FXML // search Field
    private TextField searchTextField;

    @FXML // tableView
    private TableView<Category> contentTableView;

    @FXML
    private TextField categoryInput;

    @FXML // description area
    private TextArea descriptionArea;

    private Parent root = null;
    private Stage stage;
    String updateString = null;


    public ManagementSystem4Category_managementController(){}





    @Override
    public void  initialize(URL url , ResourceBundle resourceBundle){
        setOnSelectedItem();
    }




    /*
    searches for an item in the database
     */
    @FXML
    public void onClickSearch(ActionEvent e){

        configureTableView();
        CategoryDataAccessObject categoryDataAccessObject = new CategoryDaoImplementation();
        List<Category> list = null;
        String key = searchTextField.getText();


        if(key.isEmpty()){

            list = categoryDataAccessObject.getAllCategories();

            if(list == null){
                NotificationWindow.displayNotification("No record found in the database !");
            }

            contentTableView.setItems(FXCollections.observableArrayList(list));

        }else {

            Category cTemp = categoryDataAccessObject.getCategory(key);

            if(cTemp == null){
                NotificationWindow.displayNotification("No record found in the database !");
            }
            contentTableView.setItems(FXCollections.observableArrayList(new Category[]{cTemp}));


        }



        System.out.println("Search button Clicked");
    }





    /*
    updates a category
     */
    public void onClickUpdate(ActionEvent e){

        String name = categoryInput.getText();
        String description = descriptionArea.getText();
        Category category = new Category(name , description);

        CategoryDataAccessObject categoryDataAccessObject = new CategoryDaoImplementation();
        String key = updateString;

        CategoryDataAccessObject object = new CategoryDaoImplementation();
        object.updateCategory(key , category);
        contentTableView.getItems().clear();
        onClickSearch(new ActionEvent());
        clear();
    }




    /*
    saves a new category to the database
     */
    @FXML
    public void onClickSave(ActionEvent e){

        String name = categoryInput.getText();
        String description = descriptionArea.getText();
        CategoryDataAccessObject categoryDataAccessObject = new CategoryDaoImplementation();

        if(name.isEmpty() || description.isEmpty()){
            NotificationWindow.displayNotification("Please input all the needed information");
        }

        else {

            Category category = new Category(name , description);

            if(contains(category)){
                NotificationWindow.displayNotification("This category is already conatined in the database");
            }
            else {

                categoryDataAccessObject.addNewCategory(category);
                NotificationWindow.displayNotification("New category added to the database !");
                clear();
            }
        }

        System.out.println("Save button Clicked");
    }





    @FXML
    public void onClickClear(ActionEvent e){
        clear();
        System.out.println("Clear Button Clicked");

    }

    @FXML
    public void processSystemMenu(ActionEvent e) throws Exception{
        MenuItem item = (MenuItem)e.getSource();

        String s = item.getText();
        if (s.equals("Book Selling Management")) {
            System.out.println("Book management clicked");
            changeScene(Constants.BOOKSELLING);

        } else if (s.equals("Storage Management")) {
            System.out.println("Storage management clicked ");
            changeScene(Constants.STORAGE_MANAGEMENT);

        } else if (s.equals("Book Management")) {
            System.out.println("Book management clicked ");
            changeScene(Constants.BOOK_MANAGEMENT);

        } else if (s.equals("Category Management")) {
            System.out.println("Category management clicked ");
            changeScene(Constants.CATEGORY_MANAGEMENT);

        } else if (s.equals("Publisher Management")) {
            System.out.println("Publisher management clicked");
            changeScene(Constants.PUBLISHER_MANAGEMENT);

        }
    }


/*
switches to another windows
 */
    @Override
    public void changeScene(String layoutConstant) throws Exception{

        Stage stage = (Stage)mainBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(layoutConstant));
        stage.setScene(new Scene(root));
    }




    /*
    verifies the database for the presence of the item
     */
    public boolean contains(Category category){

        CategoryDataAccessObject categoryDataAccessObject = new CategoryDaoImplementation();
        Category c2 = categoryDataAccessObject.getCategory(category.getName());

        if(c2 == null){
            return false;
        }

        return category.equals(c2);
    }




    /*
    clears all the field
     */

    public void clear(){
        categoryInput.setText("");
        descriptionArea.setText("");
    }



    public void configureTableView(){

        ObservableList<TableColumn<Category , ?>> list = contentTableView.getColumns();
        TableColumn[] tableColumns = new TableColumn[list.size()];

        for(int c = 0 ; c < tableColumns.length ; c++){
            tableColumns[c] = list.get(c);
        }

        tableColumns[0].setCellValueFactory(new PropertyValueFactory<Category , String>("name"));
        tableColumns[1].setCellValueFactory(new PropertyValueFactory<Category , String>("description"));
    }




    /*
    action for selected item
     */
    public void  setOnSelectedItem(){

        contentTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Category category = contentTableView.getSelectionModel().getSelectedItem();
                categoryInput.setText(category.getName());
                descriptionArea.setText(category.getDescription());

                updateString = category.getName();
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
