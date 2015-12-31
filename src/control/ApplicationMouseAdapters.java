package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.common.Product;
import model.tablemodel.ProductTableModel;
import view.Dialog;
import view.panel.KeywordPanel;

public final class ApplicationMouseAdapters
{
    private static int posX;

    private static int posY;


    public final static class TableMouseAdapter
        extends MouseAdapter
    {

        private final Dialog dialog;

        private final ProductTableModel tableModel;

        private KeywordPanel keywordPanel;

        private Product product;


        public TableMouseAdapter(Dialog dialog, TableModel tableModel)
        {
            this.dialog = dialog;
            this.tableModel = (ProductTableModel)tableModel;
        }


        @Override
        public void mousePressed(MouseEvent evt)
        {
            if (evt.getButton() == MouseEvent.BUTTON3)
            {
                product = tableModel.getProduct(
                    ((JTable)evt.getSource()).rowAtPoint(evt.getPoint()));
                keywordPanel = new KeywordPanel(dialog, product);

                if (product.isCategorized())
                {
                    for (int y = 0; y < product.getCategories().size(); y++)
                    {
                        keywordPanel.setCategoryTextFields(
                            y,
                            (String)new ArrayList<Object>(
                                product.getCategories()).get(y));
                    }
                }

                dialog.addContainer(keywordPanel.getKeywordPanel());
                dialog.setLocationToMousePoint(
                    evt.getXOnScreen(),
                    evt.getYOnScreen());
                dialog.setVisibility(true);
            }
        }
    }


    public final static class FrameMouseMotionListener
        extends MouseAdapter
    {
        private final JFrame frame;


        public FrameMouseMotionListener(JFrame frame)
        {
            this.frame = frame;
        }


        @Override
        public void mouseDragged(MouseEvent evt)
        {
            frame.setLocation(
                evt.getXOnScreen() - posX,
                evt.getYOnScreen() - posY);
        }
    }


    public final static class FrameMouseAdapter
        extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent evt)
        {
            posX = evt.getX();
            posY = evt.getY();
        }
    }
}
