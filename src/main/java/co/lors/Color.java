package co.lors;

public sealed interface Color permits RgbColor {

  static Color parse(String value) {
    if (value.startsWith("#")) {
      return RgbColor.fromHex(value);
    } else if (value.startsWith("rgb")) {
      return RgbColor.fromCss(value);
    }
    // todo: handle more colorspaces

    throw new UnsupportedOperationException("Colorspace of " + value + " is unsupported!");
  }

  String toCss();

}
