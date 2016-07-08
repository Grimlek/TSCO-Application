package control.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.table.TableModel;

import control.CSVFileController;
import model.ErrorMessageModel;
import model.common.Product;
import model.tablemodel.AddProductTableModel;
import view.panel.AddProductPanel;
import view.panel.ErrorMessagePane;

public class AddProductAction
        extends AbstractAction {
    private final AddProductTableModel tableModel;

    private final AddProductPanel panel;


    public AddProductAction(
            String name,
            TableModel tableModel,
            AddProductPanel panel) {
        super(name);
        this.tableModel = (AddProductTableModel) tableModel;
        this.panel = panel;
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        if (panel.getTable().isEditing())
            panel.getTable().getCellEditor().stopCellEditing();

        if (!panel.getSupplierTFText().isEmpty()) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Product product = tableModel.getProduct(i);

                if (product.validate()) {
                    CSVFileController.getControllerInstance().addProduct(
                            panel.getSupplierTFText(),
                            tableModel.getProduct(i));
                } else if ((product.getId().length() == 0
                        || product.getProductDescription().length() == 0)
                        && (product.getId().length() != 0
                        || product.getProductDescription().length() != 0)) {
                    final ErrorMessageModel errorModel =
                            new ErrorMessageModel();

                    errorModel.loadProperties();

                    if (errorModel.isDisplayable("AddProductDisplay")) {
                        final ErrorMessagePane pane = new ErrorMessagePane(
                                panel.getAddProductPanel(),
                                errorModel.getErrorMessage("AddProduct"));
                        if (pane.isCheckBoxSelected()) {
                            errorModel
                                    .saveProperties("AddProductDisplay", "false");
                            errorModel.storeProperties();
                        }
                    }
                }
            }
        } else {
            final ErrorMessageModel errorModel = new ErrorMessageModel();

            errorModel.loadProperties();

            if (errorModel.isDisplayable("SelectSupplierDisplay")) {
                final ErrorMessagePane pane = new ErrorMessagePane(
                        panel.getAddProductPanel(),
                        errorModel.getErrorMessage("SelectSupplier"));
                if (pane.isCheckBoxSelected()) {
                    errorModel.saveProperties("SelectSupplierDisplay", "false");
                    errorModel.storeProperties();
                }
            }
        }
    }
}
