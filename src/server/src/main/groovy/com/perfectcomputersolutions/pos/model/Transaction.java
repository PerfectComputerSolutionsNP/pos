package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.perfectcomputersolutions.pos.utility.Money;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends ModelEntity implements Payable {

    @OneToMany(
            fetch         = FetchType.EAGER,
            cascade       = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy      = "transaction")
    @JsonManagedReference
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    public Customer customer;

    public boolean notifyCustomer;

    public float taxRate;

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {

        // Technically, the Item is the owner or the relationship. So, if we insert
        // a Transaction, although the cascade type is set to ALL and the child entities
        // are created, the foreign key holder (child entity / owner of relationship)
        // will still have a null foreign key. To combat this, we manually set the back-reference
        // before we actually "set" the items set. This way, the foreign key is set so the join
        // be be properly made when selecting transactions.
        items.forEach(item -> item.setTransaction(this));

        this.items = items;
    }

    @Override
    public long getCost() {

        return getTotalCents();
    }

    public long getSubtotalCents() {

        return items.stream()
                    .mapToLong(Item::getCost)
                    .sum();
    }

    public long getTotalCents() {

        long cost = 0;

        for (Item item : items) {

            if (item.isTaxed()) {
                cost += item.getCost();

            } else {
                cost += item.getCost();
            }
        }

        return cost;
    }

    public double getSubtotalDollars() {
        return Money.centsToDollars(getSubtotalCents());
    }

    public double getTotalDollars() {
        return Money.centsToDollars(getTotalCents());
    }

    public String getSubtotalDollarsString() {
        return Money.toPriceStringDollars(getTotalDollars());
    }

    public String getSubtotalCentsString() {
        return Money.toPriceStringCents(getSubtotalCents());
    }

    public String getTotalDollarsString() {
        return Money.toPriceStringDollars(getTotalDollars());
    }

    public String getTotalCentsString() {
        return Money.toPriceStringCents(getTotalCents());
    }
}