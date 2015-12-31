package model.common;

import java.util.Date;

public class Order
{
    private Product product;

    private Supplier supplier;

    private Date date;

    private double totalCost;


    public Order(
        Product product,
        Supplier supplier,
        double totalCost,
        Date date)
    {
        this.product = product;
        this.supplier = supplier;
        this.totalCost = totalCost;
        this.date = date;
    }


    public Product getProduct()
    {
        return product;
    }


    public void setProduct(Product product)
    {
        this.product = product;
    }


    public Supplier getSupplier()
    {
        return supplier;
    }


    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }


    public Date getDate()
    {
        return date;
    }


    public void setDate(Date date)
    {
        this.date = date;
    }


    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }


    public double getTotalCost()
    {
        return totalCost;
    }
}
