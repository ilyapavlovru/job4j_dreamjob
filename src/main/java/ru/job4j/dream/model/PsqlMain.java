package ru.job4j.dream.model;

import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.savePost(new Post(0, "Java Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        store.savePost(new Post(1, "Java Middle Job"));
        Post post = store.findPostById(1);
        System.out.println(post.getId() + " " + post.getName());

        System.out.println("");
        store.saveCandidate(new Candidate(0, "Java Candidate", 2));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
        store.saveCandidate(new Candidate(1, "Java Middle Candidate", 2));
        Candidate candidate = store.findCandidateById(1);
        System.out.println(candidate.getId() + " " + candidate.getName());
    }
}
