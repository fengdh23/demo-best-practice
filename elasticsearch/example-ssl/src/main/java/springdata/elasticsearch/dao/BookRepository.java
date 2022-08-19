package springdata.elasticsearch.dao;

import springdata.elasticsearch.dto.AddBookReqDTO;
import springdata.elasticsearch.dto.SearchBookRespDTO;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("esBookRepository")
public interface BookRepository extends ElasticsearchRepository<AddBookReqDTO, String> {
    List<SearchBookRespDTO> findByTitleOrAuthor(String title, String author);

    @Highlight(fields = {
            @HighlightField(name = "title"),
            @HighlightField(name = "author")
    })
    @Query("{\"match\":{\"title\":\"?0\"}}")
    SearchHits<SearchBookRespDTO> find(String keyword);
}
