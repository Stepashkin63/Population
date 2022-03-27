package ru.stepashkin.hw1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        long teenage = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетнего населения: " + teenage);

        List<String> recruitList = persons.stream()
                .filter(val -> val.getSex() == Sex.MAN && val.getAge() >= 18 && val.getAge() < 27)
                .map(Person::getSurname)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Пофамильный список призывников: " + recruitList);

        List<String> workingPopulation = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(val -> (val.getSex() == Sex.WOMAN && val.getAge() >= 18 && val.getAge() < 60) ||
                        (val.getSex() == Sex.MAN && val.getAge() >= 18 && val.getAge() < 65))
                .map(Person::getSurname)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Пофамильный список работоспособного населения: " + workingPopulation);
    }
}
