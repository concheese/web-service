package net.concheese.server.concert.repository;

import jakarta.annotation.PostConstruct;
import net.concheese.server.concert.model.ConcertDate;
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
public class ConcertDateRepository {
    private static final RowMapper<ConcertDate> infoRowMapper = ConcertDateRepository::mapPostRow;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;


    public ConcertDateRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = new JdbcTemplate(
                Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
    }

    private static ConcertDate mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
        // DB에서 ConcertInfo의 각 필드에 대한 정보를 가져온다.
        UUID dateID = toUUID(resultSet.getBytes("DATE_ID"));
        LocalDate startDate = resultSet.getDate("START_DATE").toLocalDate();
        LocalDate endDate = resultSet.getDate("END_DATE").toLocalDate();
        LocalTime startTime = resultSet.getTime("START_TIME").toLocalTime();
        return new ConcertDate(dateID, startDate, endDate, startTime);
    }
    private Map<String, Object> toParamMap(ConcertDate concertDate) {
        // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장한다.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ticketingID", concertDate.getDateID().toString().getBytes());
        paramMap.put("startedAt", concertDate.getstartedAt());
        paramMap.put("endDate", concertDate.getEndDate());
        paramMap.put("startTime", concertDate.getStartTime());
        return paramMap;
    }
    @PostConstruct
    public void initializeDatabaseSchema() {
        if (!isDateTableExist()) {
            createDateTable();
        }
    }

    private boolean isDateTableExist() {
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'DATE'";
        try {
            Integer result = jdbcTemplate.queryForObject(query, Integer.class);
            return result != null && result > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private void createDateTable() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("schemas/date_schema.sql").getInputStream(),
                        StandardCharsets.UTF_8))) {
            jdbcTemplate.execute(FileCopyUtils.copyToString(reader));
        } catch (IOException | DataAccessException e) {
            throw new RuntimeException("Error creating 'DATE' table.", e);
        }
    }

    public ConcertDate insert(ConcertDate concertDate) {
        int update = namedJdbcTemplate.update(
                "INSERT INTO DATE (DATE_ID, START_DATE, END_DATE, START_TIME) VALUES (UNHEX(REPLACE(:ticketingID, '-', '')), :startDate, :endDate, :startTime)",
                toParamMap(concertDate));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return concertDate;
    }

    public ConcertDate update(UUID dateID, LocalDate startDate, LocalDate endDate, LocalTime startTime) {
        ConcertDate concertDate = readById(dateID);
        int update = namedJdbcTemplate.update(
                "UPDATE DATE SET START_DATE = :startDate, END_DATE = :endDate, START_TIME = :startTime WHERE DATE_ID = UNHEX(REPLACE(:dateID, '-', ''))",
                Collections.singletonMap("dateID", dateID.toString().getBytes()));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return concertDate;
    }

    public ConcertDate readById(UUID dateID){
        return namedJdbcTemplate.queryForObject(
                "SELECT * FROM DATE WHERE DATE_ID = UNHEX(REPLACE(:dateID, '-', ''))",
                Collections.singletonMap("dateID", dateID.toString().getBytes()), infoRowMapper);
    }

    public List<ConcertDate> readAll() {
        return namedJdbcTemplate.query("SELECT * FROM DATE", infoRowMapper);
    }

    public void deleteDate(UUID dateID) {
        namedJdbcTemplate.update("DELETE FROM DATE WHERE DATE_ID = UNHEX(REPLACE(:dateID, '-', ''))",
                Collections.singletonMap("dateID", dateID.toString().getBytes()));
    }

    @Deprecated
    public void deleteAll() {
        jdbcTemplate.update("TRUNCATE DATE");
    }

}