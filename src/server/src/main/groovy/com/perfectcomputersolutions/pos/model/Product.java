package com.perfectcomputersolutions.pos.model;

import javax.validation.constraints.NotNull;

/**
 * ModelEntity
 */
public class Product extends ModelEntity {

    @NotNull
    public short sku;

    @NotNull
    public String name;

    @NotNull
    public String categoryId;
}