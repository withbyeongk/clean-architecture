package io.hhplus.cleanarchitecture.presentation.controller;

import io.hhplus.cleanarchitecture.application.SlotService;
import io.hhplus.cleanarchitecture.domain.entity.Slot;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SlotController {
    private final SlotService slotService;

    /**
     * 전체 특강 슬롯 목록을 조회합니다.
     */
    @GetMapping("/slots")
    public List<Slot> findAllSlots() {
        return slotService.findAllSlots();
    }

    /**
     * 예약 가능한 슬롯 목록을 조회합니다.
     */
    @GetMapping("/slots/available")
    public List<Slot> findAvailableSlots() {
        return slotService.findAvailableSlots();
    }
}
