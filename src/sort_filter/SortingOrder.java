package sort_filter;

public enum SortingOrder {
    ASCENDING(1),
    DESCENDING(-1);

    private final int value;

    SortingOrder(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SortingOrder fromValue(int value) {
        for (SortingOrder sortingOrder : SortingOrder.values()) {
            if (sortingOrder.getValue() == value) {
                return sortingOrder;
            }
        }
        return null; // Повернення null, якщо значення не знайдено
    }
}