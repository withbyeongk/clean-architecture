package io.hhplus.cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "slot")
public class Slot {
    private static final int MAX_CAPACITY = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private int capacity = MAX_CAPACITY;

    @Column(nullable = false)
    private int reservedCount = 0;

    @Column
    private String title;

    @Column(nullable = false)
    private String coachName;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservationEntities;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    public Slot(Long id, LocalDate date, LocalTime startTime, int capacity, int reservedCount, String title, String coachName) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.capacity = capacity;
        this.reservedCount = reservedCount;
        this.title = title;
        this.coachName = coachName;
    }

    public void plusReservedCount() {
        this.reservedCount++;
    }
}
