package student.system.analyzer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Mode {

    private final Collection<String> values;

    public Mode() {
        this.values = new ArrayList<>();
    }

    public Collection<String> getValues() {
        return this.values;
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    @Override
    public String toString() {
        return "Mode{" +
                "values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mode)) return false;
        Mode mode = (Mode) o;
        return Objects.equals(values, mode.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
