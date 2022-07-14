public class MonthsStatisticsRecord {
    String nameProfit;
    String nameExpense;
    int maxProfit;
    int maxExpense;

    public MonthsStatisticsRecord(String nameProfit, int maxProfit, String nameExpense, int maxExpense) {
        this.nameProfit = nameProfit;
        this.maxProfit = maxProfit;
        this.nameExpense = nameExpense;
        this.maxExpense = maxExpense;
    }
}
