package EasyTasks;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class Post {
    private int id;
    private String text;
    private List<Comment> commentaries = new ArrayList<>();

    private Post() {
    }

    public Post(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void addComment(Comment comment) {
        commentaries.add(comment);
    }

    public int getId() {
        return id;
    }
}

class Comment {
    private String user;
    private String text;

    private Comment() {
    }

    public Comment(String user, String text) {
        this.user = user;
        this.text = text;
    }
}

public class HttpServerSecond {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static Gson gson = new Gson();
    private static List<Post> posts = new ArrayList<>();

    static {
        Post post1 = new Post(1, "Это первый пост, который я здесь написал.");
        post1.addComment(new Comment("Пётр Первый", "Я успел откомментировать первым!"));
        posts.add(post1);

        Post post2 = new Post(22, "Это будет второй пост. Тоже короткий.");
        posts.add(post2);

        Post post3 = new Post(333, "Это пока последний пост.");
        posts.add(post3);
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        // тут конфигурирование и запуск сервера
        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/posts", new PostsHandler());
        httpServer.start();

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
         httpServer.stop(1);
    }

    static class PostsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            // ваш код
            String method = httpExchange.getRequestMethod();
            String response = null;

            String path = httpExchange.getRequestURI().getPath();
            String[] splited = path.split("/");

            InputStream inputStream = httpExchange.getRequestBody();
            String body = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);

            switch (method) {
                case "POST":
                    boolean isBreak = false;
                    for (Post post : posts) {
                        if (post.getId() == Integer.parseInt(splited[2])) {
                            isBreak = true;
                            post.addComment(gson.fromJson(body, Comment.class));
                            httpExchange.sendResponseHeaders(201, 0);
                            response = "";
                            break;
                        }
                    }
                    if(isBreak) {
                        break;
                    }
                    response = "";
                    httpExchange.sendResponseHeaders(404, 0);
                    break;
                case "GET":
                    if (path.equals("/posts")) {
                        response = gson.toJson(posts);
                        httpExchange.sendResponseHeaders(200, 0);
                    } else {
                        boolean wasFound = false;
                        for (Post post : posts) {
                            wasFound = true;
                            if (post.getId() == Integer.parseInt(splited[2])) {
                                response = gson.toJson(post);
                                httpExchange.sendResponseHeaders(200, 0);
                                break;
                            }
                        }
                        if(wasFound) {
                            break;
                        }
                        response = "";
                        httpExchange.sendResponseHeaders(404, 0);
                    }
                    break;
            }

            try (OutputStream os = httpExchange.getResponseBody()) {
                if(response != null) {
                    os.write(response.getBytes());
                }
            }
        }
    }
}
