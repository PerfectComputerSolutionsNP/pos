package com.perfectcomputersolutions.pos.model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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

    @Min(0)
    @NotNull
    long paid;

    @Min(0)
    @NotNull
    long cost;

    @NotEmpty
    @NotNull
    String name;
}