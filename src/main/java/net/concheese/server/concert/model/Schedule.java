package net.concheese.server.concert.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class Schedule {
    @Getter @Setter
    private Timestamp timestamp;
    @Getter @Setter
    private int postal;
}
