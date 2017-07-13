package com.movietheque.front;


import org.fluentlenium.core.FluentPage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomePage extends FluentPage{
    @Override
    public String getUrl() {
        return "localhost:8080";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).contains("MovieTheque");
    }
}
