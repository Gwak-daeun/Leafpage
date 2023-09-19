package com.leafpage.controller;

public class ViewResolver {
    public String prefix;
    public String suffix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getView(String viewName) {
        String view = null;
        if (!viewName.contains(".do")) {
            if (viewName.equals("index")) {
                view = viewName + ".jsp";
            } else {
                view = prefix + viewName + suffix;
            }
        } else {
            view = viewName;
        }
        return view;
    }
}
