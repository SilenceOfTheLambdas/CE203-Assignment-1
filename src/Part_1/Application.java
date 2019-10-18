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

        JPanel butPanel = new JPanel();
        JPanel sysOutput = new JPanel();
        JPanel inputPanel = new JPanel();

//        inputPanel Components
        JButton wordSubmit = new JButton("Submit");
        JLabel wordInputLabel = new JLabel("Word: ");
        JTextField wordInput = new JTextField();
//        Set size of wordInput component
        wordInput.setColumns(size / 10);

//        Add the buttons to panel
        butPanel.add(butAddItem);
        butPanel.add(butSearch);
        butPanel.add(butRemove);
        butPanel.add(butClear);

//        Adding text input to inputPanel
        inputPanel.add(wordInputLabel);
        inputPanel.add(wordInput);
        inputPanel.add(wordSubmit);

//        Adding the panels
        add(butPanel, BorderLayout.NORTH);
        add(sysOutput, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize( size+150, size+100 );

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