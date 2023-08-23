package net.concheese.concert;

import java.time.LocalDateTime;

public class Ticketing {
    private final long id;
    private String who; // 티켓팅 대상
    private String link; // 티켓팅 링크
    private LocalDateTime ticketingDate; // 티켓팅 날짜

    public Ticketing(long id, String who, String link, LocalDateTime ticketingDate) {
        this.id = id;
        this.who = who;
        this.link = link;
        this.ticketingDate = ticketingDate;
    }

    public long getId() {
        return id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getTicketingDate() {
        return ticketingDate;
    }

    public void setTicketingDate(LocalDateTime ticketingDate) {
        this.ticketingDate = ticketingDate;
    }
}
