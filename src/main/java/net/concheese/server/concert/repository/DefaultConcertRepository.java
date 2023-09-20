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
  private static LocationRepository locationRepository;
  private static TicketingRepository ticketingRepository;
  private static ConcertDateRepository concertDateRepository;

  public DefaultConcertRepository(NamedParameterJdbcTemplate namedJdbcTemplate, LocationRepository locationRepository, TicketingRepository ticketingRepository, ConcertDateRepository concertDateRepository) {
    this.namedJdbcTemplate = namedJdbcTemplate;
    this.jdbcTemplate = new JdbcTemplate(
            Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
    this.locationRepository = locationRepository;
    this.ticketingRepository = ticketingRepository;
    this.concertDateRepository = concertDateRepository;
  }

  private static ConcertInfo mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
    UUID infoId = toUUID(resultSet.getBytes("INFO_ID"));
    String title = resultSet.getString("TITLE");
    Genre genre = Genre.valueOf(resultSet.getString("GENRE")); // Genre.valueOf()는 String을 Genre로 변환해준다.resultSet.getString("GENRE")는 DB에서 가져온 String을 반환한다.
    Location location = locationRepository.readById(toUUID(resultSet.getBytes("LOCATION_ID")));// id로 콘서트 정보를 읽어옵니다. 없을 때에 대해 예외처리 필요
    ConcertTicketInfo concertTicketing = ticketingRepository.readById(toUUID(resultSet.getBytes("PRE_TICKETING")));
    ConcertTicketInfo ticketing = ticketingRepository.readById(toUUID(resultSet.getBytes("TICKETING")));
    ConcertDate concertDate = concertDateRepository.readById(toUUID(resultSet.getBytes("CONCERT_DATE")));
    String description = resultSet.getString("DESCRIPTION");
    String link = resultSet.getString("LINK");
    return new ConcertInfo(infoId, title, genre, location, concertTicketing, ticketing, concertDate, description, link);
  }

  private Map<String, Object> toParamMap(ConcertInfo concertInfo) {
    // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장한다.
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("infoId", concertInfo.getInfoId().toString().getBytes());
    paramMap.put("title", concertInfo.getTitle());
    paramMap.put("genre", concertInfo.getGenre().toString());
    paramMap.put("location", concertInfo.getLocation().getLocationID());
    paramMap.put("concertTicketing", concertInfo.getConcertTicketing().getTicketingID());
    paramMap.put("ticketing", concertInfo.getTicketing().getTicketingID());
    paramMap.put("concertDate", concertInfo.getConcertDate().toString());
    paramMap.put("description", concertInfo.getDescription());
    paramMap.put("link", concertInfo.getLink());
    return paramMap;
  }
  // ???

  @PostConstruct
  public void initializeDatabaseSchema() {
    if (!isConcertTableExist()) {
      createConcertTable();
    }
    locationRepository.initializeDatabaseSchema();  // 필요할까요?
    ticketingRepository.initializeDatabaseSchema(); // 필요할까요?
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
                "INSERT INTO CONCERT(INFO_ID, TITLE, GENRE, LOCATION, PRE_TICKETING, TICKETING, CONCERT_DATE, DESCRIPTION, LINK) VALUES(UNHEX(REPLACE(:infoId, '-', '')), :title, :genre, :location, :concertTicketInfo, :ticketing, :concertDate, :description, :link)",
                toParamMap(concertInfo)); // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장하고, 그 Map을 update에 저장한 후 update를 실행한다.
    if (update != 1) {
      throw new RuntimeException("Nothing was inserted");
    }
    locationRepository.insert(concertInfo.getLocation());           /** 필요할까요? **/
    ticketingRepository.insert(concertInfo.getConcertTicketing());  /** 필요할까요? **/
    return concertInfo; // DB에 저장한 ConcertInfo를 반환한다.
  }

  @Override
  public ConcertInfo update(UUID infoId, String title, Genre genre, Location location,
      ConcertTicketInfo concertTicketInfo, ConcertTicketInfo ticketing, ConcertDate concertDate,
      String description, String link) {
    ConcertInfo concertInfo = readById(infoId);
    UUID locationId = concertInfo.getLocation().getLocationID();
    UUID concertTicketingId = concertInfo.getConcertTicketing().getTicketingID();
    UUID ticketingId = concertInfo.getTicketing().getTicketingID();
    /** 맞나? **/
    int update = namedJdbcTemplate.update(
            "UPDATE CONCERT SET TITLE = :title, GENRE = :genre, LOCATION = :locationID, PRE_TICKETING = :concertTicketingID, TICKETING = :ticketingID, CONCERT_DATE = :concertDate, DESCRIPTION = :description, LINK = :link WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
            toParamMap(concertInfo));
    if (update != 1) {
      throw new RuntimeException("Nothing was updated");
    }
    return concertInfo;
  }

  @Override
  public ConcertInfo readById(UUID infoId){
    try {
      return namedJdbcTemplate.queryForObject(
              "SELECT * FROM CONCERT WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
              Collections.singletonMap("infoId", infoId.toString().getBytes()), infoRowMapper);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<ConcertInfo> readByGenre(Genre genre) {
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
