package main.java.com.nhlstenden.solitaire.Classes.Decorator;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Exceptions.ResourceNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.EnumMap;

public class CardDecorator {

    EnumMap<Suit, String> suitIconMap = new EnumMap<>(Suit.class);
    EnumMap<Value, String> valueIconRedMap = new EnumMap<>(Value.class);
    EnumMap<Value, String> valueIconBlackMap = new EnumMap<>(Value.class);

    public CardDecorator() {
        fillSuitIconMap();
        fillValueIconMap("red.png", valueIconRedMap);
        fillValueIconMap("black.png", valueIconBlackMap);
    }

    public void decorateCard(Card card) {

        card.setSuitSprite(getIcon(suitIconMap.get(card.getSuit())));

        if (card.isBlack()) {
            card.setValueSprite(getIcon(valueIconBlackMap.get(card.getValue())));
            return;
        }
        card.setValueSprite(getIcon(valueIconRedMap.get(card.getValue())));


    }

    private Color getColor(Card card) {
        if (card.isBlack()) return Color.BLACK;
        else return Color.RED;
    }

    private void fillSuitIconMap() {
        suitIconMap.put(Suit.HEART, "heart.png");
        suitIconMap.put(Suit.SPADE, "spade.png");
        suitIconMap.put(Suit.DIAMOND, "diamond.png");
        suitIconMap.put(Suit.CLUB, "club.png");
    }

    private void fillValueIconMap(String mapColor, EnumMap<Value, String> valueMap) {
        valueMap.put(Value.ACE, "ace_" + mapColor);
        valueMap.put(Value.TWO, "2_" + mapColor);
        valueMap.put(Value.THREE, "3_" + mapColor);
        valueMap.put(Value.FOUR, "4_" + mapColor);
        valueMap.put(Value.FIVE, "5_" + mapColor);
        valueMap.put(Value.SIX, "6_" + mapColor);
        valueMap.put(Value.SEVEN, "7_" + mapColor);
        valueMap.put(Value.EIGHT, "8_" + mapColor);
        valueMap.put(Value.NINE, "9_" + mapColor);
        valueMap.put(Value.TEN, "10_" + mapColor);
        valueMap.put(Value.JACK, "jack_" + mapColor);
        valueMap.put(Value.QUEEN, "queen_" + mapColor);
        valueMap.put(Value.KING, "king_" + mapColor);
    }

    private ImageIcon getIcon(String imageName) {
        URL imgURL = this.getClass().getClassLoader().getResource("card_sprites/" + imageName);
        if (imgURL == null) {
            throw new ResourceNotFoundException(imageName + "sprite");
        } else {
            return new ImageIcon(imgURL);
        }
    }
}
