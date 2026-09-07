package com.paulperez.RuneScape_AI.DAO;
import com.paulperez.RuneScape_AI.model.ChatQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatQueriesDAO extends JpaRepository<ChatQuery, Integer> {
}
