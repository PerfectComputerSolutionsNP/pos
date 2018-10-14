package com.perfectcomputersolutions.pos.utility;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class IdBatch<T extends Serializable> implements Iterable<T> {

    @NotNull
    @NotEmpty
    private Set<T> ids;

    public Set<T> getIds() {
        return ids;
    }

    public void setIds(Set<T> ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdBatch)) return false;
        IdBatch<?> idBatch = (IdBatch<?>) o;
        return Objects.equals(ids, idBatch.ids);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ids);
    }

    @Override
    public String toString() {

        return ids.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return ids.iterator();
    }
}
