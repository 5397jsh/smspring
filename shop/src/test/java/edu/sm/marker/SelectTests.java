package edu.sm.marker;

import edu.sm.app.dto.Marker;
import edu.sm.app.service.MarkerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class SelectTests {
    @Autowired
    MarkerService markerService;

    @Test
    void contextLoads() {
        try {
            List<Marker> list = markerService.get();
            list.forEach(System.out::println);

            Marker marker = markerService.get(101);
            log.info(marker.toString());

            List<Marker> list2 = markerService.get();
            list.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}