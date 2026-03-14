package Converter.utils;

public class Hex {
  // generate array of hexadecimal number
  public static String[] gen_hex() {
    String[] hex = new String[16];
    char character = 'a';

    // count from 0, the last index is 15
    for (int n = 0; n < 16; ++n) {

      // 1 - 9
      if (n < 10) {
        hex[n] = String.valueOf(n);

        // a - f
      } else if (n >= 10) {
        hex[n] = String.valueOf(character);
        ++character;
      }
    }
    return hex;
  }

  // Hex encode
  public static String tohex(String str, String[] hex) {
    String result = "";

    for (int c = 0; c < str.length(); ++c) {
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
          // and a space to indicate the end of one character hex
          if (ascii == 0) {
            result = result + hex[h] + "0 ";
            // if there is a remainder
          } else {
            result = result + hex[h];
          }
          // if the result is 0 when we divide then conclude it with a space
        } else {

          result = result + hex[ascii] + " ";
          break;
        }
      }
    }
    return result;
  }

  // Hex decode
  public static String fromhex(String str, String[] hex) {
    String result = "";
    // split user input into array
    String[] hex_array = str.split(" ");
    int temp = 0;

    // one hex
    for (String each_hex : hex_array) {
      if (each_hex.length() > 2) {
        // should impliment something if there's no between space!!!!!!
        System.out
            .println("Please check your hexadecimal number again and make sure sperate by space, i.e., 48 65 6C 6C 6F");

      }
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
      // if there's something in temp / mean that can find matches
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
    }
    return result;
  }

}
