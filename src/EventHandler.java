import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventHandler implements ActionListener, KeyListener {
    
    private JTextField shiftAmountField;
    private JTextArea plainTextArea, cipherTextArea;
    private JFrame window;

    /**
     * The Event Handler takes in several parameters so it can access them later 
     * on in the program, without needing for them to send out an action event.
     * @param shiftAmountField
     * @param plainTextArea
     * @param cipherTextArea
     * @param window
     */
    public EventHandler(JTextField shiftAmountField, JTextArea plainTextArea, JTextArea cipherTextArea, JFrame window){
        this.shiftAmountField = shiftAmountField;
        this.plainTextArea = plainTextArea;
        this.cipherTextArea = cipherTextArea;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * If the action command is translate (there isn't any other) then the translation process will begin.
         */
        if(e.getActionCommand() == "translate"){
            /*
             * Set the cursors to "wait" while the process runs. This way users know the computer is working on
             * something even if it takes a while.
             */
            window.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            plainTextArea.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            cipherTextArea.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            int shift = 0;      //initialize the shift amount, just in case its left blank in the application

            /*
             * if the field isn't blank, then set the shift to what is in the field.
             * if not, then set the field's text to zero for the user.
             */
            if(shiftAmountField.getText().length() > 0){
                shift = Integer.parseInt(shiftAmountField.getText());
            } else {
                shiftAmountField.setText("0");
            }
            
            /*
             * if the translation command is sent, we need to check if this is a decode or encode.
             */
            if(plainTextArea.getText().length() > cipherTextArea.getText().length()){   //if there are characters in plaintext, we encode.
                String cipherText = "";     //initialize cipher text

                /*
                 * loop through each character individually, convert it to its ASCII or UTF code,
                 * add the shift, and add the newly encrypted character to cipher text.
                 * 
                 * Caveats: the character must not be a space, period, or newline character. This will
                 * make cipher text more "readable" or user friendly.
                 */
                for(char c : plainTextArea.getText().toCharArray()){
                    int cValue = Character.valueOf(c);
                    if(cValue != Character.valueOf(' ') && cValue != Character.valueOf('.')){
                        cValue += shift;
                    }

                    if(c != '\n'){cipherText = cipherText.concat(Character.toString((char) cValue));}
                }

                /*
                 * Once the plaintext is successfully encrypted, add the encrypted text to the cipher text area.
                 */
                cipherTextArea.setText(cipherText);
            } else if (cipherTextArea.getText().length() > 0){  //if there are characters in cipher text, we decode.
                String plainText = "";

                /*
                 * The same loop as above, except the encrypted character must be decrypted by subracting by
                 * the shift amount.
                 */
                for(char c : cipherTextArea.getText().toCharArray()){
                    int cValue = Character.valueOf(c);
                    if(cValue != Character.valueOf(' ') && cValue != Character.valueOf('.')){
                        cValue -= shift;
                    }

                    if(c != '\n'){plainText = plainText.concat(Character.toString((char) cValue));}
                }

                plainTextArea.setText(plainText);
            } else {                                    //otherwise, we do nothing.
                /*
                 * If the user does not put any text into either the plain text area or the cipher text area, then there is nothing
                 * to encrypt or decrypt. We use a JOptionPane to warn the user of this fact.
                 */
                JOptionPane.showMessageDialog(null, "There is nothing to translate!", "Error!", JOptionPane.ERROR_MESSAGE);
            }

            /*
             * Once the process is successfully completed, we change our wait cursors back into their proper form.
             */
            window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            plainTextArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            cipherTextArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
         * For a better user experience, if they hit the "ENTER" key then an action event will be
         * generated as if they had pressed the "Translate" button.
         */
        if(e.getKeyChar() == '\n'){
            this.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "translate"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}