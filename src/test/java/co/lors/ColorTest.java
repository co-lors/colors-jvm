package co.lors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

  @Test
  void parse() {
    assertEquals(RgbColor.of(255, 204, 153), Color.parse("#FFCC99"));
    assertEquals(RgbColor.of(255, 204, 153, 34), Color.parse("#FFCC9922"));

    assertEquals(RgbColor.of(255, 204, 153), Color.parse("rgb(255, 204, 153)"));
    assertEquals(RgbColor.of(255, 204, 153, 34), Color.parse("rgb(255, 204, 153 / 0.133)"));

    assertEquals(OklabColor.of(0.700f, 0.200f, 0.000f), Color.parse("oklab(0.7 0.2 0.0)"));
    assertEquals(OklabColor.of(0.700f, 0.200f, 0.000f, 1.0f), Color.parse("oklab(0.7 0.2 0.0 / 1.0)"));
  }

}