import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GUI {
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 400;
    private int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 300;
    private int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 200;
    
    /**
     * This function is to reduce the amount of redundant code in the main "createAndRunGUI" class.
     * @param panel
     * @param dimension
     */
    private void setAbsoluteSize(JPanel panel, Dimension dimension){
        panel.setMinimumSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
    }

    /**
     * This function is to reduce the amount of redundant code in the main "createAndRunGUI" class.
     * @param textArea
     * @param dimension
     */
    private void setAbsoluteSize(JTextArea textArea, Dimension dimension){
        textArea.setMinimumSize(dimension);
        textArea.setPreferredSize(dimension);
        textArea.setMaximumSize(dimension);
    }

    public void createAndRunGUI(){
        /*
         * Set the look and feel of the program. This will make everything look more modern with minimal effort on my part.
         */
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        GUI myGUI = new GUI();      //gui object so I can use the methods contained here.

        /*
         * The main window that will hold the translator. It will have a BoxLayout on the page
         * axis so that the the button panel and the text area panel will be positioned above
         * and below each other.
         */
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
        window.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setTitle("Shift Cipher Translator");

        /*
         * The two panels. These will hold the contents of the application.
         *      Button Panel: This will hold the shift amount field and the translate button.
         *      Text Panel: This will hold the plain and cipher text areas.
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        myGUI.setAbsoluteSize(buttonPanel, new Dimension(600, 40));     //the button and field should take up considerably less space.

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        myGUI.setAbsoluteSize(textPanel, new Dimension(600, 360));

        /*
         * The button and field is created here. Titled borders are necessary so I don't have to
         * add labels for everything.
         */
        JTextField shiftAmountField = new JTextField();
        JButton translateButton = new JButton();

        shiftAmountField.setBorder(BorderFactory.createTitledBorder("Shift Amount"));
        translateButton.setText("Translate");
        translateButton.setActionCommand("translate");

        /*
         * The plain and cipher text areas are created here. I set the line wrap so the text
         * won't continue off the screen, and instead wraps, as is implied by the function name.
         * Again, I use titled borders so I don't waste time with labels.
         */
        JTextArea plainTextArea = new JTextArea();
        JTextArea cipherTextArea = new JTextArea();

        plainTextArea.setLineWrap(true);
        cipherTextArea.setLineWrap(true);

        plainTextArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Plaintext"));
        cipherTextArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Ciphertext"));

        myGUI.setAbsoluteSize(plainTextArea, new Dimension(275, 345));
        myGUI.setAbsoluteSize(cipherTextArea, new Dimension(275, 345));

        /*
         * Arguably the most important part of the program is implemented here, the Event Handler.
         * The handler will take in action events and decide what get translated and how.
         */
        EventHandler handler = new EventHandler(shiftAmountField, plainTextArea, cipherTextArea, window);
        translateButton.addActionListener(handler);
        plainTextArea.addKeyListener(handler);
        cipherTextArea.addKeyListener(handler);

        /*
         * add everything to its correct position and show the frame.
         */
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(shiftAmountField);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(translateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(370, 0)));
        
        textPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        textPanel.add(plainTextArea);
        textPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        textPanel.add(cipherTextArea);

        window.add(buttonPanel);
        window.add(textPanel);

        window.pack();
        window.setVisible(true);
    }
}