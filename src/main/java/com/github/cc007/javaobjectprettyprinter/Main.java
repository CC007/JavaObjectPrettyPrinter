package com.github.cc007.javaobjectprettyprinter;

import static com.github.cc007.javaobjectprettyprinter.GenericPrettyPrinter.prettyPrint;

import lombok.AllArgsConstructor;
import lombok.Data;

class Main {

    public static void main(String[] args) {
        System.out.println(prettyPrint("Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]"));
        System.out.println(prettyPrint("Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]", 4));
        System.out.println(prettyPrint("Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]", "·  "));

        System.out.println(prettyPrint("User(username=Bob, id=42, hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\")"));
        System.out.println(prettyPrint("User(person=Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65],\n\t\t hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\", id=43)"));
        System.out.println(prettyPrint("{\"name\":\"Alice\",\"age\":28,\"hobbies\":[\"reading\",\"cycling\",\"traveling\"]}"));

        var person = new Person("Bob", 34, new Person("Alice", 30, null));
        System.out.println(prettyPrint(person));
        System.out.println(prettyPrint(person, 4));
        System.out.println(prettyPrint(person, "·  "));

        var user = new User(42, "aiw", person);
        System.out.println(prettyPrint(user));
    }

}

record Person(String name, int age, Person mother) {

}

@Data
@AllArgsConstructor
class User {
    private int id;
    private String username;
    private Person person;
}