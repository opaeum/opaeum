package org.nakeduml.tinker.activity;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.tinkerpop.pipes.HistoryIterator;

public abstract class Pipe<S> implements Iterator<S>, Iterable<S> {

    protected Iterator<S> starts;
    private boolean available = false;

    public void setStarts(final Pipe<S> starts) {
        this.starts = starts;
    }

    public void setStarts(final Iterator<S> starts) {
        if (starts instanceof Pipe)
            this.starts = starts;
        else
            this.starts = new HistoryIterator<S>(starts);
    }

    public void setStarts(final Iterable<S> starts) {
        this.setStarts(starts.iterator());
    }

    public void reset() {
        if (this.starts instanceof Pipe) {
            ((Pipe) this.starts).reset();
        }
        this.available = false;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void go() {
        if (this.available) {
            this.available = false;
        } else {
            this.processNextStart();
        }
    }

    public boolean hasNext() {
        if (this.available)
            return true;
        else {
            try {
                this.processNextStart();
                this.available = true;
                return true;
            } catch (NoSuchElementException e) {
                this.available = false;
                return false;
            }
        }
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected abstract void processNextStart() throws NoSuchElementException;

}
