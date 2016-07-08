package view.panel;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.action.SupplierButtonActions;
import model.Model;
import model.common.Supplier;
import net.miginfocom.swing.MigLayout;
import view.ApplicationStyles;
import view.Dialog;

public class SelectSupplierScrollPane {
    private final Model model;

    private final Dialog dialog;

    private final AddProductPanel addProdPanel;

    private final JScrollPane selectSupplierScrollPane;

    private final JPanel selectSupplierPanel;


    public SelectSupplierScrollPane(Dialog dialog, AddProductPanel addProdPanel) {
        this.dialog = dialog;
        this.addProdPanel = addProdPanel;
        this.model = Model.getModelInstance();
        this.selectSupplierPanel = new JPanel(new MigLayout());
        this.selectSupplierScrollPane = new JScrollPane(
                selectSupplierPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        selectSupplierScrollPane.setViewportView(selectSupplierPanel);
        addSupplierNameButtons();
    }


    public JScrollPane getScrollPane() {
        return selectSupplierScrollPane;
    }


    private void addSupplierNameButtons() {
        for (Supplier supplier : model.getSuppliers()) {
            JButton button = new JButton(
                    new SupplierButtonActions.SetSupplierAction(
                            supplier.getName(),
                            dialog,
                            addProdPanel));
            button.setFont(ApplicationStyles.STANDARD_FONT);
            button.setBackground(ApplicationStyles.BUTTON_COLOR);
            button.setPreferredSize(new Dimension(230, 30));
            button.setBorderPainted(false);
            selectSupplierPanel.add(button, "wrap, center");
        }
    }
}
