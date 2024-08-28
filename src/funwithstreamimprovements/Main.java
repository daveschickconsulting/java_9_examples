package funwithstreamimprovements;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        /*
         * Java 9 Stream takeWhile
         */
        takeWhileExample();

        /*
         * Java 9 Stream dropWhile
         */
        dropWhileExample();

    }

    /**
     * takeWhile is similar to filter. It applies a predicate to the elements in a
     * Stream and returns a new Stream with only the elements that match that predicate.
     * However in an ordered stream, takeWhile only takes elements from the input
     * Stream as long as the predicate is true.  As soon as the predicate match is false,
     * it throws away the remainder of the Stream.
     */
    public static void takeWhileExample() {

        /**
         * Ordered Stream Example
         */
        List<String> names = List.of("Dave", "Deanna", "Douglas", "Ruby", "Violet");

        names.stream()
                .takeWhile(name -> name.charAt(0) == 'D')
                .forEach(System.out::println);

        /**
         * This will print:  Dave
         *                   Deanna
         *                   Douglas
         */


        /**
         * UnOrdered Stream Example
         */
        Set<String> names1 = Set.of("Dave", "Deanna", "Douglas", "Ruby", "Violet");

        names1.stream()
                .takeWhile(name -> name.charAt(0) == 'D')
                .forEach(System.out::println);

        /**
         * This will print a different result every time.
         * Sometimes it will return an empty stream. Sometimes just "Dave"
         * Sometimes just "Dave" and "Deanna", etc.
         */

        /**
         * UnOrdered Stream Example - all elements match the predicate
         */
        Set<String> names2 = Set.of("Dave", "Deanna", "Douglas", "Dansby", "Deborah");

        names2.stream()
                .takeWhile(name -> name.charAt(0) == 'D')
                .forEach(System.out::println);

        /**
         * This will print:
         *                 Dave
         *                 Deanna
         *                 Douglas
         *                 Dansby
         *                 Deborah
         */

    }


    /**
     * The dropWhile operation is basically the opposite of takeWhile.  It will remove
     * elements while the given  predicate for an element returns true and stop
     * removing elements the first time the predicate returns false.
     */
    public static void dropWhileExample() {
        /**
         * Ordered Stream Example
         */
        List<String> names = List.of("Dave", "Deanna", "Douglas", "Ruby", "Violet");

        names.stream()
                .dropWhile(name -> name.charAt(0) == 'D')
                .forEach(System.out::println);

        /**
         * This will print:  Ruby
         *                   Violet
         */


        /**
         * UnOrdered Stream Example
         */
        Set<String> names1 = Set.of("Dave", "Deanna", "Douglas", "Ruby", "Violet");

        names1.stream()
                .dropWhile(name -> name.charAt(0) == 'D')
                .forEach(System.out::println);

        /**
         * This will print a different result every time.
         * Sometimes it will return an empty stream. Sometimes just "Dave"
         * Sometimes just "Dave" and "Deanna", etc.
         */

        /**
         * UnOrdered Stream Example - all elements match the predicate
         */
        Set<String> names2 = Set.of("Dave", "Deanna", "Douglas", "Dansby", "Deborah");

        names2.stream()
                .dropWhile(name -> name.charAt(0) == 'D')
                .forEach(System.out::println);

        /**
         * This will return an empty stream
         */

    }

}
