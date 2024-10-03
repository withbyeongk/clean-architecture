package io.hhplus.cleanarchitecture.application;

import io.hhplus.cleanarchitecture.domain.entity.Slot;
import io.hhplus.cleanarchitecture.domain.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;

    public List<Slot> findAllSlots() {
        return slotRepository.findAllSlots();
    }

    public List<Slot> findAvailableSlots() {
        return slotRepository.findAvailableSlots();
    }

    public List<Slot> findSlotsByMemberId(String memberId) {
        return slotRepository.findSlotsByMemberId(memberId);
    }

    public List<Slot> findCompletedSlots() {
        return slotRepository.findCompletedSlots();
    }
}
