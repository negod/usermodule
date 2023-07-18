/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
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
    private void initialize() throws IOException {

        tableView = new TableViewWrapper<>(UserDao.getInstance(UserEntity.class));

        Optional entitites = UserDao.getInstance(UserEntity.class).getAll();

        entitites.ifPresent(data
                -> tableView.initializeTable((Set<UserEntity>) data, userTable)
        );

        tableView.initializeDeleteListener(userTable);

    }

}
