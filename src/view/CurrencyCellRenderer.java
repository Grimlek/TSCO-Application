package view;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;

public class CurrencyCellRenderer
        extends DefaultTableCellRenderer {

    @Override
    public void setValue(Object value) {
        if (value != null) {
            DecimalFormat formatter = new DecimalFormat("$##,##0.00");
            formatter.setMinimumFractionDigits(2);
            formatter.setMinimumFractionDigits(2);
            String formattedValue = formatter.format(value);
            super.setValue(formattedValue);
        } else
            super.setValue("$0.00");
    }
}
