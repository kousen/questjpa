package com.oreilly.quest.services;

import com.oreilly.quest.entities.Castle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeocoderServiceTest {

    @Autowired
    private GeocoderService service;

    @Test
    void BostonMA() {
        Castle castle = service.fillInLatLng(new Castle("Fenway", "Boston", "MA"));
        System.out.println(castle);
        assertAll(
                () -> assertEquals(42.36, castle.getLatitude(), 0.01),
                () -> assertEquals(-71.06, castle.getLongitude(), 0.01)
        );
    }
}