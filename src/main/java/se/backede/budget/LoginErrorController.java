/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.EqualsAndHashCode;

/**
 *
 * @author joaki
 */
@EqualsAndHashCode
public class LoginErrorController {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    private void login() throws IOException {
        System.out.println("LOGIN!");
        System.out.println(username.getText());
        System.out.println(password.getText());
    }

}
