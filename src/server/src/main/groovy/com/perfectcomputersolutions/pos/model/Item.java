package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.perfectcomputersolutions.pos.utility.Money;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item")
public class Item extends ModelEntity implements Payable {

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "transaction_id", nullable = false)
    public Transaction transaction;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Min(1)
    private int quantity;

    private double weight;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isTaxed() {
        return product.isTaxed();
    }

    public long getCost() {

        // TODO - If weighted?

        return quantity * product.getCost();
    }

    public String getCostString() {

        return Money.toPriceStringCents(getCost());
    }

    public String getDollarsString() {

        return Money.toPriceStringDollars(getDollars());
    }
}
