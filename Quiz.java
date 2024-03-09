/* ***************************************
  @author    Lim Jie Ying
  @SID       230417942
  @date      14th November 2023
  @version   1

    Miniproject Level 8
    A program that allows multiple users to participate in a quiz.
   ****************************************/

import java.util.Scanner; // to import scanner
import java.io.*;

// creates a new type called player
class player
{
    String name;
    int score;
}//End player

class Quiz
{
    public static void main (String [] a) throws IOException
    {
        welcomerules();
        askQuiz();
        
        return;
    } // END main

    //method to ask the options 
    public static void askQuiz() throws IOException
    {
        int how_many_players = inputInt("How many players?");
        int counter = 0;
        int optionNumber;
        player [] players = new player[how_many_players];

        for (int i = 0; i <how_many_players; i++)
        {
            players[i] = setPlayer("PLAYER DID NOT PARTICIPATE!!", 0);
        }
    
        listOptions();

        optionNumber = inputInt("Select an option [1-3]:");
    
        while(optionNumber != 3 )
        {
        
            if (optionNumber == 1)
            {  
                players[counter] = askPlayer();
                counter = counter + 1;
            }
            else if(optionNumber == 2)
            {
                print_all_scores(players, how_many_players);
            } 
                 
            else
            {
                print("Please input a value from 1 to 3.");
            }

            optionNumber = inputInt("Select an option [1-3]:");
        }
        
        findWinners(players, how_many_players);
        askPrintscores(players, how_many_players);
    
        return;
    }

    //method to ask the players if they want a copy of the scores or not 
    public static void askPrintscores(player p[], int how_many_players ) throws IOException
    {
        String toPrint = "E";
                
        while (!toPrint.equals("y") || !toPrint.equals("n"))
        {
            toPrint = inputAnswer("Would you like a copy of the scores? y/n");
            
            if(toPrint.equals("y"))
            {
                recordscores(p, how_many_players);
                print("Here you go, Thanks for playing!");
                break;
            }
            else if(toPrint.equals("n"))
            {
                print("Okay, goodbye! Thanks for playing!");
                break;
            }
            else
            {
                print("Please type y or n!");
            }

        }
        return;
    }// End askPrintscores

    /*
    Define an ADT of player with operations
    -create a player set to a given name and score
    -extract the score of a player
    -extract the name of the player
    -print all the scores in a list
    -determine the winner
    -write all the scores into a text file
    */
    
    //method to create a variable of type player
    public static player setPlayer (String name_of_player, int score_of_player)
    {
        player new_player = new player();

        new_player.name = name_of_player;
        new_player.score = score_of_player;

        return new_player;
    }//End setPlayer

    //Gets the score of a particular player
    public static int getScore  (player p)
    {
        return p.score;   
    }//End getScore

    //Gets the Name of a particular player
    public static String getName (player p)
    {
        return p.name;
    }//End getName

    //Prints the names and scores of all the players
    public static void print_all_scores(player p[], int number_of_players)
    {
        for (int i = 0; i < number_of_players; i++)
        {
            int adjusti = i + 1;
            print("Player " + adjusti + ": " + getName(p[i]));
            print("Player " + adjusti + " score : " + getScore(p[i]));
        }
        return;
    }//End print_all_scores

    //method to find the winner of the quiz
    public static void findWinners(player p[], int number_of_players)
    {
        final int largest_score = 6;
        
        String winnername;
        
        for (int i = 0; i < number_of_players; i++)
        {
            int score = getScore(p[i]);

            if (score == largest_score)
            {
                winnername = getName(p[i]);
                print(winnername + " WINS with full score!!");
                return;
            }
        }
        
            print("huh looks like nobody got full score, is it really that hard?");
            return;
    }//End findWinners

    //Method to create a file containing the data from all players 
    public static void recordscores(player p[], int number_of_players) throws IOException
    {
        PrintWriter outputfile = new PrintWriter (new FileWriter("Scores.txt"));

        for (int i = 0; i < number_of_players; i++)
        {
            int adjusti = i + 1;
            outputfile.println("Player " + adjusti + " name: " + getName(p[i]));
            outputfile.println("Player " + adjusti + " score: " + getScore(p[i]));
            outputfile.println(" ");
        }
        
        System.out.println("Scores.txt written");
    
        outputfile.close();
        return;
    }//End recordscores
    

