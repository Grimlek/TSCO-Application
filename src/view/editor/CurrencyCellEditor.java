package view.editor;

import control.CSVFileController;
import control.validation.ValidateDouble;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import model.ErrorMessageModel;
import model.common.Product;
import model.tablemodel.AddProductTableModel;
import model.tablemodel.ProductTableModel;
import view.ApplicationStyles;
import view.panel.ErrorMessagePane;

public class CurrencyCellEditor
        extends DefaultCellEditor {
    private final JFormattedTextField textField;

    private final AbstractTableModel tableModel;

    private int productRow;


    public CurrencyCellEditor(
            JFormattedTextField textField,
            AbstractTableModel tableModel) {
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
        final DecimalFormat formatter = new DecimalFormat("$##,##0.00");
        this.productRow = row;

        textField.setFont(ApplicationStyles.TABLE_FONT);
        textField.addMouseListener(new TextFieldMouseAdapter());

        if (value != null) {
            formatter.setMinimumFractionDigits(2);
            formatter.setMinimumFractionDigits(2);
            textField.setText(formatter.format(value));
        }
        return textField;
    }


    @Override
    public Object getCellEditorValue() {
        if (!textField.getText().isEmpty()) {
            if (textField.getText().toString().contains(",")
                    || textField.getText().toString().contains("$"))
                return new BigDecimal(
                        textField.getText().toString().replaceAll("[,$]", ""));

            return new BigDecimal(textField.getText());
        }
        return new BigDecimal(0.00);
    }


    @Override
    public boolean stopCellEditing() {
        String value = textField.getText();

        if (tableModel instanceof ProductTableModel) {
            final ProductTableModel productTableModel =
                    (ProductTableModel) tableModel;
            final Product product = productTableModel.getProduct(productRow);

            if (value.contains(",") || value.contains("$"))
                value = value.replaceAll("[,$]", "");

            if (new ValidateDouble().validate(value)) {
                SwingUtilities.invokeLater(() -> {
                    CSVFileController.getControllerInstance().addProduct(
                            product.getSupplier().getName(),
                            product);
                });
                return super.stopCellEditing();
            }

            displayErrorMessage();
            return false;
        } else if (tableModel instanceof AddProductTableModel) {
            if (value.contains(",") || value.contains("$"))
                value = value.replaceAll("[,$]", "");
            if (new ValidateDouble().validate(value))
                return super.stopCellEditing();

            displayErrorMessage();
            return false;
        } else
            return false;
    }


    private void displayErrorMessage() {
        final ErrorMessageModel errorModel = new ErrorMessageModel();

        errorModel.loadProperties();

        if (errorModel.isDisplayable("ProductDoubleDisplay")) {
            final ErrorMessagePane pane = new ErrorMessagePane(
                    textField.getParent(),
                    errorModel.getErrorMessage("ProductDouble"));
            if (pane.isCheckBoxSelected()) {
                errorModel.saveProperties("ProductDoubleDisplay", "false");
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
