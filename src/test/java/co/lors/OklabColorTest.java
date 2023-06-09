package co.lors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OklabColorTest {

  @Test
  void fromCss() {
  }

  @Test
  void toCss() {
  }

  @Test
  void toRgb() {
    assertEquals(RgbColor.of(251, 92, 153), new OklabColor(0.700f, 0.200f, 0.000f, 1.0f).toRgb());
    // assertEquals(RgbColor.of(161, 66, 3), new OklabColor(0.500f, 0.100f, 0.100f, 1.0f).toRgb());
  }

  @Test
  void lightness() {
  }

  @Test
  void aAxis() {
  }

  @Test
  void bAxis() {
  }

  @Test
  void alpha() {
  }

}