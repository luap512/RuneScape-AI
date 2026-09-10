package com.paulperez.RuneScape_AI.DAO;
import com.paulperez.RuneScape_AI.Model.Chunk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChunkDAO extends JpaRepository<Chunk, Integer> {
}
