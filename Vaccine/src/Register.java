import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame {

    JTextField username, rd, fname, lname;
    JPasswordField password;

    JButton registerButton;
    JLabel title, usernameLabel, passwordLabel, positionLabel, rdLabel, fnameLabel, lnameLabel;

    JPanel usernamePanel, passwordPanel, rdPanel, fnamePanel, lnamePanel, positionPanel;

    JRadioButton emch, suvilagch, irgen;

    ButtonGroup positionGroup;

    String position = null;

    public Register() {
        createGUI();

    }

    public void createGUI() {

        setLayout(new GridLayout(7, 1, 10, 10));
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle("Бүртгүүлэх");

        title = new JLabel("Бүртгүүлэх", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 20));

        username = new JTextField(30);
        password = new JPasswordField(30);
        rd = new JTextField(30);
        fname = new JTextField(30);
        lname = new JTextField(30);

        usernameLabel = new JLabel("Нэвтрэх нэр: ");
        passwordLabel = new JLabel("Нууц үг: ");
        rdLabel = new JLabel("Регистрийн дугаар: ");
        fnameLabel = new JLabel("Өөрийн нэр: ");
        lnameLabel = new JLabel("Овог: ");

        emch = new JRadioButton("Эмч");
        suvilagch = new JRadioButton("Сувилагч");
        irgen = new JRadioButton("Иргэн");
        positionGroup = new ButtonGroup();
        positionGroup.add(emch);
        positionGroup.add(suvilagch);
        positionGroup.add(irgen);
        positionLabel = new JLabel("Эрх: ");

        usernamePanel = new JPanel(new GridLayout(1, 2));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);

        passwordPanel = new JPanel(new GridLayout(1, 2));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);

        rdPanel = new JPanel(new GridLayout(1, 2));
        rdPanel.add(rdLabel);
        rdPanel.add(rd);

        positionPanel = new JPanel(new GridLayout(1, 2));
        positionPanel.add(positionLabel);
        positionPanel.add(emch);
        positionPanel.add(suvilagch);
        positionPanel.add(irgen);

        fnamePanel = new JPanel(new GridLayout(1, 2));
        fnamePanel.add(fnameLabel);
        fnamePanel.add(fname);

        lnamePanel = new JPanel(new GridLayout(1, 2));
        lnamePanel.add(lnameLabel);
        lnamePanel.add(lname);


        emch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                position = "emch";
            }
        });
        suvilagch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                position = "suvilagch";
            }
        });
        irgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                position = "irgen";
            }
        });


        registerButton = new JButton("Бүртгүүлэх");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine","root","goat1218");

                    String sql = "insert into users values (?, ?, ?, ?, ?, ?)";


                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, rd.getText());
                    pst.setString(2, username.getText());
                    pst.setString(3, password.getText());
                    pst.setString(4, fname.getText());
                    pst.setString(5, lname.getText());
                    pst.setString(6, position);


                    int addedRows = pst.executeUpdate();
                    if(addedRows > 0) {
                        JFrame successFrame = new JFrame("Амжилттай!");
                        successFrame.setSize(100, 100);
                        successFrame.setLocationRelativeTo(null);
                        JLabel successLabel = new JLabel("Амжилттай!");
                        successFrame.add(successLabel);
                        successFrame.setVisible(true);
                    }


                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(usernamePanel);
        add(passwordPanel);
        add(fnamePanel);
        add(lnamePanel);
        add(rdPanel);
        add(positionPanel);
        add(registerButton);



    }

}
