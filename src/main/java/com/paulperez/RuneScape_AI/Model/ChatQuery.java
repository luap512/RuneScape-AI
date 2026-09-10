package com.paulperez.RuneScape_AI.Model;


import jakarta.persistence.*;

import java.util.List;


// tells spring that chunk objects match w chunks table in DB
@Table(name = "chat_queries")
// Tells spring that this object will a table in the DB
@Entity
public class ChatQuery {
    @Id// Tell Hibernate that id is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Tell Hibernate to auto incriment the ID variable for wiki pages. Similar to SERIAL
    private int id;

    @Column(columnDefinition = "TEXT") // make content variable TEXT in SQL to avoid 255 character limit of String
    private String question;

    @Column(columnDefinition = "TEXT") // make content variable TEXT in SQL to avoid 255 character limit of String
    private String answer;

    // Each query uses multiple chunks
    // Chunk can be used my multiple queries
    // THEREFORE many to many relationship
    // THEREFORE we need a join table
    // so the annotation means that when I use getChunksList it actually queries the DB on its own using the join table
    @ManyToMany
    @JoinTable(
            name = "query_chunks",
            joinColumns = @JoinColumn(name = "chat_query_id"),
            inverseJoinColumns = @JoinColumn(name = "chunk_id")
    )
    private List<Chunk> chunkList;

    // Full constructor
    public ChatQuery(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    // empty constructor
    public ChatQuery() {
    }

    // GETTERS + SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Chunk> getChunkList() {
        return chunkList;
    }

    public void setChunkList(List<Chunk> chunkList) {
        this.chunkList = chunkList;
    }
}
