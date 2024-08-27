package letsimproveoptionals;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main (String[] args){
        /*
         * Java 9 introduced Optional factory method improvements
         */
        factoryMethodImprovements();

        /*
         * Java 9 introduced Optional conditional logic with the new
         * ifPresentOrElse
         */
        ifpresentorelse();

        /*
         * Java 9 introduced Optional or() and stream() methods
         */
        orandstream();

        /*
         * You can use ifPresentOrElse() and or() to chain default Optional values
         */
        chainingexample();

        /*
         * Java 9 also introduced Optional Stream conversion
         */
        optionalstreamconversion();

    }

    /**
     * Optional.of must contain a non-null value or it will throw an NPE.
     *
     * Java 9 introduced Optional.ofNullable which can contain a null value
     * or a non-null value.
     *
     * If the value is null, it returns an empty Optional.  No need for
     * null checks prior to using the Optional.
     */
    public static void factoryMethodImprovements(){

        /**
         * In this case, the value can't be null or you'll get a NullPointerException
         */
        Optional<String> thisworks = Optional.of("I am a non-null value!");

        /**
         * This would throw an NPE if it wasn't commented out
         */
        //Optional<String> thisthrowsnpe = Optional.of(null);

        /**
         * Returns an Optional with the value if non-null
         * Returns an empty Optional if value is null
         */
        Optional<String> emptyOptional = Optional.ofNullable(null);

    }

    /**
     * Java 9 introduced ifPresentOrElse() to the Optional class.
     *
     * Lets us define the action to take if a value is present and the
     * action to take if the Optional is empty.
     *
     */
    public static void ifpresentorelse (){

        Optional<String> thisworks = Optional.of("I am a non-null value!");
        thisworks.ifPresentOrElse(
                value -> System.out.println("Value is present: " + value),
                () -> System.out.println("Value is absent")
        );

        /**
         * Prints out:  Value is present: I am a non-null value!
         */

        Optional<String> emptyOptional = Optional.ofNullable(null);
        emptyOptional.ifPresentOrElse(
                value -> System.out.println("Value is present: " + value),
                () -> System.out.println("Value is absent")
        );

        /**
         * Prints out:  Value is absent
         */

    }

    /*
     * Java 9 introduced Optional or() and stream() methods
     *
     * or() lets you specify a default Optional value if the current one is empty.
     *
     * stream() transforms an Optional containing a value into a Stream. From there you can use
     * all of the Stream API processing options available.
     */

    public static void orandstream(){
        /**
         * or() method example
         */
        Optional<String> emptyOptional = Optional.ofNullable(null);
        Optional<String> defaultOptional = emptyOptional.or(() -> Optional.of("Here's a default value for you"));
        System.out.println("Optional Default value: " + defaultOptional.get());

        /**
         *  stream() method example
         */
        Optional<String> thisworks = Optional.of("Hey, I've got a value!");
        Stream<String> streamFromOptional = thisworks.stream();
        streamFromOptional.forEach(value -> System.out.println("Stream Value: " + value));
    }

    /**
     * You can use ifPresentOrElse() and or() to chain default Optional values
     *
     * In the example below, we're saying if emptyOptional has a null value,
     * then default to emptyOptional2. If that has a null value, then default
     * to an Optional containing the value "Use This Default Value"
     */
    public static void chainingexample(){

        // ifPresentOrElse() and or() methods chaining
        Optional<String> emptyOptional = Optional.ofNullable(null);
        Optional<String> emptyOptional2 = Optional.ofNullable(null);
        Optional<String> chainedOptional = emptyOptional
                .or(() -> emptyOptional2)
                .or(() -> Optional.of("Use This Default Value"));
        System.out.println("Chained Optional value: " + chainedOptional.get());
    }

    /**
     * In this example, 3 Optional<String> values are converted to a Stream<Optional</String>>
     * and then flattened into a Stream<String>.
     */
    public static void optionalstreamconversion(){
        Optional<String> emptyOptional = Optional.ofNullable(null);
        Optional<String> thisworks = Optional.of("Hey, I've got a value!");
        Optional<String> defaultOptional = Optional.of("default value");

        Stream<Optional<String>> optionalStream = Stream.of(thisworks, emptyOptional, defaultOptional);
        Stream<String> flattenedStream = optionalStream.flatMap(Optional::stream);
        flattenedStream.forEach(value -> System.out.println("Flattened Stream Value: " + value));

        /**
         * Prints out:
         * Flattened Stream Value: Hey, I've got a value!
         * Flattened Stream Value: default value
         */
    }

}
