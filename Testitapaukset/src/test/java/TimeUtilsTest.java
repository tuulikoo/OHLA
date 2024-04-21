import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.TimeUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0:00:00",
            "1, 0:00:01",
            "60, 0:01:00",
            "3545, 0:59:05",
            "3600, 1:00:00",
            "3665, 1:01:05",
            "12000, 3:20:00",
            "90020, 25:00:20",
            "360000, 100:00:00",

    })
    void testSecToTime(int seconds, String expected) {
        assertEquals(expected, TimeUtils.secToTime(seconds));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, -0:00:01",
            "-60, -0:01:00",
            "-3545, -0:59:05",
            "-3665, -1:01:05",
            "-90000, -25:00:00",

    })
    void testSecToTimeNegative(int seconds, String expected) {
        assertEquals(expected, TimeUtils.secToTime(seconds));
    }

    @Test
    void testTypeOfReturn() {
        assertEquals(String.class, TimeUtils.secToTime(0).getClass());
    }

}
