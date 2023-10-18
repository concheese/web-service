//package net.concheese.server.concert.repository;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.*;
//
//import jakarta.annotation.PostConstruct;
//import net.concheese.server.concert.model.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.FileCopyUtils;
//
//import static net.concheese.server.common.JDBCUtils.toUUID;
//
//@Repository
//public class DefaultConcertRepository implements ConcertRepository {
//  private static final RowMapper<Concert> infoRowMapper = DefaultConcertRepository::mapPostRow;
//  private final NamedParameterJdbcTemplate namedJdbcTemplate;
//  private final JdbcTemplate jdbcTemplate;
//  private static TicketingRepository ticketingRepository;
//  private final Logger logger =  LoggerFactory.getLogger(DefaultConcertRepository.class.getName());
//
//  public DefaultConcertRepository(NamedParameterJdbcTemplate namedJdbcTemplate, TicketingRepository ticketingRepository) {
//    this.namedJdbcTemplate = namedJdbcTemplate;
//    this.jdbcTemplate = new JdbcTemplate(
//            Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
//    this.ticketingRepository = ticketingRepository;
//  }
//
//  private static Concert mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
//    UUID infoId = toUUID(resultSet.getBytes("INFO_ID"));
//    String title = resultSet.getString("TITLE");
//    Genre genre = Genre.valueOf(resultSet.getString("GENRE")); // Genre.valueOf()는 String을 Genre로 변환해준다.resultSet.getString("GENRE")는 DB에서 가져온 String을 반환한다.
//
//    String artist = resultSet.getString("ARTIST");
//    Ticketing concertTicketing = ticketingRepository.readById(toUUID(resultSet.getBytes("PRE_TICKETING")));
//    Ticketing ticketing = ticketingRepository.readById(toUUID(resultSet.getBytes("TICKETING")));
//
//    String description = resultSet.getString("DESCRIPTION");
//    String link = resultSet.getString("LINK");
//    return null;// todo: 복원해야함 new ConcertInfo(infoId, title, genre, location, artist, concertTicketing, ticketing, concertDate, description, link);
//  }
//
//  private Map<String, Object> toParamMap(Concert concert) {
//    // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장한다.
//    Map<String, Object> paramMap = new HashMap<>();
//    paramMap.put("infoId", concert.getInfoId().toString().getBytes());
//    paramMap.put("title", concert.getTitle());
//    paramMap.put("genre", concert.getGenre().toString());
//    paramMap.put("location", concert.getSchedules());
//    paramMap.put("performers", concert.getPerformers());
//    // todo: 이거 리스트로 어케함??
//    //paramMap.put("ticketing", concertInfo.getTicketing().getTicketingID());
//    paramMap.put("description", concert.getDescription());
//    paramMap.put("link", concert.getLink());
//    paramMap.put("created_at", LocalDateTime.now());
//    paramMap.put("updated_at", LocalDateTime.now());
//    return paramMap;
//  }
//  // ???
//
//  @PostConstruct
//  public void initializeDatabaseSchema() {
//    if (!isConcertTableExist()) {
//      createConcertTable();
//    }
//    ticketingRepository.initializeDatabaseSchema(); // 필요할까요?
//  }
//
//  private boolean isConcertTableExist() {
//    String query = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_NAME = 'CONCERT'";
//    try {
//      Integer result = jdbcTemplate.queryForObject(query, Integer.class);
//      return result != null && result > 0;
//    } catch (DataAccessException e) {
//      return false;
//    }
//  }
//
//  private void createConcertTable() {
//    try (BufferedReader reader = new BufferedReader(
//            new InputStreamReader(new ClassPathResource("schemas/concert.sql").getInputStream(),
//                    StandardCharsets.UTF_8))) {
//      jdbcTemplate.execute(FileCopyUtils.copyToString(reader));
//    } catch (IOException | DataAccessException e) {
//      throw new RuntimeException("Error creating 'CONCERT' table.", e);
//    }
//  }
//
//  @Override
//  public Concert insert(Concert concert) {
////    logger.info(concertInfo.toString());
////    int update = namedJdbcTemplate.update(
////                "INSERT INTO CONCERT(INFO_ID, TITLE, GENRE, LOCATION, ARTIST, PRE_TICKETING, TICKETING, CONCERT_DATE, DESCRIPTION, LINK, CREATED_AT, UPDATED_AT)" +
////                    " VALUES(UNHEX(REPLACE(:infoId, '-', '')), :title, :genre, UNHEX(REPLACE(:location, '-', ''))," +
////                        " :artist, UNHEX(REPLACE(:preTicketing, '-', '')), UNHEX(REPLACE(:ticketing, '-', ''))," +
////                        " :concertDate, :description, :link, :created_at, :updated_at)",
////                toParamMap(concertInfo)); // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장하고, 그 Map을 update에 저장한 후 update를 실행한다.
////    if (update != 1) {
////      throw new RuntimeException("Nothing was inserted");
////    }
////    locationRepository.insert(concertInfo.getLocation());           /** 필요할까요? **/
////    ticketingRepository.insert(concertInfo.getPreTicketing());  /** 필요할까요? **/
////    return concertInfo; // DB에 저장한 ConcertInfo를 반환한다.
//    // todo: 임시 주석처리
//    return null;
//  }
//
//  @Override
//  public Concert update(UUID infoId, String title, Genre genre, List<String> performers, List<Schedule> schedules,
//                        List<Ticketing> ticketing, String description, String link) {
//    Concert concert = readById(infoId);
//    //todo : SQL 수정없는상태
//    int update = namedJdbcTemplate.update(
//            "UPDATE CONCERT SET TITLE = :title, GENRE = :genre, LOCATION = :locationID, ARTIST = :artist, PRE_TICKETING = :concertTicketingID, TICKETING = :ticketingID, CONCERT_DATE = :concertDate, DESCRIPTION = :description, LINK = :link, UPDATED_AT = :updated_at WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
//            toParamMap(concert));
//
//    if (update != 1) {
//      throw new RuntimeException("Nothing was updated");
//    }
//    return concert;
//  }
//
//  @Override
//  public Concert readById(UUID infoId){
//    try {
//      return namedJdbcTemplate.queryForObject(
//              "SELECT * FROM CONCERT WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
//              Collections.singletonMap("infoId", infoId.toString().getBytes()), infoRowMapper);
//    } catch (EmptyResultDataAccessException e) {
//      return null;
//    }
//  }
//
//  @Override
//  public List<Concert> readByGenre(Genre genre) {
//    return namedJdbcTemplate.query(
//            "SELECT * FROM CONCERT WHERE GENRE = :genre ORDER BY CREATED_AT DESC",
//            Collections.singletonMap("genre", genre.toString()), infoRowMapper);
//  }
//
//  @Override
//  public List<Concert> readAllInfo() {
//    return namedJdbcTemplate.query("SELECT * FROM CONCERT ORDER BY CREATED_AT DESC", infoRowMapper);
//  }
//
//  @Override
//  public void deleteInfo(UUID infoId) {
//    namedJdbcTemplate.update(
//            "DELETE FROM CONCERT WHERE INFO_ID = UNHEX(REPLACE(:infoId, '-', ''))",
//            Collections.singletonMap("infoId", infoId.toString().getBytes()));
//  }
//
//  @Deprecated
//  public void deleteAll() {
//    jdbcTemplate.update("TRUNCATE CONCERT");
//  }
//}
