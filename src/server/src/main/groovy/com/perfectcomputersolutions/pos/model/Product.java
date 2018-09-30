package com.perfectcomputersolutions.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * ModelEntity
 */
@Entity(name = "product")
public class Product extends ModelEntity {

    @NotNull
    @Column(unique = true)
    public String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

    @NotNull
    public Long cost;
}