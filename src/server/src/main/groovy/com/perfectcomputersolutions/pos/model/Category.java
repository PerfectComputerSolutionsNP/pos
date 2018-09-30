package com.perfectcomputersolutions.pos.model;

import javax.validation.constraints.NotNull;

public class Category extends ModelEntity {

    @NotNull
    public String name;
}