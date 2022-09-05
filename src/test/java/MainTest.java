import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Test
    @Disabled
    @Timeout(22)
    void failsIfExecutionTimeExceeds22Seconds() throws Exception {
        Main.main(null);
    }
}
