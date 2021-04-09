package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {

    User findUserById(int id);

    User findUserByEmail(String email);

    void saveUser(User user);

    Collection<User> findAllUsers();

    Collection<Post> findAllPosts();

    void savePost(Post post);

    Post findPostById(int id);

    Collection<Candidate> findAllCandidates();

    void saveCandidate(Candidate candidate);

    Candidate findCandidateById(int id);

    Candidate deleteCandidate(int id);
}