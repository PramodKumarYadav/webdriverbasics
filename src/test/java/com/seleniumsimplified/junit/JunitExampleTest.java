package com.seleniumsimplified.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunitExampleTest {

    @Test
    public void twoPlusTwoEqualsFour() {
        assertEquals(4, 2 + 2,"2+2==4");
    }

    @Test
    public void twoPlusTwoNotEqualsFive() {
        assertEquals(4, 2 + 2,"2+2!=5");
    }
}
