import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Blackjack {

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("\n********Welcome to Blackjack!********");
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.println("*************************************");

        if(input.hasNextInt())
        {
            choice = input.nextInt();
        }

        if(choice == 1)
        {
            Card deck[] = new Card[52];
            deck = fillDeck(deck);
            deck = shuffleDeck(deck);
            playBlackjack(deck);
        }
        else
        {
            System.exit(0);
        }

    }

    public static Card[] fillDeck(Card[] deck) 
    {
        String[] chooseSuit = {"Clubs", "Spades", "Hearts", "Diamonds"};
        int cardNumber = 0;
        String suit;
        int value;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 1; j <= 13; j++)
            {
                suit = chooseSuit[i];
                value = j;
                Card temp = new Card(suit, value);
                deck[cardNumber] = temp;
                cardNumber++;
            }
        }
        return deck;
    }

    public static Card[] shuffleDeck(Card[] deck) 
    {
        Random rand = new Random();
        String suit;
        int value;

        for(int i = 0; i < 1000; i++)
        {
            int pos1 = rand.nextInt(52);
            int pos2 = rand.nextInt(52);
    
            if(pos1 != pos2)
            {
                suit = deck[pos1].getSuit();
                value = deck[pos1].getValue();
    
                Card temp = new Card(suit, value);
                deck[pos1] = deck[pos2];
                deck[pos2] = temp;
            }
        }
        return deck;
    }

    public static void playBlackjack(Card[] deck)
    {
        Scanner input = new Scanner(System.in);
        Stack<Card> cards = new Stack<>();
        Collections.addAll(cards, deck);
        
        Card[] dealer = new Card[5];
        Card[] player = new Card[5];

        player[0] = cards.pop();
        dealer[0] = cards.pop();
        player[1] = cards.pop();
        dealer[1] = cards.pop();

        System.out.println();
        for(int i = 0; i < 2; i++)
        {
            System.out.println("Player: " + player[i].toString());
            
        }
        boolean pAce = false;
        int pTotal = 0;
        for(Card card : player)
        {
            if(card != null)
            {
                if(card.getValue() > 10)
                {
                    pTotal += 10;
                }
                else if(card.getValue() == 1)
                {
                    pTotal += 1;
                    pAce = true;
                }
                else
                {
                    pTotal += card.getValue();
                }
            }
        }
        if(pAce == true && pTotal+10 <= 21)
        {
            int pTotal2 = pTotal+10;
            System.out.println("Player: " + pTotal + " | " + pTotal2 + "\n");
        }
        else
        {
            System.out.println("Player: " + pTotal + "\n");
        }
        
        for(int k = 0; k < 2; k++)
        {
            if(k == 0)
            {
                System.out.println("Suit: * | value: *");
            }
            else
            {
                System.out.println("Dealer: " + dealer[k].toString());
            }
            
        }
        System.out.println();
        System.out.println("1. Hit\n2. Stand");

        int choice = -1;
        if(input.hasNextInt())
        {
            choice = input.nextInt();
        }

        if(choice == 1)
        {

            for(int j = 2; j < 5; j++)
            {
                player[j] = cards.pop();
                System.out.println("\n" + player[j].toString() + "\n");
                int playerTotal = 0;
                boolean ace2 = false;
                for(Card card : player)
                {
                    if (card != null)
                    {
                        if(card.getValue() > 10)
                        {
                            playerTotal += 10;
                        }
                        else if(card.getValue() == 1)
                        {
                            playerTotal += 1;
                            ace2 = true;
                        }
                        else
                        {
                            playerTotal += card.getValue();
                        }
                    }
                }
                if(playerTotal > 21)
                {
                    System.out.println("\nPlayer total: " + playerTotal + "\n");
                    System.out.println("PLAYER BUSTS!, YOU LOSE!");
                    break;
                }
                if(ace2 == true && playerTotal+10 <= 21)
                {
                    int pTotal2 = playerTotal+10;
                    System.out.println("Player: " + playerTotal + " | " + pTotal2 + "\n");
                }
                else
                {
                    System.out.println("Player: " + playerTotal + "\n");
                }
                System.out.println("1. Hit\n2. Stand");
                choice = input.nextInt();
                if(choice == 2)
                {
                    break;
                }
            }

        }
        if(choice == 2)
        {
            boolean ace = false;
            int playerTotal = 0;
            for(Card card : player)
            {
                if (card != null)
                {
                    if(card.getValue() > 10)
                    {
                        playerTotal += 10;
                    }
                    else if(card.getValue() == 1)
                    {
                        ace = true;
                        playerTotal += 1;
                    }
                    else
                    {
                        playerTotal += card.getValue();
                    }
                    
                }
            }
            if(playerTotal + 10 <= 21 && ace)
            {
                playerTotal = playerTotal+10;
            }

            int dealerTotal = 0;
            for(Card card : dealer)
            {
                if (card != null)
                {
                    if(card.getValue() > 10)
                    {
                        dealerTotal += 10;
                    }
                    else
                    {
                        dealerTotal += card.getValue();
                    }
                    
                }
            }
            
            int l = 2;
            while(dealerTotal < 17 && dealerTotal <= 21 && dealerTotal < playerTotal)
            {
                if(dealer[4] != null)
                {
                    System.out.println("Dealer Wins : " + dealerTotal);
                    break;
                }

                dealer[l] = cards.pop();
                System.out.println(dealer[l].toString());
                if(dealer[l].getValue() > 10)
                {
                    dealerTotal += 10;
                }
                else
                {
                    dealerTotal += dealer[l].getValue();
                }
                l++;
                
            }

            if(dealerTotal > playerTotal && dealerTotal <=21)
            {
                System.out.println("\n****DEALER WINS!****");
                for(Card card : dealer)
                {
                    if(card != null)
                    {
                        System.out.println(card.toString());
                    }
                    
                }
            }
            else if(dealerTotal == playerTotal)
            {
                System.out.println("\n****TIE!****");
                for(Card card : dealer)
                {
                    if(card != null)
                    {
                        System.out.println(card.toString());
                    }
                }
            }
            else if(dealerTotal > 21)
            {
                System.out.println("\n****DEALER BUSTS, YOU WIN!****");
                for(Card card : dealer)
                {
                    if(card != null)
                    {
                        System.out.println(card.toString());
                    }
                }
            }
            else if(playerTotal> dealerTotal && playerTotal <= 21)
            {
                System.out.println("\n****PLAYER WINS!****");
                for(Card card : dealer)
                {
                    if(card != null)
                    {
                        System.out.println(card.toString());
                    }
                }
            }

            System.out.println("\nPlayer: " + playerTotal);
            System.out.println("Dealer: " + dealerTotal + "\n");
        }

    }

    public static void printDeck(Card[] deck)
    {
        for(int i = 0; i < 52; i++)
        {
            System.out.println(deck[i].toString());
        }
    }
}

