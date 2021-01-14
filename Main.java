
import java.io.FileNotFoundException;
import static java.lang.Character.isLetter;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {//make sure user inputs length is correct
            System.out.println("Usage:java ASSIGNMENT {en|de|keygen}  [deck_file] [\"message_string\"]");
            System.exit(1);
        }
        Keygen key = new Keygen();
        String message = changeToUppercase(args[2]);//change rhe message's letter to Uppercase and skip the space
        try {
            switch (args[0]) {
                case "keygen": {
                    System.out.print("Keystream values: " + key.Keygen(args[1], message.length()));//print the Keystream values
                    break;
                }
                case "en": {
                    Encryption en = new Encryption();//create a Encryption
                    System.out.print("Encrypted message: " + en.Encryption(key.Keygen(args[1], message.length(), "withOutStep"), message));
                    break;
                }
                case "de": {
                    Decryption de = new Decryption();//create a Decryption
                    System.out.print("Decrypted message: " + de.Decryption(key.Keygen(args[1], message.length(), "withOutStep"), message));
                    break;
                }
                default://if the frist argument is not equal to en|de|keygen
                    System.out.println("Usage:java ASSIGNMENT {en|de|keygen}  [deck_file] [\"message_string\"]");
                    System.out.print("please check your inputted argument ");
                    System.exit(1);
            }
        } catch (Keygen.deckException e) {
            System.out.print("the deck should consist 28 unique number which consist of 1 to 28 ");
        } catch (FileNotFoundException e) {
            System.out.print("the File can not found");
        }

    }

    public static String changeToUppercase(String message) {
        String uppercaseMessage = "";
        try {
            for (int i = 0; i < message.length(); i++) {
                if (Character.isSpaceChar(message.charAt(i))) {//skip the space
                    continue;
                }
                if (!isLetter(message.charAt(i))) {//make sure the message is consist with letter only
                    throw new RuntimeException();
                }
                uppercaseMessage += Character.toUpperCase(message.charAt(i));// convert to uppercase
            }
        } catch (RuntimeException e) {
            System.out.println("The message should consist with letters and space only");
            System.exit(1);
        }
        return uppercaseMessage;
    }

}
