package co.lors;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HexFormat;
import java.util.Locale;

/**
 * A type representing an RGB color with optional transparency.
 *
 * It uses the <a href="https://en.wikipedia.org/wiki/SRGB">sRGB</a> colorspace,
 * which uses the D65 white point.
 */
public record RgbColor(int value) implements Color {

  private static final HexFormat HEX_FORMAT = HexFormat.of().withUpperCase();
  public static final RgbColor BLACK = RgbColor.of(0, 0, 0);
  public static final RgbColor WHITE = RgbColor.of(255, 255, 255);
  public static final RgbColor RED =  RgbColor.of(255, 0, 0);
  public static final RgbColor GREEN = RgbColor.of(0, 255, 0);
  public static final RgbColor BLUE = RgbColor.of(0, 0, 255);

  /**
   * @param alpha value between 0 (completely transparent) and 255 (completely opaque)
   */
  public static RgbColor of(int red, int green, int blue, int alpha) {
    checkRange("red", red);
    checkRange("green", green);
    checkRange("blue", blue);
    checkRange("alpha", alpha);
    int value = (red << 24) | (green << 16) | (blue << 8) | alpha;
    return new RgbColor(value);
  }

  public static RgbColor of(int red, int green, int blue) {
    return RgbColor.of(red, green, blue, 255);
  }

  /** `RGB` or `#RGB` or `RRGGBB` or `#RRGGBB` or `RRGGBBAA` or `#RRGGBBAA` */
  public static RgbColor fromHex(String value) {
    if (value.length() == 3 || value.length() == 6 || value.length() == 8) {
      value = '#' + value;
    }
    int intval = Integer.decode(value);
    if (value.length() == 4) { // color shorthand?
      int red = intval & 0xF00;
      int green = intval & 0x0F0;
      int blue = intval & 0x00F;
      intval = red << 20 | red << 16;
      intval |= green << 16 | green << 12;
      intval |= blue << 12 | blue << 8;
      intval |= 0xFF;
    } else if (value.length() == 7) { // alpha missing?
      intval = (intval << 8) | 0xFF;
    }
    return new RgbColor(intval);
  }

  /** `RRGGBBAA` */
  public String toHex() {
    int value = this.value;
    int digits = 8;
    if (this.alpha() == 255) {
      value = this.value >>> 8;
      digits = 6;
    }
    return HEX_FORMAT.toHexDigits(value, digits);
  }

  /** `#RRGGBBAA` */
  public String toHexWithHash() {
    return "#" + toHex();
  }

  /** `rgb(r, g, b, a)` */
  public static RgbColor fromCss(String value) {
    String[] colors = value.substring(4, value.length() - 1).split(",");
    int r = Integer.parseInt(colors[0].trim());
    int g = Integer.parseInt(colors[1].trim());
    int b = Integer.parseInt(colors[2].trim());
    int a = 255;
    if (colors.length > 3) {
      a = (int) Math.round(Double.parseDouble(colors[3].trim()) * 255);
    }
    return RgbColor.of(r, g, b, a);
  }

  /** `rgb(r, g, b, a)` */
  @Override
  public String toCss() {
    var df = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.ROOT));
    df.setMinimumFractionDigits(1);
    return "rgb(" + red() + ", " + green() + ", " + blue() + ", " + df.format(alphaScaled()) + ")";
  }

  public int red() {
    return (value >> 24) & 0xFF;
  }

  public int green() {
    return (value >> 16) & 0xFF;
  }

  public int blue() {
    return (value >> 8) & 0xFF;
  }

  public int alpha() {
    return value & 0xFF;
  }

  private double alphaScaled() {
    return ((double) alpha()) / 255.0;
  }

  private static void checkRange(String name, int value) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " cannot be less than 0, but was " + value);
    }
    if (value > 255) {
      throw new IllegalArgumentException(name + " cannot be greater than 255, but was " + value);
    }
  }

  @Override
  public String toString() {
    String alpha = alpha() == 255 ? "" : ", alpha=" + alpha();
    return "RgbColor[" + "red=" + red() + ", green=" + green() + ", blue=" + blue() + alpha + ']';
  }

}
