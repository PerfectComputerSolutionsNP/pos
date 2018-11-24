package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    @CreatedBy
    @ApiModelProperty(notes = "Username of user that created this record")
    private U createdBy;

    @CreatedDate
    @ApiModelProperty(notes = "Timestamp that indicates the last time this record was modified")
    private Timestamp createdDate;

    @LastModifiedBy
    @ApiModelProperty(notes = "Username of last user to modify this record")
    private U lastModifiedBy;

    @LastModifiedDate
    @ApiModelProperty(notes = "Timestamp that indicates the last time this record was modified")
    private Timestamp lastModifiedDate;

    @JsonIgnore
    public U getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @JsonIgnore
    public U getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}
