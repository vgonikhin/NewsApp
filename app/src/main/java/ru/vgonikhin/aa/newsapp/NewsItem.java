package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.Date;

import static android.text.format.DateUtils.DAY_IN_MILLIS;
import static android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE;
import static android.text.format.DateUtils.HOUR_IN_MILLIS;

class NewsItem implements Serializable {

    private final String title;
    private final String imageUrl;
    private final String category;
    private final Date publishDate;
    private final String previewText;
    private final String fullText;

    NewsItem(String title, String imageUrl, String category, Date publishDate, String previewText, String fullText) {
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

    String getCategory() {
        return category;
    }

    String getPreviewText() {
        return previewText;
    }

    String getFullText() {
        return fullText;
    }

    CharSequence getDateString(Context context) {
        return DateUtils.getRelativeDateTimeString(
                context,
                publishDate.getTime(),
                HOUR_IN_MILLIS,
                5 * DAY_IN_MILLIS,
                FORMAT_ABBREV_RELATIVE
        );
    }
}
