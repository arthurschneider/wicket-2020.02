package net.gfu.wicket.backend.bo;

import java.io.Serializable;
import java.util.Objects;

public abstract class CheeseAttribute<T extends Serializable> implements Serializable {
    private String attributeName;
    private T attributeValue;

    public CheeseAttribute(String attributeName, T attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public T getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(T attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheeseAttribute<?> that = (CheeseAttribute<?>) o;
        return Objects.equals(attributeName, that.attributeName) &&
                Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, attributeValue);
    }

    @Override
    public String toString() {
        return "CheeseAttribute{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue=" + attributeValue +
                '}';
    }
}
