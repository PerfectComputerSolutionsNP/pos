package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction extends ModelEntity implements Payable {

    // https://vladmihalcea.com/how-to-map-calculated-properties-with-jpa-and-hibernate-formula-annotation/

    @OneToMany(
            fetch         = FetchType.EAGER,
            cascade       = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy      = "transaction")
    @JsonManagedReference
    @ApiModelProperty(notes = "Set of items that belong to this transaction")
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    @ApiModelProperty(notes = "The customer that this transaction belongs to. This may be null")
    private Customer customer;

    @NotNull
    @ManyToOne
    @ApiModelProperty(notes = "The user executing the transaction")
    private User user;

    @ApiModelProperty(notes = "Optional date that specifies what time the order can be picked up at")
    private Timestamp pickupTime;

    @NotNull
    @ApiModelProperty(notes = "Non-negative real number that specifies the tax rate to apply to the transaction")
    private BigDecimal taxRate;

    @JsonIgnore
    public User getUser() {

        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }

//    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {

        return customer == null ? null : customer.getId();
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {

        // Technically, the Item is the owner or the relationship. So, if we insert
        // a Transaction, although the cascade type is set to ALL and the child entities
        // are created, the foreign key holder (child entity / owner of relationship)
        // will still have a null foreign key. To combat this, we manually set the back-reference
        // before we actually "set" the items set. This way, the foreign key is set so the
        // foreign key is not null and the join will be be properly made when selecting transactions.
        items.forEach(item -> item.setTransaction(this));

        this.items = items;
    }

    public BigDecimal getTaxRate() {
        return taxRate == null ?
                new BigDecimal(0) :
                taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate == null ?
                new BigDecimal(0) :
                taxRate;
    }

    @Override
    @Transient
    public BigDecimal getCost() {

        BigDecimal sum = new BigDecimal(0);

        for (Item item : items)
            sum = sum.add(item.getCost());

        return sum;
    }

    @Transient
    public BigDecimal getSubTotal() {

        return getCost();
    }

    @Transient
    public BigDecimal getTotal() {

        return taxRate.movePointLeft(2)
                      .add(new BigDecimal(1))
                      .multiply(getSubTotal());
    }

    @Transient
    public String getSubtotalString() {

        return toDollarString(getSubTotal());
    }

    @Transient
    public String getTotalString() {

        return toDollarString(getTotal());
    }
}