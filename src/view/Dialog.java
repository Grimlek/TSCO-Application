package view;

import java.awt.Container;
import java.awt.Dialog.ModalityType;
import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;

public final class Dialog
{
    private final JDialog dialog;


    public Dialog()
    {
        this.dialog = new JDialog();
        createDialog();
    }


    private final void createDialog()
    {
        dialog.setLayout(new MigLayout());
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
    }


    public final void setLocationToMousePoint(int x, int y)
    {
        dialog.setLocation(
            x - dialog.getSize().width / 2,
            y - dialog.getSize().height / 2);
        dialog.revalidate();
    }


    public final void addContainer(Container container)
    {
        if (dialog.getContentPane() != null)
        {
            dialog.getContentPane().removeAll();
        }

        dialog.getContentPane().add(container);
        dialog.pack();
        dialog.setLocationRelativeTo(container);
    }


    public final void setVisibility(Boolean value)
    {
        dialog.setVisible(value);
    }


    public final JDialog getDialog()
    {
        return dialog;
    }
}
