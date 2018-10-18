package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "product")
public class Product extends NamedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ApiModelProperty(notes = "Category that the product belongs to")
    public Category category;

//    @NotNull
    @ApiModelProperty(notes = "A detailed string that describes the product")
    public String description;

    @NotNull
    @ApiModelProperty(notes = "The cost of the product in mills. A mill is 1/1000 of a United States dollar")
    public Long cost;

    // This is purposely private with only a getter
    // so that we do not deserialize a JSON field called
    // dollars by accident. The getter does the conversion
    // and converts the cost from mills to cents
    private Double dollars;

    @JsonProperty
    public Long getDollars() {

        return cost / 1000;
    }
}