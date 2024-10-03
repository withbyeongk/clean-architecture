package io.hhplus.cleanarchitecture.application;

import io.hhplus.cleanarchitecture.domain.entity.Reservation;
import io.hhplus.cleanarchitecture.domain.entity.Slot;
import io.hhplus.cleanarchitecture.domain.repository.ReservationRepository;
import io.hhplus.cleanarchitecture.domain.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final SlotRepository slotRepository;
    private final ReservationRepository reservationRepository;

    public Slot makeReservation(String memberId, Long slotId) {
        Reservation makeReservation = new Reservation(memberId, slotId);

        Reservation savedReservation = reservationRepository.makeReservation(makeReservation);

        Slot findSlot = slotRepository.findBySlotId(savedReservation.getSlotId()).orElseThrow(() -> new RuntimeException("특강 슬롯을 찾을 수 없습니다."));

        findSlot.plusReservedCount();

        return findSlot;
    }
}
