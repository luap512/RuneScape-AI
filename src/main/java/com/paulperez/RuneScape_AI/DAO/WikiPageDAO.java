package com.paulperez.RuneScape_AI.DAO;
import com.paulperez.RuneScape_AI.Model.Chunk;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WikiPageDAO extends JpaRepository<WikiPage, Integer> {

    @Query(value = "SELECT COUNT(*) FROM wiki_pages WHERE title = :title", nativeQuery = true)
    public Long getNumberOfWikiPages(@Param("title")String title);
}
