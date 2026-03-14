package Converter.utils;

public class CaesarCipher {
  // convert normal to caesar cipher
  public static String tocaesar(String str, int shift, char[] alpha) {
    String result = "";
    for (int c = 0; c < str.length(); ++c) {

      // number or special character
      if (!Character.isAlphabetic(str.charAt(c))) {
        result += str.charAt(c);

      } else if (Character.isAlphabetic(str.charAt(c))) {
        for (int a = 0; a < alpha.length; ++a) {

          // when rotate not exceeding index of alphabet
          if ((a + shift <= 25)) {

            if (str.charAt(c) == alpha[a]) {
              result += alpha[a + shift];

              // more than index of alphabet will revert back
            } else if (str.charAt(c) == Character.toUpperCase(alpha[a])) {
              result += Character.toUpperCase(alpha[a + shift]);
            }

            // when rotate exceed index of alphabet
          } else if (a + shift > 25) {
            if (str.charAt(c) == alpha[a]) {
              // there are 26 alphabet in total
              result += alpha[((a + shift) % 26)];

            } else if (str.charAt(c) == Character.toUpperCase(alpha[a])) {
              result += Character.toUpperCase(alpha[(a + shift) % 26]);
            }
          }
        }
      }
    }
    return result;
  }

  // convert caesar cipher to normal
  public static String fromcaesar(String str, int shift, char[] alpha) {
    String result = "";
    for (int c = 0; c < str.length(); ++c) {

      // number or special character
      if (!Character.isAlphabetic(str.charAt(c))) {
        result += str.charAt(c);

      } else if (Character.isAlphabetic(str.charAt(c))) {
        for (int a = 0; a < alpha.length; ++a) {
          // when rotate back not less than index of alphabet
          if ((a - shift >= 0)) {

            if (str.charAt(c) == alpha[a]) {
              result += alpha[a - shift];

              // more than index of alphabet will revert back
            } else if (str.charAt(c) == Character.toUpperCase(alpha[a])) {
              result += Character.toUpperCase(alpha[a - shift]);
            }
            // when rotate back less index of alphabet
          } else if (a - shift < 0) {
            if (str.charAt(c) == alpha[a]) {
              result += alpha[(26 - (Math.abs(a - shift) % 26))];

            } else if (str.charAt(c) == Character.toUpperCase(alpha[a])) {
              result += Character.toUpperCase(alpha[(26 - (Math.abs(a - shift) % 26))]);
            }
          }
        }
      }
    }
    return result;
  }

}
