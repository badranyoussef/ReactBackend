package ThirdSemesterExercises.Backend.Week10Year2024.SchoolExercises.Day1;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class App {
    //private static Map<Integer, Person> personMap = new HashMap<>();

    public static void main(String[] args) {
        //Javalin app = Javalin.create().start(7007);
        //app.get("/", ctx -> ctx.result("Hello World"));

        // En anden måde at skrive det på uden lambda udtryk
        /*app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                //Skriv din kode her
            }
        });*/

        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7070)
                .setException500()
                .setException404()
                .setRoute(() -> {
                    path("/demo", () -> {
                        get("/test", ctx -> ctx.json("{\"msg\":\"Hello From Server\"}"));
                        get("/error", ctx -> {
                            throw new Exception("Dette er en test");
                        });
                    });
                });
    }
}
        /*
        List<Person> personList = Arrays.asList(
                new Person(1, "Ahmad", 25), new Person(2, "Youssef", 26),
                new Person(3, "Hanni", 27), new Person(4, "Lasse", 28));

        app.get("/persons", ctx -> ctx.json(personList));


        //Class exercise 1
        LocalDate localDate = LocalDate.now();
        Test test = new Test();
        test.setCurrentDate("" + localDate);

        app.get("currenDate", ctx -> ctx.json(test));

        //Class exercise 2
        String s = getResponseBody("https://icanhazdadjoke.com/");
        Joke joke = parseResponseToJson(s);
        app.get("/joke", ctx -> ctx.json(joke));

        //Printing out the response
        app.get("/response", ctx -> ctx.result(s));

        //Gode begreber
        //  Path parameter (person/name/jane) , Query parameter(fx setOrder = desc)

        // As a Map
        personMap.put(1, new Person(1, "Ahmad", 26));
        personMap.put(2, new Person(2, "Youssef", 26));
        personMap.put(3, new Person(3, "Hanni", 27));
        personMap.put(4, new Person(4, "Lasse", 28));

        //app.get("/person", ctx -> ctx.json(personList));
        //app.get("/person/byid/{id}", Person.getPersonByIdHandler());
        //app.get("/person/byname/{name}", Person.getPersonByNameHandler());
        //app.get("/person/byage/{age}", Person.getPersonByAgeHandler());


        app.routes(() -> {
            path("person", () -> {
                get("/", ctx -> ctx.json(personMap));
                get("/byid/{id}", Person.getPersonByIdHandler());
                get("/byname/{name}", Person.getPersonByNameHandler());
                get("/byage/{age}", Person.getPersonByAgeHandler());
                post("/", ctx -> {
                    Person incoming = ctx.bodyAsClass(Person.class);
                    ctx.json(incoming);
                    personMap.put(incoming.id, incoming);
                });
            });
        });
    }


    //Apache HttpClient
    private static String getResponseBody(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Joke parseResponseToJson(String url) {
        // Use Gson library to parse JSON into Joke object
        Gson gson = new Gson();
        return gson.fromJson(url, Joke.class);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    private static class Person {
        int id;
        String name;
        int age;


        //Skal laves til en i PersonController
        public static Handler getPersonByIdHandler() {
            return ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Person person = personMap.get(id);
                if (person != null) {
                    ctx.json(person);
                } else {
                    ctx.status(404).result("Person not found");
                }
            };
        }

        public static Handler getPersonByNameHandler() {
            return ctx -> {
                String name = ctx.pathParam("name");
                Person person = null;
                for (Person p : personMap.values()) {
                    if (p.getName().equals(name)) {
                        person = p;
                        break;
                    }
                }
                if (person != null) {
                    ctx.json(person);
                } else {
                    ctx.status(404).result("Person not found");
                }
            };
        }

        public static Handler getPersonByAgeHandler() {
            return ctx -> {
                int age = Integer.parseInt(ctx.pathParam("age"));
                Person person = null;
                for (Person p : personMap.values()) {
                    if (p.getAge() == age) {
                        person = p;
                        break;
                    }
                }
                if (person != null) {
                    ctx.json(person);
                } else {
                    ctx.status(404).result("Person not found");
                }
            };
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    private static class Test {
        String currentDate;
    }
}*/