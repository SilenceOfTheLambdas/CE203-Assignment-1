package Part_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clearWords implements ActionListener {

    private MainFrame app;
    clearWords(MainFrame app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.wordLists.setText("");
//        Check to see if array is empty
        if (app.words.isEmpty()) {
            JOptionPane.showMessageDialog(null, "List is already empty.");
        }  else {
//            Clear the array of words
            app.words.clear();
            app.wordLists.append("All words cleared!");
        }
    }
}
