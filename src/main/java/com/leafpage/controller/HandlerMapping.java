package com.leafpage.controller;

import com.leafpage.controller.admin.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private Map<String, Controller> mappings;

    public HandlerMapping() {

        mappings = new HashMap<>();
        mappings.put("/booklistView.do", new AdminBookListController());
        mappings.put("/getBook.do", new AdminBookDetailController());
        mappings.put("/bookupload.do", new AdminBookUploadController());
        mappings.put("/remove.do", new AdminBookDeleteController());
        mappings.put("/edit.do", new AdminBookEditController());

    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}
