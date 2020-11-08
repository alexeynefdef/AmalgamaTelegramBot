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
    void testGetOriginal_invalidRequest_message() {
        String message = "Invalid request: use [artist] - [song title]";
        assertEquals(message,service.executeSearch("nirvana"));
    }

    @Test
    void testGetOriginal_noSuchSong_message() {
        String message = "Song not found";
        service.executeSearch("Nirvana - californication");
        assertEquals(message,service.getOriginal());
    }

    @Test
    void testGetOriginal() {
        service.executeSearch("Arctic monkeys - 505");
        assertTrue(service.getOriginal().startsWith("I'm going back to 505"));
    }

    @Test
    void getTranslated() {
        service.executeSearch("Arctic monkeys - 505");
        assertTrue(service.getTranslated().startsWith("Я возвращаюсь"));
    }

    @Test
    void getOriginal2() {
        service.executeSearch("Radiohead - Paranoid android");
        assertTrue(service.getOriginal().startsWith("Please could you stop"));
    }

    @Test
    void getOriginal_SongWithMultipleLyrics() {
        String text = "Please could you stop the noise, I'm trying to get some rest\n" +
                "From all the unborn chicken voices in my head\n" +
                "What's that...? (I may be paranoid, but not an android)\n" +
                "What's that...? (I may be paranoid, but not an android)\n" +
                "When I am king, you will be first against the wall\n" +
                "With your opinion which is of no consequence at all\n" +
                "What's that...? (I may be paranoid, but no android)\n" +
                "What's that...? (I may be paranoid, but no android)\n" +
                "Ambition makes you look pretty ugly\n" +
                "Kicking and squealing gucci little piggy\n" +
                "You don't remember\n" +
                "You don't remember\n" +
                "Why don't you remember my name?\n" +
                "Off with his head, man\n" +
                "Off with his head, man\n" +
                "Why don't you remember my name?\n" +
                "I guess he does....\n" +
                "Rain down, rain down\n" +
                "Come on rain down on me\n" +
                "From a great height\n" +
                "From a great height... height...\n" +
                "Rain down, rain down\n" +
                "Come on rain down on me\n" +
                "From a great height\n" +
                "From a great height... height...\n" +
                "Rain down, rain down\n" +
                "Come on rain down on me\n" +
                "That's it, sir\n" +
                "You're leaving\n" +
                "The crackle of pigskin\n" +
                "The dust and the screaming\n" +
                "The yuppies networking\n" +
                "The panic, the vomit\n" +
                "The panic, the vomit\n" +
                "God loves his children, God loves his children, yeah!\n";
        service.executeSearch("Radiohead - Paranoid android");
        assertEquals(text.length(),service.getOriginal().length());
        assertEquals(text,service.getOriginal());
    }

    @Test
    void getOriginal_SongWithMultipleLyrics2() {
        String text = "Finished with my woman 'cause she couldn't help me with my mind\n" +
                "people think I'm insane because I am frowning all the time\n" +
                "All day long I think of things but nothing seems to satisfy\n" +
                "Think I'll lose my mind if I don't find something to pacify\n" +
                "Can you help me occupy my brain?\n" +
                "I need someone to show me the things in life that I can't find\n" +
                "I can't see the things that make true happiness, I must be blind\n" +
                "Make a joke and I will sigh and you will laugh and I will cry\n" +
                "Happiness I cannot feel and love to me us so unreal\n" +
                "And so as you hear these words telling you now of my state\n" +
                "I tell you to enjoy life I wish I could but it's too late\n";
        service.executeSearch("Black sabbath - Paranoid");
        assertEquals(text,service.getOriginal());
    }
}