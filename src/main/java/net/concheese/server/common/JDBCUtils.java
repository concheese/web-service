package net.concheese.server.common;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * {@code JDBCUtils} 클래스는 JDBC 작업에서 사용되는 데이터베이스 특정 유형과 Java 유형 간의 변환을 위한 유틸리티 메서드를 제공합니다. 이 클래스는
 * {@link Timestamp}와 {@link LocalDateTime} 간의 변환, 그리고 바이트 배열을 {@link UUID}로 변환하는 메서드를 제공합니다.
 *
 * @version 1.0
 * @since 2023-08-04
 */
public class JDBCUtils {

  /**
   * {@link Timestamp}를 {@link LocalDateTime}으로 변환합니다.
   *
   * @param timestamp 변환할 {@link Timestamp}.
   * @return 해당하는 {@link LocalDateTime} 또는 입력된 timestamp가 {@code null}인 경우 {@code null}을 반환합니다.
   */
  public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
    return timestamp != null ? timestamp.toLocalDateTime() : null;
  }

  /**
   * 바이트 배열을 {@link UUID}로 변환합니다.
   *
   * @param bytes UUID를 나타내는 바이트 배열입니다.
   * @return 해당하는 {@link UUID}.
   */
  public static UUID toUUID(byte[] bytes) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
  }

}
