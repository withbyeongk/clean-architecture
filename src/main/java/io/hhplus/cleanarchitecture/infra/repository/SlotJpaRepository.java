package io.hhplus.cleanarchitecture.infra.repository;

import io.hhplus.cleanarchitecture.domain.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotJpaRepository extends JpaRepository<Slot, Long> {

    @Query("select s from Slot s where s.reservedCount < s.capacity and s.date > now()")
    List<Slot> findAllAvailableSlots();

    List<Slot> findSlotsByMemberId(String memberId);
}
