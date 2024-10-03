package io.hhplus.cleanarchitecture.domain;

import io.hhplus.cleanarchitecture.domain.entity.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationUnitTest {

    @Test
    @DisplayName("예약할 때 사용자 ID에 값이 없다면 IllegalArgumentException 에러가 발생한다.")
    void validateMemberIdTest() {
        String memberId = null;
        Long slotId = 1L;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(memberId, slotId);
        });
        assertEquals("사용자 ID를 입력해야 합니다.", exception.getMessage());
    }

}