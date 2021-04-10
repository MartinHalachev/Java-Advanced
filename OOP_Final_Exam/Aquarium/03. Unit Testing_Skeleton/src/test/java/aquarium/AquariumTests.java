package aquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AquariumTests {
    private Aquarium aquarium;
    private Fish firstFish;
    private Fish secondFish;
    private Fish thirdFish;

    @Before
    public void setUp() {
        this.aquarium = new Aquarium("Fishdom", 3);
        this.firstFish = new Fish("Bori");
        this.secondFish = new Fish("Martin");
        this.thirdFish = new Fish("Shizka");
        aquarium.add(firstFish);
        aquarium.add(secondFish);
        aquarium.add(thirdFish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquariumCapacityBelowZero() {
        new Aquarium("Fishdom", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testAquariumNameNull() {
        new Aquarium(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testAquariumNameEmpty() {
        new Aquarium("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInFullAquarium() {
        Aquarium fishipalace = new Aquarium("Fishipalace", 1);
        fishipalace.add(firstFish);
        fishipalace.add(secondFish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingFish() {
        aquarium.remove("Sezo");
    }

    @Test
    public void testRemoving() {
        aquarium.remove("Martin");
        assertEquals(2, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellNonExistingFish() {
        aquarium.sellFish("Ilko");
    }

    @Test
    public void testSellFish() {
        aquarium.sellFish("Shizka");
        assertFalse(thirdFish.isAvailable());
    }

    @Test
    public void testReport() {
        String expected = "Fish available at Fishdom: ghfgjgj";
        String actual = aquarium.report();
        assertEquals(expected, actual);
    }
}

