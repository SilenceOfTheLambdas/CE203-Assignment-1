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
//        Clear the wordList window every time the 'add word' button is pressed
        app.wordLists.setText("");

        if (wordCheck(app.word)) {
            //        Add the word to the ArrayList
            words.add(app.word);
            app.wordLists.append(words.toString().trim());
        }
        else {
            JOptionPane.showMessageDialog(null, "Error adding word! Incorrect format used.");
            app.wordLists.append(words.toString().trim());
        }
//        Clear the word input box
        app.wordInput.setText("");
    }

    public boolean wordCheck(String wordToCheck) {
        /*
        This method checks to see if the word entered is in the correct format,
        words must not: contain whitespace, start with a digit or contain anything other than letters, numbers or hyphens.
         */
        boolean isCorrectFormat = true;
        if (wordToCheck.isEmpty() || wordToCheck.isBlank() || wordToCheck.matches("^[\\d].*") || wordToCheck.matches("[^A-Za-z0-9]")) {
            isCorrectFormat = false;
        }

        return isCorrectFormat;
    }
}
