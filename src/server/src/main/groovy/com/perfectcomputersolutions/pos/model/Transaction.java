package com.perfectcomputersolutions.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends ModelEntity implements Payable {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "transaction_product",
            joinColumns = { @JoinColumn(name = "transaction_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    public Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "transaction_service",
            joinColumns = { @JoinColumn(name = "transaction_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") }
    )
    public Set<Service> services = new HashSet<>();

    @ManyToOne
    public Customer customer;

    public boolean notifyCustomer;

    @Override
    public long getCost() {

        long cost = 0;

        for (Product product : products)
            cost += product.getCost();

        for (Service service : services)
            cost += service.getCost();

        return cost;
    }
}