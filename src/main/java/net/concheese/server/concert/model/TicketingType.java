package net.concheese.server.concert.model;

public enum TicketingType {
    PRE_SALE("선예매"),
    GENERAL_SALE("일반 예매");

    private final String description;

    TicketingType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
