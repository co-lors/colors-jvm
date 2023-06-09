package co.lors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RgbColorTest {

  @Test
  public void black() {
    assertEquals(RgbColor.of(0, 0, 0), RgbColor.BLACK);
    assertEquals(RgbColor.of(0, 0, 0, 255), RgbColor.BLACK);
  }

  @Test
  public void white() {
    assertEquals(RgbColor.of(255, 255, 255), RgbColor.WHITE);
    assertEquals(RgbColor.of(255, 255, 255, 255), RgbColor.WHITE);
  }

  @Test
  public void red() {
    assertEquals(RgbColor.of(255, 0, 0), RgbColor.RED);
    assertEquals(RgbColor.of(255, 0, 0, 255), RgbColor.RED);
  }

  @Test
  public void green() {
    assertEquals(RgbColor.of(0, 255, 0), RgbColor.GREEN);
    assertEquals(RgbColor.of(0, 255, 0, 255), RgbColor.GREEN);
  }

  @Test
  public void blue() {
    assertEquals(RgbColor.of(0, 0, 255), RgbColor.BLUE);
    assertEquals(RgbColor.of(0, 0, 255, 255), RgbColor.BLUE);
  }

  @Test
  public void ofWithAlpha() {
    var color = RgbColor.of(127, 128, 129, 130);
    assertEquals(127, color.red());
    assertEquals(128, color.green());
    assertEquals(129, color.blue());
    assertEquals(130, color.alpha());
  }

  @Test
  public void ofWithAlphaInvalidRange() {
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(-1, 128, 129, 130));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(256, 128, 129, 130));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, -1, 129, 130));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 256, 129, 130));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 128, -1, 130));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 128, 256, 130));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 128, 129, -1));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 128, 129, 256));
  }

  @Test
  public void ofWithoutAlpha() {
    var color = RgbColor.of(127, 128, 129);
    assertEquals(127, color.red());
    assertEquals(128, color.green());
    assertEquals(129, color.blue());
    assertEquals(255, color.alpha());
  }

  @Test
  public void ofWithoutAlphaInvalidRange() {
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(-1, 128, 129));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(256, 128, 129));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, -1, 129));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 256, 129));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 128, -1));
    assertThrows(IllegalArgumentException.class, () -> RgbColor.of(127, 128, 256));
  }

  @Test
  public void fromHex() {
    assertEquals(RgbColor.of(18, 52, 86), RgbColor.fromHex("123456"));
    assertEquals(RgbColor.of(18, 52, 86), RgbColor.fromHex("#123456"));
  }

  @Test
  public void fromHexShorthand() {
    assertEquals(RgbColor.of(17, 34, 51), RgbColor.fromHex("123"));
    assertEquals(RgbColor.of(17, 34, 51), RgbColor.fromHex("#123"));
  }

  @Test
  public void fromHexWithAlpha() {
    assertEquals(RgbColor.of(17, 34, 51, 68), RgbColor.fromHex("11223344"));
    assertEquals(RgbColor.of(17, 34, 51, 68), RgbColor.fromHex("#11223344"));
  }

  @Test
  public void fromHexWithoutAlpha() {
    assertEquals("#112233", RgbColor.fromHex("#112233").toHexWithHash());
    assertEquals(RgbColor.of(17, 34, 51, 255), RgbColor.fromHex("#112233"));
  }

  @Test
  public void toHex1() {
    assertEquals("1A2B3C4D", RgbColor.fromHex("#1A2B3C4D").toHex());
  }

  @Test
  public void toHex1Lowercase() {
    assertEquals("1A2B3C4D", RgbColor.fromHex("#1a2b3c4d").toHex());
  }

  @Test
  public void toHex2() {
    assertEquals("01020304", RgbColor.fromHex("#01020304").toHex());
  }

  @Test
  public void toHex3ZeroAlpha() {
    assertEquals("00001100", RgbColor.fromHex("#00001100").toHex());
  }

  @Test
  public void toHex4Shorthand() {
    assertEquals("112233", RgbColor.fromHex("#123").toHex());
  }

  @Test
  public void toHex5NoAlpha() {
    assertEquals("112233", RgbColor.fromHex("#112233").toHex());
  }

  @Test
  public void toHexWithHash1() {
    assertEquals("#1A2B3C4D", RgbColor.fromHex("#1A2B3C4D").toHexWithHash());
  }

  @Test
  public void toHexWithHash1Lowercase() {
    assertEquals("#1A2B3C4D", RgbColor.fromHex("#1a2b3c4d").toHexWithHash());
  }

  @Test
  public void toHexHash2() {
    assertEquals("#01020304", RgbColor.fromHex("#01020304").toHexWithHash());
  }

  @Test
  public void toHexHash3ZeroAlpha() {
    assertEquals("#000011", RgbColor.fromHex("#000011FF").toHexWithHash());
  }

  @Test
  public void toHexHash4Shorthand() {
    assertEquals("#112233", RgbColor.fromHex("#123").toHexWithHash());
  }

  @Test
  public void toHexHash5NoAlpha() {
    assertEquals("#112233", RgbColor.fromHex("#112233").toHexWithHash());
  }

  @Test
  public void fromCss() {
    assertEquals(RgbColor.BLACK, RgbColor.fromCss("rgb(0 0 0 1.0)"));
    assertEquals(RgbColor.WHITE, RgbColor.fromCss("rgb(255 255, 255 1.0)"));
    assertEquals(RgbColor.RED, RgbColor.fromCss("rgb(255 0 0 1.0)"));
    assertEquals(RgbColor.GREEN, RgbColor.fromCss("rgb(0 255 0 1.0)"));
    assertEquals(RgbColor.BLUE, RgbColor.fromCss("rgb(0 0 255 1.0)"));

    assertEquals(RgbColor.of(0, 0, 0, 0), RgbColor.fromCss("rgb(0 0 0 0.0)"));
    assertEquals(RgbColor.of(0, 0, 0, 1), RgbColor.fromCss("rgb(0 0 0 0.004)"));
    assertEquals(RgbColor.of(0, 0, 0, 2), RgbColor.fromCss("rgb(0 0 0 0.008)"));
    assertEquals(RgbColor.of(0, 0, 0, 11), RgbColor.fromCss("rgb(0 0 0 0.043)"));
    assertEquals(RgbColor.of(0, 0, 0, 23), RgbColor.fromCss("rgb(0 0 0 0.09)"));
    assertEquals(RgbColor.of(0, 0, 0, 42), RgbColor.fromCss("rgb(0 0 0 0.165)"));
    assertEquals(RgbColor.of(0, 0, 0, 59), RgbColor.fromCss("rgb(0 0 0 0.231)"));
    assertEquals(RgbColor.of(0, 0, 0, 113), RgbColor.fromCss("rgb(0 0 0 0.443)"));
    assertEquals(RgbColor.of(0, 0, 0, 127), RgbColor.fromCss("rgb(0 0 0 0.498)"));
    assertEquals(RgbColor.of(0, 0, 0, 128), RgbColor.fromCss("rgb(0 0 0 0.502)"));
    assertEquals(RgbColor.of(0, 0, 0, 129), RgbColor.fromCss("rgb(0 0 0 0.506)"));
    assertEquals(RgbColor.of(0, 0, 0, 253), RgbColor.fromCss("rgb(0 0 0 0.992)"));
    assertEquals(RgbColor.of(0, 0, 0, 254), RgbColor.fromCss("rgb(0 0 0 0.996)"));
    assertEquals(RgbColor.of(0, 0, 0, 255), RgbColor.fromCss("rgb(0 0 0 1.0)"));

    // values without rounding to max 3 fractional digits
    assertEquals(RgbColor.of(0, 0, 0, 0), RgbColor.fromCss("rgb(0 0 0 0.0)"));
    assertEquals(RgbColor.of(0, 0, 0, 1), RgbColor.fromCss("rgb(0 0 0 0.00392156862745098)"));
    assertEquals(RgbColor.of(0, 0, 0, 2), RgbColor.fromCss("rgb(0 0 0 0.00784313725490196)"));
    assertEquals(RgbColor.of(0, 0, 0, 11), RgbColor.fromCss("rgb(0 0 0 0.043137254901960784)"));
    assertEquals(RgbColor.of(0, 0, 0, 23), RgbColor.fromCss("rgb(0 0 0 0.09019607843137255)"));
    assertEquals(RgbColor.of(0, 0, 0, 42), RgbColor.fromCss("rgb(0 0 0 0.16470588235294117)"));
    assertEquals(RgbColor.of(0, 0, 0, 59), RgbColor.fromCss("rgb(0 0 0 0.23137254901960785)"));
    assertEquals(RgbColor.of(0, 0, 0, 113), RgbColor.fromCss("rgb(0 0 0 0.44313725490196076)"));
    assertEquals(RgbColor.of(0, 0, 0, 127), RgbColor.fromCss("rgb(0 0 0 0.4980392156862745)"));
    assertEquals(RgbColor.of(0, 0, 0, 128), RgbColor.fromCss("rgb(0 0 0 0.5019607843137255)"));
    assertEquals(RgbColor.of(0, 0, 0, 129), RgbColor.fromCss("rgb(0 0 0 0.5058823529411764)"));
    assertEquals(RgbColor.of(0, 0, 0, 253), RgbColor.fromCss("rgb(0 0 0 0.9921568627450981)"));
    assertEquals(RgbColor.of(0, 0, 0, 254), RgbColor.fromCss("rgb(0 0 0 0.996078431372549)"));
    assertEquals(RgbColor.of(0, 0, 0, 255), RgbColor.fromCss("rgb(0 0 0 1.0)"));
  }

  @Test
  public void fromCssLegacyCommaSyntax() {
    assertEquals(RgbColor.BLACK, RgbColor.fromCss("rgb(0, 0, 0, 1.0)"));
    assertEquals(RgbColor.WHITE, RgbColor.fromCss("rgb(255, 255, 255, 1.0)"));
    assertEquals(RgbColor.RED, RgbColor.fromCss("rgb(255, 0, 0, 1.0)"));
    assertEquals(RgbColor.GREEN, RgbColor.fromCss("rgb(0, 255, 0, 1.0)"));
    assertEquals(RgbColor.BLUE, RgbColor.fromCss("rgb(0, 0, 255, 1.0)"));

    assertEquals(RgbColor.of(0, 0, 0, 0), RgbColor.fromCss("rgb(0, 0, 0, 0.0)"));
    assertEquals(RgbColor.of(0, 0, 0, 1), RgbColor.fromCss("rgb(0, 0, 0, 0.004)"));
    assertEquals(RgbColor.of(0, 0, 0, 2), RgbColor.fromCss("rgb(0, 0, 0, 0.008)"));
    assertEquals(RgbColor.of(0, 0, 0, 11), RgbColor.fromCss("rgb(0, 0, 0, 0.043)"));
    assertEquals(RgbColor.of(0, 0, 0, 23), RgbColor.fromCss("rgb(0, 0, 0, 0.09)"));
    assertEquals(RgbColor.of(0, 0, 0, 42), RgbColor.fromCss("rgb(0, 0, 0, 0.165)"));
    assertEquals(RgbColor.of(0, 0, 0, 59), RgbColor.fromCss("rgb(0, 0, 0, 0.231)"));
    assertEquals(RgbColor.of(0, 0, 0, 113), RgbColor.fromCss("rgb(0, 0, 0, 0.443)"));
    assertEquals(RgbColor.of(0, 0, 0, 127), RgbColor.fromCss("rgb(0, 0, 0, 0.498)"));
    assertEquals(RgbColor.of(0, 0, 0, 128), RgbColor.fromCss("rgb(0, 0, 0, 0.502)"));
    assertEquals(RgbColor.of(0, 0, 0, 129), RgbColor.fromCss("rgb(0, 0, 0, 0.506)"));
    assertEquals(RgbColor.of(0, 0, 0, 253), RgbColor.fromCss("rgb(0, 0, 0, 0.992)"));
    assertEquals(RgbColor.of(0, 0, 0, 254), RgbColor.fromCss("rgb(0, 0, 0, 0.996)"));
    assertEquals(RgbColor.of(0, 0, 0, 255), RgbColor.fromCss("rgb(0, 0, 0, 1.0)"));

    // values without rounding to max 3 fractional digits
    assertEquals(RgbColor.of(0, 0, 0, 0), RgbColor.fromCss("rgb(0, 0, 0, 0.0)"));
    assertEquals(RgbColor.of(0, 0, 0, 1), RgbColor.fromCss("rgb(0, 0, 0, 0.00392156862745098)"));
    assertEquals(RgbColor.of(0, 0, 0, 2), RgbColor.fromCss("rgb(0, 0, 0, 0.00784313725490196)"));
    assertEquals(RgbColor.of(0, 0, 0, 11), RgbColor.fromCss("rgb(0, 0, 0, 0.043137254901960784)"));
    assertEquals(RgbColor.of(0, 0, 0, 23), RgbColor.fromCss("rgb(0, 0, 0, 0.09019607843137255)"));
    assertEquals(RgbColor.of(0, 0, 0, 42), RgbColor.fromCss("rgb(0, 0, 0, 0.16470588235294117)"));
    assertEquals(RgbColor.of(0, 0, 0, 59), RgbColor.fromCss("rgb(0, 0, 0, 0.23137254901960785)"));
    assertEquals(RgbColor.of(0, 0, 0, 113), RgbColor.fromCss("rgb(0, 0, 0, 0.44313725490196076)"));
    assertEquals(RgbColor.of(0, 0, 0, 127), RgbColor.fromCss("rgb(0, 0, 0, 0.4980392156862745)"));
    assertEquals(RgbColor.of(0, 0, 0, 128), RgbColor.fromCss("rgb(0, 0, 0, 0.5019607843137255)"));
    assertEquals(RgbColor.of(0, 0, 0, 129), RgbColor.fromCss("rgb(0, 0, 0, 0.5058823529411764)"));
    assertEquals(RgbColor.of(0, 0, 0, 253), RgbColor.fromCss("rgb(0, 0, 0, 0.9921568627450981)"));
    assertEquals(RgbColor.of(0, 0, 0, 254), RgbColor.fromCss("rgb(0, 0, 0, 0.996078431372549)"));
    assertEquals(RgbColor.of(0, 0, 0, 255), RgbColor.fromCss("rgb(0, 0, 0, 1.0)"));
  }

  @Test
  public void fromCssZeroAlpha() {
    assertEquals(RgbColor.of(0, 0, 0, 0), RgbColor.fromCss("rgb(0, 0, 0, 0)"));
  }

  @Test
  public void fromCssWithoutAlpha() {
    var color = RgbColor.fromCss("rgb(0, 0, 0)");
    assertEquals(RgbColor.of(0, 0, 0), color);
  }

  @Test
  public void toCss() {
    assertEquals("rgb(0, 0, 0, 1.0)", RgbColor.BLACK.toCss());
    assertEquals("rgb(255, 255, 255, 1.0)", RgbColor.WHITE.toCss());
    assertEquals("rgb(255, 0, 0, 1.0)", RgbColor.RED.toCss());
    assertEquals("rgb(0, 255, 0, 1.0)", RgbColor.GREEN.toCss());
    assertEquals("rgb(0, 0, 255, 1.0)", RgbColor.BLUE.toCss());

    assertEquals("rgb(0, 0, 0, 0.0)", RgbColor.of(0, 0, 0, 0).toCss());
    assertEquals("rgb(0, 0, 0, 0.004)", RgbColor.of(0, 0, 0, 1).toCss());
    assertEquals("rgb(0, 0, 0, 0.008)", RgbColor.of(0, 0, 0, 2).toCss());
    assertEquals("rgb(0, 0, 0, 0.043)", RgbColor.of(0, 0, 0, 11).toCss());
    assertEquals("rgb(0, 0, 0, 0.09)", RgbColor.of(0, 0, 0, 23).toCss());
    assertEquals("rgb(0, 0, 0, 0.165)", RgbColor.of(0, 0, 0, 42).toCss());
    assertEquals("rgb(0, 0, 0, 0.231)", RgbColor.of(0, 0, 0, 59).toCss());
    assertEquals("rgb(0, 0, 0, 0.443)", RgbColor.of(0, 0, 0, 113).toCss());
    assertEquals("rgb(0, 0, 0, 0.498)", RgbColor.of(0, 0, 0, 127).toCss());
    assertEquals("rgb(0, 0, 0, 0.502)", RgbColor.of(0, 0, 0, 128).toCss());
    assertEquals("rgb(0, 0, 0, 0.506)", RgbColor.of(0, 0, 0, 129).toCss());
    assertEquals("rgb(0, 0, 0, 0.992)", RgbColor.of(0, 0, 0, 253).toCss());
    assertEquals("rgb(0, 0, 0, 0.996)", RgbColor.of(0, 0, 0, 254).toCss());
    assertEquals("rgb(0, 0, 0, 1.0)", RgbColor.of(0, 0, 0, 255).toCss());

    // values without rounding to max 3 fractional digits:
    /*
    assertEquals("rgb(0, 0, 0, 0.0)", RgbColor.of(0, 0, 0, 0).toCss());
    assertEquals("rgb(0, 0, 0, 0.00392156862745098)", RgbColor.of(0, 0, 0, 1).toCss());
    assertEquals("rgb(0, 0, 0, 0.00784313725490196)", RgbColor.of(0, 0, 0, 2).toCss());
    assertEquals("rgb(0, 0, 0, 0.043137254901960784)", RgbColor.of(0, 0, 0, 11).toCss());
    assertEquals("rgb(0, 0, 0, 0.09019607843137255)", RgbColor.of(0, 0, 0, 23).toCss());
    assertEquals("rgb(0, 0, 0, 0.16470588235294117)", RgbColor.of(0, 0, 0, 42).toCss());
    assertEquals("rgb(0, 0, 0, 0.23137254901960785)", RgbColor.of(0, 0, 0, 59).toCss());
    assertEquals("rgb(0, 0, 0, 0.44313725490196076)", RgbColor.of(0, 0, 0, 113).toCss());
    assertEquals("rgb(0, 0, 0, 0.4980392156862745)", RgbColor.of(0, 0, 0, 127).toCss());
    assertEquals("rgb(0, 0, 0, 0.5019607843137255)", RgbColor.of(0, 0, 0, 128).toCss());
    assertEquals("rgb(0, 0, 0, 0.5058823529411764)", RgbColor.of(0, 0, 0, 129).toCss());
    assertEquals("rgb(0, 0, 0, 0.9921568627450981)", RgbColor.of(0, 0, 0, 253).toCss());
    assertEquals("rgb(0, 0, 0, 0.996078431372549)", RgbColor.of(0, 0, 0, 254).toCss());
    assertEquals("rgb(0, 0, 0, 1.0)", RgbColor.of(0, 0, 0, 255).toCss());
    */
  }

  @Test
  public void value() {
    assertEquals(255, RgbColor.BLACK.value());
    assertEquals(-1, RgbColor.WHITE.value());
    assertEquals(-16776961, RgbColor.RED.value());
    assertEquals(16711935, RgbColor.GREEN.value());
    assertEquals(65535, RgbColor.BLUE.value());
  }

}
