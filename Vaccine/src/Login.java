import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    JTextField username, password;
    JButton loginButton, registerButton;
    JLabel title, status;

    public Login() {
        createGUI();

    }

    public void createGUI() {

        setLayout(new GridLayout(6, 1, 10, 10));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Вакцины систем");

        title = new JLabel("Вакцины системд тавтай морилно уу", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 20));
        status = new JLabel("Нэвтрэх нэр, нууц үгээ оруулна уу.", SwingConstants.CENTER);

        username = new JTextField(30);
        password = new JTextField(20);


        loginButton = new JButton("Нэвтрэх");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine","root","goat1218");
                    String sql = "select * from users where username = ? and password = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, username.getText());
                    pst.setString(2, password.getText());
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        status.setText("Success!");
                    }
                    else
                        status.setText("No user found");

                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        registerButton = new JButton("Бүртгүүлэх");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register rg = new Register();
                rg.setVisible(true);
            }
        });

        add(title);
        add(status);
        add(username);
        add(password);
        add(loginButton);
        add(registerButton);

    }

    public static void main(String[] args) {
        Login window = new Login();
        window.setVisible(true);
    }

}