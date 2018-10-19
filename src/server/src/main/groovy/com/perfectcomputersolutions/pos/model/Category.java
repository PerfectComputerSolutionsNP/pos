package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "category")
public class Category extends NamedEntity {

    // https://stackoverflow.com/questions/19112362/spring-hibernate-product-category-relationship

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "Set of products associated with this category. This is a " +
                              "not necessary field when creating new categories")
    Set<Product> products;
}