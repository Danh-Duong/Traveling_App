package com.example.traveling_app.model;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

// Group FilterItem by category;
public class FilterItemGroup {
    private final String key;
    private final String title;
    private int selectedItemIndex = -1;
    private final ArrayList<FilterItem> filterItems;
    private static final Consumer<Integer> DO_NOTHING = Integer -> {};
    private Consumer<Integer> onSelectedItemChanged = DO_NOTHING;
    private Consumer<Integer> onItemUnselected = DO_NOTHING;

    public FilterItemGroup(String key, String title) {
        this.key = key;
        this.title = title;
        filterItems = new ArrayList<>();
    }

    public String getKey() {
        return key;
    }

    public String getTitle() { return title; }

    public FilterItem get(int index) {
        return filterItems.get(index);
    }

    public FilterItem add(String value, String title) {
        FilterItem filterItem;
        try {
            filterItem = filterItems.stream().filter(item -> item.getValue().equals(value)).findFirst().get();
        } catch (NoSuchElementException e) {
            filterItem = new FilterItem(this, size(),  value, title);
            filterItems.add(filterItem);
        }
        return filterItem;
    }

    public int size() {
        return filterItems.size();
    }

    public FilterItem getSelectedItem() {
        if (selectedItemIndex == -1)
            return null;
        return filterItems.get(selectedItemIndex);
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
    public boolean hasSelected() {
        return selectedItemIndex != 1;
    }

    public void changeSelectedItem(FilterItem filterItem) throws IllegalArgumentException {
        if (filterItem.ofGroup() != this)
            throw new IllegalArgumentException(filterItem + " doesn't belong to " + getTitle());
        int index = filterItem.getIndexInGroup();
        changeSelectedItem(index);
    }

    public void changeSelectedItem(int newIndex) throws IllegalArgumentException {
        if (newIndex >= filterItems.size())
            throw new IndexOutOfBoundsException("Allow range is [" + 0 + " - " + size() + "]");
        int previousIndex = selectedItemIndex;
        if (previousIndex != -1)
            unselectItem();
        filterItems.get(newIndex).setSelected(true);
        selectedItemIndex = newIndex;
        onSelectedItemChanged.accept(newIndex);
    }

    public boolean unselectItem() {
        int previousIndex = selectedItemIndex;
        if (previousIndex == -1)
            return false;
        filterItems.get(previousIndex).setSelected(false);
        selectedItemIndex = -1;
        onItemUnselected.accept(previousIndex);
        return true;
    }

    public void setOnSelectedItemChanged(Consumer<Integer> listener) {
        if (listener == null)
            this.onSelectedItemChanged = DO_NOTHING;
        else
            this.onSelectedItemChanged = listener;
    }

    public void setOnItemUnselected(Consumer<Integer> listener) {
        if (listener == null)
            this.onItemUnselected = DO_NOTHING;
        else
            this.onItemUnselected = listener;
    }
}
