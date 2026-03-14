package Converter.utils;

public class Base64 {
  public static String[] gen_base64_alpha() {
    String[] result = new String[64];
    int count = 0;
    for (char c = 'A'; c <= 'Z'; ++c) {
      result[count] = String.valueOf(c);
      ++count;
    }
    for (char c = 'a'; c <= 'z'; ++c) {
      result[count] = String.valueOf(c);
      ++count;
    }
    for (int n = 0; n < 10; ++n) {
      result[count] = String.valueOf(n);
      ++count;
    }
    result[62] = "+";
    result[63] = "/";
    return result;
  }

  public static String tobase64(String str, String[] base64_alpha) {
    String[] base64 = gen_base64_alpha();
    String binary = "";
    String temp_binary = "";
    String result = "";
    int pad = 0;

    // String to char then to int then to binary
    for (int c = 0; c < str.length(); ++c) {
      int num = str.charAt(c);

      // int to binary
      while (num > 0) {
        int temp = num % 2;
        temp_binary = String.valueOf(temp) + temp_binary;
        num = Math.floorDiv(num, 2);
      }
      int eightbits_pad = 8 - temp_binary.length();

      // add 0s to get 8 bits
      // finding binary with calculation won't give us the correct bits we want
      for (int p = 0; p < eightbits_pad; ++p) {
        temp_binary = "0" + temp_binary;
      }

      binary += temp_binary;

      // reset it for next char
      temp_binary = "";

    }

    // padding
    // check if binary need padding or not
    if (binary.length() % 24 != 0) {

      // if it less than 3 bytes then subtract with 24 which is 3 byte,
      // more than 24 but less than 6 bytes subtract with 24 * 2 which is 6 byte so on
      pad = (24 * (Math.floorDivExact(binary.length(), 24) + 1)) - binary.length();
      for (int z = 0; z < pad; ++z) {
        binary = binary + "0";
      }
    }
    int power = 0;
    int toInt = 0;
    String six = "";
    for (int i = 0; i < binary.length(); ++i) {

      // add binary to variable six to make it 6 binary
      if (six.length() < 6) {
        six += String.valueOf(binary.charAt(i));
        // when it 6 we can use it and turn it into int
      }

      // don't use elif becuase when if above reach 6 below condition won't happen
      // right away and way until the loop go for another round which is 7
      // but with if after it reach 6 in above condition this if will also be able to
      // work right away
      if (six.length() == 6) {

        // binary back to int
        for (int b = six.length() - 1; b >= 0; --b) {

          // get each character in "binary" and convert to string before it can be use
          // with Integer.parseInt
          toInt = toInt + (Integer.parseInt(String.valueOf(six.charAt(b))) * (int) Math.pow(2, power));
          ++power;

        }

        // from int to base 64 character
        result += base64[toInt];
        power = 0;
        toInt = 0;
        six = "";
      }
    }

    // because right now we turn even the padding which is not a real character into
    // character we need to remove it and change it into "=" or "==" accordingly
    String temp = "";

    // 16 pads will left with two 6 bits of 0 which should be turn into ==
    if (pad == 16) {

      // remove that 2 characters from two 6 bits of 0
      for (int i = 0; i < result.length() - 2; ++i) {
        temp += String.valueOf(result.charAt(i));
      }
      result = temp + "==";

      // 8 pads will left with one 6 bits of 0 which should be turn into =
    } else if (pad == 8) {

      // remove that one character from one 6 bits of 0
      for (int i = 0; i < result.length() - 1; ++i) {
        temp += String.valueOf(result.charAt(i));
      }
      // now change the result variable into the real result
      result = temp + "=";
    }
    return result;
  }

