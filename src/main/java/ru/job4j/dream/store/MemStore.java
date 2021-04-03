package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {

    private static final Store INST = new MemStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final static AtomicInteger POST_ID = new AtomicInteger(4);

    private final static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);

    private final static AtomicInteger USER_ID = new AtomicInteger(4);

    public static Store instOf() {
        return INST;
    }

    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
        users.put(1, new User(1, "user1", "user1@local", "user1"));
        users.put(2, new User(2, "user2", "user2@local", "user2"));
        users.put(3, new User(3, "user3", "user3@local", "user3"));
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        User rst = null;
        for (int id : users.keySet()) {
            User user = users.get(id);
            if (user.getEmail().equals(email)) {
                rst = user;
                break;
            }
        }
        return rst;
    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> findAllUsers() {
        return null;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    @Override
    public Candidate deleteCandidate(int id) {
        return null;
    }
}
