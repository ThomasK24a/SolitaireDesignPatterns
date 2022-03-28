package main.java.com.nhlstenden.solitaire.Classes.ObjectPool;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Classes.Decorator.CardDecorator;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;
import java.util.Random;

public class CardObjectPool {
    private final ArrayList<ICard> pool;
    private final CardDecorator decorator;

    public CardObjectPool() {
        decorator = new CardDecorator();

        pool = createFullDeck();
    }

    public ArrayList<ICard> getRandomCardStack(int cardStackSize) {
        ArrayList<ICard> cards = new ArrayList<>();

        System.out.println(cardStackSize);
        for (int i = 0; i < cardStackSize; i++) {
            //if pool is empty return the cards gotten so far
            if (pool.size() == 0) return cards;

            cards.add(getRandomCard());
        }

        return cards;
    }

    public ICard getRandomCard() {
        //return null if pool is empty
        if (pool.size() == 0) return null;

        //get a random index of the pool
        Random random = new Random();
        int randomCardIndex = random.nextInt(pool.size());

        ICard card = pool.get(randomCardIndex);

        //remove the card from the pool
        pool.remove(randomCardIndex);

        return card;
    }

    public ArrayList<ICard> getRemainingCards() {
        return getRandomCardStack(pool.size());
    }

    public void returnCard(ICard card) {
        pool.add(card);
    }

    public void returnCards(ArrayList<ICard> cards) {
        pool.addAll(cards);
    }

    private ArrayList<ICard> createFullDeck() {
        ArrayList<ICard> deck = new ArrayList<>();
        for (Suit suit : Suit.class.getEnumConstants()) {
            for (Value value : Value.class.getEnumConstants()) {
                if (!value.equals(Value.NONE)) {
                    deck.add(createDecoratedCard(suit, value));
                }
            }
        }
        return deck;
    }

    private ICard createDecoratedCard(Suit suit, Value value) {
        ICard card = new Card(suit, value, false);
        decorator.decorateCard(card);
        return card;
    }
}
