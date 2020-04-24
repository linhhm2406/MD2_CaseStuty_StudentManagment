package controller;

import view.LoginDisplay;

import javax.swing.*;
import java.awt.event.*;

public class LoginDisplayController {
    private LoginDisplay loginDisplay;

    public LoginDisplayController() {
        this.loginDisplay = new LoginDisplay();
        loginAction();
    }

    public void showLoginScreen() {
        loginDisplay.setVisible(true);
    }

    private void loginAction() {
        loginDisplay.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed();
            }
        });
    }

    public void loginButtonActionPerformed() {
        if (loginDisplay.getUserNameInput().getText().equals("")) {
            JOptionPane.showMessageDialog(loginDisplay.getPanel(), "Please input Username & Password");
        } else if (loginDisplay.getUserNameInput().getText().equals("admin") && loginDisplay.getPasswordInput().getText().equals("admin")) {
            JOptionPane.showMessageDialog(loginDisplay.getPanel(), "Login successfully as admin!");
            loginDisplay.dispose();
            new ManagementDisplayController();
        } else {
            JOptionPane.showMessageDialog(loginDisplay.getPanel(), "Wrong Password or Username!");
        }
    }
}
