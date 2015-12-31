package view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;

public final class CurrencyCellRenderer
    extends DefaultTableCellRenderer
{

    @Override
    public void setValue(Object value)
    {
        if (value != null)
        {
            BigDecimal decimalValue =
                new BigDecimal(Double.parseDouble(value.toString()));
            decimalValue = decimalValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);

            DecimalFormat formatter = new DecimalFormat("$##,##0.00");
            formatter.setMinimumFractionDigits(2);
            formatter.setMinimumFractionDigits(2);
            String formattedValue = formatter.format(value);
            super.setValue(formattedValue);
        }
        else
            super.setValue("$0.00");
    }
}
