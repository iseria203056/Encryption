
public class Encryption {

    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public String Encryption(LinkedList keystream, String message) {
        String encryptedMessage = "";
        int keystreamValue;
        int asciiOfmessageChar;
        for (int i = 0; i < message.length(); i++) {
            keystreamValue = (int) keystream.getItemAt(i);
            asciiOfmessageChar = (int) message.charAt(i);
            System.out.println(message.charAt(i) + "\t" + (asciiOfmessageChar - 64) + "\t" + keystreamValue + "\t" + (asciiOfmessageChar + keystreamValue) % 26 + "\t" + alphabet[((asciiOfmessageChar - 65) + keystreamValue) % 26]);
            //eins: is the message's char
            //zwei: is the alphabet order of message's char(calculate by ASCII of message's char-64)
            //drei: is the keystream value
            //vier: is alphabet order of encrypted char (calculate by zwei add keystream value)
            //fünf: is encrypted alphabet
            encryptedMessage += alphabet[((asciiOfmessageChar - 65) + keystreamValue) % 26];
        }
        return encryptedMessage;
    }
}
