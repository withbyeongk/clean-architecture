package io.hhplus.cleanarchitecture.domain;

import io.hhplus.cleanarchitecture.domain.entity.Reservation;
import io.hhplus.cleanarchitecture.domain.entity.Slot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotUnitTest {

    @Test
    @DisplayName("예약할 때 특강 슬롯 ID 값이 0보다 작거나 같으면 IllegalArgumentException 에러가 발생한다.")
    void validateSlotIdTest() {
        String memberId = "test";
        Long slotId = 0L;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(memberId, slotId);
        });
        assertEquals("특강 슬롯 ID는 0보다 큰 값이어야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("예약인원 수가 증가하는지 확인합니다.")
    void plusReservedCountTest() {
        // given
        Slot slot = new Slot(1L, LocalDate.of(2022,1, 10), LocalTime.of(10, 10), 30, 1, "새 특강", "개발왕");

        // when
        slot.plusReservedCount();

        // then
        assertEquals(2, slot.getReservedCount());
    }
}
