package model.common;

import java.math.BigDecimal;
import java.util.Set;

import control.validation.ValidateDouble;
import control.validation.ValidateInteger;
import control.validation.ValidateString;

public class Product
        implements Comparable<Product> {
    private Supplier supplier;

    private Set<String> categoryList;

    private Integer minQty, qtyOnHand, orderQty;

    private BigDecimal cost;

    private String productDescription, id;


    public Product(
            Supplier supplier,
            String id,
            String productDescription,
            int qtyOnHand,
            int minQty,
            BigDecimal cost,
            int orderQty,
            Set<String> categories) {
        this.supplier = supplier;
        this.qtyOnHand = qtyOnHand;
        this.orderQty = orderQty;
        this.id = id;
        this.minQty = minQty;
        this.cost = cost;
        this.productDescription = productDescription;
        this.categoryList = categories;
    }


    public Product(
            Supplier supplier,
            String id,
            String productDescription,
            int qtyOnHand,
            int minQty,
            BigDecimal cost,
            int orderQty) {
        this.supplier = supplier;
        this.qtyOnHand = qtyOnHand;
        this.orderQty = orderQty;
        this.id = id;
        this.minQty = minQty;
        this.cost = cost;
        this.productDescription = productDescription;
    }


    public Product(
            String id,
            String productDescription,
            Integer qtyOnHand,
            Integer minQty,
            BigDecimal cost,
            Integer orderQty) {
        this.qtyOnHand = qtyOnHand;
        this.orderQty = orderQty;
        this.id = id;
        this.minQty = minQty;
        this.cost = cost;
        this.productDescription = productDescription;
    }


    public Integer getQtyOnHand() {
        return qtyOnHand;
    }


    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }


    public Integer getOrderQty() {
        return orderQty;
    }


    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public BigDecimal getCost() {
        return cost;
    }


    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


    public String getProductDescription() {
        return productDescription;
    }


    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public Integer getMinQty() {
        return minQty;
    }


    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }


    public Set<String> getCategories() {
        return categoryList;
    }


    public void setCategories(Set<String> categories) {
        this.categoryList = categories;
    }


    public Supplier getSupplier() {
        return supplier;
    }


    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


    public boolean isCategorized() {
        if (categoryList == null) {
            return false;
        } else {
            return true;
        }
    }


    public boolean validate() {
        if (new ValidateString().validate(id)
                && new ValidateString().validate(productDescription)
                && new ValidateInteger().validate(qtyOnHand)
                && new ValidateInteger().validate(minQty)
                && new ValidateDouble().validate(cost)
                && new ValidateInteger().validate(orderQty)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public int compareTo(Product product) {
        return productDescription.compareTo(product.getProductDescription());
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(id).append(",").append(productDescription).append(",")
                .append(qtyOnHand).append(",").append(minQty).append(",")
                .append(cost).append(",").append(orderQty);

        if (isCategorized()) {
            for (String category : categoryList) {
                builder.append(",").append(category);
            }

        }

        return builder.toString();
    }
}
