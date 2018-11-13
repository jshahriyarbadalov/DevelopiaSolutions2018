package solutions.util;

import javax.swing.*;

public class Methods {


    //ready
    public static boolean confirmDialog(String message) {
        boolean result = false;

        JDialog.setDefaultLookAndFeelDecorated(true);

        int response = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.NO_OPTION) {
            result = false;

        } else if (response == JOptionPane.YES_OPTION) {
            result = true;

        } else if (response == JOptionPane.CLOSED_OPTION) {

            result = false;
        }

        return result;
    }
}
