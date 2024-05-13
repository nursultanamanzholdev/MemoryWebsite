package com.nursultan.memoryjar.memory;

import java.time.LocalDate;

public class MemoryDTO {
    private String title;
    private LocalDate date;
    private String location;
    private String content;

    public MemoryDTO(String title, LocalDate date, String location, String content) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MemoryDTO{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
