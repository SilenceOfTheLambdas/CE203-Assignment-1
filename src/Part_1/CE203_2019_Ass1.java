package Part_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class  CE203_2019_Ass1 {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

class MainFrame extends JFrame {

    private int size = 400;
    String word;
    String searchLetter;
//    The ArrayList of words
    ArrayList<String> words = new ArrayList<>();
/*
* These are components that are manipulated by the addWord class.
* They are for the input of a new word, and the display of the words from the ArrayList in addWord.
* */
    JTextField wordInput = new JTextField();
    JTextArea outputScreen = new JTextArea(size / 10, size / 8);
    MainFrame() {
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
        JLabel wordInputLabel = new JLabel("Word: ");
//        Set size of wordInput and outputScreen component
        wordInput.setColumns(size / 10);
        outputScreen.setWrapStyleWord(true);

//        Adding the Word Lists to the panel
        outputScreen.setEditable(false);
        sysOutput.add(outputScreen);

//        Add the buttons to panel
        butPanel.add(butAddItem);
        butPanel.add(butSearch);
        butPanel.add(butRemove);
        butPanel.add(butClear);

//        Adding text input to inputPanel
        inputPanel.add(wordInputLabel);
        inputPanel.add(wordInput);

//        Adding the panels
        add(butPanel, BorderLayout.NORTH);
        add(sysOutput, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize( size+150, size+100 );
        outputScreen.setForeground(Color.BLACK);

//        Action Listeners
        butAddItem.addActionListener(new addWord(this));
        butClear.addActionListener(new clearWords(this));
        butSearch.addActionListener(new wordSearch(this));
        butRemove.addActionListener(new deleteWord(this));
    }

}

class addWord implements ActionListener {

    private MainFrame app;

    addWord(MainFrame app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        Obtain the word from the input
        app.word = app.wordInput.getText();
//        Clear the wordList window every time the 'add word' button is pressed
        app.outputScreen.setText("");

        if (wordCheck(app.word)) {
            //        Add the word to the ArrayList
            app.words.add(app.word);
            app.outputScreen.append("The word '" + app.word + "' was added to the list.");
            app.wordInput.setText("");
            app.word = null;
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

class clearWords implements ActionListener {

    private MainFrame app;
    clearWords(MainFrame app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.outputScreen.setText("");
//        Check to see if array is empty
        if (app.words.isEmpty()) {
            app.outputScreen.append("There are no words in the list to clear!");
        }  else {
//            Clear the array of words
            app.words.clear();
            app.outputScreen.append("All words cleared!");
        }
    }
}

class wordSearch implements ActionListener {

    private MainFrame app;
    wordSearch(MainFrame app) {
        this.app = app;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        Make sure to clear the output box
        app.outputScreen.setText("");
//        Set app.word to be the value in the wordInput text box
        app.searchLetter = app.wordInput.getText();
        String letter = app.searchLetter;
//        if (letterCheck(letter)) {
//
//        }
        listWords(letter);
    }

    private boolean letterCheck(String l) {
        boolean isGood = true;
        if (l.length() > 1) {
            app.outputScreen.append("Please use a single letter or number!");
            isGood = false;
        }

        return isGood;
    }

    private void listWords(String l) {
//    TODO: Complete this task (It's a pain in the arse)
        app.outputScreen.setText("");
        for (int i = 0; i < app.words.size(); i++) {
            String s  = app.words.get(i).toString();
            String[] temp;
            temp = s.split(l);
            for (int x = 0; x < temp.length - 1; x++) {
                app.outputScreen.append(app.words.get(x));
                if (temp[x].equals(l)) {
                    app.outputScreen.append(app.words.get(i));
                }
            }

        }
    }
}

class deleteWord implements ActionListener {

    MainFrame app;
    public deleteWord(MainFrame app) {
        this.app = app;
    }

    private void basicSearch(String word) {
        ArrayList<String> queryWords = new ArrayList<>();
        boolean removed = false;

        if (app.words.iterator().next().contains(word)) {
            queryWords.add(word);
            app.words.remove(word);
            removed = true;
        }

        if (removed) {
            app.outputScreen.append("The word(s):\n" + queryWords.toString() + "\nwere removed.");
        } else {
            app.outputScreen.append("No word found!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.outputScreen.setText("");
        app.wordInput.setText("");
        basicSearch(app.wordInput.getText());
    }
}
