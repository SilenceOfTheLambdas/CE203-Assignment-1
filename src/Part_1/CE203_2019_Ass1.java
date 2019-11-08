package Part_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CE203_2019_Ass1 {
    /**
     * @param args Any command-line arguments
     * @author Callum-James Smith (cs18804)
     * @see <a href="https://bitbucket.org/techdragongames/application-programming-assignment-1/src/master/">Repository</a>
     */

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

class MainFrame extends JFrame {
    /**
     * This class is responsible for making the JFrame and showing all the elements.
     */
    private int size = 400;
    String word = "";
    String searchLetter;
    ArrayList<String> words = new ArrayList<>();
    /*
     * These are components that are manipulated by the addWord class.
     * They are for the input of a new word, and the display of the words from the ArrayList in addWord.
     * */
    JTextField wordInput = new JTextField();
    private JTextField rColour = new JTextField(5);
    private JTextField gColour = new JTextField(5);
    private JTextField bColour = new JTextField(5);
    JTextArea outputScreen = new JTextArea(size / 10, size / 10);

    MainFrame() {
//        Close the frame properly
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        Button variable creation
        JButton butAddItem = new JButton("Add Word");
        JButton butSearch = new JButton("Search Via Last Letter");
        JButton butRemove = new JButton("Remove Word");
        JButton butClear = new JButton("Clear List");

//        Create some panels
        JPanel butPanel = new JPanel();
        JPanel sysOutput = new JPanel();
        JPanel inputPanel = new JPanel();

//        inputPanel Components
        JLabel wordInputLabel = new JLabel("Word: ");
        JLabel r = new JLabel("R:");
        JLabel g = new JLabel("G");
        JLabel b = new JLabel("B");

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
        inputPanel.add(r);
        inputPanel.add(rColour);
        inputPanel.add(g);
        inputPanel.add(gColour);
        inputPanel.add(b);
        inputPanel.add(bColour);

//        Adding the panels
        add(butPanel, BorderLayout.NORTH);
        add(sysOutput, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(size + 150, size + 100);
        outputScreen.setForeground(Color.BLACK);

//        Action Listeners
        butAddItem.addActionListener(new addWord(this));
        butClear.addActionListener(new clearWords(this));
        butSearch.addActionListener(new wordSearch(this));
        butRemove.addActionListener(new deleteWord(this));
    }

    void setOutputColor() throws NumberFormatException {
//        This method changes the colour of outputScreen according the user input.
        try {
//            Obtain the values entered by the user
            int r = Integer.parseInt(rColour.getText());
            int g = Integer.parseInt(gColour.getText());
            int b = Integer.parseInt(bColour.getText());

            if ((r > 255 || r < 0) || (g > 255 || g < 0) || (b > 255 || b < 0)) {
//                Checks to see if the values are compatible with Color
                r = 0;
                g = 0;
                b = 0;
                JOptionPane.showMessageDialog(null, "Values must be between 0 and 255!");
            }

//            Create a new color variable based in the user input
            Color userColour = new Color(r, g, b);
//            Set foreground colour
            outputScreen.setForeground(userColour);
        } catch (NumberFormatException ex) {
            outputScreen.setForeground(Color.BLACK);
            JOptionPane.showMessageDialog(null, "Please make sure to enter values into the RGB inputs!");
        }

    }

}

class addWord implements ActionListener {
    //  This class is responsible for adding words to the words list in MainFrame
    private MainFrame app;

    addWord(MainFrame app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        Set colour
        app.setOutputColor();

//        Obtain the word from the input
        app.word = app.wordInput.getText();
//        Clear the wordList window every time the 'add word' button is pressed
        app.outputScreen.setText("");

        if (wordCheck(app.word)) {
            //        Add the word to the ArrayList
            app.words.add(app.word);
            app.outputScreen.append("The word '" + app.word + "' was added to the list.");
            app.wordInput.setText("");
            app.word = "";
        } else {
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
            if (wordToCheck.contains(" ") || wordToCheck.matches("^[\\d].*") || wordToCheck.matches("[^A-Za-z0-9].*")) {
                isCorrectFormat = false;
            }
            if (wordToCheck.isBlank() || wordToCheck.isEmpty()) {
//                Checks to see if the word is empty
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
//        Set colour
        app.setOutputColor();
        app.outputScreen.setText("");
//        Check to see if array is empty
        if (app.words.isEmpty()) {
            app.outputScreen.append("There are no words in the list to clear!");
        } else {
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
//        Set colour
        app.setOutputColor();
//        Make sure to clear the output box
        app.outputScreen.setText("");
//        Set app.word to be the value in the wordInput text box
        app.searchLetter = app.wordInput.getText();
        String letter = app.searchLetter;
        if (letterCheck(letter)) listWords();
    }

    private boolean letterCheck(String l) {
//        Checks to see if the input in only a single letter/number
        boolean isGood = true;
        if (l.length() > 1) {
            app.outputScreen.append("Please use a single letter or number!");
            isGood = false;
        }

        return isGood;
    }

    private void listWords() {
//        This method displays word(s) that end in a specified value
        app.outputScreen.setText("");
        app.outputScreen.append("The following words match your search criteria: \n");
        String l = app.wordInput.getText().toLowerCase();
        boolean found = false;
//        Iterates through the words ArrayList
        for (int i = 0; i < app.words.size(); i++) {
            int intFound = app.words.get(i).toLowerCase().lastIndexOf(l);
            if (intFound == app.words.get(i).toLowerCase().length() - 1) {
                found = true;
                app.outputScreen.append(app.words.get(i) + "\n");
            }
        }
        if (!found) app.outputScreen.setText("No words found!");
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
//        method responsible for finding and deleting words from the list
        app.outputScreen.append("The following word(s) were removed: \n");
        List<String> toRemove = new ArrayList<>(); // Stores the words to be removed from app.words
        boolean found = false;
        String l = app.wordInput.getText();
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
        //        Set colour
        app.setOutputColor();
        app.outputScreen.setText(""); // Clear screen when button is activated
        basicSearch();
    }
}
