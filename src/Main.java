import java.util.Scanner;

public class Main {
    // В программе использовал LinkedHashMap, чтобы потом иттерировать ключи в правильной последовательности.

    // Для вывода статистики по месячным отчётам создал класс MonthsStatisticsRecord, объекты которого положил
    // в LinkedHashMap statistics – из него я потом выводил значения.
    // Сначала я хотел сделать объекты на базе существующего класса MonthRecord, но не уверен, что так правильно,
    // так как в объектах появятся лишние переменные, к которым потом будет возможность обратиться.

    public static void main(String[] args) {
        System.out.println("Поползли :)"); // Поехали!

        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ReaderManager readerManager = new ReaderManager(); // Для считывания файлов сделал отдельныый класс, чтобы разграничить функционал.

        while (true) {
            printMenu();
            int menuNumber = checkForInt(scanner);

            if (menuNumber == 1) {
                readerManager.readReportsOfMonths(monthlyReport);
                monthlyReport.calculateMonthlyReport();
            } else if (menuNumber == 2) {
                readerManager.readReportsOfYear(yearlyReport);
                yearlyReport.calculateYearlyReport();
            } else if (menuNumber == 3) {
                monthlyReport.checkMonthsAndYearReports(yearlyReport);
            } else if (menuNumber == 4) {
                monthlyReport.getMonthlyReport();
            } else if (menuNumber == 5) {
                yearlyReport.getYearlyReport();
            } else if (menuNumber == 0) {
                return;
            } else {
                System.out.println("Такой команды нет. Попробуйте ещё раз.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }

    private static int checkForInt(Scanner scanner) {
        int menuNumber;

        while (!scanner.hasNextInt()) {
            System.out.println("Это не число. Введите число.");
            scanner.next();
        }
        menuNumber = scanner.nextInt();

        return menuNumber;
    }
}

