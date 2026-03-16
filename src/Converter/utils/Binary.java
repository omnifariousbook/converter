package Converter.utils;
public class Binary {
  // from text to binary
  public static String tobinary(String str) {
    String result = "";
    for (int i = 0; i < str.length(); i++) {

      // turn char into int
      int tempInt = str.charAt(i);
      String tempBinary = "";

      // int into binary
      while (tempInt > 0) {
        tempBinary = (tempInt % 2) + tempBinary;
        tempInt = Math.floorDivExact(tempInt, 2);
      }
      result += tempBinary + " ";
    }

    // pad to make sure binary is 8 bits
    for (int z = 0; z < 8 - result.length(); ++z) {
      result = "0" + result;
    }

    return result;

  } 
  // From binary to text
  public static String frombinary(String str) {
    String result = "";
    String[] splitInput = str.split(" ");
    for (String b : splitInput) {
      int tempInt = 0;
      for (int i = 0; i < b.length(); ++i) {
        String charToString = String.valueOf(b.charAt(i));
        
        // Only accept binary or the result will be nothing
        if (charToString.equals("0") || charToString.equals("1")) {
          int strToint = (int) Integer.parseInt(charToString);
          tempInt += strToint * (int) Math.pow(2, (b.length() - 1) - i);

        // return nothing
        } else {
          return "";
        }
      }

      // int to char
      char intTochar = (char) tempInt;
      result += String.valueOf(intTochar);
    }
    return result;
  }
}
