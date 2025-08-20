package com.github.cc007.javaobjectprettyprinter;

public class GenericPrettyPrinter {

    private GenericPrettyPrinter() {
    }


    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well <br> - Works with a mix of the above
     * <p>
     * Note: only tested for objects with a toString implementation that prints the contents of the object.
     * It might break for object references, like when they contain [ characters for array objects
     *
     * @param object the object to be pretty-printed
     * @return the pretty-printed string
     */
    public static String prettyPrint(Object object) {
        return prettyPrint(object.toString());
    }

    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well <br> - Works with a mix of the above
     * <p>
     * Note: only tested for objects with a toString implementation that prints the contents of the object.
     * It might break for object references, like when they contain [ characters for array objects
     *
     * @param object the object to be pretty-printed
     * @param spaces the number of spaces to use for indents
     * @return the pretty-printed string
     */
    public static String prettyPrint(Object object, int spaces) {
        return prettyPrint(object.toString(), spaces);
    }

    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well <br> - Works with a mix of the above
     * <p>
     * Note: only tested for objects with a toString implementation that prints the contents of the object.
     * It might break for object references, like when they contain [ characters for array objects
     *
     * @param object the object to be pretty-printed
     * @param indentationString exact indentation string to use
     * @return the pretty-printed string
     */
    public static String prettyPrint(Object object, String indentationString) {
        return prettyPrint(object.toString(), indentationString);
    }

    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well <br> - Works with a mix of the above
     *
     * @param input the input string that needs to be pretty-printed
     * @return the pretty-printed string
     */
    public static String prettyPrint(String input) {
        return prettyPrint(input, 2);
    }

    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well <br> - Works with a mix of the above
     *
     * @param input the input string that needs to be pretty-printed
     * @param spaces the number of spaces to use for indents
     * @return the pretty-printed string
     */
    public static String prettyPrint(String input, int spaces) {
        return prettyPrint(input, " ".repeat(spaces));
    }

    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well <br> - Works with a mix of the above
     *
     * @param input the input string that needs to be pretty-printed
     * @param indentationString exact indentation string to use
     * @return the pretty-printed string
     */
    public static String prettyPrint(String input, String indentationString) {
        StringBuilder result = new StringBuilder();
        int indent = 0;
        StringState stringState = new StringState(false, (char) 0);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if ((c == '"' || c == '\'') && (i == 0 || input.charAt(i - 1) != '\\')) { // Toggle string mode when hitting " or ' that's not escaped
                if (stringState.insideString && c == stringState.stringDelimiter) {
                    stringState = new StringState(false, (char) 0);
                } else if (!stringState.insideString) {
                    stringState = new StringState(true, c);
                }
                result.append(c);
            } else if (stringState.insideString) { // If inside a string, do not pretty-print anything
                result.append(c);
            } else {
                indent = handleChar(indentationString, c, result, indent);
            }
        }
        return result.toString();
    }

    private static int handleChar(String delimiter, char c, StringBuilder result, int indent) {
        // If it's a space after a comma, do not print at all
        if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
            return indent;
        }

        switch (c) {
            case '[', '(', '{' -> {
                result.append(c).append("\n");
                indent++;
                result.append(delimiter.repeat(indent));
            }
            case ']', ')', '}' -> {
                result.append("\n");
                indent--;
                result.append(delimiter.repeat(indent)).append(c);
            }
            case ',' -> {
                result.append(c);
                result.append("\n");
                result.append(delimiter.repeat(indent));
            }
            case '=' -> result.append(" = ");
            case ':' -> result.append(": ");

            default -> result.append(c);
        }
        return indent;
    }

    private record StringState(
            boolean insideString,
            char stringDelimiter // Can be ' or "
    ) { }
}