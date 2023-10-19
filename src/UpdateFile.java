import javax.swing.*;

public class UpdateFile extends MainMenu {

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


