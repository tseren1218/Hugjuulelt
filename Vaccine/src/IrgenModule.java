import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class IrgenModule {

    User user;
    JFrame frame;
    private JPanel mainPanel;
    private JPanel sidebar;
    private JLabel userPic;
    private JLabel userFullName;
    private JButton vaccinationHistoryButton;
    private JButton infoButton;
    private JLabel title;
    private JTable vaccinationTable;
    private JPanel uiPanel;
    private JPanel accountSection;
    DefaultTableModel model;

    ImageIcon userImg;

    public IrgenModule() throws SQLException, ClassNotFoundException {
        this.user = User.getInstance();
        createGUI();
    }


    public void createGUI() {
        uiPanel.setLayout(new GridLayout(1, 1));
        mainPanel.remove(uiPanel);
        frame = new JFrame();
        frame.setSize(800, 800);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setText("Тавтай морил");
        title.setOpaque(true);
        title.setBackground(new Color(29, 13, 255));
        title.setForeground(Color.white);
        title.setBorder(new EmptyBorder(20, 20, 10, 10));


        uiPanel = new JPanel(new GridLayout(1, 1));


        sidebar.setBorder(BorderFactory.createLineBorder(Color.black));
        userPic.setText("Зураг");

        userPic.setOpaque(true);

        userFullName.setText(user.getFname() + " " + user.getLname());
        userFullName.setOpaque(true);

        infoButton.setText("Хувийн мэдээлэл");
        vaccinationHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showVaccinationHistory();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title.setText("Хувийн мэдээлэл");
                uiPanel.removeAll();
                uiPanel.revalidate();
                uiPanel.repaint();

                JPanel infoPanel = new JPanel(new GridLayout(4, 2));

                JLabel lnameLabel = new JLabel("Овог: ");
                JLabel fnameLabel = new JLabel("Нэр: ");
                JLabel rdLabel = new JLabel("Регистрийн дугаар: ");
                JLabel positionLabel = new JLabel("Системд хандах эрх: ");

                JLabel lname = new JLabel(user.getLname());
                JLabel fname = new JLabel(user.getFname());
                JLabel rd = new JLabel(user.getRd());
                JLabel position = new JLabel(user.getPosition());

                infoPanel.add(lnameLabel);
                infoPanel.add(lname);
                infoPanel.add(fnameLabel);
                infoPanel.add(fname);
                infoPanel.add(rdLabel);
                infoPanel.add(rd);
                infoPanel.add(positionLabel);
                infoPanel.add(position);
                infoPanel.setSize(200, 200);

                infoPanel.revalidate();
                infoPanel.repaint();

                uiPanel.add(infoPanel);
                uiPanel.revalidate();
                uiPanel.repaint();
                mainPanel.add(uiPanel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();

                frame.revalidate();
            }
        });

    }

    private void showVaccinationHistory() throws ClassNotFoundException, SQLException {
        title.setText("Вакцинжуулалтын түүх");
        uiPanel.removeAll();
        uiPanel.revalidate();
        uiPanel.repaint();
        mainPanel.remove(uiPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
        vaccinationTable = new JTable();

        VaccinationHistoryDAO vhd = new VaccinationHistoryDAO();
        ArrayList<VaccinationHistory> vh = new ArrayList<>(vhd.getVacHistoryByRd(this.user.getRd()));

        model = new DefaultTableModel();
        model.addColumn("Регистрийн дугаар");
        model.addColumn("Нэр");
        model.addColumn("Овог");
        model.addColumn("Вакцины нэр");
        model.addColumn("Огноо");

        int i = 0;

        while (i < vh.size()) {
            Object[] rowData = new Object[5];
            rowData[0] = vh.get(i).getRd();
            rowData[1] = vh.get(i).getFname();
            rowData[2] = vh.get(i).getLname();
            rowData[3] = vh.get(i).getVaccineName();
            rowData[4] = vh.get(i).getDate();
            model.addRow(rowData);
            i++;
        }

        vaccinationTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(vaccinationTable);
        uiPanel.add(vaccinationTable);
        uiPanel.revalidate();
        uiPanel.repaint();
        mainPanel.add(uiPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
        frame.revalidate();
    }



}
