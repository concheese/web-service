package net.concheese.server.community.repository;

import static net.concheese.server.common.JDBCUtils.toLocalDateTime;
import static net.concheese.server.common.JDBCUtils.toUUID;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import net.concheese.server.community.model.Category;
import net.concheese.server.community.model.Post;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

@Repository
public class PostJdbcRepository implements PostRepository {

  private static final RowMapper<Post> postRowMapper = PostJdbcRepository::mapPostRow;
  private final NamedParameterJdbcTemplate namedJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;

  public PostJdbcRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.namedJdbcTemplate = namedJdbcTemplate;
    this.jdbcTemplate = new JdbcTemplate(
        Objects.requireNonNull(this.namedJdbcTemplate.getJdbcTemplate().getDataSource()));
  }

  private static Post mapPostRow(ResultSet resultSet, int rowNumber) throws SQLException {
    UUID postId = toUUID(resultSet.getBytes("POST_ID"));
    String title = resultSet.getString("TITLE");
    Category category = Category.valueOf(resultSet.getString("CATEGORY"));
    String content = resultSet.getString("CONTENT");
    LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("CREATED_AT"));
    LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("UPDATED_AT"));
    return new Post(postId, title, category, content, createdAt, updatedAt);
  }

  private Map<String, Object> toParamMap(Post post) {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("postId", post.getPostId().toString().getBytes());
    paramMap.put("title", post.getTitle());
    paramMap.put("category", post.getCategory().toString());
    paramMap.put("content", post.getContent());
    paramMap.put("createdAt", post.getCreatedAt());
    paramMap.put("updatedAt", post.getUpdatedAt());
    return paramMap;
  }

  @PostConstruct
  public void initializeDatabaseSchema() {
    if (!isBoardTableExist()) {
      createBoardTable();
    }
  }

  private boolean isBoardTableExist() {
    String query = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_NAME = 'BOARD'";
    try {
      Integer result = jdbcTemplate.queryForObject(query, Integer.class);
      return result != null && result > 0;
    } catch (DataAccessException e) {
      return false;
    }
  }

  private void createBoardTable() {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new ClassPathResource("schemas/board_schema.sql").getInputStream(),
            StandardCharsets.UTF_8))) {
      jdbcTemplate.execute(FileCopyUtils.copyToString(reader));
    } catch (IOException | DataAccessException e) {
      throw new RuntimeException("Error creating 'BOARD' table.", e);
    }
  }

  @Override
  public Post insert(Post post) {
    int update = namedJdbcTemplate.update(
        "INSERT INTO BOARD(POST_ID, TITLE, CATEGORY, CONTENT, CREATED_AT, UPDATED_AT) VALUES(UNHEX(REPLACE(:postId, '-', '')), :title, :category, :content, :createdAt, :updatedAt)",
        toParamMap(post));
    if (update != 1) {
      throw new RuntimeException("Nothing was inserted");
    }
    return post;
  }

  @Override
  public Optional<Post> selectById(UUID postId) {
    try {
      return Optional.ofNullable(namedJdbcTemplate.queryForObject(
          "SELECT * FROM BOARD WHERE POST_ID = UNHEX(REPLACE(:postId, '-', ''))",
          Collections.singletonMap("postId", postId.toString().getBytes()), postRowMapper));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Post> selectByCategory(Category category) {
    return namedJdbcTemplate.query(
        "SELECT * FROM BOARD WHERE CATEGORY = :category ORDER BY CREATED_AT DESC",
        Collections.singletonMap("category", category.toString()), postRowMapper);
  }

  @Override
  public List<Post> selectAll() {
    return namedJdbcTemplate.query("SELECT * FROM BOARD ORDER BY CREATED_AT DESC", postRowMapper);
  }

  @Override
  public Post update(Post post) {
    int update = namedJdbcTemplate.update(
        "UPDATE BOARD SET TITLE = :title, CATEGORY = :category, CONTENT = :content, UPDATED_AT = :updatedAt WHERE POST_ID = UNHEX(REPLACE(:postId, '-', ''))",
        toParamMap(post));
    if (update != 1) {
      throw new RuntimeException("Nothing was updated");
    }
    return post;
  }

  @Override
  public void deleteById(UUID postId) {
    namedJdbcTemplate.update(
        "DELETE FROM BOARD WHERE POST_ID = UNHEX(REPLACE(:postId, '-', ''))",
        Collections.singletonMap("postId", postId.toString().getBytes()));
  }

  @Deprecated
  public void deleteAll() {
    jdbcTemplate.update("TRUNCATE BOARD");
  }

}
