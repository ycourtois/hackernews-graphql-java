package com.howtographql.init;

import com.howtographql.graphql.type.Article;
import com.howtographql.graphql.type.Link;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.ArticleRepository;
import com.howtographql.repositories.LinkRepository;
import com.howtographql.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yann.courtois@ippon.fr
 * @since 12/27/2017
 */
@Component
@RequiredArgsConstructor
public class BootStrap implements CommandLineRunner {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
//    @Qualifier(value = "myBeanLink")
    private Link myBeanLink;

    @Override
    public void run(String... args) throws Exception {
//        loadUsers();
        loadLink();
    }

    private void loadUsers() {
        User user = new User("", "", "");
    }

    private void loadLink() {

        List<Article> articles = new ArrayList<>();
        Article article1 = new Article("How To GraphQL", "Anonymous");
        Article article2 = new Article("Learn GraphQL", "John Doe");
        articles.add(article1);
        articles.add(article2);
        articleRepository.save(articles);

        List<Link> links = new ArrayList<>();
        final Link link1 = new Link("http://howtographql.com", "Your favorite GraphQL page", article1);
        links.add(link1);
        final Link link2 = new Link("http://graphql.org/learn/", "The official docks", article2);
        links.add(link2);

        linkRepository.save(links);

        article2.setLink(link2);
        article1.setLink(link1);
        articleRepository.save(article1);
        articleRepository.save(article2);
    }
}
