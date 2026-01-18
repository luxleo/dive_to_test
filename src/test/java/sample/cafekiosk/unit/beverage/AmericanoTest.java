package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();

        String name = "아메리카노";
        assertEquals(name, americano.getName());
        assertThat(americano.getName()).isEqualTo(name);
    }

}