package view.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import control.CSVFileController;
import control.action.DialogActions;
import control.action.SupplierButtonActions;
import net.miginfocom.swing.MigLayout;
import view.ApplicationStyles;
import view.Dialog;

public final class AddSupplierPanel
{
    private final JPanel addSupPanel;

    private final Dialog dialog;

    private JTextField tfID, tfName;


    public AddSupplierPanel(Dialog dialog)
    {
        this.dialog = dialog;
        this.addSupPanel = new JPanel(new MigLayout());
        createAddSupplierPanel();
    }


    private final void createAddSupplierPanel()
    {
        final JLabel nameLbl = new JLabel("Name");
        nameLbl.setFont(ApplicationStyles.STANDARD_FONT);
        addSupPanel.add(nameLbl);

        tfName = new JTextField("", 25);
        tfName.setFont(ApplicationStyles.STANDARD_FONT);
        addSupPanel.add(tfName, "wrap");

        final JLabel idLbl = new JLabel("ID");
        idLbl.setFont(ApplicationStyles.STANDARD_FONT);
        addSupPanel.add(idLbl);

        tfID = new JTextField("", 25);
        tfID.setFont(ApplicationStyles.STANDARD_FONT);
        addSupPanel.add(tfID, "wrap");

        final JButton submit = new JButton(
            new SupplierButtonActions.NewSupplierAction(
                "Submit",
                dialog,
                this));
        submit.setFont(ApplicationStyles.STANDARD_FONT);
        submit.setFocusable(false);
        submit.setBackground(ApplicationStyles.BUTTON_COLOR);
        addSupPanel.add(submit);

        final JButton cancel =
            new JButton(new DialogActions.CloseDialogAction(dialog, "Cancel"));
        cancel.setFont(ApplicationStyles.STANDARD_FONT);
        cancel.setFocusable(false);
        cancel.setBackground(ApplicationStyles.BUTTON_COLOR);
        addSupPanel.add(cancel);
    }


    public final String getSupplierID()
    {
        return tfID.getText().trim().toString();
    }


    public final String getSupplierName()
    {
        return tfName.getText().trim().toString();
    }


    public final JPanel getAddSupplierPanel()
    {
        return addSupPanel;
    }
}
