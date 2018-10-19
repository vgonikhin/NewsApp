package ru.vgonikhin.aa.newsapp;

import java.io.Serializable;
import java.util.Date;

public class NewsItem implements Serializable {

    private final String title;
    private final String imageUrl;
    private final Category category;
    private final Date publishDate;
    private final String previewText;
    private final String fullText;

    NewsItem(String title, String imageUrl, Category category, Date publishDate, String previewText, String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
    }

    public String getTitle() {
        return title;
    }

    String getImageUrl() {
        return imageUrl;
    }

    Category getCategory() {
        return category;
    }

    Date getPublishDate() {
        return publishDate;
    }

    String getPreviewText() {
        return previewText;
    }

    String getFullText() {
        return fullText;
    }
}
