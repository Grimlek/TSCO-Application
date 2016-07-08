package view;

import java.awt.Container;
import java.awt.Dialog.ModalityType;
import javax.swing.JDialog;

import net.miginfocom.swing.MigLayout;

public class Dialog {
    private final JDialog dialog;


    public Dialog() {
        this.dialog = new JDialog();
        createDialog();
    }


    private void createDialog() {
        dialog.setLayout(new MigLayout());
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
    }


    public void setLocationToMousePoint(int x, int y) {
        dialog.setLocation(
                x - dialog.getSize().width / 2,
                y - dialog.getSize().height / 2);
        dialog.revalidate();
    }


    public void addContainer(Container container) {
        if (dialog.getContentPane() != null) {
            dialog.getContentPane().removeAll();
        }

        dialog.getContentPane().add(container);
        dialog.pack();
        dialog.setLocationRelativeTo(container);
    }


    public void setVisibility(Boolean value) {
        dialog.setVisible(value);
    }


    public JDialog getDialog() {
        return dialog;
    }
}
