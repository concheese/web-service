package net.concheese.server.concert.model;

/**
 * {@code Genre} 열거형은 콘서트 장르를 정의합니다.
 */
public enum Genre {
    IDOL("아이돌");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
