// Important Links
// Github: https://github.com/CapitanAcGuy/Soulbound-Calculator

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class SoulboundCalculator 
{
   public static void main(String[] args) 
   {
      // Create a Scanner  
      Scanner keyboard = new Scanner(System.in);
            
      // Welcome Message
      System.out.println("---------------------------------------------------------------------------------------------");  
      System.out.println("                             Welcome to the Soulbound Calculator                             ");
      System.out.println("---------------------------------------------------------------------------------------------");
      
      // Expected Number of Attack Dice
      System.out.println("\n");
      int attack;
      System.out.println("Expected Number of Attack Dice (1-20):");
      do
      {     
         while (!keyboard.hasNextInt()) // Checks to see if keyboard.next is an Int
         {
            String input = keyboard.next();
            System.out.println("Input must be a whole number between 1-20. Please try again.");
         }
         attack = keyboard.nextInt();
         if (attack < 1 || attack > 20) // Validation
         {
            System.out.println("Input must be a whole number between 1-20. Please try again.");
         }
      } while (attack < 1 || attack > 20);
      
      // Expected Attack Value
      System.out.println("\n");
      int av;
      System.out.println("Expected Attack Value? \n 1. Poor \n 2. Average \n 3. Good \n 4. Great \n 5. Superb \n 6. Extraordinary");
      do
      {     
         while (!keyboard.hasNextInt()) // Checks to see if keyboard.next is an Int
         {
            String input = keyboard.next();
            System.out.println("Input must be a whole number between 1-6. Please try again.");
         }
         av = keyboard.nextInt();
         if (av < 1 || av > 6) // Validation
         {
            System.out.println("Input must be a whole number between 1-6. Please try again.");
         }
      } while (av < 1 || av > 6);
      String avFeedback = convertValue(av); // Calls convertValue to convert to appropriate String for Results (see bottom)

      // Expected Defense Value
      System.out.println("\n");
      int dv;
      System.out.println("Expected Defense Value? \n 1. Poor \n 2. Average \n 3. Good \n 4. Great \n 5. Superb \n 6. Extraordinary");
      do
      {     
         while (!keyboard.hasNextInt()) // Checks to see if keyboard.next is an Int
         {
            String input = keyboard.next();
            System.out.println("Input must be a whole number between 1-6. Please try again.");
         }
         dv = keyboard.nextInt();
         if (dv < 1 || dv > 6) // Validation
         {
            System.out.println("Input must be a whole number between 1-6. Please try again.");
         }
      } while (dv < 1 || dv > 6);
      String dvFeedback = convertValue(dv); // Calls convertValue to convert to appropriate String for Results (see bottom)
       
      // Calls calcSNum to determine Success Number
      int sNum = calcSNum(av, dv); 
      
      // Rolls a number of d6 dice = "attack" and stores it in "results"
      Random roll = new Random();
      int[] results = new int[attack];
      for (int i = 0; i < attack; i++)
      {
         results[i] = roll.nextInt(6) +1;
      }
      int[] cResults = results.clone(); // Used for Results (see bottom)
      
      // Expected Focus Value
      System.out.println("\n");
      int focus;
      int cFocus;
      System.out.println("Expected Focus Value? (0-6)");
      do
      {     
         while (!keyboard.hasNextInt()) // Checks to see if keyboard.next is an Int
         {
            String input = keyboard.next();
            System.out.println("Input must be a whole number between 0-6. Please try again.");
         }
         focus = keyboard.nextInt();
         cFocus = focus; // Used for Results (see bottom)
         if (focus < 0 || focus > 6) // Validation
         {
            System.out.println("Input must be a whole number between 0-6. Please try again.");
         }
      } while (focus < 0 || focus > 6);

      // Applies "focus" to "results" to meet "sNum" min value
      int largest;
      int position = 0;
      while (focus > 0) // keeps looping till "focus" = 0
      {
         largest = 0; // Resets "largest" at the beginning of every loop
         for (int x = 0; x < attack; x++) // Scans "results" for largest value < sNum
         {
            if (results[x] > largest && results[x] < sNum)
            {
               largest = results[x];
               position = x;
            }
         }
         if (results[position] < sNum) // Doesn't apply Focus if "largest" = "sNum"
         {
            results[position]++;
         }
         focus--;
      }
      
      // Tallies "results" that meet "sNum" min value and adds to running Total Damage
      int tDam = 0;
      for (int a = 0; a < attack; a++)
      {
         if (results[a] >= sNum)
         {
            tDam++;
         }
      }
      
      // Expected Weapon Damage Value
      System.out.println("\n");
      int wDam;
      System.out.println("Expected Weapon Damage Value? (0-9)");
      do
      {     
         while (!keyboard.hasNextInt()) // Checks to see if keyboard.next is an Int
         {
            String input = keyboard.next();
            System.out.println("Input must be a whole number between 0-9. Please try again.");
         }
         wDam = keyboard.nextInt();
         if (wDam < 0 || wDam > 9) // Validation
         {
            System.out.println("Input must be a whole number between 0-9. Please try again.");
         }
      } while (wDam < 0 || wDam > 9);

      // Expected Armor Value
      System.out.println("\n");
      int armor;
      System.out.println("Expected Armor Value? (0-9)");
      do
      {     
         while (!keyboard.hasNextInt()) // Checks to see if keyboard.next is an Int
         {
            String input = keyboard.next();
            System.out.println("Input must be a whole number between 0-9. Please try again.");
         }
         armor = keyboard.nextInt();
         if (armor < 0 || armor > 9) // Validation
         {
            System.out.println("Input must be a whole number between 0-9. Please try again.");
         }
      } while (armor < 0 || armor > 9);
  
      // Applies modifiers to running Total Damage
      int fDam = Math.max(((tDam + wDam) - armor), 0); // Prevents Values < 0 (for Results)
      
      // Calls calcAverage to determine Expected Average Damage
      float average = calcAverage(attack, sNum, focus, wDam, armor);
      
      // Results
      System.out.println("\n");
      System.out.println("---------------------------------------------------------------------------------------------");
      System.out.println("                        Thank you for using the Soulbound Calculator!                        ");
      System.out.println("---------------------------------------------------------------------------------------------");     
      System.out.println("\n"); 
      System.out.println("Ladder:");
      System.out.println("---------------------------------------------------------------------------------------------"); 
      System.out.println("Attack Value: " + avFeedback);
      System.out.println("Defense Value: " + dvFeedback);
      System.out.println("Success Number: " + sNum);
      System.out.println("\n"); 
      System.out.println("Dice Roll Results:");
      System.out.println("---------------------------------------------------------------------------------------------"); 
      System.out.println("Number of Attack Dice: " + attack);
      System.out.println("Focus Value: " + cFocus);
      System.out.println("Dice Roll Results (Focus OFF):  " + Arrays.toString(cResults));
      System.out.println("Dice Roll Results (Focus ON):   " + Arrays.toString(results));
      System.out.println("Number of Successes: " + tDam);
      System.out.println("\n"); 
      System.out.println("Modifiers:");
      System.out.println("---------------------------------------------------------------------------------------------");  
      System.out.println("Weapon Damage Value: " + wDam);
      System.out.println("Armor Value: " + armor);
      System.out.println("\n");
      System.out.println("Final Damage Results:");
      System.out.println("---------------------------------------------------------------------------------------------");      
      System.out.println("Final Damage: " + fDam);
      System.out.println("Average Expected Damage: " + average);
      System.out.println("\n");
      System.out.println("---------------------------------------------------------------------------------------------");  
      System.out.println("                                    Enter Any Key to Exit                                    ");
      System.out.println("---------------------------------------------------------------------------------------------");   
      String input = keyboard.next();
   }   
   public static String convertValue(int value) // Converts Attack Value and Defense Value from Int to appropriate String for Results
   {
      String feedback = "";
      if (value == 1)
      {
         feedback = "Poor";
      }
      else if (value == 2)
      {
         feedback = "Average";
      }
      else if (value == 3)
      {
         feedback = "Good";
      }
      else if (value == 4)
      {
         feedback = "Great";
      }
      else if (value == 5)
      {
         feedback = "Superb";
      }
      else if (value == 6)
      {
         feedback = "Extraordinary";
      }
      return feedback;
   }
   public static int calcSNum(int av, int dv) // Attack Value and Defense Value are compared to determine Success Number
   {
      int success = 0;
      if (av > dv)
      {
         if (av - dv >= 2) // AV is 2 higher than DV
         {
            success = 2;
         }
         else if (av - dv == 1) // AV is 1 higher than DV
         {
            success = 3;
         }
      }
      else if (av == dv) // AV = DV
      {
         success = 4;
      }
      else if (dv > av)
      {
         if (dv - av >= 2) // AV is 2 lower than DV
         {
            success = 6;
         }
         else if (dv - av == 1) // AV is 1 lower than DV
         {
            success = 5;
         }
      }
      return success;   
   }
   public static float calcAverage(int attack, int sNum, int focus, int wDam, int armor) // Calculates Average Damage
   {
      float fDam = 0;
      float gTotal = 0;
      int numRuns = 1000; // The number of tests that calcAverage will use to test Average Damage
      for (int i = 0; i < numRuns; i++)
      {
         // Rolls a number of d6 dice = "attack" and stores it in "results"
   		Random roll = new Random();
    	   int[] results = new int[attack];
    	   for (int x = 0; x < attack; x++)
    	   {
    		   results[x] = roll.nextInt(6)+1;
    	   }
    
    	   // Applies "focus" to "results" to meet "sNum" min value
      	int largest;
      	int position = 0;
      	while (focus > 0) // keeps looping till "focus" = 0
      	{
         	largest = 0; // Resets "largest" at the beginning of every loop
         	for (int y = 0; y < attack; y++) // Scans "results" for largest value < sNum
         	{
            	if (results[y] > largest && results[y] < sNum)
            	{
               	largest = results[y];
               	position = y;
            	}
         	}
         	if (results[position] < sNum) // Doesn't apply Focus if "largest" = "sNum"
         	{
            	results[position]++;
         	}
         	focus--;
      	}
    
    	   // Tallies "results" that meet "sNum" min value and adds to running Total Damage
    	   int tDam = 0;
      	for (int z = 0; z < attack; z++)
      	{
         	if (results[z] >= sNum)
         	{
            	tDam++;
         	}
      	}
         // Applies modifiers to running Total Damage
    	   fDam = Math.max(((tDam + wDam) - armor), 0);
        
         // Adds "fDam" to running Grand Total Damage value
         gTotal = gTotal + fDam;
    }
    // Averages "gTotal" based off 100 rolls
    gTotal = gTotal/numRuns;
    
    return gTotal;
  }
}