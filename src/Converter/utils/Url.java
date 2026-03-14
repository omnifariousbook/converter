package Converter.utils;

public class Url {
  // URL Encoding
  public static String tourl(String str, String[] hex) {
    String result = "";

    for (int c = 0; c < str.length(); ++c) {

      // all safe character
      boolean isalpha = Character.isAlphabetic(str.charAt(c));
      boolean isdigit = Character.isDigit(str.charAt(c));
      boolean dash = str.charAt(c) == '-';
      boolean period = str.charAt(c) == '.';
      boolean underscore = str.charAt(c) == '_';
      boolean tilde = str.charAt(c) == '~';

      // turn character into ascii decimal only they are not one of the safe char
      if (!isalpha && !isdigit && !dash && !period && !underscore && !tilde) {
        // character in ascii decimal number
        int ascii = str.charAt(c);
        // prevent division by 0
        while (ascii > 0) {
          //
          if (Math.floorDiv(ascii, 16) > 0) {
            int h = Math.floorDiv(ascii, 16);

            // remainder become divident
            ascii = ascii % 16;

            // 0 mean that it divisible so it gonna be only one number.
            // Thus add another 0 behind to make it pair like normal hexadecimal number
            if (ascii == 0) {
              result = result + "%" + hex[h] + "0";
              // if there is a remainder
            } else {
              result = result + "%" + hex[h];
            }
            // if the result is 0 when we divide
          } else {

            result = result + hex[ascii];
            break;
          }
        }
      } else {
        result += str.charAt(c);
      }
    }
    return result;
  }

  // URL Decoding
  public static String fromurl(String str, String[] hex) {
    String result = "";
    // split user input into array
    String each_hex = "";
    int temp = 0;

    // one hex
    for (int f = 0; f < str.length(); ++f) {
      if (str.charAt(f) == '%') {
        each_hex = each_hex + String.valueOf(str.charAt(f + 1)) + String.valueOf(str.charAt(f + 2));
        // fast forward 2 char that we already stored
        f += 2;

        // loop or each character in one hex
        for (int c = 0; c < each_hex.length(); ++c) {

          // loop array of hexadecimal number that generated
          for (int h = 0; h < hex.length; h++) {

            // convert char into string with String.valueOf then convert it lowercase
            // use equals to compare string
            if (String.valueOf(each_hex.charAt(c)).toLowerCase().equals(hex[h])) {

              // when found matches we can convert user hex into int by using index of the
              // generated hex array which is "h" in this case
              // each_hex.length() - 1 because length is 1 number bigger than index
              // and 16 power will decrease each time as hex move from left to right
              temp += h * ((int) (Math.pow(16, (each_hex.length() - 1) - c)));
            }
          }
        }
        // if there's not nothing in temp / mean that can find matches
        if (temp != 0) {
          // int to char (ascii)
          char convert_to_char = (char) temp;

          // char to String
          result += String.valueOf(convert_to_char);

          // reset temp for each hex number
          temp = 0;
        } else {
          System.out.println("Error, please check your Hexadecimal numbers again");
          System.exit(1);
        }
      } else {
        result += str.charAt(f);
      }
      // reset it back so it can store next upcomming hex values
      each_hex = "";
    }
    return result;
  }

}
