import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReaderManager {
    ArrayList<MonthRecord> records;
    void readReportsOfMonths(MonthlyReport report) {
        for (int i = 1; i < 4; i++) {
            records = new ArrayList<>();
            String file = readFileContentsOrNull("resources\\m.20210" + i + ".csv");
            if (file == null) {
                System.out.println("Ошибка! Проверьте, путь – к файлу с отчётом.");
            } else {
                String[] lines = file.split("\n");
                for (int j = 1; j < lines.length; j++) {
                    String line = lines[j];
                    String[] value = line.split(",");
                    String itemName = value[0];
                    boolean isExpense = Boolean.parseBoolean(value[1]);
                    int quantity = Integer.parseInt(value[2]);
                    int sumOfOne = Integer.parseInt(value[3]);
                    MonthRecord monthRecord = new MonthRecord(itemName, isExpense, quantity, sumOfOne);
                    records.add(monthRecord);
                }
                report.reportsByMonths.put(i, records);
            }
        }
        if (!report.reportsByMonths.isEmpty()) {
            System.out.println("Месячные отчёты добавлены.");
        }
    }
    void readReportsOfYear(YearlyReport report) {
        String file = readFileContentsOrNull("resources\\y.2021.csv");
        if (file == null) {
            System.out.println("Ошибка! Проверьте, путь – к файлу с отчётом.");
        } else {
            String[] lines = file.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] value = line.split(",");
                int month = Integer.parseInt(value[0]);
                int amount = Integer.parseInt(value[1]);
                boolean isExpense = Boolean.parseBoolean(value[2]);
                YearRecord yearRecord = new YearRecord(month, amount, isExpense);
                report.reportsByYear.add(yearRecord);
            }
        }
        if (!report.reportsByYear.isEmpty()) {
            System.out.println("Годовой отчёт добавлен.");
        }
    }

    String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файлы с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
