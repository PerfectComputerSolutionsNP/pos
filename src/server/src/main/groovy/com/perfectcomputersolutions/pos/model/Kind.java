package com.perfectcomputersolutions.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "kind")
public class Kind extends ModelEntity {

    // TODO - UNIQUE CONSTRAINT

    @NotNull
    @Enumerated(EnumType.STRING)
    private KindName name;

//    @OneToMany(mappedBy = "kind")
//    private List<Product> products;

    public KindName getName() {
        return name;
    }

    public void setName(KindName name) {
        this.name = name;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
