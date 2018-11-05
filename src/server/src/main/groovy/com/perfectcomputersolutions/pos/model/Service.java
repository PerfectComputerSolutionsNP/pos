package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SERVICE")
class Service extends NamedEntity implements Payable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ApiModelProperty(notes = "Category that the product belongs to")
    public Category category;

    @ManyToMany(
            mappedBy = "services",
            cascade  = {CascadeType.ALL}
    )
    Set<Transaction> transactions = new HashSet<>();

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "A detailed string that describes the product")
    public String description;

    @Override
    public long getCost() {
        return 0;
    }
}
