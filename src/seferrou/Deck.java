package seferrou;

/**
 *
 * @author victor
 */
public class Deck {

    private Card[] deck;

    private int cardsUsed;

    /**
     * Constroi um baralho de truco. As cartas são criadas em ordem. Devemos
     * embaralhar antes de usar o baralho.
     */
    public Deck() {
        this(false);
    }

    /**
     * Constroi um baralho normal.
     *
     * @param fullDeck se verdadeiro, constroi o baralho inteiro o que pode ser
     * útil futuramente. caso contrário, constrói baralho de truco
     */
    public Deck(boolean fullDeck) {
        if (fullDeck) {
            this.deck = new Card[52];
        } else //baralho de truco (sem 8's, 9's e 10's)
        {
            this.deck = new Card[40];
        }
        int cardCt = 0;
        int realValue = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int value = 1; value <= 13; value++) {
                if (!fullDeck && (value == 8 || value == 9 || value == 10)) {
                    continue;
                }
                switch (value) {
                    case 3:
                        realValue = 5;
                        break;
                    case 2:
                        realValue = 6;
                        break;
                    case 1:
                        if (suit == 0) {
                            realValue = 3; //espadilha
                        } else {
                            realValue = 7;
                        }
                        break;
                    case 7:
                        if (suit == 1) {
                            realValue = 2; // 7 de copas
                        } else {
                            if (suit == 2) {
                                realValue = 4; //7 de ouros
                            } else {
                                realValue = 8;
                            }
                        }
                        break;
                    case 6:
                        realValue = 9;
                        break;
                    case 5:
                        realValue = 10;
                        break;
                    case 4:
                        if (suit == 3) {
                            realValue = 1;
                        } else {
                            realValue = 11;
                        }
                        break;
                }
                deck[cardCt] = new Card(value, suit, realValue);
                cardCt++;
            }
        }
        this.cardsUsed = 0;
    }

    /**
     * Put all the used cards back into the deck (if any), and shuffle the deck
     * into a random order.
     */
    public void shuffle() {
        for (int i = this.deck.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        this.cardsUsed = 0;
    }

    public int cardsLeft() {
        return this.deck.length - this.cardsUsed;
    }

    /**
     * Retorna a prox carta disponível no baralho.
     *
     * @return
     */
    public Card dealCard() {
        if (this.cardsUsed == this.deck.length) {
            throw new IllegalStateException("O baralho acabou.");
        }
        this.cardsUsed++;
        return this.deck[this.cardsUsed - 1];
        // Programming note:  Cards are not literally removed from the array
        // that represents the deck.  We just keep track of how many cards
        // have been used.
    }
}
