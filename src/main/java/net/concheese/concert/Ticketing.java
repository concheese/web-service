package net.concheese.concert;

import java.time.LocalDateTime;

public class Ticketing {
    private final long id; //공연 id
    private String title; //티켓팅 이름
    private LocalDateTime ticketingDateTime; //티켓팅 일시

    private String link; //티켓팅 링크

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTicketingDateTime() {
        return ticketingDateTime;
    }

    public void setTicketingDateTime(LocalDateTime ticketingDateTime) {
        this.ticketingDateTime = ticketingDateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public Ticketing(long id, String title, LocalDateTime ticketingDateTime, String link) {
        this.id = id;
        this.title = title;
        this.ticketingDateTime = ticketingDateTime;
        this.link = link;
    }


}
