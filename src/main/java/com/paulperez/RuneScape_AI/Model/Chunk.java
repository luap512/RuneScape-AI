package com.paulperez.RuneScape_AI.Model;

import com.pgvector.PGvector;
import jakarta.persistence.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

// tells spring that chunk objects match w chunks table in DB
@Table(name = "chunks")
// Tells spring that this object will a table in the DB
@Entity
public class Chunk {

    // DECLARE CLASS VARIABLES

    @Id// Tell Hibernate that id is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Tell Hibernate to auto incriment the ID variable for wiki pages. Similar to SERIAL
    private int id;

    @ManyToOne // each chunk belongs to one wiki page; one wiki page has many chunks
    private WikiPage wikiPage;

    @Column(columnDefinition = "TEXT") // make content variable TEXT in SQL to avoid 255 character limit of String
    private String content;

    // tell hibernate to use PGvector to create a vector column in the DB.
    @Column(name = "embedding")
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 768)
    private float[] embeddingVector;


    // EMPTY CONSTRUCTOR
    public Chunk() {
    }

    // FULL CONSTRUCTOR
    public Chunk(int id, WikiPage wikiPage, String content, float[] embeddingVector) {
        this.id = id;
        this.wikiPage = wikiPage;
        this.content = content;
        this.embeddingVector = embeddingVector;
    }


    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WikiPage getWikiPage() {
        return wikiPage;
    }

    public void setWikiPage(WikiPage wikiPage) {
        this.wikiPage = wikiPage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float[] getEmbeddingVector() {
        return embeddingVector;
    }

    public void setEmbeddingVector(float[] embeddingVector) {
        this.embeddingVector = embeddingVector;
    }
}
