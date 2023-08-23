package net.concheese.concert;

import java.util.List;

public class ConcertInfo {
    private long id;
    private String title;
    private String genre;
    private String performers;
    private List<ConcertTime> time;
    private List<Ticketing> ticketing;
    private String description;

    public ConcertInfo(long id, String title, String genre, String location, String performers, List<ConcertTime> time, List<Ticketing> ticketing, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.time = time;
        this.performers = performers;
        this.ticketing = ticketing;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPerformers() {
        return performers;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
    }

    public List<ConcertTime> getTime() {
        return time;
    }

    public void setTime(List<ConcertTime> time) {
        this.time = time;
    }

    public List<Ticketing> getTicketing() {
        return ticketing;
    }

    public void setTicketing(List<Ticketing> ticketing) {
        this.ticketing = ticketing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


