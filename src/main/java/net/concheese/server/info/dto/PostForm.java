package net.concheese.server.info.dto;

import java.util.UUID;
import net.concheese.server.info.model.PostCategory;

public record PostForm(UUID id, UUID userId, String title, PostCategory category, String content) {

}
