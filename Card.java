public class Card 
{
    String suit;
    int value;

    public Card(String suit, int value) 
    {
        this.suit = suit;
        this.value = value;
    }

    public void setSuit(String suit) 
    {
        this.suit = suit;
    }

    public void setValue(int value) 
    {
        this.value = value;
    }

    public String getSuit() 
    {
        return this.suit;
    }

    public int getValue() 
    {
        return this.value;
    }

    public String toString() 
    {
        String info;
        if(this.value == 11)
        {
            info = "Suit: " + this.suit + " | value: Jack";
        }
        else if(this.value == 12)
        {
            info = "Suit: " + this.suit + " | value: Queen";
        }
        else if(this.value == 13)
        {
            info = "Suit: " + this.suit + " | value: King";
        }
        else if(this.value == 1)
        {
            info = "Suit: " + this.suit + " | value: Ace";
        }
        else
        {
            info = "Suit: " + this.suit + " | value: " + this.value;
        }
        return info;
    }
}



