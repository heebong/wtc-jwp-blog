package techcourse.myblog.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();
    private int articleId = 0;

    public List<Article> findAll() {
        return articles.stream()
                .sorted(Comparator.comparing(Article::getId).reversed())
                .collect(Collectors.toList())
                ;
    }

    public void save(Article article) {
        article.setId(++articleId);
        articles.add(article);
    }

    public Article find(int articleId) {
        return articles.stream()
                .filter(article -> article.isMatchId(articleId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                ;
    }

    public void saveEdited(Article editedArticle) {
        delete(editedArticle.getId());
        articles.add(editedArticle);
    }

    public void delete(int articleId) {
        Article articleToDelete = find(articleId);
        articles.remove(articleToDelete);
    }
}
