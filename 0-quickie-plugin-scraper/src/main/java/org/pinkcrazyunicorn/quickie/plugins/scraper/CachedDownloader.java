package org.pinkcrazyunicorn.quickie.plugins.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class CachedDownloader {
    private final String baseUrl;

    public CachedDownloader(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
    }

    public Document getDocument(String url) throws IOException {
        return this.getDocument(url, true);
    }

    public Document getDocument(String url, boolean useCache) throws IOException {
        Path cachePath = this.getCachePath(url);

        File cacheFile = cachePath.toFile();
        if (cacheFile.exists() && cacheFile.isFile()) {
            String html = String.join("\n", Files.readAllLines(cachePath));
            return Jsoup.parse(html);
        }

        Document document = Jsoup.connect(this.baseUrl + url).ignoreHttpErrors(true).get();

        this.createFile(cachePath.toFile());
        try (PrintWriter out = new PrintWriter(cachePath.toFile())) {
            out.print(document.html());
        }

        return document;
    }

    private void createFile(File file) throws IOException {
        if (file.exists()) {
            return;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    private Path getCachePath(String url) {
        String path = "page-cache/" + url.replace("/", "__") + ".html";
        return Path.of(path);
    }
}
