package io.hhplus.cleanarchitecture.infra.repository;

import io.hhplus.cleanarchitecture.domain.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotJpaRepository extends JpaRepository<Slot, Long> {

    List<Slot> findAllAvailableSlots();

    List<Slot> findSlotsByMemberId(String memberId);
}
