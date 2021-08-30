package com.manifestcorp.kata;

import java.util.Scanner;

import com.manifestcorp.kata.logic.Evaluator;

public class Main
{
	public static void main(String[] args)
	{
		// Prompt player for input / game data.
		System.out.print("Please enter your game data in the following exact format:\n\nV = Value: 1, 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A\nS = Suit: C, D, H, S\n\nBlack: VS VS VS VS VS White: VS VS VS VS VS\n");

		// Get player input for evaluation.
		Scanner scanner = new Scanner(System.in);
		String playersHands = scanner.nextLine();

		// Instantiate evaluator (referee) to compare and contrast both hands.
		Evaluator evaluator = new Evaluator();

		// Send input string to evaluator class object for evaluation.
		try
		{
			evaluator.evaluateGame(playersHands);
		}

		catch (Exception e)
		{
			System.out.println("Sorry, something failed while evaluating your hands...\nPlease try again...");
		}
	}
}
