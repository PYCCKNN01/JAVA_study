public class ConcertTicket {

    //Константы тарифов
    private static final String TIER_DANCE_FLOOR = "DANCE_FLOOR";
    private static final String TIER_FAN_ZONE    = "FAN_ZONE";
    private static final String TIER_VIP_LOGE    = "VIP_LOGE";

    //Коэффициенты
    private static final double COEFF_DANCE_FLOOR = 1.0;
    private static final double COEFF_FAN_ZONE    = 1.6;
    private static final double COEFF_VIP_LOGE    = 3.5;

    //Надбавка за fast-track
    private static final double FAST_TRACK_SURCHARGE = 500.0;

    private final String eventName;
    private double basePrice;
    private String tier;
    private FanZonePass appliedOption = null;

    //Основной конструктор
    public ConcertTicket(String eventName, double basePrice, String tier) {
        this.eventName = eventName;
        setBasePrice(basePrice);
        setTier(tier);
    }

    //Дополнительный конструктор
    public ConcertTicket(String eventName, double basePrice) {
        this(eventName, basePrice, TIER_DANCE_FLOOR);
    }

    //Геттеры
    public String getEventName() {
        return eventName;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public String getTier() {
        return tier;
    }
    public FanZonePass getAppliedOption() {
        return appliedOption;
    }

    //Сеттеры
    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Введите корректную цену");
        }
        this.basePrice = basePrice;
    }

    public void setTier(String tier) {
        if (tier == null || tier.isBlank()) {
            throw new IllegalArgumentException("Введите корректный тариф");
        }
        this.tier = tier;
    }

    //Перегрузка 1 (только тариф)
    public double calculateFinalPrice() {
        double coefficient = switch (tier) {
            case TIER_DANCE_FLOOR -> COEFF_DANCE_FLOOR;
            case TIER_FAN_ZONE    -> COEFF_FAN_ZONE;
            case TIER_VIP_LOGE    -> COEFF_VIP_LOGE;
            default -> throw new IllegalArgumentException("Введите корректный тариф");
        };
        return basePrice * coefficient;
    }

    //Перегрузка 2 (опция)
    public double calculateFinalPrice(FanZonePass option) {
        this.appliedOption = option;
        return calculateFinalPrice() + option.getPrice();
    }

    //Перегрузка 3 (опция и фаст трек)
    public double calculateFinalPrice(FanZonePass option, boolean fastTrack) {
        double total = calculateFinalPrice(option);
        if (fastTrack) {
            total = total + FAST_TRACK_SURCHARGE;
        }
        return total;
    }
}