    //END OF ADT
    
    // a method which calls the other methods to form the quiz
    public static player askPlayer () throws IOException
    {   
        String name_of_player = playerName();
        

        boolean correct1 = question1();
        boolean correct2 = question2(correct1);
        boolean correct3 = question3(correct1, correct2);
        int score = decideScore(correct1, correct2, correct3);
        printScore(score);
        print("This is the end of the quiz.");

        player newPlayer = setPlayer(name_of_player, score);

        return newPlayer;   
    }//End askPlayer
    

   //prints the rules  
   public static void welcomerules ()
    {
        print("Welcome to the quiz.");
        print("The rules are simple.");
        print("1. No cheating. (eg. using the internet, asking others for help)");
        print("2. If you get a question right, the points for the next question increases."); 
        print("3. If you get one question wrong, the next question drops back to being worth one point.");
        print("4. To win, you must get full score.");
        print("5. Every question must be attempted.");
        blankline();
    
         return;
    } // END welcomerules

    //Prints out the options for the functions of the game
    public static void listOptions()
    {
        print("Options: (1) Play (2) Print scores (3) Exit");
        return;
    }//End listOptions

    
    // Asks the users name then greets them by name and pepares them to start the quiz
    //
    public static String playerName ()
    {
        String name = inputAnswer("What is your name?");
        print("Hello " + name + ", nice to meet you. Prepare to answer some questions.");
        blankline();
        
        return name;
    } // END player1Name
    

    //asks the first question and determines if the answer is correct or incorrect
    //
    public static boolean question1 () throws IOException
    {
        final String correctanswer1 = "24";
        String answer1;
        boolean correct;

        inputQuestions();
        answer1 = inputAnswer("");
        print("Your answer is " + answer1 + ".");
        
        if(answer1.equals (correctanswer1) | answer1.equals ("24 hours") | answer1.equals ("24 hrs") | answer1.equals ("24hours") | answer1.equals ("24hrs") | answer1.equals ("twenty four") | answer1.equals("Twenty four") | answer1.equals("twenty four hours")| answer1.equals("Twenty four hours"))
        {
            print("You got 1 point!");
            correct = true;
        } 
        else 
        {
            print("Incorrect. your next question will be worth one point.");
            correct = false;
        }
        
        print("The correct answer is " + correctanswer1 + ".");
        blankline();
        
        return correct;
    } // END question1

