package com.perfectcomputersolutions.pos.payload;

import com.perfectcomputersolutions.pos.utility.Utility;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Batch<T> implements Iterable<T> {

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Set of values.")
    private Set<T> batch;

    @Override
    public String toString() {
        return Utility.serialize(this);
    }

    public Set<T> getBatch() {
        return batch;
    }

    public void setBatch(Set<T> batch) {
        this.batch = batch;
    }

    @Override
    public Iterator<T> iterator() {
        return batch.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batch)) return false;
        Batch<?> batch = (Batch<?>) o;
        return Objects.equals(this.batch, batch.batch);
    }

    @Override
    public int hashCode() {

        return Objects.hash(batch);
    }
}
