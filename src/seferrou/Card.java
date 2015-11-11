package seferrou;

/**
 *
 * @author victor
 */
public class Card {

    public final static int SPADES = 0;   // 4 nipes + coringa
    public final static int HEARTS = 1;
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;

    public final static int ACE = 1;      // cartas com valores não numéricos
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;

    /**
     * Nipe da carta. Não pode ser alterado.
     */
    private final int suit;

    /**
     * Valor da carta. 1 até 13, não pode ser alterado
     */
    private final int value;

    /**
     * Valor REAL da carta. É útil para se implementar a mecânica do jogo. No
     * nosso caso, por ex, usamos o baralho de truco. Então temos o zap (4 de
     * paus) com um valor diferente dos outros 4.
     */
    private int realValue;
    
    /**
     * Quem jogou a carta. Útil em jogos onde se tem cartas na mesa.
     */
    private int playerId;

    
    /**
     * Cria carta com um valor e um nipe.
     *
     * @param theValue
     * @param theSuit
     */
    public Card(int theValue, int theSuit,int realValue) {
        if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS
                && theSuit != CLUBS) {
            throw new IllegalArgumentException("Naipe inválido!");
        }
        if (theValue < 1 || theValue > 13) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.value = theValue;
        this.suit = theSuit;
        this.realValue=realValue;
    }

    public int getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }
    
    public int getRealValue() {
        return realValue;
    }

    public void setRealValue(int realValue) {
        this.realValue = realValue;
    }
    
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }


    public String getSuitAsString() {
        switch (this.suit) {
            case SPADES:
                return "S";
            case HEARTS:
                return "H";
            case DIAMONDS:
                return "D";
            case CLUBS:
                return "C";
        }
        return null;
    }

    public String getValueAsString() {
        switch (value) {
            case 1:
                return "A";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            default:
                return "K";
        }

    }

    @Override
    public String toString() {
        return getValueAsString() + getSuitAsString();
    }

}
