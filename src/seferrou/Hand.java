package seferrou;

/**
 *
 * @author victor
 */
import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;
    private Integer predicton; //elementos importantes para o se ferrou
    private Integer roundsWon;

    
    public Hand() {
        this.hand = new ArrayList<Card>();
        this.predicton=0;
        this.roundsWon=0;
    }

    public void clear() {
        this.hand.clear();
        this.predicton=0;
        this.roundsWon=0;
    }
    
    public Integer getPredicton() {
        return predicton;
    }

    public void setPredicton(Integer predicton) {
        this.predicton = predicton;
    }
    
    public Integer getRoundsWon() {
        return roundsWon;
    }

    public void wonRound() {
        this.roundsWon++;
    }
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException();
        this.hand.add(c);
    }

    /**
     * Retira carta C.
     */
    public void removeCard(Card c) {
        hand.remove(c);
    }

    /**
     * Retira carta em uma certa posição da mão.
     * @param position
     */
    public void removeCard(int position) {
        if (position < 0 || position >= this.hand.size())
            throw new IllegalArgumentException();
        this.hand.remove(position);
    }

    public int getCardCount() {
        return hand.size();
    }

    public Card getCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException();
        return hand.get(position);
    }
    
    public void printHand(){
        for(int i=0;i<hand.size();i++){
            System.out.println(hand.get(i).toString() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Sorts the cards in the hand so that cards of the same value are
     * grouped together.  Cards with the same value are sorted by suit.
     * Note that aces are considered to have the lowest value, 1.
     */
    public void sortByRealValue() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if (c1.getRealValue() < c.getRealValue()) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

}
