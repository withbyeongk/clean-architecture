package io.hhplus.cleanarchitecture.application;


import io.hhplus.cleanarchitecture.domain.entity.Reservation;
import io.hhplus.cleanarchitecture.domain.entity.Slot;
import io.hhplus.cleanarchitecture.domain.repository.ReservationRepository;
import io.hhplus.cleanarchitecture.domain.repository.SlotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceUnitTest {

    @InjectMocks
    private ReservationService reservationService;
    @Mock
    private SlotRepository slotRepository;
    @Mock
    private ReservationRepository reservationRepository;

    @Test
    @DisplayName("사용자ID와 특강ID를 입력해서 예약을 하면 예약 내역이 저장되고, 해당 특강 슬롯을 조회하여 예약자 수를 증가시키는 메소드를 실행한다.")
    void 예약_테스트_정상케이스() {
        // given
        String memberId = "programKing";
        Long slotId = 1L;

        Reservation reservation = new Reservation(memberId, slotId);
        Slot mockSlot = mock(Slot.class);

        // when
        when(reservationRepository.makeReservation(any(Reservation.class))).thenReturn(reservation);
        when(slotRepository.findBySlotId(slotId)).thenReturn(Optional.of(mockSlot));
        Slot reservedSlot = reservationService.makeReservation(memberId, slotId);

        // then
        verify(reservationRepository).makeReservation(any(Reservation.class));
        verify(slotRepository).findBySlotId(slotId);
        verify(mockSlot, times(1)).plusReservedCount();
        assertEquals(mockSlot, reservedSlot);
    }

    @Test
    @DisplayName("예약 시 조회할 수 없는 slotId 값이 입력되었을 때 RuntimeException 오류가 발생한다.")
    void 예약시_slotId_에러_테스트() {
        // given
        String memberId = "programKing";
        Long slotId = 1L;

        // when
        when(reservationRepository.makeReservation(any(Reservation.class))).thenReturn(new Reservation(memberId, slotId));
        when(slotRepository.findBySlotId(slotId)).thenReturn(Optional.empty());

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.makeReservation(memberId, slotId);
        });

        assertEquals("특강 슬롯을 찾을 수 없습니다.", exception.getMessage());
    }
}