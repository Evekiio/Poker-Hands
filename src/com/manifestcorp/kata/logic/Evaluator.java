package com.manifestcorp.kata.logic;

import com.manifestcorp.kata.objects.Card;
import com.manifestcorp.kata.objects.Hand;

public class Evaluator
{
	/* CORE METHODS
	Takes string of game information and converts it 
	into 2 Hand object consisting of a (String) "name" and 5 
	Card objects with 2 variables (Value/Suit)
	*/
	public void evaluateGame(String playersHands)
	{
		// Breakdown player data down into 2 seperate strings.
		int blackHandIndex = playersHands.indexOf("Black:");
		int whiteHandIndex = playersHands.indexOf("White:");

		// Split input string, player1 = black index to white index, player2 = white index to end.
		String player1 = playersHands.substring(blackHandIndex, whiteHandIndex);
		String player2 = playersHands.substring(whiteHandIndex);

		// Remove the colon char from strings to avoid instantiating it within the hand objects. 
		player1 = player1.replace(":", "");
		player2 = player2.replace(":", "");

		// Convert player string hands into array hands for instatiation of hand objects (Separator = White-Space : Regex \\s+).
		String[] p1 = player1.split("\\s+");
		String[] p2 = player2.split("\\s+");

		// Generate the player hand objects.
		Hand player1Hand = createHand(p1);
		Hand player2Hand = createHand(p2);

		// Sort Player Hands by Value (Bubble-Sort : Not Efficient in terms of Big-O, but works for this.)
		player1Hand = sortHand(player1Hand);
		player2Hand = sortHand(player2Hand);

		int player1Value = scoreHand(player1Hand);
		int player2Value = scoreHand(player2Hand);

		finalCheck(player1Hand, player2Hand, player1Value, player2Value);

	}

