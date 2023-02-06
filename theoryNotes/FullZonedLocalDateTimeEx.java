package theoryNotes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FullZonedLocalDateTimeEx {
    // Задайте форматирование для времени и даты в формате часы:минуты день.месяц.год
    // Пример - 12:15 02.11.21
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm dd.MM.yy");
    // Задайте форматирование для времени в формате часы:минуты
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        System.out.println("Тест №1:");
        printFlightInformation(
                "12:15 02.11.21",
                "VKO",
                "LED",
                30,
                1,
                55
        );

        System.out.println("\nТест №2:");
        printFlightInformation(
                "14:00 03.10.21",
                "SVX",
                "VVO",
                0,
                9,
                5
        );

        System.out.println("\nТест №3:");
        printFlightInformation(
                "06:00 12.12.21",
                "DME",
                "VVO",
                0,
                12,
                0
        );

        System.out.println("\nТест №4:");
        printFlightInformation(
                "23:00 29.03.22",
                "LED",
                "SVX",
                0,
                2,
                55
        );


    }

    private static void printFlightInformation(
            String formattedDepartureTime,
            String departureAirportCode,
            String arrivalAirportCode,
            int delay,
            int flightDurationHours,
            int flightDurationMinutes
    ) {
        Airport departureAirport;
        Airport arrivalAirport;
        // С помощью класса AirportDatabase получите данные об аэропортах вылета и посадки.
        // При получении исключения выведите сообщение исключения.
        try {
            departureAirport = AirportDatabase.getAirportByCode(departureAirportCode);
            arrivalAirport = AirportDatabase.getAirportByCode(arrivalAirportCode);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Создайте экземпляр ZonedDateTime с помощью formattedDepartureTime и зоны аэропорта вылета.
        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.parse(formattedDepartureTime,
                DATE_TIME_FORMATTER), ZoneId.of(departureAirport.getZone()));

        // Выведите информацию о том, между какими городами будет перелёт.
        System.out.println("Ваш билет на рейс " + departureAirport.getCity() + " - "
                + arrivalAirport.getCity() + ": ");

        // Найдите продолжительность полёта.
        Duration flightDuration = Duration.ofMinutes((flightDurationHours*60)+flightDurationMinutes);
        // Найдите время прибытия с учётом зоны прилёта.
        ZonedDateTime arrival = departure.withZoneSameInstant(ZoneId.of(arrivalAirport.getZone()))
                .plus(flightDuration);

        // Заполните данные для передачи в метод печати билета.
        // Город вылета
        String departureCity = departureAirport.getCity();
        // Город прилёта
        String arrivalCity = arrivalAirport.getCity();
        // Отформатированное время прилёта
        String formattedArrivalTime = arrival.toLocalTime().format(TIME_FORMATTER);
        // Только время вылета
        String departureTimeOnly = departure.toLocalTime().format(TIME_FORMATTER);

        printTicket(
                formattedDepartureTime,
                departureAirportCode,
                arrivalAirportCode,
                departureCity,
                arrivalCity,
                formattedArrivalTime,
                departureTimeOnly
        );

        // Добавьте проверку на случай задержки.
        if (delay > 0) {
            // Определите продолжительность задержки.
            Duration delayDuration = Duration.ofMinutes(delay);
            // Вычислите время вылета с учётом задержки.
            ZonedDateTime departureWithDelay = departure.plus(delayDuration);
            // Вычислите время прилёта с учётом задержки.
            ZonedDateTime arrivalWithDelay = arrival.plus(delayDuration);

            System.out.println("Ваш вылет задерживается.");
            // Выведите продолжительность задержки в формате часы:минуты
            System.out.println("Задержка: " + delayDuration.toHours() + ":" + delayDuration.toMinutesPart());
            // Выведите отформатированное время вылета с учётом задержки.
            System.out.println("Обновлённое время вылета: " + departure.toLocalTime().format(TIME_FORMATTER));
            // Выведите отформатированное время прилёта с учётом задержки.
            System.out.println("Обновлённое время прилёта: " + arrival.toLocalTime().format(TIME_FORMATTER));
        } else {
            System.out.println("Удачного полёта!");
        }
    }

    private static void printTicket(
            String departureTime,
            String departureAirportCode,
            String arrivalAirportCode,
            String departureCity,
            String arrivalCity,
            String arrivalTime,
            String departureTimeOnly
    ) {
        System.out.println(
                " _______________________________________________________\n" +
                        "|                                            |          |\n" +
                        "|  " + departureCity + "|" + departureAirportCode + "      "
                        + departureTime + "  |   " + departureAirportCode + "    |\n" +
                        "|  " + arrivalCity + "|" + arrivalAirportCode + "      "
                        + arrivalTime + "  |   " + arrivalAirportCode + "    |\n" +
                        "|                                            |          |\n" +
                        "|  BOARDING TIME   --:--      SEAT  1A       |   " + departureTimeOnly + "  |\n" +
                        "|  GATE  23                                  |   1A     |\n" +
                        "|____________________________________________|__________|");
    }
}

class Airport {
    private final String name;
    private final String city;
    private final String cityForTicket;
    private final String zoneName;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCityForTicket() {
        return cityForTicket;
    }

    public String getZone() {
        return zoneName;
    }

    public Airport(String name, String city, String cityForTicket, String zoneName) {
        this.name = name;
        this.city = city;
        this.cityForTicket = cityForTicket;
        this.zoneName = zoneName;
    }
}

class AirportDatabase {
    private static Airport vnukovo = new Airport(
            "Внуково",
            "Москва",
            "MOSCOW          ",
            "Europe/Moscow"
    );

    private static Airport pulkovo = new Airport(
            "Пулково",
            "Санкт-Петербург",
            "SAINT-PETERSBURG",
            "Europe/Moscow"
    );

    private static Airport vladivostok = new Airport(
            "Владивосток",
            "Владивосток",
            "VLADIVOSTOK     ",
            "Asia/Vladivostok"
    );

    private static Airport koltsovo = new Airport(
            "Кольцово",
            "Екатеринбург",
            "YEKATERINBURG   ",
            "Asia/Yekaterinburg"
    );

    public static Airport getAirportByCode(String airportCode) {
        /* С помощью оператора switch case верните правильный аэропорт по его коду:
         * VKO - vnukovo
         * LED - pulkovo
         * SVX - koltsovo
         * VVO - vladivostok
         * Для неверного кода пробросьте исключение.
         */
        switch(airportCode) {
            case "VKO":
                return vnukovo;
            case "LED":
                return pulkovo;
            case "SVX":
                return koltsovo;
            case "VVO":
                return vladivostok;
            default:
                throw new IllegalStateException("Неизвестный код аэропорта: ["+airportCode+"]");
        }
    }
}
