import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu {

    public JPanel panel1;
    private JCheckBox Cb1;
    private JCheckBox Cb2;
    private JCheckBox Cb3;
    private JCheckBox Cb4;
    private JTextField Tf1;
    private JTextField Tf2;
    private JTextField Tf3;
    private JTextField Tf4;
    private JButton updateButton1;
    private JButton updateButton2;
    private JButton updateButton3;
    private JButton updateButton4;
    private JButton updateAllButton;

    public MainMenu() {

        // Add action listeners to the update buttons
        updateButton1.addActionListener(e -> {
            try {
                updateFile(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton2.addActionListener(e -> {
            try {
                updateFile(2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton3.addActionListener(e -> {
            try {
                updateFile(3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton4.addActionListener(e -> {
            try {
                updateFile(4);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateAllButton.addActionListener(e -> {
            try {
                updateAllFiles();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void updateFile(int fieldNumber) throws IOException {

        // Get the checkbox state
        boolean isChecked1 = Cb1.isSelected();
        boolean isChecked2 = Cb2.isSelected();
        boolean isChecked3 = Cb3.isSelected();
        boolean isChecked4 = Cb4.isSelected();

        // Get the input in the corresponding text field
        String input = Tf1.getText();
        if (fieldNumber == 2) {
            input = Tf2.getText();
        } else if (fieldNumber == 3) {
            input = Tf3.getText();
        } else if (fieldNumber == 4) {
            input = Tf4.getText();
        }

        // Update the file
        updateFile(fieldNumber, isChecked1, isChecked2, isChecked3, isChecked4, input);
    }

    private void updateAllFiles() throws IOException {

        // Update each file
        updateFile(1);
        updateFile(2);
        updateFile(3);
        updateFile(4);
    }

    private static void updateFile(int fieldNumber, boolean isChecked1, boolean isChecked2, boolean isChecked3, boolean isChecked4, String input) throws IOException {

        // Create a new BufferedWriter object to write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/Data/Data.txt")));

        // Read the file line by line
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/Data/Data.txt")));
        String line;
        while ((line = reader.readLine()) != null) {

            // Split the line into columns
            String[] columns = line.split(",");

            // If the checkbox is checked, update the corresponding column
            if (isChecked1) {
                columns[1] = input;
            }
            if (isChecked2) {
                columns[2] = input;
            }
            if (isChecked3) {
                columns[3] = input;
            }
            if (isChecked4) {
                columns[4] = input;
            }

            // Write the updated line to the file
            writer.write(String.join(",", columns));
            writer.newLine();
        }

        // Close the BufferedWriter and BufferedReader objects
        writer.close();
        reader.close();
    }


    public static void main(String[] args) {

        // Create a new UpdateFile object
        UpdateFile updateFile = new UpdateFile();

        // Create a new JFrame object and add the UpdateFile panel to it
        JFrame frame = new JFrame("Update File");
        frame.setContentPane(updateFile.panel1);
        frame.pack();
        frame.setVisible(true);
    }
}