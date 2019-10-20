package Part_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class addWord implements ActionListener {

    private MainFrame app;

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
            app.words.add(app.word);
            app.wordLists.append("The word '" + app.word + "' was added to the list.");
            app.wordInput.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null, "Error! The string '" + app.word + "' was not added as it is not a valid word.");
        }
    }

    private boolean wordCheck(String wordToCheck) {
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
