import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Logger;

public class EmchModule {

    JFrame frame;
    User user;
    private JPanel panel1;
    private JPanel avatar;
    private JPanel List;
    private JLabel label;
    private JPanel Search;
    private JTextField search;
    private JLabel JLabel;
    private JTable showTable;
    DefaultTableModel model;
    private JButton searchButton;
    private JButton personal_infoButton;
    private JButton addPatient;
    private JLabel searchLabel;
    private JLabel helpLabel;

    public EmchModule(User user) {
        this.user = user;
        createGUI();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    showinfo();
                    Logger.getLogger("======");
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        personal_infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List.removeAll();
                panel1.remove(List);
                panel1.revalidate();
                panel1.repaint();

                List.setLayout(new FlowLayout());

                JLabel lnameLabel = new JLabel("Овог: ");
                JLabel fnameLabel = new JLabel("Нэр: ");
                JLabel rdLabel = new JLabel("Регистрийн дугаар: ");
                JLabel positionLabel = new JLabel("Системд хандах эрх: ");

                JLabel lname = new JLabel(user.getLname());
                JLabel fname = new JLabel(user.getFname());
                JLabel rd = new JLabel(user.getRd());
                JLabel position = new JLabel(user.getPosition());

                List.add(lnameLabel);
                List.add(lname);
                List.add(fnameLabel);
                List.add(fname);
                List.add(rdLabel);
                List.add(rd);
                List.add(positionLabel);
                List.add(position);

                panel1.add(List, BorderLayout.CENTER);
                frame.revalidate();
            }
        });


        addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                List.removeAll();
                panel1.remove(List);
                panel1.revalidate();
                panel1.repaint();
                List.setLayout(new GridLayout(5, 1));


                JPanel burtgelPanel = new JPanel();
                burtgelPanel.setLayout(new GridLayout(8, 2));

                JLabel lnameLabel = new JLabel("Овог");
                JTextField lnameField = new JTextField(20);
                JLabel fnameLabel = new JLabel("Нэр");
                JTextField fnameField = new JTextField(20);
                JLabel rdLabel = new JLabel("Регистрийн дугаар");
                JTextField rdField = new JTextField(20);
                JLabel vaccineLabel = new JLabel("Хийлгэж буй вакцин");

                final DefaultComboBoxModel vaccineNames = new DefaultComboBoxModel();
                vaccineNames.addElement("Verocell");
                vaccineNames.addElement("Moderna");
                vaccineNames.addElement("Sputnik");

                final JComboBox vaccineCombo = new JComboBox(vaccineNames);
                vaccineCombo.setSelectedIndex(0);

                JScrollPane vaccineScroll = new JScrollPane(vaccineCombo);

                JButton burtgelButton = new JButton("Бүртгэх");

                burtgelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String vaccine = "";
                        String vaccineId = "";
                        if(vaccineCombo.getSelectedIndex() != -1) {
                            vaccine = "select id from vaccines where vaccineName = '" + (String)vaccineCombo.getItemAt(vaccineCombo.getSelectedIndex()) + "'";

                            try {

                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine","root","goat1218");
                                String sql = "insert into vaccination_history " +
                                        "set rd = ?, " + "vaccineId = ?, " + "date = now()";
                                PreparedStatement pst = con.prepareStatement(sql);
                                PreparedStatement pst2 = con.prepareStatement(vaccine);
                                ResultSet rs = pst2.executeQuery();
                                rs.next();
                                vaccineId = rs.getString("id");
                                pst.setString(1, rdField.getText());
                                pst.setString(2, vaccineId);
                                int addedRows = pst.executeUpdate();
                                if(addedRows > 0) {
                                    JFrame successFrame = new JFrame();
                                    successFrame.setLocationRelativeTo(null);
                                    successFrame.setSize(200, 200);
                                    JLabel successLabel = new JLabel("Амжилттай бүртгэлээ!");
                                    successFrame.add(successLabel);
                                    successFrame.setVisible(true);
                                }
                                else {
                                    JFrame unsuccessfulFrame = new JFrame();
                                    unsuccessfulFrame.setLocationRelativeTo(null);
                                    unsuccessfulFrame.setSize(200, 200);
                                    JLabel unsuccessfulLabel = new JLabel("Бүртгэл амжилтгүй!");
                                    unsuccessfulFrame.add(unsuccessfulLabel);
                                    unsuccessfulFrame.setVisible(true);
                                }
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });

                burtgelPanel.add(lnameLabel);
                burtgelPanel.add(lnameField);
                burtgelPanel.add(fnameLabel);
                burtgelPanel.add(fnameField);
                burtgelPanel.add(rdLabel);
                burtgelPanel.add(rdField);
                burtgelPanel.add(vaccineLabel);
                burtgelPanel.add(vaccineCombo);
                burtgelPanel.add(burtgelButton);

                List.add(burtgelPanel);
                panel1.add(List, BorderLayout.CENTER);
                frame.revalidate();
            }
        });

    }
    public void createGUI() {
        frame = new JFrame();
        frame.setSize(800, 800);
        frame.add(panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        personal_infoButton.setText("Хувийн мэдээлэл");
        searchLabel.setText("Регистрийн дугаараар хайх");
        helpLabel.setText("(Хоосон хайвал бүх илэрц гарна)");

    }
    public void showinfo() throws ClassNotFoundException, SQLException {
        List.removeAll();
        panel1.remove(List);
        panel1.revalidate();
        panel1.repaint();
        showTable = new JTable();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine","root","goat1218");
        PreparedStatement nameSt;
        String rdSQL;
        String rd = search.getText().trim();
        ResultSet rs;

        if(rd.equals("")){
            rdSQL = "select " +
                    "u.rd, u.fname, u.lname, v.vaccineName, vh.date " +
                    "from " +
                    "users u, vaccination_history vh, vaccines v " +
                    "where u.rd = vh.rd and " +
                    "vh.vaccineId = v.id ";
        }

        else
            rdSQL = "select " +
                "u.rd, u.fname, u.lname, v.vaccineName, vh.date " +
                "from " +
                "users u, vaccination_history vh, vaccines v " +
                "where u.rd = vh.rd and " +
                "vh.vaccineId = v.id " +
                "and " +
                "vh.rd = ?";

        nameSt = con.prepareStatement(rdSQL);

        if(!rd.equals(""))
            nameSt.setString(1, search.getText());

        rs = nameSt.executeQuery();

        model = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i-1] = rs.getObject(i);
            }
            model.addRow(rowData);
        }


        showTable.setModel(model);
        List.setLayout(new GridLayout(1, 1));
        List.add(showTable);
        panel1.add(List, BorderLayout.CENTER);
        frame.revalidate();
    }
}
