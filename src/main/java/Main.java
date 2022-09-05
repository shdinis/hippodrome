import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        List<Horse> horses = List.of(
                new Horse("Áóöåôàë", 2.4),
                new Horse("Òóç Ïèê", 2.5),
                new Horse("Çåôèð", 2.6),
                new Horse("Ïîæàð", 2.7),
                new Horse("Ëîáñòåð", 2.8),
                new Horse("Ïåãàñ", 2.9),
                new Horse("Âèøíÿ", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        logger.info("Íà÷àëî ñêà÷åê. Êîëëè÷åñòâî ó÷àñòíèêîâ: {}", horses.size());
        for (int i = 0; i < 1; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println("Ïîáåäèë " + winnerName + "!");
        logger.info("Îêîí÷àíèå ñêà÷åê. Ïîáåäèòåëü: {}", winnerName);
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}

