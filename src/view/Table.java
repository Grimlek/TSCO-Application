package view;

import java.awt.Dimension;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import view.editor.CurrencyCellEditor;
import view.editor.IntegerCellEditor;
import view.editor.StringCellEditor;

public class Table
        extends JTable {
    private final AbstractTableModel tableModel;


    public Table(AbstractTableModel tableModel) {
        super(tableModel);
        this.tableModel = tableModel;
        createTable();
    }


    private void createTable() {
        getTableHeader().setPreferredSize(
                new Dimension(getColumnModel().getTotalColumnWidth(), 48));
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setFont(ApplicationStyles.STANDARD_FONT);
        setFont(ApplicationStyles.TABLE_FONT);
        setRowHeight(20);
        setRowSelectionAllowed(true);
        getColumnModel().getColumn(4)
                .setCellRenderer(new CurrencyCellRenderer());
        addColumnEditors();
    }


    private void addColumnEditors() {
        getColumnModel().getColumn(0)
                .setCellEditor(new StringCellEditor(new JTextField(), tableModel));
        getColumnModel().getColumn(1)
                .setCellEditor(new StringCellEditor(new JTextField(), tableModel));
        getColumnModel().getColumn(2)
                .setCellEditor(new IntegerCellEditor(new JTextField(), tableModel));
        getColumnModel().getColumn(3)
                .setCellEditor(new IntegerCellEditor(new JTextField(), tableModel));
        getColumnModel().getColumn(4).setCellEditor(
                new CurrencyCellEditor(new JFormattedTextField(), tableModel));
        getColumnModel().getColumn(5)
                .setCellEditor(new IntegerCellEditor(new JTextField(), tableModel));
    }


    public void setTableColumnWidth() {
        final TableColumnModel columnModel = getColumnModel();

        if (super.getModel().getColumnCount() == 6) {
            columnModel.getColumn(0).setPreferredWidth(200);
            columnModel.getColumn(1).setPreferredWidth(300);
            columnModel.getColumn(2).setPreferredWidth(80);
            columnModel.getColumn(3).setPreferredWidth(75);
            columnModel.getColumn(4).setPreferredWidth(80);
            columnModel.getColumn(5).setPreferredWidth(75);
        }
    }
}
