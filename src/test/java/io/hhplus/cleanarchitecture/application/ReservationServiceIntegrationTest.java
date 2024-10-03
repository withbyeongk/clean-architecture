package io.hhplus.cleanarchitecture.application;

import io.hhplus.cleanarchitecture.domain.entity.Slot;
import io.hhplus.cleanarchitecture.domain.repository.SlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReservationServiceIntegrationTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SlotRepository slotRepository;

    @BeforeEach
    void setUp() {
        Slot slot = new Slot(1L, LocalDate.of(2024, 10, 10), LocalTime.of(10, 00), 30, 0, "항해플러스 특강", "코치님");
        slotRepository.save(slot);
    }

    @Test
    @DisplayName("총 40번의 예약 요청을 하면 30명만 성공한다.")
    void maxCapacityTest() throws InterruptedException {
        // given
        int threadCount = 40;
        String memberId = "programKing";
        Slot savedSlot = slotRepository.findBySlotId(1L).get();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);


        // when
        AtomicLong atomicSuccessCnt = new AtomicLong(0);
        AtomicLong atomicFailCnt = new AtomicLong(0);

        for (int i = 0; i < threadCount; i++) {
            Long idx = (long) (i + 1);
            executorService.submit(() -> {
                try {
                    reservationService.makeReservation(memberId + idx, savedSlot.getId());
                    atomicSuccessCnt.incrementAndGet();
                } catch (Exception e) {
                    atomicFailCnt.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();

        // then
        assertEquals(30, atomicSuccessCnt.get());
        assertEquals(10, atomicFailCnt.get());
    }

    @Test
    @DisplayName("같은 사용자가 같은 강의에 여러번 예약할 경우 에러가 발생하는 테스트")
    void duplicateReservationTest() throws InterruptedException {
        // given
        Slot savedSlot = slotRepository.findBySlotId(1L).get();
        String memberId = "programKing";
        int threadCount = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);


        // when
        AtomicLong atomicSuccessCnt = new AtomicLong(0);
        AtomicLong atomicFailCnt = new AtomicLong(0);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reservationService.makeReservation(memberId, savedSlot.getId());
                    atomicSuccessCnt.incrementAndGet();
                } catch (Exception e) {
                    atomicFailCnt.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();

        // then
        assertEquals(1, atomicSuccessCnt.get());
        assertEquals(4, atomicFailCnt.get());
    }

}