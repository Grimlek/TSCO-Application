package view;

import javax.swing.SwingUtilities;
import model.Model;
import control.CSVFileController;

public final class CreateAndShowUI
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {

            private Model model;

            private CSVFileController controller;

            private MainFrame frame;


            @Override
            public void run()
            {
                model = Model.getModelInstance();
                controller = CSVFileController.getControllerInstance();
                frame = MainFrame.getMainFrameInstance();
            }
        });
    }
}
