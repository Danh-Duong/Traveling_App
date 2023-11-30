package com.example.traveling_app.model.filter;



public class DoubleRangeFilterItem extends FilterItem {

    private double startAt;
    private double endAt;
    public DoubleRangeFilterItem(FilterItemGroup filterItemGroup, double startAt, double endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.filterItemGroup = filterItemGroup;
        index = filterItemGroup.addFilterItem(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoubleRangeFilterItem integerFilterItem)
            return integerFilterItem.ofGroup() == this.ofGroup() && integerFilterItem.startAt == this.startAt && integerFilterItem.endAt == this.endAt;
        return false;
    }

    @Override
    public String getName() {
        return startAt + " - " + endAt;
    }

    @Override
    public boolean isSatisfied(Object obj) {
        if (obj instanceof Double value)
            return startAt <= value && value <= endAt;
        return false;
    }
}
