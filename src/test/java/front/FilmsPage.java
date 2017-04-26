package front;


import org.fluentlenium.core.FluentPage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FilmsPage extends FluentPage{
    @Override
    public String getUrl() {
        return "http://localhost:8080/films/";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).contains("Films");
    }
}
