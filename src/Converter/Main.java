package Converter;

import java.util.Scanner;
import Converter.utils.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ArgGroup;

@Command(name = "converter", mixinStandardHelpOptions = true, version = "converter 1.0.0", description = "%nEx: converter -e --base64 \"Hello world\"%n")
public class Main implements Runnable {

  // Allow only one of these per command
  static class EnorDe {
    @Option(names = { "-e", "--encode" }, description = "Encode")
    boolean encode;
    @Option(names = { "-d", "--decode" }, description = "Decode")
    boolean decode;
  }

  // Allow only one of these per command
  static class ExclusiveType {
    @Option(names = { "--rot13" }, description = "ROT13")
    boolean rot13;
    @Option(names = { "--base64" }, description = "Base64")
    boolean base64;
    @Option(names = { "--caesar" }, description = "Caesar Cipher")
    boolean caesar;
    @Option(names = { "--url" }, description = "Url")
    boolean url;
    @Option(names = { "--hex" }, description = "Hex")
    boolean hex;
  }

  // take words string
  @Parameters(arity = "0...*", description = "Words to encode/decode")
  String str;

  // interactive mode
  @Option(names = { "-i",
      "--interactive" }, description = "Enable interactive mode. Using this mode will ignore all other option")
  boolean interactive;

  // choose to encode or decode
  @ArgGroup(exclusive = true, multiplicity = "0..1", heading = "%nChoose one of the following option:%n")
  EnorDe enorde;

  // choose one of the type to decode/encode
  @ArgGroup(exclusive = true, multiplicity = "0..1", heading = "%nChoose one of the following to encode/decode:%n")
  ExclusiveType convertTo;

