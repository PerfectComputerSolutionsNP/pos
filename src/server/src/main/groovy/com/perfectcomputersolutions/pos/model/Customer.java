package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends PersonEntity {

    @OneToMany(mappedBy = "customer")
    private Set<Transaction> transactions;

    @JsonIgnore
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    @JsonProperty
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
