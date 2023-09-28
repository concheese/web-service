package net.concheese.server.concert.repository;

import jakarta.annotation.PostConstruct;
import net.concheese.server.concert.model.Location;
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
import java.util.*;

import static net.concheese.server.common.JDBCUtils.toUUID;

@Repository
public class LocationRepository {
    private static final RowMapper<Location> infoRowMapper = LocationRepository::mapPostRow;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;


    public LocationRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = new JdbcTemplate(
                Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
    }

    private static Location mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
        // DB에서 ConcertInfo의 각 필드에 대한 정보를 가져온다.
        UUID locationID = toUUID(resultSet.getBytes("LOCATION_ID"));
        int coordinate_1 = resultSet.getInt("COORDINATE_1");
        int coordinate_2 = resultSet.getInt("COORDINATE_2");
        String locationName = resultSet.getString("LOCATION_NAME");
        return new Location(locationID, coordinate_1, coordinate_2, locationName);
    }
    private Map<String, Object> toParamMap(Location location) {
        // DB에 저장하기 위해 ConcertInfo의 각 필드를 Map에 저장한다.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("locationID", location.getLocationID().toString().getBytes());
        paramMap.put("coordinate_1", location.getCoordinate_1());
        paramMap.put("coordinate_2", location.getCoordinate_2());
        paramMap.put("locationName", location.getLocationName());

        return paramMap;
    }
    @PostConstruct
    public void initializeDatabaseSchema() {
        if (!isLocationTableExist()) {
            createLocationTable();
        }
    }

    private boolean isLocationTableExist() {
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'LOCATION'";
        try {
            Integer result = jdbcTemplate.queryForObject(query, Integer.class);
            return result != null && result > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private void createLocationTable() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("schemas/location_schema.sql").getInputStream(),
                        StandardCharsets.UTF_8))) {
            jdbcTemplate.execute(FileCopyUtils.copyToString(reader));
        } catch (IOException | DataAccessException e) {
            throw new RuntimeException("Error creating 'LOCATION' table.", e);
        }
    }

    public Location insert(Location location) {
        int update = namedJdbcTemplate.update(
                "INSERT INTO LOCATION (LOCATION_ID, COORDINATE_1, COORDINATE_2, COORDINATE_3, LOCATION_NAME) VALUES (UNHEX(REPLACE(:locationID, '-', '')), :coordinate_1, :coordinate_2, :coordinate_3, :locationName)",
                toParamMap(location));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return location;
    }

    public Location update(UUID locationId, int coordinate_1, int coordinate_2, int coordinate_3, String locationName) {
        Location location = readById(locationId);
        int update = namedJdbcTemplate.update(
                "UPDATE LOCATION SET COORDINATE_1 = :coordinate_1, COORDINATE_2 = :coordinate_2, COORDINATE_3 = :coordinate_3, LOCATION_NAME = :locationName WHERE LOCATION_ID = UNHEX(REPLACE(:locationID, '-', ''))",
                toParamMap(location));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return location;
    }

    public Location readById(UUID locationId) {
        return namedJdbcTemplate.queryForObject(
                "SELECT * FROM LOCATION WHERE LOCATION_ID = UNHEX(REPLACE(:locationID, '-', ''))",
                Collections.singletonMap("locationID", locationId.toString().getBytes()), infoRowMapper);
    }

    public List<Location> readAll() {
        return namedJdbcTemplate.query("SELECT * FROM LOCATION", infoRowMapper);
    }

    public void deleteLocation(UUID locationId) {
        namedJdbcTemplate.update("DELETE FROM LOCATION WHERE LOCATION_ID = UNHEX(REPLACE(:locationID, '-', ''))",
                Collections.singletonMap("locationID", locationId.toString().getBytes()));
    }

    @Deprecated
    public void deleteAll() {
        jdbcTemplate.update("TRUNCATE LOCATION");
    }

}
