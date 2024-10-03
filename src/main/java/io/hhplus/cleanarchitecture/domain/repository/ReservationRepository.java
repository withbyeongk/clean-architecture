package io.hhplus.cleanarchitecture.domain.repository;

import io.hhplus.cleanarchitecture.domain.entity.Reservation;

public interface ReservationRepository {

    Reservation makeReservation(Reservation reservation);
}
