package ru.job4j.dream.servlet;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostServletTest {

    @Test
    public void testAGetPosts() throws IOException, ServletException {
        Store store = MemStore.instOf();
        Collection<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "Junior Java Job"));
        posts.add(new Post(2, "Middle Java Job"));
        posts.add(new Post(3, "Senior Java Job"));
        List<String> expectedPostNames = posts.stream()
                .map(Post::getName)
                .collect(Collectors.toList());
        User user = new User(4, "user4", "user4@local", "");

        PowerMockito.mockStatic(PsqlStore.class);

        HttpSession session = mock(HttpSession.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);

        when(PsqlStore.instOf()).thenReturn(store);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getRequestDispatcher("posts.jsp")).thenReturn(reqDispatcher);

        new PostServlet().doGet(req, resp);

        verify(req).getRequestDispatcher("posts.jsp");
        List<String> actualPostNames = store.findAllPosts().stream()
                .map(Post::getName)
                .collect(Collectors.toList());

        Assert.assertEquals(expectedPostNames, actualPostNames);
    }

    @Test
    public void testBAddPost() throws IOException {
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("action")).thenReturn("update");
        when(req.getParameter("name")).thenReturn("Java Senior Job");
        new PostServlet().doPost(req, resp);
        Post post = store.findAllPosts().stream().reduce((a, b) -> b).orElse(null);
        assertThat(post.getName(), is("Java Senior Job"));
    }
}
