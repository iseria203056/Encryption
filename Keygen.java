
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Keygen {

    private LinkedList list = new LinkedList();
    private LinkedList keystream = new LinkedList();

    public LinkedList Keygen(String deck, int messageLength) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(deck));
        int count = 0;

        while (scanner.hasNext()) {//add the deck into LinkedList
            list.addToTail(scanner.nextInt());
        }
        if (!checkDeck(list)) {//check the Correctness of the deck 
            throw new deckException();
        }
        do {
            swap(27);//step1
            System.out.println("step1" + list);//swap the jokerA
            swap(28);//step2
            swap(28);
            System.out.println("step2" + list);//swap the jokerB
            step3();
            System.out.println("step3" + list);//triple cut
            step4();
            System.out.println("step4" + list);//count cut
            if (step5() > 26) {//make sure the keystream is not a joker
                System.out.println("Joker: Key skipped");
                continue;
            }
            keystream.addToTail(step5());
            count++;
            System.out.println("key" + count + ":" + step5());

        } while (count < messageLength);//make sure run times is same with the messagelength

        return keystream;
    }

    public LinkedList Keygen(String deck, int time, String withOutPrint) throws FileNotFoundException {//do not need to print the step
        Scanner scanner = new Scanner(new File(deck));
        int count = 0;
        while (scanner.hasNext()) {//add the deck into LinkedList
            list.addToTail(scanner.nextInt());
        }
        if (!checkDeck(list))//check the Correctness of the deck
        {
            throw new deckException();
        }
        do {
            swap(27);// step1

            swap(28);// step2
            swap(28);

            step3();//triple cut

            step4();//count cut

            if (step5() > 26) {//make sure the keystream is not a joker
                continue;

            }
            keystream.addToTail(step5());
            count++;
        } while (count < time);//make sure run times is same with the messagelength
        return keystream;
    }

    public void swap(int joker) {
        int jokerPosit = list.findIndew(joker);//find joker position
        int swap = (jokerPosit + 1) % list.getCount();//find the swap position
        list.addItemAt(list.getItemAt(jokerPosit), swap);//put the joker in swap position
        Object temp = list.removeItemAt(swap + 1);//take out the swap
        list.addItemAt(temp, jokerPosit);//put the swap in joker position
        list.removeItemAt(jokerPosit + 1);//remove the original joker
    }

    public void step3() {
        int joker1Posit = list.findIndew(27);//find joker A position
        int joker2Posit = list.findIndew(28);//find joker B position
        Object temp;
        if (joker1Posit > joker2Posit) {//when  jokerA position is after  jokerB
            for (int i = 0; i < 27 - joker1Posit; i++) {
                temp = list.removeFromTail();//remove all the card before the Joker A
                list.addToHead(temp);//add the removed card to the head
            }
            for (int i = 0; i < joker2Posit; i++) {
                temp = list.removeItemAt(27 - joker1Posit);//remove all the card after the Joker B and between moved card
                list.addToTail(temp);//add the removed card to the tail
            }
        }
        if (joker1Posit < joker2Posit) {//when  jokerA position is before  jokerB
            for (int i = 0; i < 27 - joker2Posit; i++) {
                temp = list.removeFromTail();//remove all the card before the Joker B
                list.addToHead(temp);//add the removed card to the head
            }
            for (int i = 0; i < joker1Posit; i++) {
                temp = list.removeItemAt(27 - joker2Posit);//remove all the card after the Joker A and between moved card
                list.addToTail(temp);//add the removed card to the tail
            }
        }
    }

    public void step4() {
        if (list.getItemAt(27).equals(27) || list.getItemAt(27).equals(28)) {//when last one is joker,that mean the value is 27,and do not need to do anything
            return;
        }
        for (int i = (int) list.getItemAt(27); i > 0; i--) {
            Object temp = list.removeFromHead();//remove the frist card
            list.addItemAt(temp, 26);//add the removed card before the last cad
        }

    }

    public int step5() {
        int keyAt = (int) list.getItemAt(0);//take the first card value
        if (keyAt == 28) {//when the first is joker,that mean the value is 27.
            keyAt--;
        }
        return (int) list.getItemAt(keyAt);
    }

    public boolean checkDeck(LinkedList deck) {
        HashSet deckSet = new HashSet();//create a set
        int deckLenght = deck.getCount();
        int count = 0; //control the position of array(deckArray) 
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};//the expected composition of the deck
        int[] deckArray = new int[deckLenght];
        for (int i = 0; i < deckLenght; i++) {//add the deck's content into a set
            deckSet.add(deck.getItemAt(i));
        }
        Iterator iterator = deckSet.iterator();
        while (iterator.hasNext()) {
            deckArray[count] = (int) iterator.next();//add the set content in a array
            count++;
        }
        return Arrays.equals(expected, deckArray);//ensure composition of deckArray is same with expected deck
    }

    class deckException extends RuntimeException {
    }
}
