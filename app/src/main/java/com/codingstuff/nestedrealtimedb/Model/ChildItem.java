package com.codingstuff.nestedrealtimedb.Model;

public class ChildItem {

    private String childName , childImage;

    public ChildItem(){}

    public ChildItem(String childName, String childImage) {
        this.childName = childName;
        this.childImage = childImage;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildImage() {
        return childImage;
    }

    public void setChildImage(String childImage) {
        this.childImage = childImage;
    }
}
