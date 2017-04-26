package front;


import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Wait
public class Home extends FluentTest {

    @Page
    private HomePage page;

    @Override
    public String getWebDriver() {
        return "chrome";
    }

    @Test
    public void test_no_exception() {
        goTo(page);
        assertThat(window().title()).contains("MovieTheque");
    }

}