    //method to input questions
    public static void inputQuestions() throws IOException
    {
        BufferedReader inputQuestion = new BufferedReader (new FileReader("Questions.txt"));
        String s = inputQuestion.readLine();

        System.out.println(s); 
        inputQuestion.close();

        return;
    } // End inputQuestions

    
    //asks the second question and determines if the answer is correct or incorrect
    //
    public static boolean question2 (boolean correct1)
    {
        final String correctanswer2 = "365";
        String answer2;
        boolean correct2;
        
        answer2 = inputAnswer("How many days are in a year? (excluding leap year)");
        print("Your answer is " + answer2 + ".");


        if (correct1 == true && answer2.equals (correctanswer2) | answer2.equals ("365 days") | answer2.equals ("365days") | answer2.equals ("three hundred and sixty five days") | answer2.equals ("three hundred and sixty five") | answer2.equals ("Three hundred and sixty five days") | answer2.equals ("Three hundred and sixty five") )
        {
            print("You got 2 points!");
            correct2 = true;
        }
        else if(answer2.equals (correctanswer2) | answer2.equals ("365 days") | answer2.equals ("365days") | answer2.equals ("three hundred and sixty five days") | answer2.equals ("three hundred and sixty five") | answer2.equals ("Three hundred and sixty five days") | answer2.equals ("Three hundred and sixty five"))
        {
            print("You got 1 point!"); 
            correct2 = true;
        } 
        else 
        {
            print("Incorrect. your next question will be worth one point.");
            correct2 = false;
        }
        
        print("The correct answer is " + correctanswer2 + ".");
        blankline();
        
        return correct2;      
    } // END question2

    
    //asks the Third question and determines if the answer is correct or incorrect
    //
    public static boolean question3 (boolean correct1, boolean correct2)
    {
        final String correctanswer3 = "54";
        String answer3;
        boolean correct3;
        
        for (int i = 1; i<=5; i++)
        {
            System.out.println( i*9);
        }
        answer3 = inputAnswer("What is the next number in this sequence?");
         
        print("Your answer is " + answer3 + ".");
        
        if(correct1 == true && correct2 == true && answer3.equals (correctanswer3) | answer3.equals (" 54") | answer3.equals ("5 4") | answer3.equals ("fifty four") | answer3.equals ("Fifty four"))
        {
            print("You got 3 points!");
            correct3 = true;
        }
        else if(correct1 == false && correct2 == true && answer3.equals (correctanswer3) | answer3.equals (" 54") | answer3.equals ("5 4") | answer3.equals ("fifty four") | answer3.equals ("Fifty four"))
        {
            print("You got 2 points!");
            correct3 = true;
        }
        else if(answer3.equals (correctanswer3) | answer3.equals (" 54") | answer3.equals ("5 4") | answer3.equals ("fifty four") | answer3.equals ("Fifty four"))
        {
            print("You got 1 point!"); 
            correct3 = true;
        } 
        else 
        {
            print("Incorrect.");
            correct3 = false;
        }
        
        print("The correct answer is " + correctanswer3 + ".");
        blankline();
        
        return correct3;      
    } // END question3

    //to decide the total points of the player
    public static int decideScore(boolean correct1, boolean correct2, boolean correct3)
    {
        int score = 0;
        if(correct1 == true && correct2 == true && correct3 == true)
        {
            score = 6;
        }
        else if(correct1 == true && correct2 == true && correct3 == false)
        {
            score = 3;
        }
        else if(correct1 == false && correct2 == true && correct3 == true)
        {
            score = 3;
        }
        else if(correct1 == true && correct2 == false && correct3 == true)
        {
            score = 2;
        }
        else if(correct1 == true && correct2 == false && correct3 == false)
        {
            score = 1;
        }
        else if(correct1 == false && correct2 == true && correct3 == false)
        {
            score = 1;
        }
        else if(correct1 == false && correct2 == false && correct3 == true)
        {
            score = 1;
        }
        else
        {
            score = 0;
        }
        
        return score;
    }//End decideScores

    //method to print the score of the player
    public static void printScore(int score)
    {
        print("You got " + score + " points.");
        return;
    }//End printScore

    
    //Method for users to input strings
    public static String inputAnswer(String question)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);

        print(question);
        answer = scanner.nextLine();

        return answer;
    }//End inputAnswer

    //method to print a blank line
    public static void blankline()
    {
        print("");
        return;
    }//blankline()

    //to convert Strings to integers
    
    public static int inputInt(String message)
    {
        int answerint = 0;
        String answer = inputAnswer(message);
        boolean input_is_an_int = checkInt(answer);

        while (input_is_an_int == false)
        {
            answer = inputAnswer(message);
            input_is_an_int = checkInt(answer);
        }

        answerint = Integer.parseInt(answer);
   
        return answerint;
    }//End inputInt2

    //to check if the input is an int or not
    public static boolean checkInt (String answer)
    {
        boolean input_is_an_int = false;

        for(int i = 0; i <= 0; i++)
        {
            char current = answer.charAt(i);
        
            if(current == '0' || current =='1' || current =='2' || current == '3' || current == '4' || current == '5' || current =='6' ||current == '7' ||current =='8' ||current == '9')
            {
                input_is_an_int = true;
            }     
        }
    return input_is_an_int;
    }//End checkInt

    //Method to print any message
    public static void print(String message)
    {
        System.out.println(message);
        return;
    }//End print

} // END class Quiz