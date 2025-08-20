package com.github.cc007.javaobjectprettyprinter;

import static com.github.cc007.javaobjectprettyprinter.GenericPrettyPrinter.prettyPrint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

class Commandline {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        if (args.length == 1) {
            // Single argument: treat it as the message
            String message = args[0];
            System.out.println(prettyPrint(message));
            System.exit(0);
        } else if (args.length == 2 && "-f".equals(args[0])) {
            // Two arguments: -f <filename>
            String fileName = args[1];
            try {
                String content = Files.readString(Path.of(fileName), StandardCharsets.UTF_8);
                System.out.println(prettyPrint(content));
                System.exit(0);
            } catch (InvalidPathException e) {
                System.err.println("Error: invalid file path: " + fileName);
                System.exit(2);
            } catch (IOException e) {
                System.err.println("Error: could not read file '" + fileName + "': " + e.getMessage());
                System.exit(2);
            }
        } else {
            printUsageAndExit();
        }
    }

    @SuppressWarnings("java:S106")
    private static void printUsageAndExit() {
        System.err.println("Usage:");
        System.err.println("  java -jar jopp-1.0-SNAPSHOT.jar \"<your message>\"");
        System.err.println("  java -jar jopp-1.0-SNAPSHOT.jar -f <path/to/file>");
        System.err.println();
        System.err.println("Examples:");
        System.err.println("  java -jar jopp-1.0-SNAPSHOT.jar \"Hello, world!\"");
        System.err.println("  java -jar jopp-1.0-SNAPSHOT.jar -f notes.txt");
        System.exit(1);
    }

}
