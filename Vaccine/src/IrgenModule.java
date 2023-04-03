import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IrgenModule {

    User user;
    JFrame frame;
    private JPanel mainPanel;
    private JPanel sidebar;
    private JLabel userPic;
    private JLabel userFullName;
    private JButton vaccinationHistoryButton;
    private JButton vaccinesButton;
    private JTable vaccinationTable;
    private JLabel title;

    ImageIcon userImg;

    public IrgenModule(User user) {
        this.user = user;
        createGUI();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.setSize(800, 800);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setOpaque(true);
        title.setBackground(new Color(29, 13, 255));
        title.setForeground(Color.white);
        title.setBorder(new EmptyBorder(20, 20, 10, 10));

        sidebar.setBackground(new Color(180, 255, 255));

        userFullName.setOpaque(true);
        userPic.setOpaque(true);




    }



}
