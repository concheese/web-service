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

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostServiceTest {
    private ConcertInfo concertInfo;

}