  public static String frombase64(String str, String[] base64_alpha) {

    String result = "";
    int[] temp_int = new int[str.length()];
    String temp_binary = "";
    int pad = 0;

    // Base64 character to int
    for (int c = 0; c < str.length(); ++c) {

      // no need to compare with base64 list
      // we want to convert equal sign into 0 so we can remove it all together when
      // converting into all 0 binary with
      // other 0 that conbine with the last character when it were split into 6 bits
      if (String.valueOf(str.charAt(c)).equals("=")) {
        temp_int[c] = 0;
        pad += 1;
      }
      for (int b = 0; b < base64_alpha.length; ++b) {
        if (String.valueOf(str.charAt(c)).equals(base64_alpha[b])) {
          // index of char of the string (c) equals to the index in base64 (b)
          temp_int[c] = b;
        }
      }
    }
    // int to binary
    for (int e : temp_int) {
      String temp = "";
      int dividen = e;

      if (dividen > 0) {
        // int to binary using math
        while (dividen != 0) {
          temp = (dividen % 2) + temp;
          dividen = Math.floorDivExact(dividen, 2);
        }
        int needed = 6 - temp.length();
        // add missing 0s from converting via math operation
        for (int z = 0; z < (needed); ++z) {
          temp = "0" + temp;
        }
        // add a complete 6 bits to temp_binary variable which can be use later
        temp_binary = temp_binary + temp;

        // if dividen is 0 we can just add 6 bits of 0 directly
      } else if (dividen == 0) {
        temp_binary = temp_binary + "000000";

      }
    }

    // turn from binary -> int -> char -> String
    if (pad == 0) {
      String eight = "";
      int count = 0;
      for (int b = 0; b < temp_binary.length(); ++b) {
        eight += String.valueOf(temp_binary.charAt(b));

        // beucase 0 to 7 is 8
        if ((b + 1) % 8 == 0) {
          int temp = 0;

          // convert from 8 bits binary to int
          for (int i = 0; i < eight.length(); ++i) {

            // get one char from "eight" then convert it to String so it can then be convert
            // to int
            // the multiply it with 2 to the power of it's position but from the right so
            // the right most is 0
            // we use eight.lenght and minus one because index is smaller by 1 number
            // then it will decrease as i increase
            temp += Integer.parseInt(String.valueOf(eight.charAt(i))) * Math.pow(2, (eight.length() - 1) - i);

          }
          eight = "";
          char temp_char = (char) temp;
          result += String.valueOf(temp_char);
        }
      }
    }

    // if pad is 1 then remove the last 8 bits of 0 which were pad when encode
    else if (pad == 1) {
      String eight = "";
      int count = 0;
      for (int b = 0; b < (temp_binary.length() - 8); ++b) {
        eight += String.valueOf(temp_binary.charAt(b));

        // beucase 0 to 7 is 8
        if ((b + 1) % 8 == 0) {
          int temp = 0;

          // convert from 8 bits binary to int
          for (int i = 0; i < eight.length(); ++i) {

            // get one char from "eight" then convert it to String so it can then be convert
            // to int
            // the multiply it with 2 to the power of it's position but from the right so
            // the right most is 0
            // we use eight.lenght and minus one because index is smaller by 1 number
            // then it will decrease as i increase
            temp += Integer.parseInt(String.valueOf(eight.charAt(i))) * Math.pow(2, (eight.length() - 1) - i);

          }
          eight = "";
          char temp_char = (char) temp;
          result += String.valueOf(temp_char);
        }
      }
    }

    // if pad is 2 then remove the last 16 bits of 0 which were pad when encode
    else if (pad == 2) {
      String eight = "";
      int count = 0;
      for (int b = 0; b < (temp_binary.length() - 16); ++b) {
        eight += String.valueOf(temp_binary.charAt(b));

        // beucase 0 to 7 is 8
        if ((b + 1) % 8 == 0) {
          int temp = 0;
          // convert from 8 bits binary to int
          for (int i = 0; i < eight.length(); ++i) {

            // get one char from "eight" then convert it to String so it can then be convert
            // to int
            // the multiply it with 2 to the power of it's position but from the right so
            // the right most is 0
            // we use eight.lenght and minus one because index is smaller by 1 number
            // then it will decrease as i increase
            temp += Integer.parseInt(String.valueOf(eight.charAt(i))) * Math.pow(2, (eight.length() - 1) - i);

          }

          eight = "";
          char temp_char = (char) temp;
          result += String.valueOf(temp_char);

        }
      }
    }
    return result;
  }
}
