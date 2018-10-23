package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "product")
public class Product extends NamedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ApiModelProperty(notes = "Category that the product belongs to")
    public Category category;

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "A detailed string that describes the product")
    public String description;

    @Min(0)
    @NotNull
    @ApiModelProperty(notes = "The cost of the product in mills. A mill is 1/1000 of a United States dollar")
    public Long cost;

    @JsonProperty
    public Long getCents() {

        return cost / 10;
    }

    @JsonProperty
    public Long getDollars() {

        return cost / 1000;
    }
}