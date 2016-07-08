package view;

import javax.swing.SwingUtilities;

import model.Model;
import control.CSVFileController;

public class CreateAndShowUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            Model.getModelInstance();
            CSVFileController.getControllerInstance();
            MainFrame.getMainFrameInstance();
        });
    }
}
