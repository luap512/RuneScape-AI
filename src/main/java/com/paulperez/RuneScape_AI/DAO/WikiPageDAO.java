package com.paulperez.RuneScape_AI.DAO;
import com.paulperez.RuneScape_AI.model.WikiPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiPageDAO extends JpaRepository<WikiPage, Integer> {
}
