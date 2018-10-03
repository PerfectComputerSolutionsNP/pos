package com.perfectcomputersolutions.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "category")
public class Category extends NamedEntity {

    // https://stackoverflow.com/questions/19112362/spring-hibernate-product-category-relationship

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    Set<Product> products;
}