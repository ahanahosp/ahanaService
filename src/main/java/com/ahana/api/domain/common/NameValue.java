package com.ahana.api.domain.common;

import java.io.Serializable;
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class NameValue implements Comparable, Serializable {

    private static final long serialVersionUID = 1L;

    private String label;

    private Object value;

    public static final Comparator CASE_INSENSITIVE_ORDER = new Comparator() {

        public int compare(final Object o1, final Object o2) {
            String label1 = ((NameValue) o1).getLabel();
            String label2 = ((NameValue) o2).getLabel();
            return label1.compareToIgnoreCase(label2);
        }
    };

    public NameValue() {
        super();
    }

    public NameValue(final String labels, final Object values) {
        this.label = labels;
        this.value = values;
    }

    public final int compareTo(final Object o) {
        String otherLabel = ((NameValue) o).getLabel();
        return this.getLabel().compareTo(otherLabel);
    }

    public final String toString() {
        StringBuffer sb = new StringBuffer("NameValue[");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.value);
        sb.append("]");
        return (sb.toString());
    }

    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof NameValue)) {
            return false;
        }

        NameValue bean = (NameValue) obj;
        int nil = (this.getValue() == null) ? 1 : 0;
        nil += (bean.getValue() == null) ? 1 : 0;

        if (nil == 2) {
            return true;
        } else if (nil == 1) {
            return false;
        } else {
            return this.getValue().equals(bean.getValue());
        }
    }

    public final int hashCode() {
        return (this.getValue() == null) ? 17 : this.getValue().hashCode();
    }

    public final String getLabel() {
        return label;
    }

    public final void setLabel(final String labels) {
        this.label = labels;
    }

    public final Object getValue() {
        return value;
    }

    public final void setValue(final Object values) {
        this.value = values;
    }
}