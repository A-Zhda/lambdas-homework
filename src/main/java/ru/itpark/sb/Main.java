package ru.itpark.sb;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        //1
        List<Integer> numbers = List.of(1,2,3,4,5,6,7);
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n*n)
                .filter(n -> n>10)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //2
        List<String> words = List.of("stream","map","nap","distinct","stream");
        words.stream().map(word -> word.toLowerCase())
                .distinct()
                .sorted((s1,s2) ->
                {if (s1.length() == s2.length() ){
                        return s1.compareTo(s2);
                    }else return Integer.compare(s1.length(),s2.length());
                })
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //3
        record Person(String name, Integer age){};

        List<Person> persons = List.of(
                new Person("Pppp", 12),
                new Person("Dddd", 25),
                new Person("Ffff", 21),
                new Person("Kkkk", 20));

        persons.stream().filter(p -> p.age >= 18)
                .map(p -> p.name())
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //4
        List<Person> persons2 = List.of(
                new Person("Pppp", 12),
                new Person("Dddd", 25),
                new Person("Ffff", 21),
                new Person("Kkkk", 20));
        double average = persons2.stream()
                .mapToInt(p -> p.age())
                .average()
                .orElseThrow();

        System.out.println(average);

        //5
        record Product(
            String name,
            String category,
            Double price
        ){};

        List<Product> products = List.of(
                new Product("milk","dairy products", 150.0),
                new Product("cheese","dairy products", 114.0),
                new Product("bread","bakery", 113.56),
                new Product("cake","bakery",0.0)
        );

        products.stream().collect(Collectors.groupingBy(p -> p.category()))
                .forEach((category,p) -> {
                    System.out.println(category + ":");
                    p.forEach(p1 -> System.out.println(p1.name()));
                });

        //6
        System.out.println(products.stream().allMatch(p -> p.price() >0));
        System.out.println(products.stream().anyMatch(p -> p.price() <100));
        System.out.println(products.stream().noneMatch(p -> p.price() == 0));

        //7
        List<String> sentences = List.of(
                "Hello! How are you",
                "Hi!, I'm fine",
                "My name is Denis",
                "I'm forty years old"
        );

        sentences.stream().map(s -> s.toLowerCase().replaceAll("[!,']","").trim())
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(word -> !word.isEmpty())
                .distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);

        //8
        List<Person> people = List.of(
                new Person("Lilo",11),
                new Person("Lina",22),
                new Person("Polly",25),
                new Person("Oleg",88),
                new Person("Kolya",15)
        );

        Map<Boolean, List<Person>> groupOfPeople = people.stream()
                .collect(Collectors.groupingBy(p -> p.age() >= 18));

        groupOfPeople.forEach((isAdult, p) ->
        {
            String group = "Adults";
            if (!isAdult) {
                group = "kids";
            }
            System.out.println(group + ": ");
            p.forEach(person -> System.out.print(person.name() + ", "));
            System.out.println("\n");
        });

        //9
        List<Product> productsList = List.of(
                new Product("milk","dairy products", 150.0),
                new Product("cheese","dairy products", 114.0),
                new Product("bread","bakery", 113.56),
                new Product("cake","bakery",1000.0)
        );

        Map<String, Double> productPrice = productsList.stream()
                .collect(Collectors.groupingBy(
                        c -> c.category(),
                        Collectors.summingDouble(p -> p.price())));

        productPrice.forEach((category,price) ->
                System.out.println(category + ": " + price));

        //10
        List<Product> productsWithLowPrice = productsList.stream()
                .sorted(Comparator.comparingDouble(p -> p.price())).limit(3)
                .collect(Collectors.toList());

        String cheapProducts = productsWithLowPrice.stream()
                .map(p -> p.name())
                .collect(Collectors.joining(", "));

        System.out.println(cheapProducts);



    }
}