package com.codingstuff.nestedrealtimedb.Model;

import java.util.List;

public class ParentItem {

    private String parentName , parentImage;
    private List<ChildItem> childItemList;

    public ParentItem(){}

    public ParentItem(String parentName, String parentImage, List<ChildItem> childItemList) {
        this.parentName = parentName;
        this.parentImage = parentImage;
        this.childItemList = childItemList;
    }

    public List<ChildItem> getChildItemList() {
        return childItemList;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentImage() {
        return parentImage;
    }

    public void setParentImage(String parentImage) {
        this.parentImage = parentImage;
    }
}
