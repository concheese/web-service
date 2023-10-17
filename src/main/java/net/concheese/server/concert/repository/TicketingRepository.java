package net.concheese.server.concert.repository;

import jakarta.annotation.PostConstruct;
import net.concheese.server.concert.model.ConcertTicketInfo;
import net.concheese.server.concert.model.TicketingType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.UUID;

import static net.concheese.server.common.JDBCUtils.toUUID;

@Repository
public class TicketingRepository {
    private static final RowMapper<ConcertTicketInfo> infoRowMapper = TicketingRepository::mapPostRow;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;


    public TicketingRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = new JdbcTemplate(
                Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
    }

    private static ConcertTicketInfo mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
        // DB에서 ConcertInfo의 각 필드에 대한 정보를 가져온다.
        UUID ticketingID = toUUID(resultSet.getBytes("TICKETING_ID"));
        LocalDate start = resultSet.getDate("START").toLocalDate();
        LocalDate end = resultSet.getDate("END").toLocalDate();
        TicketingType type = TicketingType.valueOf(resultSet.getString("TYPE"));
        return new ConcertTicketInfo(ticketingID, start, end, type);
    }
    private Map<String, Object> toParamMap(ConcertTicketInfo concertTicketInfo) {
        // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장한다.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ticketingID", concertTicketInfo.getTicketingID().toString().getBytes());
        paramMap.put("start", concertTicketInfo.getStart());
        paramMap.put("end", concertTicketInfo.getEnd());
        paramMap.put("status", concertTicketInfo.getStatus().toString());
        return paramMap;
    }
    @PostConstruct
    public void initializeDatabaseSchema() {
        if (!isTicketingTableExist()) {
            createTicketingTable();
        }
    }

    private boolean isTicketingTableExist() {
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'TICKETING'";
        try {
            Integer result = jdbcTemplate.queryForObject(query, Integer.class);
            return result != null && result > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private void createTicketingTable() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("schemas/ticketing_schema.sql").getInputStream(),
                        StandardCharsets.UTF_8))) {
            jdbcTemplate.execute(FileCopyUtils.copyToString(reader));
        } catch (IOException | DataAccessException e) {
            throw new RuntimeException("Error creating 'TICKETING' table.", e);
        }
    }

    public ConcertTicketInfo insert(ConcertTicketInfo concertTicketInfo) {
        int update = namedJdbcTemplate.update(
                "INSERT INTO TICKETING (TICKETING_ID, START_DATE, END_DATE, START_TIME, TYPE) VALUES (UNHEX(REPLACE(:ticketingID, '-', '')), :startedAt, :endAt, :startTime, :type)",
                toParamMap(concertTicketInfo));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return concertTicketInfo;
    }

    public ConcertTicketInfo update(java.util.UUID ticketingID, LocalDate startedAt,  LocalTime startTime, TicketingType type) {
        ConcertTicketInfo concertTicketInfo = readById(ticketingID);
        int update = namedJdbcTemplate.update(
                "UPDATE TICKETING SET START_DATE = :startedAt, START_TIME = :startTime, TYPE = :type WHERE TICKETING_ID = UNHEX(REPLACE(:ticketingID, '-', ''))",
                toParamMap(concertTicketInfo));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return concertTicketInfo;
    }

    public ConcertTicketInfo readById(java.util.UUID ticketingID) {
        return namedJdbcTemplate.queryForObject(
                "SELECT * FROM TICKETING WHERE TICKETING_ID = UNHEX(REPLACE(:ticketingID, '-', ''))",
                Collections.singletonMap("ticketingID", ticketingID.toString().getBytes()), infoRowMapper);
    }

    public List<ConcertTicketInfo> readAll() {
        return namedJdbcTemplate.query("SELECT * FROM TICKETING", infoRowMapper);
    }

    public void deleteLocation(UUID ticketingID) {
        namedJdbcTemplate.update("DELETE FROM TICKETING WHERE TICKETING_ID = UNHEX(REPLACE(:ticketingID, '-', ''))",
                Collections.singletonMap("ticketingID", ticketingID.toString().getBytes()));
    }

    @Deprecated
    public void deleteAll() {
        jdbcTemplate.update("TRUNCATE TICKETING");
    }

}

