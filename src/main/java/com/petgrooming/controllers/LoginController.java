package com.petgrooming.controllers;

import com.petgrooming.models.User;
import com.petgrooming.utils.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Hibernate logic to validate user credentials
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = (User) session.createQuery("FROM User WHERE username = :username")
                                      .setParameter("username", username)
                                      .uniqueResult();
            if (user != null && user.getPassword().equals(password)) {
                // Successful login
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Login successful");
                alert.show();
            } else {
                // Failed login
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid credentials");
                alert.show();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
