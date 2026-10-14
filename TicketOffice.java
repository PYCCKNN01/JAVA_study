public class TicketOffice {

    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    public static int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    //Фиксация оплаты
    public static void registerPayment(double amount) {
        totalOrdersCount++;
        totalRevenue = totalRevenue + amount;
    }

    public static void printGlobalStats() {
        System.out.println("        Отчёт доходности:");
        System.out.println("Заказов: " + totalOrdersCount);
        System.out.println("Выручка: " + totalRevenue + " руб.");

    }
}

public static void main(String[] args) {
    //Новая опиця
    FanZonePass photo = new FanZonePass("c получением фотографии", 1200);

    System.out.println("");

    //3 билета
    ConcertTicket ticket1 = new ConcertTicket("Группа 'Кино'", 5000);
    ConcertTicket ticket2 = new ConcertTicket("Романсы в исполнении Александра Малинина", 2500, "FAN_ZONE");
    ConcertTicket ticket3 = new ConcertTicket("Филипп Киркоров: лучшее", 10000, "VIP_LOGE");

    //Перегрузки
    double price1 = ticket1.calculateFinalPrice();                       //1
    System.out.println(ticket1.getEventName() + " - " + price1 + " руб.");
    TicketOffice.registerPayment(price1);

    double price2 = ticket2.calculateFinalPrice(photo);                   //2
    System.out.println(ticket2.getEventName() + " - Фан-зона " + photo.getName() + " - " + price2 + " руб.");
    TicketOffice.registerPayment(price2);

    double price3 = ticket3.calculateFinalPrice(
            new FanZonePass("Личная встреча", 200000), true);               //3
    System.out.println(ticket3.getEventName() + " - Вип ложа, проход без очереди и личная встреча " + "- " + price3 + " руб.");
    TicketOffice.registerPayment(price3);

    //Итоговая статистика
    System.out.println();
    TicketOffice.printGlobalStats();
}