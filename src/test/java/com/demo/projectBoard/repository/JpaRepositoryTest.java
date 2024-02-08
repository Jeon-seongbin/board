package com.demo.projectBoard.repository;

import com.demo.projectBoard.config.JpaConfig;
import com.demo.projectBoard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Jpa test")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {

        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    public void selectTest() {
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotNull().hasSize(0);
    }

    @Test
    public void insertTest(){
        long prevCount = articleRepository.count();
        Article article = Article.of("title","content","hashtag");
        articleRepository.save(article);
        assertThat(articleRepository.count()).isEqualTo(prevCount + 1);
    }
}