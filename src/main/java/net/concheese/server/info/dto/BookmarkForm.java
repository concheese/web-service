package net.concheese.server.info.dto;

import java.util.UUID;
import net.concheese.server.info.model.BookmarkLike;
import net.concheese.server.info.model.BookmarkStatus;

public record BookmarkForm(UUID id, BookmarkStatus status, BookmarkLike liked, String concertId) {

}
