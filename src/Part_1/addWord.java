package Part_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class addWord implements ActionListener {

    MainFrame app;
    ArrayList<String> words = new ArrayList<String>();

    addWord(MainFrame app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        Obtain the word from the input
        app.word = app.wordInput.getText();
//        Add the word to the ArrayList
        words.add(app.word);
//        Do something
        app.wordLists.setText("");
        app.wordLists.append(words.toString().trim());
//        Clear the word input box
        app.wordInput.setText("");
    }
}
