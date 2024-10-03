package io.hhplus.cleanarchitecture.infra.repository;

import io.hhplus.cleanarchitecture.domain.entity.Slot;
import io.hhplus.cleanarchitecture.domain.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SlotRepositoryImpl implements SlotRepository {

    private final SlotJpaRepository slotJpaRepository;

    @Override
    public List<Slot> findSlotsByMemberId(String memberId) {
        return slotJpaRepository.findSlotsByMemberId(memberId);
    }

    @Override
    public List<Slot> findAllSlots() {
        return slotJpaRepository.findAll();
    }

    @Override
    public List<Slot> findAvailableSlots() {
        return slotJpaRepository.findAllAvailableSlots();
    }

    @Override
    public Optional<Slot> findBySlotId(Long slotId) {
        return slotJpaRepository.findById(slotId);
    }

    @Override
    public List<Slot> findCompletedSlots() {
        return slotJpaRepository.findCompletedSlots();
    }

    @Override
    public Slot save(Slot slot) {
        return slotJpaRepository.save(slot);
    }
}