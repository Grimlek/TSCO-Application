package model;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import model.common.Product;
import model.common.Supplier;

public class Model {
    private Map<Supplier, TreeSet<Product>> suppliersProductList;

    private static Model model;


    private Model() {
        this.suppliersProductList = new TreeMap<>();
    }


    public synchronized void addSupplier(Supplier supplier) {
        if (!suppliersProductList.containsKey(supplier))
            this.suppliersProductList.put(supplier, new TreeSet<>());
    }


    public synchronized void addProduct(String name, Product product) {
        TreeSet<Product> productList;

        for (Supplier supplier : suppliersProductList.keySet()) {
            if (supplier.getName().equals(name)) {
                productList = suppliersProductList.get(supplier);
                productList.add(product);
                suppliersProductList.put(supplier, productList);
            }
        }
    }


    public synchronized void
    addProductList(Supplier supplier, TreeSet<Product> productList) {
        this.suppliersProductList.put(supplier, productList);
    }


    public TreeSet<Product> getProducts(String name) {
        for (Supplier supplier : getSuppliers()) {
            if (supplier.getName().equals(name))
                return new TreeSet<Product>(suppliersProductList.get(supplier));
        }
        return null;
    }


    public TreeSet<Supplier> getSuppliers() {
        return new TreeSet<>(suppliersProductList.keySet());
    }


    public Map<Supplier, TreeSet<Product>> getSuppliersProductList() {
        return suppliersProductList;
    }


    public static Model getModelInstance() {
        if (model == null)
            model = new Model();

        return model;
    }
}
