package com.dynatrace.installvalidator.profile.reporting.model;

/**
 * Created by kristof on 07.07.15.
 */
public class HtmlTableStyle extends HtmlBaseStyle {
    private boolean isBorder = false;
    private String tableAttribs; //eg: 1px;solid;black
    private int tableSize;
    private String tableStyle;
    private String tableColor;

    public boolean isBorder() {
        return isBorder;
    }

    public void setIsBorder(boolean isBorder) {
        this.isBorder = isBorder;
    }

    public String getTableAttribs() {
        return tableAttribs;
    }

    public void setTableAttribs(String tableAttribs) {
        this.tableAttribs = tableAttribs;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getTableStyle() {
        return tableStyle;
    }

    public void setTableStyle(String tableStyle) {
        this.tableStyle = tableStyle;
    }

    public String getTableColor() {
        return tableColor;
    }

    public void setTableColor(String tableColor) {
        this.tableColor = tableColor;
    }
}
