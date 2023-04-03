import javax.swing.*;

public class EmchModule {

    JFrame frame;
    User user;

    public EmchModule(User user) {
        this.user = user;
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setVisible(true);


    }

}
