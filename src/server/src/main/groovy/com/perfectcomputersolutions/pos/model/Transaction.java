package com.perfectcomputersolutions.pos.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
@Entity
public class Transaction extends ModelEntity {

    @NotNull
    public String userId;

    @NotNull
    public String paymentMethodId;

    @NotNull
    public Date date;

    @NotNull
    long paid;

    @NotNull
    long cost;

    @NotNull
    String name;
}