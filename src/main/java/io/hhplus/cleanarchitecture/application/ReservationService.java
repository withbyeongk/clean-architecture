package io.hhplus.cleanarchitecture.application;

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
        return new Slot();

    }
}
