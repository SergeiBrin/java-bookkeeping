import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MonthlyReport {
    LinkedHashMap<Integer, List<MonthRecord>> reportsByMonths = new LinkedHashMap<>();
    String[] monthsName;
    int[] incomeByMonths;
    int[] expenseByMonths;

    MonthlyReport() {
        monthsName = new String[] {
                "Январь",
                "Февраль",
                "Март",
        };
        expenseByMonths = new int[3];
        incomeByMonths = new int[3];
    }

    // Метод, который проводит основные математические расчёты по месячным отчётам.
    void calculateMonthlyReport() {
        int i = 0;

        for (Integer key : reportsByMonths.keySet()) {
            int incomesByMonth = 0;
            int expenseByMonth = 0;
            for (MonthRecord report : reportsByMonths.get(key)) {
                if (!report.isExpense) {
                    int income = report.quantity * report.sumOfOne;
                    incomesByMonth += income;
                } else {
                    int expense = report.quantity * report.sumOfOne;
                    expenseByMonth += expense;
                }
            }
            expenseByMonths[i] = expenseByMonth;
            incomeByMonths[i] = incomesByMonth;
            i++;
        }
    }

    void checkMonthsAndYearReports(YearlyReport report) {
        int check = 0;
        boolean isNotNullReports = (reportsByMonths == null) || (report.reportsByYear == null);
        boolean isNotEmpty = (reportsByMonths.isEmpty()) || (report.reportsByYear.isEmpty());

        if (isNotNullReports) {
            System.out.println("Ошибка! Вы не проинициализировали Collections. Проинициализируйте Collections, а потом считайте месячные и годовой отчёты.");
        } else if (isNotEmpty) {
            System.out.println("Ошибка! Вы ещё не считали месячные и/или годовой отчёты. Чтобы сверить отчёты – сперва считайте их.");
        } else {
            for (int i = 0; i < 3; i++) {
                if (report.expenseByMonths[i] == expenseByMonths[i]) {
                    if (report.incomeByMonths[i] == incomeByMonths[i]) {
                        check++;
                    } else {
                        System.out.println(monthsName[i] + " – обнаружены несоответствия в доходах!");
                    }
                } else {
                    System.out.println(monthsName[i] + " – обнаружены несоответствия в расходах!");
                }
            }
            if (check == 3) {
                System.out.println("С отчётами всё в порядке! Выдайте премию тому, кто это сделал :)");
            } else {
                System.out.println("К сожалению, это залёт :P");
            }
        }
    }

    void getMonthlyReport() {
        HashMap<String, ArrayList<MonthsStatisticsRecord>> statistics = new HashMap<>();
        int i = 0;

        if (reportsByMonths == null) {
            System.out.println("Ошибка! Вы не проинициализировали Collections. Проинициализируйте Collections, а потом считайте месячные отчёты.");
        } else if (reportsByMonths.isEmpty()) {
            System.out.println("Ошибка! Вы ещё не считали месячные отчёты. Считайте их.");
        } else {
            for (Integer key : reportsByMonths.keySet()) {
                String nameProfit = "";
                String nameExpense = "";
                int maxProfit = 0;
                int maxExpense = 0;

                for (MonthRecord report : reportsByMonths.get(key)) {
                    if (!report.isExpense) {
                        int checkProfit = report.quantity * report.sumOfOne;
                        if (maxProfit < checkProfit) {
                            maxProfit = checkProfit;
                            nameProfit = report.itemName;
                        }
                    } else {
                        int checkExpense = report.quantity * report.sumOfOne;
                        if (maxExpense < checkExpense) {
                            maxExpense = checkExpense;
                            nameExpense = report.itemName;
                        }
                    }
                }

                MonthsStatisticsRecord record = new MonthsStatisticsRecord(nameProfit, maxProfit, nameExpense, maxExpense);
                ArrayList<MonthsStatisticsRecord> list = new ArrayList<>();
                list.add(record);
                statistics.put(monthsName[i], list);
                i++;
            }

            for (String key : statistics.keySet()) {
                System.out.println(key + ":");
                for (MonthsStatisticsRecord report : statistics.get(key)) {
                    System.out.println("Самый прибыльный товар – «" + report.nameProfit + "». Сумма – " + report.maxProfit + ",");
                    System.out.println("Самый большая трата – «" + report.nameExpense + "». Сумма – " + report.maxExpense + ".");
                    System.out.println();
                }
            }
        }
    }
}
