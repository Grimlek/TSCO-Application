package view.panel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import control.action.AddProductAction;
import control.action.DialogActions;
import net.miginfocom.swing.MigLayout;
import view.ApplicationStyles;
import view.Dialog;
import view.Table;
import model.tablemodel.AddProductTableModel;

public class AddProductPanel {
    private final AbstractTableModel tableModel;

    private final Dialog dialog;

    private final JPanel addProdPanel;

    private final Table table;

    private JTextField supplierTF;


    public AddProductPanel(Dialog dialog) {
        this.dialog = dialog;
        this.tableModel = new AddProductTableModel();
        this.table = new Table(tableModel);
        this.addProdPanel = new JPanel(new MigLayout("flowy"));
        createAddProductPanel();
    }


    private void createAddProductPanel() {
        final JLabel supplierLbl = new JLabel("Supplier");
        supplierLbl.setFont(ApplicationStyles.STANDARD_FONT);
        addProdPanel.add(supplierLbl, "flowx");

        supplierTF = new JTextField("", 25);
        supplierTF.setFont(ApplicationStyles.STANDARD_FONT);
        supplierTF.setEditable(false);
        supplierTF.setBackground(Color.WHITE);
        addProdPanel.add(supplierTF, "cell 0 0, gap 10px");

        final JButton supplierBut =
                new JButton(new DialogActions.AddSelectSupplierPanelAction(this));
        supplierBut.setBackground(ApplicationStyles.BUTTON_COLOR);
        supplierBut.setPreferredSize(new Dimension(20, 22));
        supplierBut.setFocusable(false);
        addProdPanel.add(supplierBut, "cell 0 0");

        table.setTableColumnWidth();

        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(850, 259));
        addProdPanel.add(scrollPane, "wrap");

        final JButton submit =
                new JButton(new AddProductAction("Submit", table.getModel(), this));
        submit.setFont(ApplicationStyles.STANDARD_FONT);
        submit.setBackground(ApplicationStyles.BUTTON_COLOR);
        submit.setFocusable(false);
        addProdPanel.add(submit, "cell 0 2, flowx, alignx left");

        final JButton cancel =
                new JButton(new DialogActions.CloseDialogAction(dialog, "Cancel"));
        cancel.setFont(ApplicationStyles.STANDARD_FONT);
        cancel.setBackground(ApplicationStyles.BUTTON_COLOR);
        cancel.setFocusable(false);
        addProdPanel.add(cancel, "cell 0 2");
    }


    public void setTableColumnWidth(TableModel tableModel) {
        final TableColumnModel columnModel = table.getColumnModel();

        if (tableModel.getColumnCount() == 6) {
            columnModel.getColumn(0).setPreferredWidth(200);
            columnModel.getColumn(1).setPreferredWidth(300);
            columnModel.getColumn(2).setPreferredWidth(80);
            columnModel.getColumn(3).setPreferredWidth(75);
            columnModel.getColumn(4).setPreferredWidth(80);
            columnModel.getColumn(5).setPreferredWidth(75);
        }
    }


    public JDialog getDialog() {
        return dialog.getDialog();
    }


    public JTable getTable() {
        return table;
    }


    public void setSupplierTFText(String data) {
        supplierTF.setText(data);
    }


    public String getSupplierTFText() {
        return supplierTF.getText().trim();
    }


    public final JPanel getAddProductPanel() {
        return addProdPanel;
    }
}
