package com.nursultan.memoryjar.memory;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "memories")
public class Memory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false)
   private String title;
   @Column
   private LocalDate date;
   @Column
   private String location;
   @Column
   private String content;

    public Memory() {
    }

    public Memory(Long id, String title, LocalDate date, String location, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.content = content;
    }

    public Memory(String title, LocalDate date, String location, String content) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Memory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
