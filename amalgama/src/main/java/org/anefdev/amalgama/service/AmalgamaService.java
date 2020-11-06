package org.anefdev.amalgama.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AmalgamaService {

    final String SEARCH_URL = "https://www.amalgama-lab.com/songs/";
    Document doc;
    String song;

    public String executeSearch(String request) {
        try{
            getConnection(request);
        }catch (IndexOutOfBoundsException e) {
            return "Invalid request: use [artist] - [song title]";
        }
        String lyricsOriginal = getOriginal();
        String lyricsTranslated = getTranslated();
        return request + "\n\n" + lyricsOriginal + "\n\n" + lyricsTranslated;
    }

    void getConnection(String input) {

        String[] rawText = input.split("-");
        String artistRaw = rawText[0].trim();
        String songRaw = rawText[1].trim();
        String artistFin = artistRaw.toLowerCase().replaceAll(" ", "_");
        String songFin = songRaw.toLowerCase().replaceAll(" ", "_");

        try {
            doc = Jsoup.connect(SEARCH_URL + artistFin.charAt(0) + "/" + artistFin + "/" + songFin + ".html").get();
            song = songRaw;
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    String getOriginal() {
        try {
            List<String> origin = doc.select("div.original").eachText();
            StringBuilder builder = new StringBuilder();

            for (String line : origin) {
                if (line.equalsIgnoreCase(song)) {
                    break;
                }
                builder.append(line);
                builder.append("\n");
            }
            return builder.toString();

        } catch (NullPointerException e) {
            return "Song not found";
        }
    }

    String getTranslated() {

        try {
            List<String> trans = doc.select("div.translate").eachText();
            StringBuilder builder = new StringBuilder();

            for (String line : trans) {
                if (line.contains("перевод")) {
                    break;
                }
                builder.append(line);
                builder.append("\n");
            }
            return builder.toString();
        } catch (NullPointerException e) {
            return "Song not found";
        }

    }
}
