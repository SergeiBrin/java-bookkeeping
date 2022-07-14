import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearRecord> reportsByYear;
    int year;
    String[] monthsName;
    int[] profit;
    int[] incomeByMonths;
    int[] expenseByMonths;
    double averageIncome;
    double averageExpense;

    public YearlyReport() {
        reportsByYear = new ArrayList<>();
        year = 2021;
        monthsName = new String[] {
                "Январь",
                "Февраль",
                "Март",
                "Апрель",
                "Май",
                "Июнь",
                "Июль",
                "Август",
                "Сентябрь",
                "Октябрь",
                "Ноябрь",
                "Декабрь"
        };
        profit = new int[3];
        incomeByMonths = new int[3];
        expenseByMonths = new int[3];

    }

    // Метод, который проводит основные математические расчёты по годовому отчёту.
    void calculateYearlyReport() {
        int incomeJanuary = 0;
        int incomeFebruary = 0;
        int incomeMarch = 0;

        int expenseJanuary = 0;
        int expenseFebruary = 0;
        int expenseMarch = 0;


        for (YearRecord report : reportsByYear) {
            if (report.month == 1) {
                if (!report.isExpense) {
                    incomeJanuary = report.amount;
                } else {
                    expenseJanuary = report.amount;
                }
            } else if (report.month == 2) {
                if (!report.isExpense) {
                    incomeFebruary = report.amount;
                } else {
                    expenseFebruary = report.amount;
                }
            } else if (report.month == 3) {
                if (!report.isExpense) {
                    incomeMarch = report.amount;
                } else {
                    expenseMarch = report.amount;
                }
            }
        }

        // Не стал помещать в цикл, чтоб код был понятнее.
        expenseByMonths[0] = expenseJanuary;
        expenseByMonths[1] = expenseFebruary;
        expenseByMonths[2] = expenseMarch;

        incomeByMonths[0] = incomeJanuary;
        incomeByMonths[1] = incomeFebruary;
        incomeByMonths[2] = incomeMarch;

        profit[0] = incomeJanuary - expenseJanuary;
        profit[1] = incomeFebruary - expenseFebruary;
        profit[2] = incomeMarch - expenseMarch;

        averageExpense = (double) (expenseJanuary + expenseFebruary + expenseMarch) / 3;
        averageIncome = (double) (incomeJanuary + incomeFebruary + incomeMarch) / 3;
    }

    void getYearlyReport () {
        if (reportsByYear == null) {
            System.out.println("Ошибка! Вы не проинициализировали Collections. Проинициализируйте Collections, а потом считайте годовой отчёт.");
        } else if (reportsByYear.isEmpty()) {
            System.out.println("Ошибка! Вы ещё не считали годовой отчёт. Считайте годовой отчёт.");
        } else {
            System.out.println(year + " год.");
            System.out.print("Прибыль по каждому месяцу: ");
            for (int i = 0; i < profit.length; i++) {
                System.out.print(monthsName[i] + " – " + profit[i]);
                if (i + 1 < profit.length) {
                    System.out.print("; ");
                } else {
                    System.out.println(".");
                }
            }
            System.out.println("Средний расход за все месяцы: " + averageExpense);
            System.out.println("Средний доход за все месяцы: " + averageIncome);
        }
    }
}
