package net.concheese.server.info.model;

/**
 * concheese 어플리케이션에서 문화 활동 티켓의 판매 상태를 나타내는 열거형입니다.
 * <p>
 * 이 열거형은 각 문화 활동 티켓의 판매 상태를 정의합니다. 예를 들면, 일반 판매와 선착순 판매 상태를 구분합니다.
 * </p>
 *
 * @author MyoungHa Ji
 * @version 1.0
 * @since 2023-10-26
 */
public enum TicketingStatus {
  /**
   * 선예매 상태입니다. 이 상태는 일반 판매 시작 전에 특정 그룹이나 우선 순위를 가진 사람들을 위한 판매를 나타냅니다.
   */
  PRE_SALE,

  /**
   * 일반 예매 상태입니다. 이 상태는 모든 대상에게 티켓이 판매되는 일반적인 상태를 나타냅니다.
   */
  GENERAL_SALE
}
