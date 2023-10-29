package net.concheese.server.info.dto;

import java.util.List;
import java.util.UUID;

public record UserInfoForm(UUID id, String name, List<String> bookmarks) {

}
