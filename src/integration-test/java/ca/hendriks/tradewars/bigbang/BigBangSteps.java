package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.solarsystem.Sector;
import ca.hendriks.tradewars.solarsystem.SolarSystem;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BigBangSteps {

    private SolarSystem solarSystem;

    @When("BigBang runs")
    public void big_bang_runs() {
        solarSystem = new BigBang().generate();
    }

    @Then("Sector One should have Warp Lanes in to and out of:")
    public void sector_one_has_warp_lanes_out_to(final DataTable dataTable) {
        final List<Map<String, String>> expectedRows = dataTable.asMaps();
        final Set<Sector> actualDestinations = findDestinationsFromSectorOne();
        final Set<Sector> actualOrigins = findSectorsLeadingToSectorOne();
        assertAll(
                () -> assertEquals(expectedRows.size(), actualDestinations.size()),
                () -> assertEquals(expectedRows.size(), actualOrigins.size())
        );
        expectedRows.forEach(expected -> {
            final Sector expectedSector = solarSystem.findSector(Integer.parseInt(expected.get("Sector")));
            assertAll(
                    () -> assertTrue(actualDestinations.contains(expectedSector)),
                    () -> assertTrue(actualOrigins.contains(expectedSector))
            );
        });
    }

    private Set<Sector> findDestinationsFromSectorOne() {
        final Sector one = solarSystem.findSector(1);
        final List<Sector> warpLanesOutOfSector = solarSystem.findSectorsLeadingAway(one);
        return new HashSet<>(warpLanesOutOfSector);
    }

    private Set<Sector> findSectorsLeadingToSectorOne() {
        final Sector one = solarSystem.findSector(1);
        final List<Sector> warpLanesOutOfSector = solarSystem.findSectorsLeadingTo(one);
        return new HashSet<>(warpLanesOutOfSector);
    }

}
