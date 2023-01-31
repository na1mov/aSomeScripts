package EasyTasks.httpConnections;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

/*
откройте адрес http://localhost:8080/hello в браузере — это действие будет равноценно отправке запроса GET
http://localhost:8080/hello. После запуска сервер будет ожидать запросы и начнёт обрабатывать их, как только
они поступят. Когда это произойдёт, в консоли появится сообщение: Началась обработка /hello запроса от клиента.,
а в браузере вы увидите ответ от сервера: Привет! Рады видеть на нашем сервере..
*/

public class HttpServerFirst {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    // IOException могут сгенерировать методы create() и bind(...)
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/hello", new HelloHandler());
        httpServer.createContext("/day", new DayHandler());
        httpServer.start(); // запускаем сервер

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String method = httpExchange.getRequestMethod();
            String response;

            System.out.println("Началась обработка /hello запроса от клиента.");

            // С помощью этого метода можно добавить к ответу HTTP-заголовки.
            // Метод getResponseHeaders() возвращает объект класса Headers.
            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", "text/plain");
            headers.set("X-your-own-header", "any-information-you-want");
            headers.set("X-your-own-header-2", "any-information-you-want-2");

            // печатает полученный параметр из запроса вида http://localhost:8080/hello/{имя}
            String path = httpExchange.getRequestURI().getPath();
            String name = path.split("/")[2];
            System.out.println("Имя: " + name);

            // разный ответ в зависимости от вызванного метода
            switch(method) {
                case "POST":
                    response = "Вы использовали метод POST!";
                    break;
                case "GET":
                    response = "Вы использовали метод GET!";
                    break;
                default:
                    response = "Вы использовали какой-то другой метод!" +
                            "\nHey! Glad to see you on our server.";
            }

            // вывод списка заголовков
            Headers requestHeaders = httpExchange.getRequestHeaders();
            System.out.println("Заголовки запроса: " + requestHeaders.entrySet());
            // печать в зависимости от типа контента
            List<String> contentTypeValues = requestHeaders.get("Content-type");
            if ((contentTypeValues != null) && (contentTypeValues.contains("application/json"))) {
                System.out.println("Это JSON!");
            }

            // печать тела запроса
            InputStream inputStream = httpExchange.getRequestBody();
            String body = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
            System.out.println("Тело запроса:\n" + body);

            // Обратите внимание: метод sendResponseHeaders нужно вызывать до вызова getResponseBody()
            httpExchange.sendResponseHeaders(200, 0);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    static class DayHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("Началась обработка /day запроса от клиента.");

            String[] array = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
            Random random = new Random();
            int randomDay = random.nextInt(7);
            String response = array[randomDay];
            httpExchange.sendResponseHeaders(200, 0);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
