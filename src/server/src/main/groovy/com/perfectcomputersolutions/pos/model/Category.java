package com.perfectcomputersolutions.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "category")
public class Category extends ModelEntity {

    // https://stackoverflow.com/questions/19112362/spring-hibernate-product-category-relationship

    @NotNull
    @Column(unique = true)
    public String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    Set<Product> products;
}