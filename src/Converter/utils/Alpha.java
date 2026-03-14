package Converter.utils;

public class Alpha {

  // Generate normal alphabet
  public static char[] gen_alpha(char start, char end) {
    char[] alpha = new char[26];
    int count = 0;
    for (char i = start; i <= end; ++i) {
      alpha[count] = i;
      ++count;
    }
    return alpha;
  }
}
