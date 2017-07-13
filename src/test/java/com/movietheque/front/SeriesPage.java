package com.movietheque.front;


import org.fluentlenium.core.FluentPage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SeriesPage extends FluentPage{
    @Override
    public String getUrl() {
        return "http://localhost:8080/series/";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).contains("Series");
    }
}
