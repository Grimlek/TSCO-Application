package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import model.Model;
import model.common.Product;
import model.common.Supplier;

public class CSVFileController {
    private BufferedReader reader;

    private BufferedWriter writer;

    private String line;

    private Model model;

    private static CSVFileController controller;


    private CSVFileController() {
        this.model = Model.getModelInstance();
        this.reader = null;
        this.writer = null;
        this.line = "";
        updateModel();
    }


    public void updateModel() {
        synchronized (this) {
            final TreeSet<Supplier> suppliers = new TreeSet<Supplier>();

            try {
                reader = new BufferedReader(new FileReader("Suppliers.csv"));

                while ((line = reader.readLine()) != null) {
                    String[] dataArray = line.split(",");
                    suppliers.add(
                            new Supplier(
                                    dataArray[0],
                                    Integer.parseInt(dataArray[1])));
                }

                for (Supplier supplier : suppliers) {
                    TreeSet<Product> productList = new TreeSet<Product>();

                    reader = new BufferedReader(
                            new FileReader(supplier.getName() + ".csv"));

                    while ((line = reader.readLine()) != null) {
                        String[] dataArray = line.split(",");
                        if (dataArray.length == 6) {
                            productList.add(
                                    new Product(
                                            supplier,
                                            dataArray[0],
                                            dataArray[1],
                                            Integer.parseInt(dataArray[2]),
                                            Integer.parseInt(dataArray[3]),
                                            new BigDecimal(dataArray[4]),
                                            Integer.parseInt(dataArray[5])));
                        } else {
                            Set<String> categories = new TreeSet<String>();

                            for (int x = 6; x < dataArray.length; x++) {
                                categories.add(dataArray[x]);
                            }

                            productList.add(
                                    new Product(
                                            supplier,
                                            dataArray[0],
                                            dataArray[1],
                                            Integer.parseInt(dataArray[2]),
                                            Integer.parseInt(dataArray[3]),
                                            new BigDecimal(dataArray[4]),
                                            Integer.parseInt(dataArray[5]),
                                            categories));
                        }
                    }

                    model.addProductList(supplier, productList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeReader(reader);
            }
        }
    }


    public void adjustProductCategories(Product product) {
        final TreeSet<Product> supplierProductList =
                model.getProducts(product.getSupplier().getName());

        try {
            writer = new BufferedWriter(
                    new FileWriter(product.getSupplier().getName() + ".csv"));

            for (Product prod : supplierProductList) {
                supplierProductList.add(product);
                model
                        .addProductList(product.getSupplier(), supplierProductList);

                writer.write(prod.toString());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeWriter(writer);
        }
    }


    public void addSupplier(Supplier supplier) {
        synchronized (this) {
            try {
                File file = new File(
                        File.separator + "TSCOApplication" + File.separator
                                + supplier.getName() + ".csv");

                if (file.getParentFile().mkdirs())
                    file.createNewFile();

                model.addSupplier(supplier);

                TreeSet<Supplier> suppliers = model.getSuppliers();

                writer = new BufferedWriter(
                        new FileWriter(supplier.getName() + ".csv"));

                model.addSupplier(supplier);

                for (Supplier sup : suppliers) {
                    writer.write(sup.toString());
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                closeWriter(writer);
            }
        }
    }


    public void addProduct(String name, Product product) {
        synchronized (this) {
            model.addProduct(name, product);

            try {
                writer = new BufferedWriter(new FileWriter(name + ".csv"));

                for (Product prod : model.getProducts(name)) {
                    writer.write(prod.toString());
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                closeWriter(writer);
            }
        }
    }


    public void closeWriter(BufferedWriter bw) {
        try {
            if (bw != null)
                bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeReader(BufferedReader br) {
        try {
            if (br != null)
                br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static CSVFileController getControllerInstance() {
        if (controller == null)
            controller = new CSVFileController();

        return controller;
    }
}
