package io.hhplus.cleanarchitecture.domain.repository;


import io.hhplus.cleanarchitecture.domain.entity.Slot;

import java.util.List;
import java.util.Optional;

public interface SlotRepository {
    List<Slot> findSlotsByMemberId(String memberId);

    List<Slot> findAllSlots();

    List<Slot> findAvailableSlots();

    Optional<Slot> findBySlotId(Long slotId);

    List<Slot> findCompletedSlots();
}
