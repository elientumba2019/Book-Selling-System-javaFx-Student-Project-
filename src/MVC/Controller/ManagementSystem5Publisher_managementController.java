/**
 * controls event  for the publisher layout
 */



/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */

package MVC.Controller;

import Data_Access_Object.DAO_implementations.PublisherDAOImplementation;
import Data_Access_Object.DAO_interfaces.PublisherDataAccessObject;
import MVC.Model.Publisher;
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
 * Created by elie on 17-5-10.
 */
public class ManagementSystem5Publisher_managementController implements MenuHandler , Initializable , Serializable{

    @FXML
    private VBox mainBox;

    @FXML // field search
    private TextField searchTextField;

    @FXML // tableView content
    private TableView<Publisher> detailsTableView;

    @FXML //pusblisher nam
    private TextField pulishingHouseNameInputField;

    @FXML // contact person
    private TextField contactPersonInputField;

    @FXML // phone number
    private TextField telephoneNumberInputField;

    @FXML
    private TextArea descriptionAreaField;

    private Parent root = null;
    private String updateString = null;




    public ManagementSystem5Publisher_managementController(){}







    /*
    initializes
     */@Override
    public void initialize(URL url , ResourceBundle bundle){

        setonSelectedItem();
    }






    /*
    searches for publishers
     */
    @FXML
    public void onClickSearch(ActionEvent e){

        configureTablesView();
        PublisherDataAccessObject publisherDataAccessObject = new PublisherDAOImplementation();
        String key = searchTextField.getText();
        List<Publisher> publishers = null;

        if(key.isEmpty()){

            publishers = publisherDataAccessObject.getAllPublishers();

            if(publishers == null){
                NotificationWindow.displayNotification("No record found in the database !");
            }
            detailsTableView.setItems(FXCollections.observableArrayList(publishers));

        }
        else {

            Publisher pTemp = publisherDataAccessObject.getPublisher(key);

            if(pTemp == null){
                NotificationWindow.displayNotification("No record found in the database !");
            }
            detailsTableView.setItems(FXCollections.observableArrayList(new Publisher[]{pTemp}));
        }

        System.out.println("Search Button Clicked");
    }







    /*
    saves a new publisher to the data base
     */
    @FXML
    public void onClickSave(ActionEvent e){

        PublisherDataAccessObject publisherDao = new PublisherDAOImplementation();

        String name = pulishingHouseNameInputField.getText();
        String contact = contactPersonInputField.getText();
        String telephone = telephoneNumberInputField.getText();
        String description = descriptionAreaField.getText();


        /*
        when all input field are empty a notification window will bw displayed
         */
        if(name.isEmpty() || contact.isEmpty() || telephone.isEmpty() || description.isEmpty()){

            NotificationWindow.displayNotification("The database already contains this Publisher");
        }
        else {

            Publisher publisher = new Publisher(name , contact , telephone , description);

            if(contains(publisher)){
                NotificationWindow.displayNotification("The database already contains this Publisher");
            }
            else {
                publisherDao.addPublisher(publisher);
                NotificationWindow.displayNotification("New publisher added to the database !");
                clear();
            }

        }
    }








    /*
    updates a category
     */
    public void onClickUpdate(ActionEvent e){

        String name = pulishingHouseNameInputField.getText();
        String contact = contactPersonInputField.getText();
        String telephone = telephoneNumberInputField.getText();
        String description = descriptionAreaField.getText();
        String key = updateString;

        PublisherDataAccessObject object = new PublisherDAOImplementation();

        Publisher publisher = new Publisher(name , contact , telephone , description);
        object.updatePublisher(key , publisher);
        detailsTableView.getItems().clear();
        onClickSearch(new ActionEvent());
        clear();

    }







    /*
    clears all the fields
     */
    @FXML
    public void onClickClear(ActionEvent e){
        clear();
        System.out.println("Clear Button Clicked");
    }







    /*
    processes menu by changins scenes
     */
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
            System.out.println("Category management clicked");
            changeScene(Constants.CATEGORY_MANAGEMENT);

        } else if (s.equals("Publisher Management")) {
            System.out.println("Publisher  management clicked");
            changeScene(Constants.PUBLISHER_MANAGEMENT);

        }
    }







    /*
    changes the scene to another window
     */
    @Override
    public void changeScene(String layoutConstant) throws Exception{

        Stage stage = (Stage)mainBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(layoutConstant));
        stage.setScene(new Scene(root));
    }







    /*
    checks if the database contains a specific publisher
     */
    public boolean contains(Publisher p){

        PublisherDataAccessObject PDao = new PublisherDAOImplementation();
        Publisher p2 = PDao.getPublisher(p.getName());

        if(p2 == null){
            return false;
        }

        return p.equals(p2);
    }







    /*
    clears all the fields
     */
    public void clear(){

        pulishingHouseNameInputField.setText("");
        contactPersonInputField.setText("");
        telephoneNumberInputField.setText("");
        descriptionAreaField.setText("");
    }








    /*
    confire a table view to receive specific element
     */
    public void configureTablesView(){

        ObservableList<TableColumn<Publisher , ?>> columns = detailsTableView.getColumns();
        TableColumn[] tableColumns = new TableColumn[columns.size()];

        for(int c = 0 ; c < columns.size() ; c++){
            tableColumns[c] = columns.get(c);
        }

        tableColumns[0].setCellValueFactory(new PropertyValueFactory<Publisher , String>("name"));
        tableColumns[1].setCellValueFactory(new PropertyValueFactory<Publisher , String>("contact"));
        tableColumns[2].setCellValueFactory(new PropertyValueFactory<Publisher , String>("telephoneNumber"));
        tableColumns[3].setCellValueFactory(new PropertyValueFactory<Publisher , String>("description"));
    }







    /*
    defines the action for a selected item
     */

    public void setonSelectedItem(){

        detailsTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Publisher publisher = detailsTableView.getSelectionModel().getSelectedItem();
                pulishingHouseNameInputField.setText(publisher.getName());
                contactPersonInputField.setText(publisher.getContact());
                telephoneNumberInputField.setText(publisher.getTelephoneNumber());
                descriptionAreaField.setText(publisher.getDescription());

                updateString = publisher.getName();
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
