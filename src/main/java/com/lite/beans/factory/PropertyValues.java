package com.lite.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * bean 属性的集合
 *
 * @author vince 2024/1/26 16:20
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        propertyValues.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValues) {
            if (propertyName.equals(propertyValue.getName())) {
                return propertyValue;
            }
        }

        return null;
    }
}
