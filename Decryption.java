
public class Decryption {

    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public String Decryption(LinkedList keystream, String message) {
        String decryptedMessage = "";
        int keystreamValue;
        int asciiOfmessageChar;
        for (int i = 0; i < message.length(); i++) {
            keystreamValue = (int) keystream.getItemAt(i);
            asciiOfmessageChar = (int) message.charAt(i);
            System.out.println(message.charAt(i) + "\t" + (asciiOfmessageChar - 64) + "\t" + keystreamValue + "\t" + (((asciiOfmessageChar - 64) - keystreamValue) + 26) % 26 + "\t" + alphabet[(((asciiOfmessageChar - 65) - keystreamValue) + 26) % 26]);
            //eins: is the encrypted char
            //zwei: is the alphabet order of encrypted char(calculate by encrypted char's ASCII - 64 )
            //drei: is the keystream value
            //vier: is alphabet order of Decrypted char (calculate by zwei subtract keystream value)
            //fünf: is decrypted alphabet
            decryptedMessage += alphabet[(((asciiOfmessageChar - 65) - keystreamValue) + 26) % 26];
        }
        return decryptedMessage;
    }
}
