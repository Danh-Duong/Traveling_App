package com.example.traveling_app.model.filter;


public abstract class FilterItem {
    FilterItemGroup filterItemGroup;
    int index;
    private boolean selected = false;


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

    public String toString() {
        return filterItemGroup.getKey() + ": " + getName();
    };

    @Override
    public abstract boolean equals(Object obj);

    public abstract String getName();
    public abstract boolean isSatisfied(Object value);



}
