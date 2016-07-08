package view.editor;

import control.CSVFileController;
import control.validation.ValidateString;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import model.ErrorMessageModel;
import model.common.Product;
import model.tablemodel.AddProductTableModel;
import model.tablemodel.ProductTableModel;
import view.ApplicationStyles;
import view.panel.ErrorMessagePane;

public class StringCellEditor
        extends DefaultCellEditor {
    private final JTextField textField;

    private final AbstractTableModel tableModel;

    private int productRow;


    public StringCellEditor(JTextField textField, AbstractTableModel tableModel) {
        super(textField);
        this.textField = textField;
        this.tableModel = tableModel;
        productRow = 0;
    }


    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column) {
        this.productRow = row;

        textField.setFont(ApplicationStyles.TABLE_FONT);
        textField.addMouseListener(new TextFieldMouseAdapter());

        if (value != null)
            textField.setText(value.toString());
        return textField;
    }


    @Override
    public Object getCellEditorValue() {
        if (!textField.getText().isEmpty())
            return textField.getText();
        else
            return "";
    }


    @Override
    public boolean stopCellEditing() {
        final String value = textField.getText();

        if (tableModel instanceof ProductTableModel) {
            final ProductTableModel productTableModel =
                    (ProductTableModel) tableModel;
            final Product product = productTableModel.getProduct(productRow);

            if (new ValidateString().validate(value)) {
                SwingUtilities.invokeLater(() ->
                {
                    CSVFileController.getControllerInstance().addProduct(
                            product.getSupplier().getName(),
                            product);

                });
                return super.stopCellEditing();
            }

            displayErrorMessage();
            return false;
        } else if (tableModel instanceof AddProductTableModel) {
            if (new ValidateString().validate(value) || value.length() == 0)
                return super.stopCellEditing();

            displayErrorMessage();
            return false;
        } else
            return false;
    }


    private void displayErrorMessage() {
        final ErrorMessageModel errorModel = new ErrorMessageModel();

        errorModel.loadProperties();

        if (errorModel.isDisplayable("ProductStringDisplay")) {
            final ErrorMessagePane pane = new ErrorMessagePane(
                    textField.getParent(),
                    errorModel.getErrorMessage("ProductString"));
            if (pane.isCheckBoxSelected()) {
                errorModel.saveProperties("ProductStringDisplay", "false");
                errorModel.storeProperties();
            }
        }
    }


    private class TextFieldMouseAdapter
            extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent evt) {
            if ((evt.getButton() == MouseEvent.BUTTON1)
                    && evt.getClickCount() == 2)
                SwingUtilities.invokeLater(() -> {

                    final int offset =
                            textField.viewToModel(evt.getPoint());
                    textField.setCaretPosition(offset);

                });
        }
    }

}
