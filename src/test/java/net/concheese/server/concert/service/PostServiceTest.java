package net.concheese.server.concert.service;

import net.concheese.server.community.controller.PostRequest;
import net.concheese.server.community.model.Category;
import net.concheese.server.concert.controller.ConcertInfoRequest;
import net.concheese.server.concert.model.*;
import net.concheese.server.concert.repository.DefaultConcertRepository;
import net.concheese.server.concert.repository.LocationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostServiceTest {
    private final ConcertInfoRequest postRequest = new ConcertInfoRequest("Test Concert", Genre.IDOL,
            new Location(UUID.randomUUID(),1,2,"local"),
            "Rho",
            null,
            new ConcertTicketInfo(UUID.randomUUID(), LocalDate.now(), LocalTime.now(), TicketingType.GENERAL_SALE),
            new ConcertDate(UUID.randomUUID(), null,null,null),
            "test description",
            "test link"
            );
    private ConcertInfo concertInfo;

    @Autowired
    private DefaultConcertInfoService defaultConcertInfoService;

    @Test
    @Order(1)
    public void testCreateConcertInfo(){
        concertInfo= new ConcertInfo(UUID.randomUUID(),postRequest.title(), postRequest.genre(), postRequest.location(),
                postRequest.artist(), postRequest.preTicketing(), postRequest.ticketing(),
                postRequest.concertDate(), postRequest.description(), postRequest.link()
                );
        assertNotNull(concertInfo);
    }
}
