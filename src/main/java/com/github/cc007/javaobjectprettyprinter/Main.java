package com.github.cc007.javaobjectprettyprinter;

import static com.github.cc007.javaobjectprettyprinter.GenericPrettyPrinter.prettyPrint;

public class Main {

    public static void main(String[] args) {
        System.out.println(prettyPrint( "Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]"));
        System.out.println(prettyPrint( "User(username=bob, id=42, hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\")"));
        System.out.println(prettyPrint( "User(person=Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65],\n\t\t hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\", id=43)"));
        System.out.println(prettyPrint("{\"name\":\"Alice\",\"age\":28,\"hobbies\":[\"reading\",\"cycling\",\"traveling\"]}"));
    }
}
