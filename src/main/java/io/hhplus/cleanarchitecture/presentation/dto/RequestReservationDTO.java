package io.hhplus.cleanarchitecture.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestReservationDTO {
    private String memberId;
    private Long slotId;

}
