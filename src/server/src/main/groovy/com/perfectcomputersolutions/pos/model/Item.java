package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends ModelEntity implements Payable {

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "transaction_id", nullable = false)
    @ApiModelProperty(notes = "The transaction that this item belongs to")
    private Transaction transaction;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @ApiModelProperty(notes = "The product that this item consists of")
    private Product product;

    @Min(1)
    @ApiModelProperty(notes = "Positive integer value that specifies the quantity of the product")
    private int quantity;

    @ApiModelProperty(notes = "Positive real number that indicates the weight of the item")
    private BigDecimal weight;

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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public boolean isTaxed() {
        return product.isTaxed();
    }

    @Transient
    public BigDecimal getCost() {

        return product.isWeighted() ?
                product.getCost().multiply(weight) :
                product.getCost().multiply(new BigDecimal(quantity));
    }
}
