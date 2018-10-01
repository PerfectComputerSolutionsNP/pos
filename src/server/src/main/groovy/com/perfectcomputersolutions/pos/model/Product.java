package com.perfectcomputersolutions.pos.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "product")
public class Product extends NamedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

    @NotNull
    public Long cost;
}