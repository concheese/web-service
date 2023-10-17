package net.concheese.server.concert.model;

/**
 * {@code Genre} 열거형은 콘서트 장르를 정의합니다.
 */
public enum Genre {
    CONCERT("콘서트"),
    ORCHESTRA("오케스트라"),
    FESTIVAL("축제"),
    OTHERS("기타"),
    DRAMA("연극");


    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
