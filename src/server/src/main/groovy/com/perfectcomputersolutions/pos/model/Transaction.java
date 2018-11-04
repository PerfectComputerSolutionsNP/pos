package com.perfectcomputersolutions.pos.model;

import javax.persistence.Entity;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Set;

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
@Entity
public class Transaction extends ModelEntity implements Payable {

    @ManyToMany(mappedBy = "transactions")
    public Set<Product> products;

    @ManyToOne
    public Customer customer;

    public boolean notifyCustomer;

    @NotNull
    private long cost;

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public long getCost() {
        return this.cost;
    }
}