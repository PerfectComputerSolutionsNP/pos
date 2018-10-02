package com.perfectcomputersolutions.pos.model;


import com.perfectcomputersolutions.pos.utility.Utility;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityBatch<T extends ModelEntity> implements Iterable<T> {

    @NotNull
    private List<T> entities;

//    public EntityBatch() {
//        this.entities = new ArrayList<>();
//    }

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
