/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import se.backede.budget.generics.TableViewWrapper;
import se.backede.budget.user.UserDao;
import se.backede.budget.user.UserEntity;

/**
 *
 * @author joaki
 */
public class UserController {

    @FXML
    TableView<UserEntity> userTable;

    TableViewWrapper<UserEntity> tableView;

    @FXML
    private void initialize() {

        tableView = new TableViewWrapper<>(userTable, UserDao.getInstance(UserEntity.class));
        tableView.initializeDeleteListener();

    }

}
