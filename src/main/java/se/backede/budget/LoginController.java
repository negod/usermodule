/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import se.backede.budget.user.UserDao;
import se.backede.budget.user.UserEntity;

/**
 *
 * @author joaki
 */
//@ManagedBean
//@ApplicationScoped
@EqualsAndHashCode
@Slf4j
public class LoginController {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    private void login() throws IOException {

        UserEntity entity = new UserEntity();
        entity.setName(username.getText());
        entity.setPassword(password.getText());

        Optional<UserEntity> persistUser = UserDao.getInstance().persistUser(entity);

        App.setRoot("user/user");

    }

}
