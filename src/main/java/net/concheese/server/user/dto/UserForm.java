package net.concheese.server.user.dto;

import java.util.UUID;

public record UserForm(UUID id, String naverId, String name) {

}
