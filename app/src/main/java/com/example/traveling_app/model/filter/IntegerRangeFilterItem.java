package com.example.traveling_app.model.filter;


import android.util.Log;

import java.text.NumberFormat;

public class IntegerRangeFilterItem extends FilterItem {

    private int startAt;
    private int endAt;
    public IntegerRangeFilterItem(FilterItemGroup filterItemGroup, int startAt, int endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
	this.filterItemGroup = filterItemGroup;
        index = filterItemGroup.addFilterItem(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntegerRangeFilterItem integerFilterItem)
            return integerFilterItem.ofGroup() == this.ofGroup() && integerFilterItem.startAt == this.startAt && integerFilterItem.endAt == this.endAt;
        return false;
    }

    @Override
    public String getName() {
        return startAt + " - " + endAt;
    }

    @Override
    public boolean isSatisfied(Object obj) {
        if (obj instanceof Integer value)
            return startAt <= value && value <= endAt;
        if (obj instanceof Double value) {
            int num = value.intValue();
            return startAt <= num && num <= endAt;
        }
        if (obj instanceof Long value) {
            int num = value.intValue();
            return startAt <= num && num <= endAt;
        }
        if (obj instanceof String value) {
            try {
                int num = Integer.parseInt(value);
                return startAt <= num && num <= endAt;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
