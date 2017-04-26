package front;


import entities.SerieEntity;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeTest extends FluentTest {

    @Page
    private HomePage homePage;

    @Page
    private FilmsPage filmsPage;

    @Page
    private ActeursPage acteursPage;

    @Page
    private SeriesPage seriesPage;

    @Page
    private ApiPage apiPage;

    @Override
    public String getWebDriver() {
        return "chrome";
    }

    @Test
    public void test_navbar_home() {
        goTo(homePage);
        $("#homeLink").click();
        homePage.isAt();
    }

    @Test
    public void test_navbar_FilmsPage(){
        goTo(homePage);
        $("#filmsLink").click();
        filmsPage.isAt();
    }

    @Test
    public void test_navbar_ActeursPage(){
        goTo(homePage);
        $("#acteursLink").click();
        acteursPage.isAt();
    }

    @Test
    public void test_navbar_SeriesPage(){
        goTo(homePage);
        $("#seriesLink").click();
        seriesPage.isAt();
    }

    @Test
    public void test_navbar_ApiPage(){
        goTo(homePage);
        $("#filmsLink").click();
        filmsPage.isAt();
    }


}
