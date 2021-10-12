package quickie.download;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import quickie.db.entities.RecipePage;
import quickie.db.repositories.AppPropertyRepository;
import quickie.db.repositories.RecipePageRepository;

import java.io.IOException;
import java.util.Arrays;

public class HensslerDownloader {
    public static final String BASE_URL = "https://www.hensslers-schnelle-nummer.de";

    private final RecipePageRepository pageRepository;
    private final AppPropertyRepository propertyRepository;

    public HensslerDownloader(RecipePageRepository pageRepository, AppPropertyRepository propertyRepository) {
        this.pageRepository = pageRepository;
        this.propertyRepository = propertyRepository;
    }

    public void downloadRecipes() {
        Arrays.stream(HensslerPage.values()).forEach(page -> {
            Document categoryPage;
            try {
                categoryPage = Jsoup.connect(page.getUrl()).ignoreHttpErrors(true).get();
                Elements recipeBoxes = categoryPage.select("div[class~=.*recipeBox .*]");
                recipeBoxes.stream()
                        .map(box -> BASE_URL + "/" + box.child(0).child(0).attributes().get("href"))
                        .filter(url -> !this.pageRepository.exists(url))
                        .forEach(url -> {
                            String pageString;
                            try {
                                pageString = Jsoup.connect(url).ignoreHttpErrors(true).get().html();
                                this.pageRepository.save(new RecipePage(page.getCategory(), url, true, pageString));
                                Thread.sleep(200);
                            } catch (IOException e) {
                                System.out.println("Error: Failed to save recipe page with url " + url);
                            } catch (InterruptedException e) {
                                System.out.println("Error: Failed to pause thread after request");
                            }
                        });
            } catch (IOException e) {
                System.out.println("Error: Failed to connect to " + page.getUrl());
            }
        });
        this.propertyRepository.updateLatestDownload();
        // TODO: Find the reason for this
        this.pageRepository.remove("https://www.hensslers-schnelle-nummer.de/");
    }
}
