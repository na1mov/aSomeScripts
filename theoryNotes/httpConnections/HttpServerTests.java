package theoryNotes.httpConnections;

// test for http server

public class HttpServerTests {
//    HttpClient client = HttpClient.newHttpClient();
//    @Test
//    public void getUsers() throws IOException, InterruptedException {
//        HttpClient httpClient = HttpClient.newHttpClient();
//        URI uri = URI.create("http://localhost:8080/api/v1/users");
//        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(200, response.statusCode());
//
//        Type userType = new TypeToken<ArrayList<User>>() {}.getType();
//        List<User> actual = gson.fromJson(response.body(), userType);
//
//        assertNotNull(actual, "Пользователи не возвращаются.");
//        assertEquals(1, actual.size(), "Неверное кол-во пользователей");
//        assertEquals(user, actual.get(0), "Пользователи не совпадают");
//    }
}
