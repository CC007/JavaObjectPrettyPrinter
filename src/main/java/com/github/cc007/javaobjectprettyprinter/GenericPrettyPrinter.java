package com.github.cc007.javaobjectprettyprinter;

public class GenericPrettyPrinter {

    private GenericPrettyPrinter() {
    }

    public static String prettyPrint(String input) {
        return prettyPrint(input, 2);
    }

    public static String prettyPrint(String input, int spaces) {
        return prettyPrint(input, " ".repeat(spaces));
    }

    /**
     * Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
     * <p>
     * Handles: <br> - Nested brackets <br> - Skips formatting inside string literals <br> - Works with Lombok's default
     * format: User(username=bob, id=42) <br> - Works with JSON format as well - Works with a mix of the above
     */
    public static String prettyPrint(String input, String delimiter) {
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
                indent = handleChar(delimiter, c, result, indent);
            }
        }
        return result.toString();
    }

    private record StringState(
            boolean insideString,
            char stringDelimiter // Can be ' or "
    ) { }

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
}