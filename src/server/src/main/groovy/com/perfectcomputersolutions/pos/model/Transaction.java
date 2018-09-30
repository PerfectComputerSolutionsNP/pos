package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
public class Transaction extends ModelEntity {

    @NotNull
    public String userId;

    @NotNull
    public String paymentMethodId;

    @NotNull
    public Date date;

    @NotNull
    public List<Product> products;

    @NotNull
    long paid;

    @NotNull
    long cost;

    @JsonIgnore
    public String getName() {
        return super.getId();
    }
}