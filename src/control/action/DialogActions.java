package control.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import control.CSVFileController;
import model.Model;
import view.Dialog;
import view.panel.AddProductPanel;
import view.panel.AddSupplierPanel;
import view.panel.SelectSupplierScrollPane;

public final class DialogActions
{
    public final static class CloseDialogAction
        extends AbstractAction
    {
        private final Dialog dialog;


        public CloseDialogAction(Dialog dialog, String name)
        {
            super(name);
            this.dialog = dialog;
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            dialog.getDialog().dispose();
        }
    }


    public final static class AddProductPanelAction
        extends AbstractAction
    {
        private final Dialog dialog;

        private final Model model;


        public AddProductPanelAction(Dialog dialog, String name)
        {
            super(name);
            this.dialog = dialog;
            this.model = Model.getModelInstance();
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            dialog
                .addContainer(new AddProductPanel(dialog).getAddProductPanel());
            dialog.setVisibility(true);
        }
    }


    public final static class AddSupplierPanelAction
        extends AbstractAction
    {
        private final Dialog dialog;


        public AddSupplierPanelAction(Dialog dialog, String name)
        {
            super(name);
            this.dialog = dialog;
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            dialog.addContainer(
                new AddSupplierPanel(dialog).getAddSupplierPanel());
            dialog.setVisibility(true);
        }
    }


    public static class AddSelectSupplierPanelAction
        extends AbstractAction
    {
        private final Dialog dialog;

        private final Model model;

        private final AddProductPanel addProdPanel;


        public AddSelectSupplierPanelAction(AddProductPanel addProdPanel)
        {
            this.model = Model.getModelInstance();
            this.addProdPanel = addProdPanel;
            this.dialog = new Dialog();
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            dialog.addContainer(
                new SelectSupplierScrollPane(dialog, addProdPanel)
                    .getScrollPane());
            dialog.setVisibility(true);
        }
    }
}