	// Final Condition check for determining winner based on tie values and print results to console.
	public void finalCheck(Hand inputHand1, Hand inputHand2, int val1, int val2)
	{

		if (val1 > val2)
		{
			switch (val1)
			{
			case 8:
				System.out.println(inputHand1.getName() + " wins with a straight flush! High Card: " + cardConversion(inputHand1.getCard5().getValue()));
				break;
			case 7:
				System.out.println(inputHand1.getName() + " wins with four of a kind! Four of a Kind Value: " + cardConversion(inputHand1.getCard3().getValue()));
				break;
			case 6:
				System.out.println(inputHand1.getName() + " wins with a full house! Three of a Kind Value: " + cardConversion(inputHand1.getCard3().getValue()));
				break;
			case 5:
				System.out.println(inputHand1.getName() + " wins with a flush! High Card: " + cardConversion(inputHand1.getCard5().getValue()));
				break;
			case 4:
				System.out.println(inputHand1.getName() + " wins with a straight! High Card: " + cardConversion(inputHand1.getCard5().getValue()));
				break;
			case 3:
				System.out.println(inputHand1.getName() + " wins with three of a kind! Three of a Kind Value: " + cardConversion(inputHand1.getCard3().getValue()));
				break;
			case 2:

				if (inputHand1.getCard4().getValue() == inputHand1.getCard5().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with two pair! Two Pair Value: " + cardConversion(inputHand1.getCard5().getValue()));
				}

				else if (inputHand1.getCard3().getValue() == inputHand1.getCard4().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with two pair! Two Pair Value: " + cardConversion(inputHand1.getCard3().getValue()));
				}
				break;
			case 1:

				if (inputHand1.getCard5().getValue() == inputHand1.getCard4().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand1.getCard5().getValue()));
				}

				else if (inputHand1.getCard4().getValue() == inputHand1.getCard3().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand1.getCard4().getValue()));
				}

				else if (inputHand1.getCard3().getValue() == inputHand1.getCard2().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand1.getCard3().getValue()));
				}

				else if (inputHand1.getCard2().getValue() == inputHand1.getCard1().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand1.getCard2().getValue()));
				}
				break;
			}
		}

		else if (val2 > val1)
		{
			switch (val2)
			{
			case 8:
				System.out.println(inputHand2.getName() + " wins with a straight flush! High Card: " + cardConversion(inputHand2.getCard5().getValue()));
				break;
			case 7:
				System.out.println(inputHand2.getName() + " wins with four of a kind! Four of a Kind Value: " + cardConversion(inputHand2.getCard3().getValue()));
				break;
			case 6:
				System.out.println(inputHand2.getName() + " wins with a full house! Three of a Kind Value: " + cardConversion(inputHand2.getCard3().getValue()));
				break;
			case 5:
				System.out.println(inputHand2.getName() + " wins with a flush! High Card: " + cardConversion(inputHand2.getCard5().getValue()));
				break;
			case 4:
				System.out.println(inputHand2.getName() + " wins with a straight! High Card: " + cardConversion(inputHand2.getCard5().getValue()));
				break;
			case 3:
				System.out.println(inputHand2.getName() + " wins with three of a kind! Three of a Kind Value: " + cardConversion(inputHand2.getCard3().getValue()));
				break;
			case 2:

				if (inputHand2.getCard4().getValue() == inputHand2.getCard5().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with two pair! Two Pair Value: " + cardConversion(inputHand2.getCard5().getValue()));
				}

				else if (inputHand2.getCard3().getValue() == inputHand2.getCard4().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with two pair! Two Pair Value: " + cardConversion(inputHand2.getCard3().getValue()));
				}
				break;
			case 1:

				if (inputHand2.getCard5().getValue() == inputHand2.getCard4().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand2.getCard5().getValue()));
				}

				else if (inputHand2.getCard4().getValue() == inputHand2.getCard3().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand2.getCard4().getValue()));
				}

				else if (inputHand2.getCard3().getValue() == inputHand2.getCard2().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand2.getCard3().getValue()));
				}

				else if (inputHand2.getCard2().getValue() == inputHand1.getCard1().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with one pair! Pair Value: " + cardConversion(inputHand2.getCard2().getValue()));
				}
				break;
			}
		}

		else if (val1 == val2)
		{
			switch ((val1 + val2) / 2)
			{
			case 8:
				if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with a straight flush! High Card: " + cardConversion(inputHand1.getCard5().getValue()));
				}

				else
				{
					System.out.println(inputHand2.getName() + " wins with a straight flush! High Card: " + cardConversion(inputHand2.getCard5().getValue()));
				}
				break;
			case 7:
				if (inputHand1.getCard3().getValue() > inputHand2.getCard3().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with four of a kind! Four of a Kind Value: " + cardConversion(inputHand1.getCard3().getValue()));
				}

				else
				{
					System.out.println(inputHand2.getName() + " wins with four of a kind! Four of a Kind Value: " + cardConversion(inputHand2.getCard3().getValue()));
				}
				break;
			case 6:
				if (inputHand1.getCard3().getValue() > inputHand2.getCard3().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with a full house! Three of a Kind Value: " + cardConversion(inputHand1.getCard3().getValue()));
				}

				else
				{
					System.out.println(inputHand2.getName() + " wins with a full house! Three of a Kind Value: " + cardConversion(inputHand2.getCard3().getValue()));
				}
				break;
			case 5:
				int results = highCardCheck(inputHand1, inputHand2);

				if (results == 1)
				{
					System.out.println(inputHand1.getName() + " wins with a flush! High Card: " + cardConversion(inputHand1.getCard5().getValue()));
				}

				else if (results == 2)
				{
					System.out.println(inputHand2.getName() + " wins with a flush! High Card: " + cardConversion(inputHand2.getCard5().getValue()));
				}
				break;
			case 4:
				if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with a straight! High Card: " + cardConversion(inputHand1.getCard5().getValue()));
				}

				else if (inputHand2.getCard5().getValue() > inputHand1.getCard5().getValue())
				{
					System.out.println(inputHand2.getName() + " wins with a straight! High Card: " + cardConversion(inputHand2.getCard5().getValue()));
				}
				break;
			case 3:
				if (inputHand1.getCard3().getValue() > inputHand2.getCard3().getValue())
				{
					System.out.println(inputHand1.getName() + " wins with three of a kind! Three of a Kind Value: " + cardConversion(inputHand1.getCard3().getValue()));
				}

				else
				{
					System.out.println(inputHand2.getName() + " wins with three of a kind! Three of a Kind Value: " + cardConversion(inputHand2.getCard3().getValue()));
				}

				break;
			case 2:

				int player1Pair = 0;
				int player2Pair = 0;

				if (inputHand1.getCard2().getValue() == inputHand1.getCard3().getValue() && inputHand1.getCard4().getValue() == inputHand1.getCard5().getValue())
				{
					player1Pair = inputHand1.getCard5().getValue();
				}

				else if (inputHand1.getCard1().getValue() == inputHand1.getCard2().getValue() && inputHand1.getCard3().getValue() == inputHand1.getCard4().getValue())
				{
					player1Pair = inputHand1.getCard4().getValue();
				}

				if (inputHand2.getCard2().getValue() == inputHand2.getCard3().getValue() && inputHand2.getCard4().getValue() == inputHand2.getCard5().getValue())
				{
					player2Pair = inputHand2.getCard5().getValue();
				}

				else if (inputHand2.getCard1().getValue() == inputHand2.getCard2().getValue() && inputHand2.getCard3().getValue() == inputHand2.getCard4().getValue())
				{
					player2Pair = inputHand2.getCard4().getValue();
				}

				if (player1Pair > player2Pair)
				{
					System.out.println(inputHand1.getName() + " wins with two pair! Two Pair Value: " + cardConversion(inputHand1.getCard4().getValue()));
				}

				else if (player2Pair > player1Pair)
				{
					System.out.println(inputHand2.getName() + " wins with two pair! Two Pair Value: " + cardConversion(inputHand2.getCard4().getValue()));
				}

				else if (player1Pair == player2Pair)
				{
					if (inputHand1.getCard2().getValue() > inputHand2.getCard2().getValue())
					{
						System.out.println(inputHand1.getName() + " wins with two pair! Two Pair Value (2nd Pair): " + cardConversion(inputHand1.getCard2().getValue()));
					}

					else if (inputHand2.getCard2().getValue() > inputHand1.getCard2().getValue())
					{
						System.out.println(inputHand2.getName() + " wins with two pair! Two Pair Value (2nd Pair): " + cardConversion(inputHand2.getCard2().getValue()));
					}

					else
					{
						if (inputHand1.getCard1().getValue() > inputHand2.getCard1().getValue() || inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println(inputHand1.getName() + " wins with two pair!");
						}

						else
						{
							System.out.println(inputHand2.getName() + " wins with two pair!");
						}
					}

				}

				break;
			case 1:

				int player1PairLocation = findPair(inputHand1);
				int player2PairLocation = findPair(inputHand2);

				if (player1PairLocation == 2 && player2PairLocation == 2)
				{
					if (inputHand1.getCard2().getValue() > inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard2().getValue() < inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard5().getValue() < inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard4().getValue() > inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard4().getValue() < inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard3().getValue() > inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard3().getValue() < inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}

				else if (player1PairLocation == 2 && player2PairLocation == 3)
				{
					if (inputHand1.getCard2().getValue() > inputHand2.getCard3().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard2().getValue() < inputHand2.getCard3().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard5().getValue() < inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard4().getValue() > inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard4().getValue() < inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard3().getValue() > inputHand2.getCard1().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard3().getValue() < inputHand2.getCard1().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}

				else if (player1PairLocation == 2 && player2PairLocation == 4)
				{
					if (inputHand1.getCard2().getValue() > inputHand2.getCard4().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard2().getValue() < inputHand2.getCard4().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard5().getValue() < inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard4().getValue() > inputHand2.getCard2().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard4().getValue() < inputHand2.getCard2().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard3().getValue() > inputHand2.getCard1().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard3().getValue() < inputHand2.getCard1().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}

				else if (player1PairLocation == 2 && player2PairLocation == 5)
				{
					if (inputHand1.getCard2().getValue() > inputHand2.getCard5().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard2().getValue() < inputHand2.getCard5().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard5().getValue() > inputHand2.getCard3().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard5().getValue() < inputHand2.getCard3().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard4().getValue() > inputHand2.getCard2().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard4().getValue() < inputHand2.getCard2().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard3().getValue() > inputHand2.getCard1().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard3().getValue() < inputHand2.getCard1().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}

				else if (player1PairLocation == 3 && player2PairLocation == 2)
				{
					if (inputHand1.getCard3().getValue() > inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard3().getValue() < inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard5().getValue() < inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard4().getValue() > inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard4().getValue() < inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard1().getValue() > inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard1().getValue() < inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}

				else if (player1PairLocation == 4 && player2PairLocation == 2)
				{
					if (inputHand1.getCard4().getValue() > inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard4().getValue() < inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard5().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard5().getValue() < inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard2().getValue() > inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard2().getValue() < inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard1().getValue() > inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard1().getValue() < inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}

				else if (player1PairLocation == 5 && player2PairLocation == 2)
				{
					if (inputHand1.getCard5().getValue() > inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 1 wins");
					} else if (inputHand1.getCard5().getValue() < inputHand2.getCard2().getValue())
					{
						System.out.println("PLayer 2 wins");
					} else
					{
						if (inputHand1.getCard3().getValue() > inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 1 wins");
						} else if (inputHand1.getCard3().getValue() < inputHand2.getCard5().getValue())
						{
							System.out.println("PLayer 2 wins");
						} else
						{
							if (inputHand1.getCard2().getValue() > inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 1 wins");
							} else if (inputHand1.getCard2().getValue() < inputHand2.getCard4().getValue())
							{
								System.out.println("PLayer 2 wins");
							} else
							{
								if (inputHand1.getCard1().getValue() > inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 1 wins");
								} else if (inputHand1.getCard1().getValue() < inputHand2.getCard3().getValue())
								{
									System.out.println("PLayer 2 wins");
								} else
								{
									System.out.println("Tie");
								}
							}
						}
					}
				}


				break;
			case 0:
				Card[] cards1 = inputHand1.getAllCards();
				Card[] cards2 = inputHand2.getAllCards();

				if (highCardCheck(inputHand1, inputHand2) == 1)
				{
					System.out.println(inputHand1.getName() + " wins with high card! High Card: " + cardConversion(highCardCheckReturnInd(inputHand1, inputHand2)));
				}

				else if (highCardCheck(inputHand1, inputHand2) == 2)
				{
					System.out.println(inputHand2.getName() + " wins with high card! High Card: " + cardConversion(highCardCheckReturnInd(inputHand1, inputHand2)));
				}

				else
				{
					System.out.println("Tie");
				}
				break;
			}
		}
	}

	/*
	 *  CONVERSION METHODS (VALUE TO STRING, 14 >> ACE, 13 >> KING, ETC)
	 */
	public String cardConversion(int inputValue)
	{
		String cardName = "";

		switch (inputValue)
		{
		case 14:
			cardName = "Ace";
			return cardName;

		case 13:
			cardName = "King";
			return cardName;

		case 12:
			cardName = "Queen";
			return cardName;
		case 11:
			cardName = "Jack";
			return cardName;
		default:
			cardName = String.valueOf(inputValue);
			return cardName;
		}
	}

	public Hand createHand(String[] inputArr)
	{
		Hand playerHand = new Hand(inputArr[0]);

		for (int i = 1; i < inputArr.length; i++)
		{
			Card card = new Card();

			// Change Value from Char to Int
			switch (inputArr[i].charAt(0))
			{
			case 'T':
				card.setValue(10);
				break;
			case 'J':
				card.setValue(11);
				break;
			case 'Q':
				card.setValue(12);
				break;
			case 'K':
				card.setValue(13);
				break;
			case 'A':
				card.setValue(14);
				break;
			default:
				card.setValue(inputArr[i].charAt(0) - '0');
			}

			// Change Suit from Char to Int
			switch (inputArr[i].charAt(1))
			{
			case 'C':
				card.setSuit(1);
				break;
			case 'D':
				card.setSuit(2);
				break;
			case 'H':
				card.setSuit(3);
				break;
			case 'S':
				card.setSuit(4);
				break;
			}

			switch (i)
			{
			case 1:
				playerHand.setCard1(card);
				break;
			case 2:
				playerHand.setCard2(card);
				break;
			case 3:
				playerHand.setCard3(card);
				break;
			case 4:
				playerHand.setCard4(card);
				break;
			case 5:
				playerHand.setCard5(card);
				break;
			}
		}
		return playerHand;
	}

	/*
	 *  UTILITY METHODS (SORT, SUIT MATCH, CONSECUTIVE, DUPLICATES)
	 */
	// Sort the hand for organized conditional checks.
	public Hand sortHand(Hand inputHand)
	{
		Card[] cardArr = inputHand.getAllCards();

		for (int i = 0; i < cardArr.length; i++)
		{
			for (int j = i + 1; j < cardArr.length; j++)
			{
				if (cardArr[i].getValue() > cardArr[j].getValue())
				{
					Card tempCard = cardArr[j];
					cardArr[j] = cardArr[i];
					cardArr[i] = tempCard;
				}
			}
		}
		inputHand.setAllCards(cardArr);

		return inputHand;
	}

	// Checks if all 5 suits match in a single hand.
	public boolean ifSuitsMatch(Hand inputHand)
	{
		if (inputHand.getCard1().getSuit() == inputHand.getCard2().getSuit() && inputHand.getCard2().getSuit() == inputHand.getCard3().getSuit() && inputHand.getCard3().getSuit() == inputHand.getCard4().getSuit()
				&& inputHand.getCard4().getSuit() == inputHand.getCard5().getSuit())
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	// Checks if hand is in consecutive order (Must sort hand first to avoid issues).
	public boolean ifValuesAreConsecutive(Hand inputHand)
	{
		int value = 0;

		Card[] cards = inputHand.getAllCards();

		for (int i = 4; i > 0; i--)
		{
			value = (cards[i].getValue() - cards[i - 1].getValue()) + value;
		}

		if (value == 4)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	// Count pairs and return value based on results. 
	public int countMatches(Hand inputHand)
	{
		Card[] cards = inputHand.getAllCards();
		int count = 0;

		for (int i = 0; i < cards.length; i++)
		{
			for (int j = i + 1; j < cards.length; j++)
			{
				if (cards[i].getValue() == cards[j].getValue() && i != j)
				{
					count++;
				}
			}
		}


		return count;
	}

	// Simply compares if a single card value is higher than another. (Returns 1 for Card 1 Higher, 2 for card 2 Higher, and 0 for Equal)
	// Compare 2 cards (Which is greater...)
	public int compareCards(Card card1, Card card2)
	{
		if (card1.getValue() > card2.getValue())
		{
			return 1;
		} else if (card2.getValue() > card1.getValue())
		{
			return 2;
		} else
		{
			return 0;
		}
	}

	// Conditions for scoring overall hand values prior to compairing player 1 to player 2 for winning calculations.
	public int scoreHand(Hand inputHand)
	{
		int count = countMatches(inputHand);

		// STRAIGHT FLUSH
		if (ifValuesAreConsecutive(inputHand) && ifSuitsMatch(inputHand))
		{
			return 8;
		}

		// FOUR OF A KIND
		else if (count == 6)
		{
			return 7;
		}

		// FULL HOUSE
		else if ((count == 4 && inputHand.getCard1().getValue() == inputHand.getCard2().getValue()) || count == 4 && inputHand.getCard4().getValue() == inputHand.getCard5().getValue())
		{
			return 6;
		}

		// FLUSH
		else if (ifSuitsMatch(inputHand) && !ifValuesAreConsecutive(inputHand))
		{
			return 5;
		}

		// STRAIGHT
		else if (ifValuesAreConsecutive(inputHand) && !ifSuitsMatch(inputHand))
		{
			return 4;
		}

		// 3 OF A KIND
		else if (count == 3)
		{
			return 3;
		}

		// TWO PAIR
		else if (count == 2)
		{
			return 2;
		}

		// ONE PAIR
		else if (count == 1)
		{
			return 1;
		}

		return 0;


	}

	public int highCardCheck(Hand hand1, Hand hand2)
	{
		Card[] card1 = hand1.getAllCards();
		Card[] card2 = hand2.getAllCards();

		for (int i = 4; i > 0; i--)
		{
			if (card1[i].getValue() > card2[i].getValue() && card1[i].getValue() != card2[i].getValue())
			{
				return 1;
			}

			else if (card2[i].getValue() > card1[i].getValue() && card1[i].getValue() != card2[i].getValue())
			{
				return 2;
			}
		}

		return 0;
	}

	public int highCardCheckReturnInd(Hand hand1, Hand hand2)
	{
		Card[] card1 = hand1.getAllCards();
		Card[] card2 = hand2.getAllCards();

		for (int i = 4; i > 0; i--)
		{
			if (card1[i].getValue() > card2[i].getValue() && card1[i].getValue() != card2[i].getValue())
			{
				return card1[i].getValue();
			}

			else if (card2[i].getValue() > card1[i].getValue() && card1[i].getValue() != card2[i].getValue())
			{
				return card2[i].getValue();
			}
		}

		return 0;
	}

	public int findPair(Hand inputHand)
	{
		if (inputHand.getCard1().getValue() == inputHand.getCard2().getValue())
		{
			return 2;
		}

		else if (inputHand.getCard2().getValue() == inputHand.getCard3().getValue())
		{
			return 3;
		}

		else if (inputHand.getCard3().getValue() == inputHand.getCard4().getValue())
		{
			return 4;
		}

		else
		{
			return 5;
		}
	}

	public void highRemainingCheck(Hand hand1, Hand hand2)
	{
		Card[] cards1 = hand1.getAllCards();
		Card[] cards2 = hand2.getAllCards();


		for (int i = 0; i < cards1.length; i++)
		{

		}

	}
}

