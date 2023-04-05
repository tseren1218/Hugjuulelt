import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    User user;
    JTextField username;
    JPasswordField password;
    JButton loginButton, registerButton;
    JLabel title, status;

    public Login() {
        createGUI();
    }

    public void createGUI() {

        setLayout(new GridLayout(6, 1, 10, 10));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setTitle("Вакцины систем");

        title = new JLabel("Вакцины системд тавтай морилно уу", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 20));
        status = new JLabel("Регистрийн дугаар, нууц үгээ оруулна уу.", SwingConstants.CENTER);

        username = new JTextField(30);
        password = new JPasswordField(20);


        loginButton = new JButton("Нэвтрэх");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    DatabaseConnection dbcon = DatabaseConnection.getInstance();
                    String sql = "select * from users where rd = ? and password = ?";
                    PreparedStatement pst = dbcon.getConnection().prepareStatement(sql);
                    pst.setString(1, username.getText());
                    pst.setString(2, password.getText());
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        String userData = "Select * from users where rd = ?";
                        PreparedStatement userStatement = dbcon.getConnection().prepareStatement(userData);
                        userStatement.setString(1, username.getText());
                        ResultSet result = userStatement.executeQuery();
                        result.next();

                        user = User.getInstance();
                        user.setRd(result.getString("rd"));
                        user.setFname(result.getString("fname"));
                        user.setLname( result.getString("lname"));
                        user.setPosition(result.getString("position"));
                        String res = user.getPosition().toString();

                        if(res.equals("emch") || res.equals("suvilagch")){
                            dispose();
                            new EmchModule();
                        }
                        else if(res.equals("irgen")){
                            dispose();
                            new IrgenModule();
                        }
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
