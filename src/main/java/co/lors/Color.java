package co.lors;

public sealed interface Color permits RgbColor, OklabColor {

  static Color parse(String value) {
    if (value.startsWith("#")) {
      return RgbColor.fromHex(value);
    } else if (value.startsWith("rgb")) {
      return RgbColor.fromCss(value);
    } else if (value.startsWith("oklab")) {
      return OklabColor.fromCss(value);
    }

    throw new UnsupportedOperationException("Colorspace of " + value + " is unsupported!");
  }

  String toCss();

  RgbColor toRgb();

  OklabColor toOklab();

}
