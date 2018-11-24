package com.perfectcomputersolutions.pos.payload;

import com.perfectcomputersolutions.pos.utility.Utility;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Batch<T> implements Iterable<T> {

    // TODO - Why does'nt Set<T> work?

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Set of values to be submitted or returned.")
    private List<T> batch = new ArrayList<>();

    public boolean contains(T object) {
        return batch.contains(object);
    }

    public int getSize() {
        return batch.size();
    }

    public boolean isEmpty() {
        return batch.isEmpty();
    }

    @Override
    public String toString() {
        return Utility.serialize(this);
    }

    @NotNull
    public List<T> getBatch() {
        return batch;
    }

    public void setBatch(@NotNull List<T> batch) {
        this.batch = batch;
    }

    @NotNull
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
