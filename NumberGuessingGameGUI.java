package Task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {
    private JFrame frame;
    private JTextField guessField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JButton playAgainButton;
    private int targetNumber;
    private int maxAttempts = 10;
    private int attemptsLeft;
    private int score = 0;

    public NumberGuessingGameGUI() {
        frame = new JFrame("Number Guessing Game");

        guessField = new JTextField();
        guessField.setFont(new Font("Tahoma", Font.BOLD, 22));
        guessField.setBounds(111, 221, 195, 50);
        submitButton = new JButton("Submit Guess");
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        submitButton.setForeground(Color.RED);
        submitButton.setBounds(111, 320, 195, 50);
        feedbackLabel = new JLabel("");
        feedbackLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        feedbackLabel.setBounds(120, 405, 219, 32);
        attemptsLabel = new JLabel("");
        attemptsLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        attemptsLabel.setBounds(111, 448, 195, 44);
        playAgainButton = new JButton("Play Again");
        playAgainButton.setForeground(Color.DARK_GRAY);
        playAgainButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        playAgainButton.setBounds(111, 502, 208, 44);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("Welcome to the Number Guessing Game!");
        label.setForeground(Color.RED);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setBounds(36, 22, 438, 55);
        frame.getContentPane().add(label);
        JLabel lblImThinkingOf = new JLabel("I'm thinking of a number between 1 and 100. ");
        lblImThinkingOf.setForeground(new Color(102, 51, 0));
        lblImThinkingOf.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblImThinkingOf.setBounds(36, 91, 464, 32);
        frame.getContentPane().add(lblImThinkingOf);
        frame.getContentPane().add(guessField);
        frame.getContentPane().add(submitButton);
        frame.getContentPane().add(feedbackLabel);
        frame.getContentPane().add(attemptsLabel);
        frame.getContentPane().add(playAgainButton);
        
        JLabel lblNewLabel = new JLabel("Enter Your Guess Here.");
        lblNewLabel.setForeground(new Color(0, 128, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(93, 153, 246, 32);
        frame.getContentPane().add(lblNewLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(504, 613);
        frame.setVisible(true);

        initializeGame();
        addActionListeners();
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = maxAttempts;
        feedbackLabel.setText("");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessField.setText("");
        submitButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    private void addActionListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());
                    checkGuess(userGuess);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Invalid input. Please enter a number.");
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame();
            }
        });
    }

    private void checkGuess(int userGuess) {
        attemptsLeft--;

        if (userGuess == targetNumber) {
            feedbackLabel.setText("Congratulations! You've guessed the correct number.");
            score++;
            submitButton.setEnabled(false);
            playAgainButton.setEnabled(true);
        } else if (userGuess < targetNumber) {
            feedbackLabel.setText("Your guess is too low.");
        } else {
            feedbackLabel.setText("Your guess is too high.");
        }

        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        if (attemptsLeft == 0) {
            feedbackLabel.setText("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
            submitButton.setEnabled(false);
            playAgainButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI();
            }
        });
    }
}
