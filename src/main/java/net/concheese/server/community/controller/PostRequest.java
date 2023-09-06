package net.concheese.server.community.controller;

import net.concheese.server.community.model.Category;

public record PostRequest(String title, Category category, String content) {

}
