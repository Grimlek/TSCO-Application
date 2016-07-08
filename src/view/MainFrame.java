package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.ApplicationMouseAdapters;
import control.action.CloseAction;
import control.action.DialogActions;
import control.action.SearchAction;
import control.action.SupplierButtonActions;
import net.miginfocom.swing.MigLayout;
import model.Model;
import model.common.Supplier;
import model.tablemodel.ProductTableModel;

public class MainFrame {
    private final JFrame frame;

    private final JPanel mainPanel;

    private final Dialog dialog;

    private final MenuBar menuBar;

    private final ButtonPanel buttonPanel;

    private final Table table;

    private static MainFrame mainFrame;


    private MainFrame() {
        this.frame = new JFrame();
        this.dialog = new Dialog();
        this.table = new Table(new ProductTableModel());
        this.menuBar = new MenuBar();
        this.buttonPanel = new ButtonPanel();
        this.mainPanel = new JPanel(new MigLayout("", "", "[]13[]"));
        createMainFrame();
    }


    private void createMainFrame() {
        table.setTableColumnWidth();
        table.addMouseListener(
                new ApplicationMouseAdapters.TableMouseAdapter(
                        dialog,
                        table.getModel()));

        mainPanel.add(menuBar.getParent());

        final JLabel search = new JLabel("Search");
        mainPanel.add(search, "cell 0 0, gap 25px");

        final JTextField searchTF = new JTextField();
        searchTF.setPreferredSize(new Dimension(200, 20));
        searchTF.addActionListener(new SearchAction(table.getModel()));
        mainPanel.add(searchTF, "cell 0 0");

        final JButton xButton = new JButton(new CloseAction("X"));
        xButton.setFocusable(false);
        xButton.setFont(ApplicationStyles.STANDARD_FONT);
        xButton.setBackground(new Color(158, 7, 7));
        mainPanel.add(xButton, "cell 0 0, gap 398px, wrap");

        final JScrollPane buttonScrollPane = new JScrollPane(
                buttonPanel.getParent(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        buttonScrollPane.setViewportView(buttonPanel.getParent());
        buttonScrollPane.setPreferredSize(new Dimension(325, 990));
        mainPanel.add(buttonScrollPane);

        final JScrollPane listScrollPane = new JScrollPane(
                table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScrollPane.setPreferredSize(new Dimension(850, 990));
        mainPanel.add(listScrollPane, "cell 0 1");

        frame.getContentPane().add(mainPanel);
        frame.setUndecorated(true);
        frame.setPreferredSize(new Dimension(1100, 550));
        frame
                .addMouseListener(new ApplicationMouseAdapters.FrameMouseAdapter());
        frame.addMouseMotionListener(
                new ApplicationMouseAdapters.FrameMouseMotionListener(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public JFrame getFrame() {
        return frame;
    }


    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }


    public static MainFrame getMainFrameInstance() {
        if (mainFrame == null)
            mainFrame = new MainFrame();

        return mainFrame;
    }


    public class ButtonPanel {
        private final JPanel panel;


        public ButtonPanel() {
            this.panel = new JPanel(new MigLayout());
            addButtons();
        }


        public void addButtons() {
            final TreeSet<Supplier> supplierList =
                    Model.getModelInstance().getSuppliers();

            for (Supplier supplier : supplierList) {
                final JButton button = new JButton(
                        new SupplierButtonActions.ShowSupplierProductsAction(
                                supplier.getName(),
                                table));
                button.setFocusable(false);
                button.setFont(ApplicationStyles.STANDARD_FONT);
                button.setBackground(ApplicationStyles.BUTTON_COLOR);
                button.setPreferredSize(new Dimension(250, 30));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(button, "wrap");
            }
        }


        public JPanel getParent() {
            return panel;
        }
    }


    private class MenuBar {
        private final JMenuBar menuBar;


        private MenuBar() {
            this.menuBar = new JMenuBar();
            createMenuBar();
        }


        private void createMenuBar() {

            final JMenuItem close = new JMenuItem(new CloseAction("Close"));
            close.setPreferredSize(new Dimension(125, 25));
            close.setFont(ApplicationStyles.HEADER_FONT);

            final JMenu application = new JMenu("Application");
            application.setFont(ApplicationStyles.HEADER_FONT);
            application.setPreferredSize(new Dimension(125, 70));
            application.add(close);
            menuBar.add(application);

            final JMenuItem newSupplier = new JMenuItem(
                    new DialogActions.AddSupplierPanelAction(dialog, "New"));
            newSupplier.setPreferredSize(new Dimension(125, 25));
            newSupplier.setFont(ApplicationStyles.HEADER_FONT);

            final JMenuItem addProduct = new JMenuItem(
                    new DialogActions.AddProductPanelAction(
                            dialog,
                            "Add Products"));
            addProduct.setPreferredSize(new Dimension(125, 25));
            addProduct.setFont(ApplicationStyles.HEADER_FONT);

            final JMenu suppliers = new JMenu("Suppliers");
            suppliers.setFont(ApplicationStyles.HEADER_FONT);
            suppliers.setPreferredSize(new Dimension(125, 70));
            suppliers.add(newSupplier);
            suppliers.add(addProduct);
            menuBar.add(suppliers);

            final JMenuItem viewOrders = new JMenuItem("View");
            viewOrders.setFont(ApplicationStyles.HEADER_FONT);
            viewOrders.setPreferredSize(new Dimension(125, 25));

            final JMenu orders = new JMenu("Orders");
            orders.setFont(ApplicationStyles.HEADER_FONT);
            orders.setPreferredSize(new Dimension(125, 70));
            orders.add(viewOrders);
            menuBar.add(orders);
        }


        private JMenuBar getParent() {
            return menuBar;
        }
    }
}
