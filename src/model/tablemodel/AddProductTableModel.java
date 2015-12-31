package model.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.common.Product;

public class AddProductTableModel
    extends AbstractTableModel
{
    private List<Product> productList;

    private final String[] columnNames = { "ID", "Description", "Inventory",
        "Minimum Quantity", "Cost", "Order Quantity" };


    public AddProductTableModel()
    {
        productList = new ArrayList<Product>();
        createEmptyTable();
    }


    public void createEmptyTable()
    {
        while (productList.size() <= 12)
        {
            productList.add(new Product("", "", null, null, null, null));
        }
    }


    public void resetTable()
    {
        productList.clear();
        fireTableDataChanged();
    }


    public void addRow(Product product)
    {
        productList.add(product);
        fireTableRowsInserted(productList.size() - 1, productList.size() - 1);
    }


    public final Product getProduct(int row)
    {
        return productList.get(row);
    }


    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }


    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "<html>ID<br></html>";
            case 1:
                return "<html>Description<br></html>";
            case 2:
                return "<html>Inventory<br></html>";
            case 3:
                return "<html>Minimum<br>Quantity</html>";
            case 4:
                return "<html>Cost<br></html>";
            case 5:
                return "<html>Order<br>Quantity</html>";
            default:
                return null;
        }
    }


    @Override
    public int getRowCount()
    {
        return productList.size();
    }


    @Override
    public boolean isCellEditable(int row, int column)
    {
        return true;
    }


    @Override
    public Object getValueAt(int row, int column)
    {
        final Product product = productList.get(row);

        switch (column)
        {
            case 0:
                return product.getId();
            case 1:
                return product.getProductDescription();
            case 2:
                return product.getQtyOnHand();
            case 3:
                return product.getMinQty();
            case 4:
                return product.getCost();
            case 5:
                return product.getOrderQty();
            default:
                throw new IndexOutOfBoundsException();
        }
    }


    @Override
    public void setValueAt(Object value, int row, int column)
    {
        final Product product = productList.get(row);

        switch (column)
        {
            case 0:
                product.setId((String)value);
                break;
            case 1:
                product.setProductDescription((String)value);
                break;
            case 2:
                product.setQtyOnHand(Integer.parseInt(value.toString()));
                break;
            case 3:
                product.setMinQty(Integer.parseInt(value.toString()));
                break;
            case 4:
                product.setCost(new BigDecimal(value.toString()));
                break;
            case 5:
                product.setOrderQty(Integer.parseInt(value.toString()));
                break;
            default:
                throw new IndexOutOfBoundsException();
        }

        fireTableCellUpdated(row, column);
    }
}