  @Override
  public void run() {
    Scanner input = new Scanner(System.in);

    if (interactive) {
      System.out.print("1. Encode\n2. Decode\n>> ");
      String en_de = input.nextLine();

      // if encoding
      if (en_de.equals("1")) {
        System.out.print("1. ROT13\n2. Base64\n3. Caesar Cipher\n4. Hex Encoding\n5.URL Encoding\n>> ");
        String method = input.nextLine();

        // if ROT13
        if (method.equals("1")) {
          char[] alpha = Alpha.gen_alpha('a', 'z');
          char[] rot13_alpha = Rot13.gen_rot13_alpha('a', 'z');
          System.out.print("String: ");
          String str = input.nextLine();
          String result = Rot13.torot13(str, alpha, rot13_alpha);
          System.out.println(result);

          /// Base64
        } else if (method.equals("2")) {
          String[] base64_alpha = Base64.gen_base64_alpha();
          System.out.print("String: ");
          String str = input.nextLine();
          String result = Base64.tobase64(str, base64_alpha);
          System.out.println(result);

          // Caesar Cipher
        } else if (method.equals("3")) {
          char[] alpha = Alpha.gen_alpha('a', 'z');
          System.out.print("String: ");
          String str = input.nextLine();
          System.out.print("Shift: ");
          int shift = input.nextInt();
          String result = CaesarCipher.tocaesar(str, shift, alpha);
          System.out.println(result);

          // Hex Encode
        } else if (method.equals("4")) {
          String[] hex = Hex.gen_hex();
          System.out.print("String: ");
          String str = input.nextLine();
          String result = Hex.tohex(str, hex);
          System.out.println(result);

          // Url Encoding
        } else if (method.equals("5")) {
          String[] hex = Hex.gen_hex();
          System.out.print("String: ");
          String str = input.nextLine();
          String result = Url.tourl(str, hex);
          System.out.println(result);

        } else {
          System.out.println("Not exist");
          // exit with status code error
          System.exit(1);
        }

        // if decode
      } else if (en_de.equals("2")) {
        System.out.print("1. ROT13\n2. Base64\n3. Caesar Cipher\n4. Hex Decoding\n5. URL Encoding\n>> ");
        String method = input.nextLine();

        // if ROT13
        if (method.equals("1")) {
          char[] alpha = Alpha.gen_alpha('a', 'z');
          char[] rot13_alpha = Rot13.gen_rot13_alpha('a', 'z');
          System.out.print("String: ");
          String str = input.nextLine();
          String result = Rot13.fromrot13(str, alpha, rot13_alpha);
          System.out.println(result);

        } else if (method.equals("2")) {
          String[] base64_alpha = Base64.gen_base64_alpha();
          System.out.print("String: ");
          String str = input.nextLine();
          String result = Base64.frombase64(str, base64_alpha);
          System.out.println(result);
        }

        // if Caesar Cipher
        else if (method.equals("3")) {
          char[] alpha = Alpha.gen_alpha('a', 'z');
          System.out.print("String: ");
          String str = input.nextLine();
          System.out.print("Shift: ");
          int shift = input.nextInt();
          String result = CaesarCipher.fromcaesar(str, shift, alpha);
          System.out.println(result);

          // Hex Decoding
        } else if (method.equals("4")) {
          System.out.print("String: ");
          String str = input.nextLine();
          String[] hex = Hex.gen_hex();
          String result = Hex.fromhex(str, hex);
          System.out.println(result);

          // Url Decoding
        } else if (method.equals("5")) {
          System.out.print("String: ");
          String str = input.nextLine();
          String[] hex = Hex.gen_hex();
          String result = Url.fromurl(str, hex);
          System.out.println(result);

        } else {
          System.out.println("Not exist");
          // exit with status code error
          System.exit(1);
        }
      } else {
        System.out.println("Not exist");
        // exit with status code error
        System.exit(1);
      }

      // if not interactive
    } else {
      if (enorde == null) {
        System.out.println("Please choose to encode or decode. use -h or --help to see the options.");
      }
      if (convertTo == null) {
        System.out.println("Please choose to one type to encode/decode. use -h or --help to see the options.");
      }
      if (str == null) {
        System.out.println("Please provide a string to encode/decode. use -h or --help to see the options.");
      }
      if (enorde != null && convertTo != null && str != null) {

        // if encode
        if (enorde.encode) {
          // Rot13
          if (convertTo.rot13) {
            char[] alpha = Alpha.gen_alpha('a', 'z');
            char[] rot13_alpha = Rot13.gen_rot13_alpha('a', 'z');
            String result = Rot13.torot13(str, alpha, rot13_alpha);
            System.out.println(result);

            // Base64
          } else if (convertTo.base64) {
            String[] base64_alpha = Base64.gen_base64_alpha();
            String result = Base64.tobase64(str, base64_alpha);
            System.out.println(result);

            // CaesarCipher
          } else if (convertTo.caesar) {
            char[] alpha = Alpha.gen_alpha('a', 'z');
            System.out.print("Shift: ");
            int shift = input.nextInt();
            String result = CaesarCipher.tocaesar(str, shift, alpha);
            System.out.println(result);

            // Hex
          } else if (convertTo.hex) {
            String[] hex = Hex.gen_hex();
            String result = Hex.tohex(str, hex);
            System.out.println(result);

            // Url
          } else if (convertTo.url) {
            String[] hex = Hex.gen_hex();
            String result = Url.tourl(str, hex);
            System.out.println(result);

          }
          // if decode
        } else if (enorde.decode) {

          // Rot13
          if (convertTo.rot13) {
            char[] alpha = Alpha.gen_alpha('a', 'z');
            char[] rot13_alpha = Rot13.gen_rot13_alpha('a', 'z');
            String result = Rot13.fromrot13(str, alpha, rot13_alpha);
            System.out.println(result);

            // Base64
          } else if (convertTo.base64) {
            String[] base64_alpha = Base64.gen_base64_alpha();
            String result = Base64.frombase64(str, base64_alpha);
            System.out.println(result);

            // CaesarCipher
          } else if (convertTo.caesar) {
            char[] alpha = Alpha.gen_alpha('a', 'z');
            System.out.print("Shift: ");
            int shift = input.nextInt();
            String result = CaesarCipher.fromcaesar(str, shift, alpha);
            System.out.println(result);

            // Hex
          } else if (convertTo.hex) {
            String[] hex = Hex.gen_hex();
            String result = Hex.fromhex(str, hex);
            System.out.println(result);

            // Url
          } else if (convertTo.url) {
            String[] hex = Hex.gen_hex();
            String result = Url.fromurl(str, hex);
            System.out.println(result);
          }
        }
      }
    }
    input.close();
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new Main()).execute(args);
    System.exit(exitCode);
  }
}
