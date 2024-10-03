package io.hhplus.cleanarchitecture.infra.repository;

import io.hhplus.cleanarchitecture.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

}

