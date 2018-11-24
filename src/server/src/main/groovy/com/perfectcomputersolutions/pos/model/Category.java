package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends NamedEntity {

    // https://stackoverflow.com/questions/19112362/spring-hibernate-product-category-relationship

    // TODO - Do we want to cascade deletions or enforce foreign key constraint?

    // If we do not cascade deletions, then all products under this category must be
    // deleted before the category can be deleted. We should then expose a mechanism
    // that allows clearing a category. There should be a warning message saying this
    // is a dangerous action? But then again, couldn't we just do that with delete to
    // begin with?

    @OneToMany(mappedBy = "category")
    @ApiModelProperty(notes = "Set of products associated with this category. This is a " +
                              "not necessary field when creating new categories")
    Set<Product> products;

//    String description;
}