package view.panel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.KeywordPanelController;
import model.common.Product;
import net.miginfocom.swing.MigLayout;
import view.ApplicationStyles;
import view.Dialog;

public class KeywordPanel {
    private final Product product;

    private final Dialog dialog;

    private final JPanel keywordPanel;

    private JTextField[] textFields;


    public KeywordPanel(Dialog dialog, Product product) {
        this.product = product;
        this.dialog = dialog;
        this.keywordPanel = new JPanel(new MigLayout());
        createKeywordPanel();
    }


    private void createKeywordPanel() {
        final JLabel searchLbl = new JLabel("KeyWords");
        searchLbl.setFont(ApplicationStyles.STANDARD_FONT);
        keywordPanel.add(searchLbl, "wrap");

        textFields = new JTextField[]{new JTextField(""), new JTextField(""),
                new JTextField(""), new JTextField(""), new JTextField("")};
        for (JTextField textField : textFields) {
            textField.setFont(ApplicationStyles.STANDARD_FONT);
            textField.setPreferredSize(new Dimension(242, 30));
            keywordPanel.add(textField, "wrap");
        }

        final JButton remove = new JButton(
                new KeywordPanelController.RemoveAction("Remove", product));
        remove.setBackground(ApplicationStyles.BUTTON_COLOR);
        remove.setFont(ApplicationStyles.STANDARD_FONT);
        keywordPanel.add(remove);

        final JButton close = new JButton(
                new KeywordPanelController.SaveAndCloseAction(
                        "Save & Close",
                        dialog,
                        product,
                        textFields));
        close.setBackground(ApplicationStyles.BUTTON_COLOR);
        close.setFont(ApplicationStyles.STANDARD_FONT);
        keywordPanel.add(close, "cell 0 6, gapx 30px");
    }


    public void setCategoryTextFields(int index, String category) {
        textFields[index].addMouseListener(
                new KeywordPanelController.TextFieldMouseAdapter(
                        textFields[index]));
        textFields[index].setEditable(false);
        textFields[index].setBackground(Color.WHITE);
        textFields[index].setText(category);
    }


    public void setLocation(int x, int y) {
        keywordPanel.setLocation(x, y);
    }


    public JPanel getKeywordPanel() {
        return keywordPanel;
    }
}
