package org.anefdev.amalgama.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmalgamaServiceTest {

    AmalgamaService service;

    @BeforeEach
    void init() {
        service = new AmalgamaService();
    }

    @Test
    void getOriginal() {
        service.getConnection("Arctic monkeys - 505");
        assertTrue(service.getOriginal().startsWith("I'm going back to 505"));
    }

    @Test
    void getTranslated() {
        service.getConnection("Arctic monkeys - 505");
        assertTrue(service.getTranslated().startsWith("Я возвращаюсь"));
    }

    @Test
    void getOriginal2() {
        service.getConnection("Radiohead - Paranoid android");
        assertTrue(service.getOriginal().startsWith("Please could you stop"));
    }

    @Test
    void getOriginal_SongWithMultipleLyrics() {
        service.getConnection("Radiohead - Paranoid android");
        System.out.println(service.getOriginal());
    }
}