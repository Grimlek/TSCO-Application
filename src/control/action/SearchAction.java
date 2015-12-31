package control.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.table.TableModel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.Model;
import model.common.Product;
import model.common.Supplier;
import model.tablemodel.ProductTableModel;

public final class SearchAction
    extends AbstractAction
{
    private final ProductTableModel tableModel;


    public SearchAction(TableModel tableModel)
    {
        this.tableModel = (ProductTableModel)tableModel;
    }


    @Override
    public void actionPerformed(ActionEvent evt)
    {

        final String search = evt.getActionCommand();

        tableModel.resetTable();

        for (Supplier supplier : Model.getModelInstance().getSuppliers())
        {
            for (Product product : Model.getModelInstance()
                .getProducts(supplier.getName()))
            {
                if (product.getProductDescription().toLowerCase()
                    .contains(search.toLowerCase())
                    || (product.isCategorized()
                        && product.getCategories().equals(search)))
                    tableModel.addRow(product);
            }
        }
    }
}
