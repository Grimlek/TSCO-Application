package view.panel;

import java.awt.Container;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import view.ApplicationStyles;

public class ErrorMessagePane {
    private final JLabel message;

    private final JCheckBox checkBox;

    private final JPanel panel;


    public ErrorMessagePane(Container parent, String errorMessage) {
        panel = new JPanel(new MigLayout(""));

        message = new JLabel(errorMessage);
        message.setFont(ApplicationStyles.STANDARD_FONT);
        panel.add(message, "wrap");

        checkBox = new JCheckBox("Don't Show This Message Again");
        checkBox.setFont(ApplicationStyles.STANDARD_FONT);
        panel.add(checkBox);

        JOptionPane.showMessageDialog(
                parent,
                panel,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }


    public boolean isCheckBoxSelected() {
        return checkBox.isSelected();
    }
}
