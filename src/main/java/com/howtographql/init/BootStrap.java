package com.howtographql.init;

import com.howtographql.repositories.LinkRepository;
import com.howtographql.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//        loadLink();
    }

//    private void loadLink() {
//        List<Link> links = new ArrayList<>();
//        links.add(new Link("http://howtographql.com", "Your favorite GraphQL page"));
//        links.add(new Link("http://graphql.org/learn/", "The official docks"));
//        linkRepository.save(links);
//    }
}
