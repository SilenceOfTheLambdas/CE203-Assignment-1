package Part_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

class MainFrame extends JFrame {
    int size = 400;

    public MainFrame()
    {
//        Close the frame properly
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        Button variable creation
        JButton butAddItem   = new JButton("Add Word");
        JButton butSearch  = new JButton("Search Via Last Letter");
        JButton butRemove   = new JButton("Remove Word");
        JButton butClear = new JButton("Clear List");

        SquarePanel panel = new SquarePanel(this);
        JPanel butPanel = new JPanel();

        butPanel.add(butAddItem);
        butPanel.add(butSearch);
        butPanel.add(butRemove);
        butPanel.add(butClear);
        add(butPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setSize( size+100, size+100 );

    }

}

    class SquarePanel extends JPanel
    {
        MainFrame theApp;

        SquarePanel(MainFrame app)
        {
            theApp = app;
        }
}
