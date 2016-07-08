package model.common;

public class Supplier
        implements Comparable<Supplier> {
    private String name;

    private int id;


    public Supplier(String name, int id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }


    @Override
    public int compareTo(Supplier supplier) {
        return name.compareTo(supplier.name);
    }
}
