package com.example.traveling_app.model;
public class FilterItem {
    private FilterItemGroup filterItemGroup;
    private final int index;
    private final String value;
    private final String name;
    private boolean selected = false;

    FilterItem(FilterItemGroup filterItemGroup, int index, String value, String name) {
        this.filterItemGroup = filterItemGroup;
        this.index = index;
        this.value = value;
        this.name = name;
    }


    void setFilterItemGroup(FilterItemGroup filterItemGroup) {
        this.filterItemGroup = filterItemGroup;
    }

    public FilterItemGroup ofGroup() {
        return filterItemGroup;
    }

    public boolean unselectSelf() {
        return filterItemGroup.unselectItem();
    }

    void setSelected(boolean state) {
        this.selected = state;
    }

    public void selectSelf() {
        ofGroup().changeSelectedItem(this);
    }

    public int getIndexInGroup() {
        return index;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
