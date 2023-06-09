package co.lors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OklabColorTest {

  public static final OklabColor COLOR1 = OklabColor.of(0.700f, 0.200f, 0.000f, 1.0f);
  public static final OklabColor COLOR2 = OklabColor.of(0.500f, 0.100f, 0.100f, 1.0f);

  @Test
  void fromCss() {
    assertEquals(COLOR1, OklabColor.fromCss("oklab(0.7 0.2 0.0 / 1.0)"));
    assertEquals(COLOR2, OklabColor.fromCss("oklab(0.5 0.1 0.1 / 1.0)"));
  }

  @Test
  void toCss() {
    assertEquals(COLOR1.toCss(), "oklab(0.7 0.2 0.0 / 1.0)");
    assertEquals(COLOR2.toCss(), "oklab(0.5 0.1 0.1 / 1.0)");
  }

  @Test
  void toRgb() {
    assertEquals(COLOR1.toRgb(), RgbColor.of(251, 92, 153, 255));
    assertEquals(COLOR2.toRgb(), RgbColor.of(161, 66, 3, 255));
  }

  @Test
  void lightness() {
    assertEquals(COLOR1.lightness(), 0.7f);
    assertEquals(COLOR2.lightness(), 0.5f);
  }

  @Test
  void aAxis() {
    assertEquals(COLOR1.aAxis(), 0.2f);
    assertEquals(COLOR2.aAxis(), 0.1f);
  }

  @Test
  void bAxis() {
    assertEquals(COLOR1.bAxis(), 0.0f);
    assertEquals(COLOR2.bAxis(), 0.1f);
  }

  @Test
  void alpha() {
    assertEquals(COLOR1.alpha(), 1.0f);
    assertEquals(COLOR2.alpha(), 1.0f);
  }

}