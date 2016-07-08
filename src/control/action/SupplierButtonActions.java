package control.action;

import java.awt.event.ActionEvent;
import java.util.TreeSet;
import javax.swing.AbstractAction;

import control.CSVFileController;
import control.validation.ValidateString;
import control.validation.ValidateSupplierID;
import view.Dialog;
import view.MainFrame;
import view.Table;
import view.panel.AddProductPanel;
import view.panel.AddSupplierPanel;
import view.panel.ErrorMessagePane;
import model.ErrorMessageModel;
import model.Model;
import model.common.Product;
import model.common.Supplier;
import model.tablemodel.ProductTableModel;

public class SupplierButtonActions {
    public static class ShowSupplierProductsAction
            extends AbstractAction {
        private final ProductTableModel tableModel;

        private final Table table;

        private String name;


        public ShowSupplierProductsAction(String name, Table table) {
            super(name);
            this.name = name;
            this.table = table;
            this.tableModel = (ProductTableModel) table.getModel();
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            final TreeSet<Product> productList =
                    Model.getModelInstance().getProducts(name);

            if (table.getCellEditor() != null)
                table.getCellEditor().stopCellEditing();

            tableModel.resetTable();

            for (Product product : productList) {
                tableModel.addRow(product);
            }
        }
    }


    public static class SetSupplierAction
            extends AbstractAction {
        private final String name;

        private final Dialog dialog;

        private final AddProductPanel addProdPanel;


        public SetSupplierAction(
                String name,
                Dialog dialog,
                AddProductPanel addProdPanel) {
            super(name);
            this.name = name;
            this.dialog = dialog;
            this.addProdPanel = addProdPanel;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.getDialog().dispose();
            addProdPanel.setSupplierTFText(name);
        }
    }


    public static class NewSupplierAction
            extends AbstractAction {
        private final Dialog dialog;

        private final AddSupplierPanel panel;


        public NewSupplierAction(
                String name,
                Dialog dialog,
                AddSupplierPanel panel) {
            super(name);
            this.panel = panel;
            this.dialog = dialog;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (new ValidateSupplierID().validate(panel.getSupplierID())
                    && new ValidateString().validate(panel.getSupplierName())) {
                CSVFileController.getControllerInstance().addSupplier(
                        new Supplier(
                                panel.getSupplierName(),
                                Integer.parseInt(panel.getSupplierID())));
                MainFrame.getMainFrameInstance().getButtonPanel().getParent()
                        .removeAll();
                MainFrame.getMainFrameInstance().getButtonPanel().addButtons();
                MainFrame.getMainFrameInstance().getButtonPanel().getParent()
                        .revalidate();
                dialog.getDialog().dispose();
            } else {
                final ErrorMessageModel errorModel = new ErrorMessageModel();

                errorModel.loadProperties();

                if (errorModel.isDisplayable("NewSupplierDisplay")) {
                    final ErrorMessagePane pane = new ErrorMessagePane(
                            panel.getAddSupplierPanel(),
                            errorModel.getErrorMessage("NewSupplier"));
                    if (pane.isCheckBoxSelected()) {
                        errorModel
                                .saveProperties("NewSupplierDisplay", "false");
                        errorModel.storeProperties();
                    }
                }
            }
        }
    }
}
