import java.util.*;

/**
 * Клас, що представляє працівника з декількома професіями.
 */
public class Employee {
    private String name;
    private List<Profession> professions;

    /**
     * Конструктор для створення працівника з заданим ім'ям.
     *
     * @param name ім'я працівника
     */
    public Employee(String name) {
        this.name = name;
        this.professions = new ArrayList<>();
    }

    /**
     * Метод для додавання нової професії працівникові.
     *
     * @param profession нова професія для додавання
     */
    public void addProfession(Profession profession) {
        professions.add(profession);
    }

    /**
     * Метод для видалення професії у працівника.
     *
     * @param profession професія для видалення
     */
    public void removeProfession(Profession profession) {
        professions.remove(profession);
    }

    /**
     * Метод для призначення працівника на певну посаду та виконання обов'язків
     * з усіх його професій.
     *
     * @param position посада, на яку призначається працівник
     */
    public void assignToPosition(String position) {
        System.out.println(name + " назначений на посаду " + position);
        for (Profession profession : professions) {
            System.out.print(name + " ");
            profession.performDuties();
        }
    }

    /**
     * Метод для отримання списку професій працівника.
     *
     * @return список професій працівника
     */
    public List<Profession> getProfessions() {
        return professions;
    }

    /**
     * Метод для отримання ім'я працівника.
     *
     * @return ім'я працівника
     */
    public String getName() {
        return name;
    }
}
