package com.paulperez.RuneScape_AI.Model;

import jakarta.persistence.*;

// tells spring that wikiPage objects match w wiki_pages table in DB
@Table(name = "wiki_pages")
// Tells spring that this object will a table in the DB
@Entity
public class WikiPage {

    // DECALRE CLASS VARIABLES
    @Id// Tell Hibernate that id is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Tell Hibernate to auto incriment the ID variable for wiki pages. Similar to SERIAL
    private int id;

    private String title;

    private String URL;

    @Column(columnDefinition = "TEXT") // make content variable TEXT in SQL to avoid 255 character limit of String
    private String content;

    // EMPTY CONSTRUCTOR
    public WikiPage() {
    }

    // FULL CONSTRUCTOR
    public WikiPage(int id, String title, String URL, String content) {
        this.id = id;
        this.title = title;
        this.URL = URL;
        this.content = content;
    }

    // GETTERS + SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
