package com.example.aaa.test.model;

import java.util.List;
import java.util.Map;

/**
 * Created by phatt on 4/2/2560.
 */

public class Question {
    String title;
    int score = -1;
    List<String> items;

    public Question(String title, List<String> items) {
        this.title = title;
        this.items = items;
    }
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
