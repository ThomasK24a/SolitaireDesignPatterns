package main.java.com.nhlstenden.solitaire.Classes.Factory;

import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Classes.ObjectPool.CardObjectPool;

import java.util.ArrayList;

public class BoardFactory {
    private final CardObjectPool cardObjectPool;

    public BoardFactory() {
        this.cardObjectPool = new CardObjectPool();
    }

    public void fillDeck(DeckStack deck) {
        deck.addCards(cardObjectPool.getRemainingCards());
    }

    public void fillBoardStacks(ArrayList<BoardStack> boardStacks) {
        for(int i = 0; i < boardStacks.size(); i++){
            BoardStack boardStack = boardStacks.get(i);
            ArrayList<Card> cardsToAdd = cardObjectPool.getRandomCardStack(i + 1);
            boardStack.addCards(cardsToAdd);
        }
    }
}
