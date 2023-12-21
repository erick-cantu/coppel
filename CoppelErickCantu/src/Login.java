import db.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Login extends JFrame{
    private JPanel panelMain;
    private JTextField textFieldUser;
    private JButton logIn;
    private JPasswordField passwordField;

    public Login(){

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateUserAndPass();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    validateUserAndPass();
                }
            }
        });
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setContentPane(login.panelMain);
        login.setVisible(true);
        //login.setLocationRelativeTo(null);
        login.setSize(400,200);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void showMainWindows(){
        Main main = new Main();
        main.setContentPane(main.getTabbedPane1());
        main.setVisible(true);
        //main.setLocationRelativeTo(null);
        main.setSize(700,450);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void validateUserAndPass(){
        Database db = new Database();
        int result = 0;
        try {
            result = db.validateUser(textFieldUser.getText(), passwordField.getText());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if(result == 1){
            showMainWindows();
        }else{
            JOptionPane.showMessageDialog(null,"Usuario no existe","WARNING_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }

}



