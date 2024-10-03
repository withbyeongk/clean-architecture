package io.hhplus.cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Slot slot;

    @Column(nullable = false)
    private Long slotId;

    @Column(nullable = false)
    private String memberId;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();


    public Reservation(String memberId, Long slotId) {
        if (memberId == null || memberId.isEmpty()) {
            throw new IllegalArgumentException("사용자 ID를 입력해야 합니다.");
        }

        if (slotId == null || slotId <= 0) {
            throw new IllegalArgumentException("특강 슬롯 ID는 0보다 큰 값이어야 합니다.");
        }
        this.memberId = memberId;
        this.slotId = slotId;
    }

}