package com.manifestcorp.kata.objects;

public class Hand
{
	private String name;
	private Card card1, card2, card3, card4, card5;
	private Card[] allCards = { card1, card2, card3, card4, card5 };

	public Hand(String name)
	{
		super();
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Card getCard1()
	{
		return card1;
	}

	public void setCard1(Card card1)
	{
		this.card1 = card1;
		this.allCards[0] = card1;

	}

	public Card getCard2()
	{
		return card2;

	}

	public void setCard2(Card card2)
	{
		this.card2 = card2;
		this.allCards[1] = card2;

	}

	public Card getCard3()
	{
		return card3;
	}

	public void setCard3(Card card3)
	{
		this.card3 = card3;
		this.allCards[2] = card3;

	}

	public Card getCard4()
	{
		return card4;
	}

	public void setCard4(Card card4)
	{
		this.card4 = card4;
		this.allCards[3] = card4;

	}

	public Card getCard5()
	{
		return card5;
	}

	public void setCard5(Card card5)
	{
		this.card5 = card5;
		this.allCards[4] = card5;

	}

	public void setAllCards(Card[] allCards)
	{
		this.card1 = allCards[0];
		this.card2 = allCards[1];
		this.card3 = allCards[2];
		this.card4 = allCards[3];
		this.card5 = allCards[4];
		this.allCards = allCards;
	}

	public Card[] getAllCards()
	{
		return allCards;
	}


}
