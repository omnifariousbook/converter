package Converter.utils;

public class Rot13 {

  // Generate rot13 alphabet
  public static char[] gen_rot13_alpha(char start, char end) {
    char[] rot13_alpha = new char[26];
    int countRot = 13;
    for (char i = start; i <= end; ++i) {

      if (countRot >= 13 && countRot < 26) {
        // rot13 aphabet 'A' start from 13 and end at index 25
        rot13_alpha[countRot] = i;
        ++countRot;

        // When if and only if countRot is equal to 26, reset it to 0
      } else if (countRot == 26) {
        countRot = 0;
        rot13_alpha[countRot] = i;
        ++countRot;

        // it doesn't fulfill first condition
      } else {
        rot13_alpha[countRot] = i;
        ++countRot;
      }
    }
    return rot13_alpha;
  }

  // convert normal to rot13
  public static String torot13(String str, char[] alpha, char[] rot13_alpha) {
    String result = "";
    for (int c = 0; c < str.length(); ++c) {

      // if not a character
      if (!Character.isAlphabetic(str.charAt(c))) {
        result += str.charAt(c);

      } else {
        for (int a = 0; a < 26; ++a) {

          // Lower case character
          if (str.charAt(c) == alpha[a]) {
            result += rot13_alpha[a];

            // Upper case character
          } else if (str.charAt(c) == Character.toUpperCase(alpha[a])) {
            result += Character.toUpperCase(rot13_alpha[a]);

          }
        }
      }
    }
    return result;
  }

  // convert rot13 to normal
  public static String fromrot13(String str, char[] alpha, char[] rot13_alpha) {
    String result = "";
    for (int c = 0; c < str.length(); ++c) {

      // if not a character
      if (!Character.isAlphabetic(str.charAt(c))) {
        result += str.charAt(c);

      } else {
        for (int a = 0; a < 26; ++a) {

          // Lower case character
          if (str.charAt(c) == rot13_alpha[a]) {
            result += alpha[a];

            // Upper case character
          } else if (str.charAt(c) == Character.toUpperCase(rot13_alpha[a])) {
            result += Character.toUpperCase(alpha[a]);

          }
        }
      }
    }
    return result;
  }

}
