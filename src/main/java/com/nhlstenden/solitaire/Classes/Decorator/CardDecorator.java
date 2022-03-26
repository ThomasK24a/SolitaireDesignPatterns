package main.java.com.nhlstenden.solitaire.Classes.Decorator;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Classes.CardPanel;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.EnumMap;

public class CardDecorator {

    EnumMap<Suit, String> suitIconMap = new EnumMap<>(Suit.class);
    EnumMap<Value, String> valueIconMap = new EnumMap<>(Value.class);

    public CardDecorator() {
        fillSuitIconMap();
        fillValueIconMap();
    }

    public void decorateCard(Card card){
    }

    private Color getColorFromSuit(Suit suit){
        //if suit is clubs or spades return black
        if(suit.equals(Suit.CLUB) || suit.equals(Suit.SPADE)) return Color.BLACK;
        //if suit is hearts or diamonds return red
        return Color.RED;
    }

    private void fillSuitIconMap(){
        suitIconMap.put(Suit.CLUB, "");
        suitIconMap.put(Suit.HEART, "");
        suitIconMap.put(Suit.DIAMOND, "");
        suitIconMap.put(Suit.SPADE, "");
    }

    private void fillValueIconMap(){
        valueIconMap.put(Value.THREE, "");
        valueIconMap.put(Value.ACE, "");
        valueIconMap.put(Value.TWO, "");
        valueIconMap.put(Value.FOUR, "");
        valueIconMap.put(Value.FIVE, "");
        valueIconMap.put(Value.SIX, "");
        valueIconMap.put(Value.SEVEN, "");
        valueIconMap.put(Value.EIGHT, "");
        valueIconMap.put(Value.NINE, "");
        valueIconMap.put(Value.TEN, "");
        valueIconMap.put(Value.JACK, "");
        valueIconMap.put(Value.QUEEN, "");
        valueIconMap.put(Value.KING, "");
    }

    private ImageIcon getIcon(String imageName){
        URL imgURL = getClass().getResource("src/resources/card_sprites/" + imageName);
        if(imgURL == null){
            throw new RuntimeException();
        }else{
            return new ImageIcon(imgURL);
        }
    }
}
