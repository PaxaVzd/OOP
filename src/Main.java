import java.util.*;

/**
 * Основний клас програми для керування працівниками та їх професіями.
 */
public class Main {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        // Створення 5 працівників з початковою професією програміста
        for (int i = 1; i <= 5; i++) {
            Employee employee = new Employee("Працівник " + i);
            employee.addProfession(new Programmer());
            employees.add(employee);
        }

        // Меню для вибору професій
        Profession[] availableProfessions = {new Programmer(), new DatabaseAdministrator(), new Engineer()};

        int choice;
        do {
            System.out.println("\nМеню:");
            System.out.println("1. Призначити працівника на посаду");
            System.out.println("2. Додати професію працівнику");
            System.out.println("3. Видалити професію у працівника");
            System.out.println("4. Вивести інформацію про всіх працівників");
            System.out.println("5. Вийти з програми");
            System.out.print("Виберіть дію: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    assignEmployeeToPosition(scanner, employees);
                    break;
                case 2:
                    addProfessionToEmployee(scanner, employees, availableProfessions);
                    break;
                case 3:
                    removeProfessionFromEmployee(scanner, employees);
                    break;
                case 4:
                    displayAllEmployeesInfo(employees);
                    break;
                case 5:
                    System.out.println("Програма завершує роботу.");
                    break;
                default:
                    System.out.println("Неправильний вибір.");
                    break;
            }
        } while (choice != 5);
    }

    /**
     * Логіка для призначення працівника на певну посаду.
     *
     * @param scanner   об'єкт Scanner для отримання вводу від користувача
     * @param employees список працівників
     */
    private static void assignEmployeeToPosition(Scanner scanner, List<Employee> employees) {
        System.out.print("Введіть номер працівника (1-5): ");
        int employeeIndex;
        while (true) {
            if (scanner.hasNextInt()) {
                employeeIndex = scanner.nextInt() - 1;
                if (employeeIndex >= 0 && employeeIndex < employees.size()) {
                    break;
                } else {
                    System.out.println("Неправильний номер працівника. Спробуйте ще раз: ");
                }
            } else {
                System.out.println("Введено неправильний формат. Спробуйте ще раз: ");
                scanner.next(); // Потрібно видалити неправильний ввід з буфера
            }
        }
        System.out.print("Введіть посаду: ");
        scanner.nextLine();
        String position = scanner.nextLine();
        employees.get(employeeIndex).assignToPosition(position);
    }

    /**
     * Логіка для додавання професії працівнику.
     *
     * @param scanner             об'єкт Scanner для отримання вводу від користувача
     * @param employees           список працівників
     * @param availableProfessions список доступних професій
     */
    private static void addProfessionToEmployee(Scanner scanner, List<Employee> employees, Profession[] availableProfessions) {
        System.out.print("Введіть номер працівника (1-5): ");
        int addEmployeeIndex;
        while (true) {
            if (scanner.hasNextInt()) {
                addEmployeeIndex = scanner.nextInt() - 1;
                if (addEmployeeIndex >= 0 && addEmployeeIndex < employees.size()) {
                    break;
                } else {
                    System.out.println("Неправильний номер працівника. Спробуйте ще раз: ");
                }
            } else {
                System.out.println("Введено неправильний формат. Спробуйте ще раз: ");
                scanner.next(); // Потрібно видалити неправильний ввід з буфера
            }
        }

        System.out.println("Доступні професії:");
        for (int i = 0; i < availableProfessions.length; i++) {
            System.out.println((i + 1) + ". " + availableProfessions[i].getClass().getSimpleName());
        }

        System.out.print("Виберіть номер професії для додавання: ");
        int professionChoice;
        while (true) {
            if (scanner.hasNextInt()) {
                professionChoice = scanner.nextInt();
                if (professionChoice >= 1 && professionChoice <= availableProfessions.length) {
                    break;
                } else {
                    System.out.println("Неправильний вибір професії. Спробуйте ще раз: ");
                }
            } else {
                System.out.println("Введено неправильний формат. Спробуйте ще раз: ");
                scanner.next(); // Потрібно видалити неправильний ввід з буфера
            }
        }

        // Перевірка на однакові професії
        List<Profession> existingProfessions = employees.get(addEmployeeIndex).getProfessions();
        if (existingProfessions.contains(availableProfessions[professionChoice - 1])) {
            System.out.println("Цей працівник вже має таку професію.");
        } else {
            employees.get(addEmployeeIndex).addProfession(availableProfessions[professionChoice - 1]);
            System.out.println("Професія успішно додана.");
        }
    }

    /**
     * Логіка для видалення професії у працівника.
     *
     * @param scanner   об'єкт Scanner для отримання вводу від користувача
     * @param employees список працівників
     */
    private static void removeProfessionFromEmployee(Scanner scanner, List<Employee> employees) {
        System.out.print("Введіть номер працівника (1-5): ");
        int removeEmployeeIndex;
        while (true) {
            if (scanner.hasNextInt()) {
                removeEmployeeIndex = scanner.nextInt() - 1;
                if (removeEmployeeIndex >= 0 && removeEmployeeIndex < employees.size()) {
                    break;
                } else {
                    System.out.println("Неправильний номер працівника. Спробуйте ще раз: ");
                }
            } else {
                System.out.println("Введено неправильний формат. Спробуйте ще раз: ");
                scanner.next();
            }
        }

        List<Profession> professions = employees.get(removeEmployeeIndex).getProfessions();
        if (!professions.isEmpty()) {
            System.out.println("Поточні професії працівника:");
            for (int i = 0; i < professions.size(); i++) {
                System.out.println((i + 1) + ". " + professions.get(i).getClass().getSimpleName());
            }

            System.out.print("Виберіть номер професії для видалення: ");
            int removeProfessionIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    removeProfessionIndex = scanner.nextInt() - 1;
                    if (removeProfessionIndex >= 0 && removeProfessionIndex < professions.size()) {
                        break;
                    } else {
                        System.out.println("Неправильний номер професії. Спробуйте ще раз: ");
                    }
                } else {
                    System.out.println("Введено неправильний формат. Спробуйте ще раз: ");
                    scanner.next();
                }
            }

            employees.get(removeEmployeeIndex).removeProfession(professions.get(removeProfessionIndex));
            System.out.println("Професія успішно видалена.");
        } else {
            System.out.println("У цього працівника немає професій для видалення.");
        }
    }

    /**
     * Логіка для виведення інформації про всіх працівників.
     *
     * @param employees список працівників
     */
    private static void displayAllEmployeesInfo(List<Employee> employees) {
        System.out.println("\nІнформація про всіх працівників:");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + ":");
            List<Profession> empProfessions = employee.getProfessions();
            if (!empProfessions.isEmpty()) {
                for (Profession profession : empProfessions) {
                    System.out.println("- " + profession.getClass().getSimpleName());
                }
            } else {
                System.out.println("- Немає професій");
            }
        }
    }
}
