package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.solarsystem.SolarSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigBangTest {

    @Test
    void test() {
        final SolarSystem solarSystem = new BigBang().seed(16309)
                .maxSectors(1000)
                .oneWayWarpLanes(50)
                .generate();
        assertEquals(1000, solarSystem.size());
    }

}
