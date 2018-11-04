package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "product")
public class Product extends NamedEntity implements Payable {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "transaction_product",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "transaction_id") }
    )
    Set<Transaction> transactions = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ApiModelProperty(notes = "Category that the product belongs to")
    public Category category;

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "A detailed string that describes the product")
    public String description;

    @Min(0)
    @NotNull
    @ApiModelProperty(notes = "The cost of the product in mills. A mill is 1/1000 of a United States dollar")
    public long cost;

    @Override
    public long getCost() {
        return cost;
    }
}