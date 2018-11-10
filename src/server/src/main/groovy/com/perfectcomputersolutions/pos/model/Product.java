package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "PRODUCT")
public class Product extends NamedEntity implements Payable {

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "kind_id", nullable = false)
//    public Kind kind;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ApiModelProperty(notes = "Category that the product belongs to")
    private Category category;

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "A detailed string that describes the product")
    private String description;

    @Min(0)
    @ApiModelProperty(notes = "The cost of the product in mills. A mill is 1/1000 of a United States dollar")
    private long cost;

    @ApiModelProperty(notes = "Boolean value to indicate whether or not the cost should be calculated as a flat cost or by weight")
    private boolean weighted;

    private boolean taxed = true;

    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public boolean isTaxed() {
        return taxed;
    }

    public void setTaxed(boolean taxed) {
        this.taxed = taxed;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}