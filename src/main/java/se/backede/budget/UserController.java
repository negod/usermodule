/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import se.backede.budget.user.UserDao;
import se.backede.budget.user.UserEntity;

/**
 *
 * @author joaki
 */
public class UserController {

    @FXML
    TableView<UserEntity> userTable;

    @FXML
    private void initialize() throws IOException {
        // Create the columns
        TableColumn<UserEntity, String> nameColumn = new TableColumn<>("name");
        TableColumn<UserEntity, String> ageColumn = new TableColumn<>("password");

        // Set up the cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Add the columns to the TableView
        userTable.getColumns().add(nameColumn);
        userTable.getColumns().add(ageColumn);
        

        Optional<Set<UserEntity>> all = UserDao.getInstance().getAll();

        // Create some sample data
        ObservableList<UserEntity> data = FXCollections.observableArrayList(all.get());

        // Set the items
        userTable.setItems(data);

        userTable.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent) -> {

            switch (keyEvent.getCode()) {
                case DELETE:
                    UserEntity selectedItem = userTable.getSelectionModel().getSelectedItem();

                    System.out.println("Deleting " + selectedItem.toString());

                    if (UserDao.getInstance().deleteUser(selectedItem).isPresent()) {
                        userTable.getItems().remove(selectedItem);
                    }

                    break;
                default:
                    throw new AssertionError();
            }

        });
    }

    @FXML
    private void deleteUser() {

    }

}
