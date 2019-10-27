package Part_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class  CE203_2019_Ass1 {
    /**
     * @author Callum-James Smith (cs18804)
     * @param args Any command-line arguments
     */

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

class MainFrame extends JFrame {

    private int size = 400;
    String word = "";
    String searchLetter;
//    The ArrayList of words
    ArrayList<String> words = new ArrayList<>();
/*
* These are components that are manipulated by the addWord class.
* They are for the input of a new word, and the display of the words from the ArrayList in addWord.
* */
    JTextField wordInput = new JTextField();
    JTextField rColour = new JTextField(5);
    JTextField gColour = new JTextField(5);
    JTextField bColour = new JTextField(5);
    JTextArea outputScreen = new JTextArea(size / 10, size / 10);
    MainFrame() {
//        Close the frame properly
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        Button variable creation
        JButton butAddItem   = new JButton("Add Word");
        JButton butSearch  = new JButton("Search Via Last Letter");
        JButton butRemove   = new JButton("Remove Word");
        JButton butClear = new JButton("Clear List");
//        Create some panels for stuffs
        JPanel butPanel = new JPanel();
        JPanel sysOutput = new JPanel();
        JPanel inputPanel = new JPanel();

//        inputPanel Components
        JLabel wordInputLabel = new JLabel("Word: ");
//        Set size of wordInput and outputScreen component
        wordInput.setColumns(20);
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
        inputPanel.add(rColour);
        inputPanel.add(gColour);
        inputPanel.add(bColour);

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
            app.outputScreen.append(app.words.toString());
            app.word = "";
        }
        else {
            app.outputScreen.append("Error! The string '" + app.word + "' was not added as it is not a valid word.");
            app.word = "";
        }
    }

    private boolean wordCheck(String wordToCheck) throws NullPointerException {
        /*
        This method checks to see if the word entered is in the correct format,
        words must not: contain whitespace, start with a digit or contain anything other than letters, numbers or hyphens.
         */
        boolean isCorrectFormat = true;
        try {
            if (wordToCheck.contains(" ") || wordToCheck.matches("^[\\d].*") || wordToCheck.matches("[^A-Za-z0-9]")) {
                isCorrectFormat = false;
            }
            if (wordToCheck.isBlank() || wordToCheck.isEmpty()) {
                isCorrectFormat = false;
            }
        } catch (NullPointerException ex) {
            app.outputScreen.append("Please enter a word into the text box below!");
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
        if (letterCheck(letter)) listWords(letter);
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
        app.outputScreen.setText(""); // Clear the screen when button is activated
        app.outputScreen.append("The following words match your search criteria: \n"); // Display message on top of output screen
        boolean found = false; // Has a word been found inside of app.words
        for (int i = 0; i < app.words.size(); i++) {
            int intFound = app.words.get(i).toLowerCase().lastIndexOf(l);
            if (intFound == app.words.get(i).toLowerCase().length() - 1) {
                found = true;
                app.outputScreen.append(app.words.get(i) + "\n");
            }
        } if (!found) app.outputScreen.setText("No words found!");

        }
}

class deleteWord implements ActionListener {
    /**
     * This class deletes a word from <code>app.words</code> according to the word obtained from <code>app.wordInput</code>
     */

    private MainFrame app;
    deleteWord(MainFrame app) {
        this.app = app;
    }

    private void basicSearch() {
        app.outputScreen.append("The following word(s) were removed: \n"); // Message to be displayed at top of output
        List<String> toRemove = new ArrayList<>(); // Stores the words to be removed from app.words
        boolean found = false; // Has the word been found in the array list?
        String l = app.wordInput.getText(); // Stores the word inputted by the user
        for (String str : app.words) {
            if (str.equalsIgnoreCase(l)) {
                app.outputScreen.append(str + "\n");
                toRemove.add(str);
                found = true;
            }
        }
        app.words.removeAll(toRemove); // Remove all words from app.words according to the values in toRemove array
        app.wordInput.setText(""); // Clear the textfield

        if (!found) {
            app.outputScreen.setText("No words could be found matching your search!");
            app.wordInput.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.outputScreen.setText(""); // Clear screen when button is activated
        basicSearch();
    }
}
