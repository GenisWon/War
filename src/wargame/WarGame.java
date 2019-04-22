/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wargame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bbgen
 */
public class WarGame {

    Player human = new Player(); // Human Player
    Player computer = new Player(); // Computer Player
    String[] ranks; // Array of card ranks
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        WarGame game = new WarGame();
        game.gameInit(); // Initialize game
        game.gameLoop(); // Begin game
        
    }
    
    public void gameLoop() throws IOException
    {
        List<Card> playField = new ArrayList<>(); // List of cards in play
        Card humanCard; // Card played by human
        Card computerCard; // Card played by computer
        int cardRanking; // Ranking of card comparison
        Scanner sc = new Scanner(System.in); // Allow for user input
        
        while (human.hand.size() != 52 && computer.hand.size() != 52)
        {
            try // If player is out of cards, catch out of bounds exception
            {
                humanCard = human.hand.remove(0); // Take card from beginning of array
                computerCard = computer.hand.remove(0); // Take card from beginning of array
            } catch (IndexOutOfBoundsException error)
            {
                break;
            }
            
            System.out.println("You play the " + humanCard.rank + " of " + humanCard.suit + ".");
            System.out.println("The computer plays the " + computerCard.rank + " of " + computerCard.suit + ".");
            
            playField.add(humanCard); // Add card to playfield
            playField.add(computerCard); // Add card to playfield
            
            cardRanking = rankCompare(humanCard, computerCard); // Determine card's rankings
            switch (cardRanking) {
                case 0: // Human card > Computer card
                    Collections.shuffle(playField); // Shuffle cards being added back to hand
                    human.hand.addAll(playField); // Add won cards to hand
                    playField.clear(); // Delete all cards from playfield list
                    System.out.println("You win the hand!");
                    System.out.println("You now have " + human.hand.size() + " cards.");
                    break;
                case 1: // Computer card > Human card
                    Collections.shuffle(playField); // Shuffle cards being added back to hand
                    computer.hand.addAll(playField); // Add won cards to hand
                    playField.clear(); // Delete all cards from playfield list
                    System.out.println("The computer wins the hand!");
                    System.out.println("You now have " + human.hand.size() + " cards.");
                    break;
                default: // Tie
                    try // Take cards from hands. If player is out of cards, catch out of bounds exception
                    {
                        humanCard = human.hand.remove(0); // Take card from beginning of array
                        computerCard = computer.hand.remove(0); // Take card from beginning of array
                    } catch (IndexOutOfBoundsException error)
                    {
                        break;
                    }
                    playField.add(humanCard); // Add card to playfield
                    playField.add(computerCard); // Add card to playfield
                    System.out.println("War! You each place a card face down, and a card face up.");
                    break;
            }
            
            System.out.println("Press Enter to continue");
            sc.nextLine();// Wait for user input
        }
        
        if (computer.hand.isEmpty())
        {
            System.out.println("Your opponent is out of cards.");
            System.out.println("You Won! Congratulations on your success!");
        }
        else
        {
            System.out.println("You are out of cards.");
            System.out.println("You lost. Try again next time!");
        }
    }
    
    public void gameInit()
    {
        String[] suits = new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
        ranks = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        List<Card> deck = new ArrayList<>();
        
        for (String suit : suits) // Generate 52 card deck
        {
            for (String rank : ranks)
            {
                deck.add(new Card(suit, rank));
            }
        }
        
        Collections.shuffle(deck); // Shuffle deck
        
        for (int i = 0; i < 52; i++) // Deal cards to players
        {
            if (i % 2 == 0)
            {
                human.hand.add(deck.get(i));
            }
            else
            {
                computer.hand.add(deck.get(i));
            }
        }
    }
    
    // 
    public int rankCompare(Card card1, Card card2)
    {
        int card1Ranking = Arrays.asList(ranks).indexOf(card1.rank);
        int card2Ranking = Arrays.asList(ranks).indexOf(card2.rank);
        
        if (card1Ranking > card2Ranking)
        {
            return 0;
        }
        else if (card2Ranking > card1Ranking)
        {
            return 1;
        }
        else // Tie
        {
            return 2;
        }
    }
}
