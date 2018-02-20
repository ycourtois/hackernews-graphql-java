package com.howtographql.init;

import com.howtographql.graphql.type.Link;
import com.howtographql.graphql.type.User;
import com.howtographql.repositories.LinkRepository;
import com.howtographql.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void run(String... args) throws Exception {
//        loadUsers();
        loadLink();
    }

    private void loadUsers() {
        User user = new User("", "", "");
    }

    private void loadLink() {
        List<Link> links = new ArrayList<>();
        links.add(new Link("http://howtographql.com", "Your favorite GraphQL page", "0"));
        links.add(new Link("http://graphql.org/learn/", "The official docks", "1"));
        linkRepository.save(links);
    }
}
