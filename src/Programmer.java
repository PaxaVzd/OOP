/**
 * Клас, що реалізує професію програміста.
 */
public class Programmer implements Profession {

    /**
     * Метод, що виконує обов'язки програміста.
     * Виводить повідомлення про виконання роботи програміста.
     */
    @Override
    public void performDuties() {
        System.out.println("виконує роботу програміста");
    }
}
