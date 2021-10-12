package quickie;

import quickie.db.PersistenceManager;
import quickie.db.repositories.*;
import quickie.download.HensslerDownloader;
import quickie.webscraping.HensslerRecipeScraper;

import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = PersistenceManager.getInstance().getEmf();
            HensslerDownloader downloader = new HensslerDownloader(
                    new RecipePageRepository(emf.createEntityManager()),
                    new AppPropertyRepository(emf.createEntityManager())
            );
            AppPropertyRepository propertyRepository = new AppPropertyRepository(emf.createEntityManager());

            if (propertyRepository.downloadAllowed()) {
                downloader.downloadRecipes();
            }

            HensslerRecipeScraper scraper = new HensslerRecipeScraper(
                    new RecipePageRepository(emf.createEntityManager()),
                    new RecipeRepository(emf.createEntityManager()),
                    new IngredientRepository(emf.createEntityManager()),
                    new IngredientUsageRepository(emf.createEntityManager()),
                    new StepRepository(emf.createEntityManager())
            );
            scraper.parseDirtyPages();
        } finally {
            PersistenceManager.getInstance().getEmf().close();
        }
    }
}
