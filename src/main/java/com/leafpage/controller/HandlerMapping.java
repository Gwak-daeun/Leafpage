package com.leafpage.controller;

import com.leafpage.controller.admin.AdminBookDetailController;
import com.leafpage.controller.admin.AdminBookEditController;
import com.leafpage.controller.admin.AdminBookListController;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private Map<String, Controller> mappings;

    public HandlerMapping() {

        mappings = new HashMap<>();
        mappings.put("/booklistView.do", new AdminBookListController());
        mappings.put("/getBook.do", new AdminBookDetailController());
        mappings.put("/books/edit.do", new AdminBookEditController());

    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}
