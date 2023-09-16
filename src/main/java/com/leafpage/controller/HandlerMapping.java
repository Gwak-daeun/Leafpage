package com.leafpage.controller;

import com.leafpage.controller.user.LikeEmptyHeartController;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private Map<String, Controller> mappings;

    public HandlerMapping() {

        mappings = new HashMap<>();

        mappings.put("/LikeHeart.do",new LikeEmptyHeartController());

    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}
