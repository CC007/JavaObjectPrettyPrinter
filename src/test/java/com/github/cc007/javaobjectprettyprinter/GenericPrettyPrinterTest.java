package com.github.cc007.javaobjectprettyprinter;

import static com.github.cc007.javaobjectprettyprinter.GenericPrettyPrinter.prettyPrint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GenericPrettyPrinterTest {

    private Person person = new Person("Bob", 34, new Person("Alice", 30, null));
    private User user = new User(42, "aiw", person);

    @Test
    void prettyPrintRecord() {
        Assertions.assertEquals(
                """
                Person[
                  name = Alice,
                  age = 30,
                  bloodtype = 'A"\\'',
                  weight = 65
                ]""",
                prettyPrint("Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]")
        );
    }

    @Test
    void prettyPrintRecordWith4Spaces() {
        Assertions.assertEquals(
                """
                Person[
                    name = Alice,
                    age = 30,
                    bloodtype = 'A"\\'',
                    weight = 65
                ]""",
                prettyPrint("Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]", 4)
        );
    }

    @Test
    void prettyPrintRecordWithIndentationString() {
        Assertions.assertEquals(
                """
                Person[
                ·  name = Alice,
                ·  age = 30,
                ·  bloodtype = 'A"\\'',
                ·  weight = 65
                ]""",
                prettyPrint("Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65]", "·  ")
        );
    }

    @Test
    void prettyPrintLombokDataClass() {
        Assertions.assertEquals(
                """
                User(
                  username = bob,
                  id = 42,
                  hash = "4c\\"rt8340(jt9 8y34m)jt'03,4"
                )""",
                prettyPrint("User(username=bob, id=42, hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\")")
        );
    }

    @Test
    void prettyPrintMixed() {
        Assertions.assertEquals(
                """
                User(
                  person = Person[
                    name = Alice,
                    age = 30,
                    bloodtype = 'A"\\'',
                    weight = 65
                  ],
                  hash = "4c\\"rt8340(jt9 8y34m)jt'03,4",
                  id = 43
                )""",
                prettyPrint("User(person=Person[name=Alice, age=30, bloodtype='A\"\\'', weight=65],\n\t\t hash=\"4c\\\"rt8340(jt9 8y34m)jt'03,4\", id=43)")
        );
    }

    @Test
    void prettyPrintJson() {
        Assertions.assertEquals(
                """
                {
                  "name": "Alice",
                  "age": 28,
                  "hobbies": [
                    "reading",
                    "cycling",
                    "traveling"
                  ]
                }""",
                prettyPrint("{\"name\":\"Alice\",\"age\":28,\"hobbies\":[\"reading\",\"cycling\",\"traveling\"]}")
        );
    }

    @Test
    void prettyPrintRecordObject() {
        Assertions.assertEquals(
                """
                Person[
                  name = Bob,
                  age = 34,
                  mother = Person[
                    name = Alice,
                    age = 30,
                    mother = null
                  ]
                ]""",
                prettyPrint(person)
        );
    }

    @Test
    void prettyPrintRecordObjectWith4Spaces() {
        Assertions.assertEquals(
                """
                Person[
                    name = Bob,
                    age = 34,
                    mother = Person[
                        name = Alice,
                        age = 30,
                        mother = null
                    ]
                ]""",
                prettyPrint(person, 4)
        );
    }

    @Test
    void prettyPrintRecordObjectWithIndentationString() {
        Assertions.assertEquals(
                """
                Person[
                ·  name = Bob,
                ·  age = 34,
                ·  mother = Person[
                ·  ·  name = Alice,
                ·  ·  age = 30,
                ·  ·  mother = null
                ·  ]
                ]""",
                prettyPrint(person, "·  ")
        );
    }

    @Test
    void prettyPrintLombokDataClassObject() {
        Assertions.assertEquals(
                """
                User(
                  id = 42,
                  username = aiw,
                  person = Person[
                    name = Bob,
                    age = 34,
                    mother = Person[
                      name = Alice,
                      age = 30,
                      mother = null
                    ]
                  ]
                )""",
                prettyPrint(user)
        );
    }


}