package enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseStatusTest {

    @Test
    public void matchCode() {
        System.out.println(ResponseStatus.matchCode(209));
    }
}