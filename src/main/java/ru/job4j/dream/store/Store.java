package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {

    User findUserByEmail(String email);

    void saveUser(User user);

    Collection<Post> findAllPosts();

    void savePost(Post post);

    Post findPostById(int id);

    Post deletePost(int id);

    Collection<Candidate> findAllCandidates();

    void saveCandidate(Candidate candidate);

    Candidate findCandidateById(int id);

    Candidate deleteCandidate(int id);

    Collection<City> findAllCities();
}