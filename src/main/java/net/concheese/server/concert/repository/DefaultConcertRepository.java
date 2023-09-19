package net.concheese.server.concert.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import jakarta.annotation.PostConstruct;
import net.concheese.server.concert.model.ConcertDate;
import net.concheese.server.concert.model.ConcertInfo;
import net.concheese.server.concert.model.ConcertTicketInfo;
import net.concheese.server.concert.model.Genre;
import net.concheese.server.concert.model.Location;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import static net.concheese.server.common.JDBCUtils.toUUID;

@Repository
public class DefaultConcertRepository implements ConcertRepository {
  private static final RowMapper<ConcertInfo> infoRowMapper = DefaultConcertRepository::mapPostRow;
  private final NamedParameterJdbcTemplate namedJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;

  public DefaultConcertRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.namedJdbcTemplate = namedJdbcTemplate;
    this.jdbcTemplate = new JdbcTemplate(
            Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
  }

  // ???
  private static ConcertInfo mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
    UUID infoId = toUUID(resultSet.getBytes("INFO_ID"));
    String title = resultSet.getString("TITLE");
    Genre genre = Genre.valueOf(resultSet.getString("GENRE"));
    Location location = Location.valueOf(resultSet.getString("LOCATION"));
    ConcertTicketInfo concertTicketing = ConcertTicketInfo.valueOf(resultSet.getString("CONCERT_TICKET_INFO"));
    ConcertTicketInfo ticketing = ConcertTicketInfo.valueOf(resultSet.getString("TICKETING"));
    ConcertDate concertDate = ConcertDate.valueOf(resultSet.getString("CONCERT_DATE"));
    String description = resultSet.getString("DESCRIPTION");
    String link = resultSet.getString("LINK");
    return new ConcertInfo(infoId, title, genre, location, concertTicketing, ticketing, concertDate, description, link);
  }
  // ???

  // ???
  private Map<String, Object> toParamMap(ConcertInfo concertInfo) {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("infoId", concertInfo.getInfoId().toString().getBytes());
    paramMap.put("title", concertInfo.getTitle());
    paramMap.put("genre", concertInfo.getGenre().toString());
    paramMap.put("location", concertInfo.getLocation().toString());
    paramMap.put("concertTicketing", concertInfo.getConcertTicketing().toString());
    paramMap.put("ticketing", concertInfo.getTicketing().toString());
    paramMap.put("concertDate", concertInfo.getConcertDate().toString());
    paramMap.put("description", concertInfo.getDescription());
    paramMap.put("link", concertInfo.getLink());
    return paramMap;
  }
  // ???

  @PostConstruct
  public void initializeDatabaseSchema() {
    if (!isConcertTableExist()) {
      createConcertTable(); // BoardTable은
    }
  }

  private boolean isConcertTableExist() {
    String query = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_NAME = 'CONCERT'";
    try {
      Integer result = jdbcTemplate.queryForObject(query, Integer.class);
      return result != null && result > 0;
    } catch (DataAccessException e) {
      return false;
    }
  }

  private void createConcertTable() {
    try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new ClassPathResource("schemas/concert_schema.sql").getInputStream(),
                    StandardCharsets.UTF_8))) {
      jdbcTemplate.execute(FileCopyUtils.copyToString(reader));
    } catch (IOException | DataAccessException e) {
      throw new RuntimeException("Error creating 'CONCERT' table.", e);
    }
  }

  @Override
  public ConcertInfo insert(ConcertInfo concertInfo) {
    int update = namedJdbcTemplate.update(
                "INSERT INTO CONCERT(INFO_ID, TITLE, GENRE, LOCATION, CONCERT_TICKET_INFO, TICKETING, CONCERT_DATE, DESCRIPTION, LINK) VALUES(UNHEX(REPLACE(:infoId, '-', '')), :title, :genre, :location, :concertTicketInfo, :ticketing, :concertDate, :description, :link)",
                toParamMap(concertInfo));
    if (update != 1) {
      throw new RuntimeException("Nothing was inserted");
    }
    return concertInfo;
  }

  @Override
  public ConcertInfo update(UUID infoId, String title, Genre genre, Location location,
      ConcertTicketInfo concertTicketInfo, ConcertTicketInfo ticketing, ConcertDate concertDate,
      String description, String link) {
    ConcertInfo concertInfo = readById(infoId).orElseThrow(() -> new RuntimeException("Not found")); // id로 콘서트 정보를 읽어옵니다. 없으면 예외를 던집니다.
    int update = namedJdbcTemplate.update(
            "UPDATE CONCERT SET TITLE = :title, GENRE = :genre, LOCATION = :location, CONCERT_TICKET_INFO = :concertTicketInfo, TICKETING = :ticketing, CONCERT_DATE = :concertDate, DESCRIPTION = :description, LINK = :link WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
            toParamMap(concertInfo));
    if (update != 1) {
      throw new RuntimeException("Nothing was updated");
    }
    return concertInfo;
  }

  @Override
  public Optional<ConcertInfo> readById(UUID infoId) {
    /**
     * 고유 식별자로 콘서트 정보를 읽어옵니다.
     *
     * @param infoId 읽어올 콘서트 정보의 고유 식별자
     * @return 찾은 경우 {@link ConcertInfo} 찾지 못한 경우 {@code null}
     */
    try {
      return Optional.ofNullable(namedJdbcTemplate.queryForObject(
              "SELECT * FROM CONCERT WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
              Collections.singletonMap("infoId", infoId.toString().getBytes()), infoRowMapper));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<ConcertInfo> readByGenre(Genre genre) {
    /**
     * 모든 콘서트 정보 항목의 목록을 검색합니다
     *
     * @return 모든 사용 가능한 {@link ConcertInfo} 항목 목록
     */
    return namedJdbcTemplate.query(
            "SELECT * FROM CONCERT WHERE GENRE = :genre ORDER BY CREATED_AT DESC",
            Collections.singletonMap("genre", genre.toString()), infoRowMapper);
  }

  @Override
  public List<ConcertInfo> readAllInfo() {
    return namedJdbcTemplate.query("SELECT * FROM CONCERT ORDER BY CREATED_AT DESC", infoRowMapper);
  }

  @Override
  public void deleteInfo(UUID infoId) {
    namedJdbcTemplate.update(
            "DELETE FROM CONCERT WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
            Collections.singletonMap("infoId", infoId.toString().getBytes()));
  }

  @Deprecated
  public void deleteAll() {
    jdbcTemplate.update("TRUNCATE CONCERT");
  }
}
