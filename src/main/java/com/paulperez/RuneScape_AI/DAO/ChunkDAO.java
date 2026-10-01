package com.paulperez.RuneScape_AI.DAO;
import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.EmbedContentResponse;
import com.paulperez.RuneScape_AI.Model.Chunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChunkDAO extends JpaRepository<Chunk, Integer> {

    @Query(value = "SELECT * FROM chunks ORDER BY embedding <=> CAST(:queryEmbedding AS vector) LIMIT 5", nativeQuery = true)
    public List<Chunk> getSimilarChunks(@Param("queryEmbedding")float[] queryEmbedding);
}
