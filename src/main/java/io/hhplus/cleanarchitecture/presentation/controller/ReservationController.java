package io.hhplus.cleanarchitecture.presentation.controller;
import io.hhplus.cleanarchitecture.application.ReservationService;
import io.hhplus.cleanarchitecture.domain.entity.Slot;
import io.hhplus.cleanarchitecture.presentation.dto.RequestReservationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 사용자가 특강에 예약을 합니다.
     */
    @PostMapping("/reservations")
    public Slot makeReservation(RequestReservationDTO dto) {
        Slot slot = reservationService.makeReservation(dto.getMemberId(), dto.getSlotId());
        return slot;
    }
}