package com.perfectcomputersolutions.pos.payload;


import com.perfectcomputersolutions.pos.model.ModelEntity;
import com.perfectcomputersolutions.pos.utility.Utility;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;

public class EntityBatch<T extends ModelEntity> implements Iterable<T> {

    // TODO - Set<T> ? to force uniqueness? Then we must implement equals(), hashCode() for ALL entities :(

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "List of entities.")
    private List<T> entities;

    @Override
    public String toString() {
        return Utility.serialize(this);
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    @Override
    public Iterator<T> iterator() {
        return entities.iterator();
    }
}
