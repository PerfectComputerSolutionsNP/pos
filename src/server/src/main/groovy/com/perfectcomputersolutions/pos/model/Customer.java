package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends PersonEntity {

    @OneToMany(mappedBy = "customer")
    public Set<Transaction> transactions;
}
