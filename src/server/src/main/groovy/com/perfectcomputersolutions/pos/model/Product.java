package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity()
@Table(name = "PRODUCT")
public class Product extends NamedEntity implements Payable {

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
    @ApiModelProperty(notes = "Long value that specifies the cost of the product in cents")
    private long cents;

    @ApiModelProperty(notes = "Boolean value to indicate whether or not the cost should be calculated as a flat cost or by weight")
    private boolean weighted;

    @ApiModelProperty(notes = "Boolean flag to indicate whether or not tax should be applied to this product")
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

    public long getCents() {
        return cents;
    }

    public void setCents(long cents) {
        this.cents = cents;
    }

    @Override
    @Transient
    public BigDecimal getCost() {

        return new BigDecimal(cents).movePointLeft(2);
    }

}