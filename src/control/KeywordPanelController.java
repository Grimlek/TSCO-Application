package control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeSet;
import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import view.Dialog;
import control.validation.ValidateString;
import model.common.Product;

public final class KeywordPanelController
{
    public final static class RemoveAction
        extends TextAction
    {
        private final Product product;


        public RemoveAction(String name, Product product)
        {
            super(name);
            this.product = product;
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            final JTextComponent textField = (JTextField)getFocusedComponent();

            if (!textField.isEditable())
            {
                product.getCategories().remove(textField.getText().trim());
                product.setCategories(product.getCategories());
                CSVFileController.getControllerInstance()
                    .adjustProductCategories(product);
                textField.setText("");
                textField.setEditable(true);
            }
        }
    }


    public final static class TextFieldMouseAdapter
        extends MouseAdapter
    {
        private final JTextField textField;


        public TextFieldMouseAdapter(JTextField textField)
        {
            this.textField = textField;
        }


        @Override
        public void mouseClicked(MouseEvent evt)
        {
            textField.setSelectionColor(new Color(142, 15, 6));
            textField.setSelectedTextColor(new Color(255, 255, 255));
            textField.setSelectionStart(0);
            textField.setSelectionEnd(textField.getText().trim().length());
        }
    }


    public static class SaveAndCloseAction
        extends AbstractAction
    {
        private final JTextField[] textFields;

        private final Product product;

        private final Dialog dialog;


        public SaveAndCloseAction(
            String name,
            Dialog dialog,
            Product product,
            JTextField[] textFields)
        {
            super(name);
            this.textFields = textFields;
            this.product = product;
            this.dialog = dialog;
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {

            dialog.getDialog().dispose();

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run()
                {
                    final TreeSet<String> categories = new TreeSet<String>();

                    for (JTextField textField : textFields)
                    {
                        if (new ValidateString()
                            .validate(textField.getText().trim()))
                            categories.add(textField.getText().trim());
                    }

                    product.setCategories(categories);

                    CSVFileController.getControllerInstance()
                        .adjustProductCategories(product);
                }
            });
        }
    }
}
