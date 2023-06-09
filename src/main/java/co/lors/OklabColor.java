package co.lors;

/**
 * A type representing an Oklab color with optional transparency.
 *
 * It uses the <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/color_value/oklab">Oklab</a> colorspace,
 * which uses the D65 white point.
 * Compared to sRGB, it can express a wider range of colors.
 */
public record OklabColor(float lightness, float aAxis, float bAxis, float alpha) implements Color {

  public OklabColor {
    checkUnitRange("lightness", lightness);
    checkAxisRange("aAxis", aAxis);
    checkAxisRange("bAxis", bAxis);
    checkUnitRange("alpha", alpha);
  }

  public static OklabColor of(float lightness, float aAxis, float bAxis, float alpha) {
    return new OklabColor(lightness, aAxis, bAxis, alpha);
  }

  public static OklabColor of(float lightness, float aAxis, float bAxis) {
    return new OklabColor(lightness, aAxis, bAxis, 1.0f);
  }

  public static OklabColor fromCss(String value) {
    String[] colors = value.substring(7, value.length() - 1).split(" / |, |,| ");
    float lightness = Float.parseFloat(colors[0].trim());
    float aAxis = Float.parseFloat(colors[1].trim());
    float bAxis = Float.parseFloat(colors[2].trim());
    float alpha = 1.0f;
    if (colors.length > 3) {
      alpha = Float.parseFloat(colors[3].trim());
    }
    return OklabColor.of(lightness, aAxis, bAxis, alpha);
  }

  @Override
  public String toCss() {
    String alphaString = alpha == 0.0f ? "" : " / " + alpha;
    return "oklab(" + lightness + " " + aAxis + " " + bAxis + alphaString + ")";
  }

  @Override
  public RgbColor toRgb() {
      double l = lightness + 0.3963377774 * aAxis + 0.2158037573 * bAxis;
      double m = lightness - 0.1055613458 * aAxis - 0.0638541728 * bAxis;
      double s = lightness - 0.0894841775 * aAxis - 1.2914855480 * bAxis;

      double l3 = l * l * l;
      double m3 = m * m * m;
      double s3 = s * s * s;

    double red = +4.0767416621 * l3 - 3.3077115913 * m3 + 0.2309699292 * s3;
    double green = -1.2684380046 * l3 + 2.6097574011 * m3 - 0.3413193965 * s3;
    double blue = -0.0041960863 * l3 - 0.7034186147 * m3 + 1.7076147010 * s3;
    return RgbColor.of(scaleToByte(red), scaleToByte(green), scaleToByte(blue), (int) (alpha * 255));
  }

  @Override
  public OklabColor toOklab() {
    return this;
  }

  private static double gamma(double value) {
    return value >= 0.0031308
        ? 1.055 * Math.pow(value, 1 / 2.4) - 0.055
        : 12.92 * value;
  }

  private static int scaleToByte(double value) {
    return Math.max(0, Math.min(255, (int) (Math.round(gamma(value) * 255))));
  }

  private static void checkUnitRange(String name, float value) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " cannot be less than 0, but was " + value);
    }
    if (value > 1) {
      throw new IllegalArgumentException(name + " cannot be greater than 1, but was " + value);
    }
  }

  private static void checkAxisRange(String name, float value) {
    if (value < -0.4f) {
      throw new IllegalArgumentException(name + " cannot be less than -0.4, but was " + value);
    }
    if (value > 0.4f) {
      throw new IllegalArgumentException(name + " cannot be greater than 0.4, but was " + value);
    }
  }

}
