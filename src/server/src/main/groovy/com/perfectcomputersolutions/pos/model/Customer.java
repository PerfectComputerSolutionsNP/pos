package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends PersonEntity {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "customer_notification",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "notification_id") }
    )
    Set<Notification> notifications = new HashSet<>();

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
