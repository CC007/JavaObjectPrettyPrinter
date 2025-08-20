package com.github.cc007.javaobjectprettyprinter;

import static com.github.cc007.javaobjectprettyprinter.GenericPrettyPrinter.prettyPrint;
import static java.lang.System.out; //NOSONAR

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;

class Example {

    public static void main(String[] args) {
        var personString = "Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]";
        out.println(prettyPrint(personString));
        out.println(prettyPrint(personString, 4));
        out.println(prettyPrint(personString, "·  "));

        out.println(prettyPrint("User(username=Bob, id=42, hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\")"));
        out.println(prettyPrint("User(person=Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65],\n\t\t hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\", id=43)"));
        out.println(prettyPrint("{\"name\":\"Alice\",\"age\":28,\"hobbies\":[\"reading\",\"cycling\",\"traveling\"]}"));

        var alice = new Person("Alice", 30, null);
        var bob = new Person("Bob", 34, alice);
        out.println(prettyPrint(bob));
        out.println(prettyPrint(bob, 4));
        out.println(prettyPrint(bob, "·  "));

        var user = new User(42, "aiw", bob);
        out.println(prettyPrint(user));
        out.println(prettyPrint(new DontDoThis(
                new String[]{alice.name(), bob.name()}, new int[]{42, 43, 44}, new Object[]{alice, user})));
        out.println(prettyPrint(new ThisIsFineThough(
                new String[]{alice.name(), bob.name()}, new int[]{42, 43, 44}, new Object[]{alice, user})));
        out.println(prettyPrint(new ThisIsFineToo(
                new String[]{alice.name(), bob.name()}, new int[]{42, 43, 44}, new Object[]{alice, user})));
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

record DontDoThis(String[] names, int[] foo, Object[] bar) { //NOSONAR

}

@Data
@AllArgsConstructor
class ThisIsFineThough {

    private String[] names;
    private int[] foo;
    private Object[] bar;
}


record ThisIsFineToo(String[] names, int[] foo, Object[] bar) { //NOSONAR
    @Override
    public String toString() {
        return "ThisIsFineToo {" +
               "names=" + Arrays.toString(names) +
               ", foo=" + Arrays.toString(foo) +
               ", bar=" + Arrays.toString(bar) +
               '}';
    }
}