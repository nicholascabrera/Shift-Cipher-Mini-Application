/**
 * This project is influenced by the chapter on cryptography and encryption.
 */
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
         * Start the event handling thread from the initial thread and run the GUI.
         */
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GUI().createAndRunGUI();
            }
        });
    }
}
