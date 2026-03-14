import java.util.Scanner;
class Handcricket{
    int computer_score,user_score;//Stores the score of computer and the user rescpectively
    int overs,wickets;//Stores the wickets and the overs
    int reader_input[];//Stores how much of ecah number the user has input
    double difficulty;//Strores the difficulty of the game
    int second_innings_checker;//Checks if it is second innings or not.Multplies of 2 means second innings
    static Scanner sc=new Scanner(System.in);
    private Handcricket(int main_overs,int main_wickets) {
        computer_score=0;
        user_score=0;
        difficulty=0.0;
        second_innings_checker=1;
        overs=main_overs;
        wickets=main_wickets;
        reader_input=new int[7];
    }

    private int random_generator_six(){//Random number generator from 1 to 6
        return (int)(Math.random()*6)+1;
    }

    private int user_pattern_reader(){//Reads the users pattern
        int max_input=0;//The number which the user has input the most times
        for(int loop_c=1;loop_c<=6;loop_c++)
            if(reader_input[loop_c]>max_input)
                max_input=loop_c;
        return max_input;
    }

    private int toss_manager(String choose){//A dedicated toss manager to simulate toss
    int ran_choose_storer=1;//Storing choose in integer form.Default-Haed
        if(choose.equalsIgnoreCase("Head"))
            ran_choose_storer=1;//Head-1 as integer
        else if(choose.equalsIgnoreCase("Tail"))
            ran_choose_storer=2;//Tail-2 as integer
        else{
            System.out.println("Error!!! Wrong Input");
            System.exit(0);
        }
        int ran_choose_comp=(int)(Math.random()*2)+1;//Random Variable.Assign value from 1 to 2
        if(ran_choose_comp==ran_choose_storer)
            return 1;//Win will return 1
        return 0;//Lose will return 0
    }

    private double over_simulator_manager(int checker,int pre_wickets){//Function for simulating over.The use of checeker is to make sure who is balling or batting
        double scorer_over=0.001;//Wickets are kept in file(.00).the last 1 is added so that the .00 does not convert into .0
        for(int ball=1;ball<=6;ball++){//Simulating a over
            int user_input=sc.nextInt();//User input
            int computer_output;
            if(user_input<1||user_input>6){//Makes sure the user input is correct or else asks again
                System.out.println("Wrong Input.Wide run initiated");
                computer_score++;//One extra run to computer for wrong input
                ball--;
                continue;
            }
            reader_input[user_input]++;//Sends the data for the computer to recognize
            if(checker==1)//When user batting computer recognizes pattren
                computer_output=Math.random()<difficulty?random_generator_six():user_pattern_reader();//Computer randomized output
            else//When user balling computer is completely random
                computer_output=random_generator_six();
            System.out.println("Computer : "+computer_output);//Prints Computer Output
            if(user_input==computer_output){
                scorer_over+=0.01;//Wickets counter
                System.out.println("Wicket Fallen");
            }
            else
                if(checker==1)//1 means user batting
                    scorer_over+=user_input;
                else//0 means user balling
                    scorer_over+=computer_output;
            String score=Double.toString(scorer_over);//Converts score to String
            int total_wickets=Integer.parseInt(score.substring(score.indexOf('.')+1,score.indexOf('.')+3))+pre_wickets;//Stores the total number of wickets in the over and pre_wickets
            if(second_innings_checker%2==0&&checker==1&&(int)(scorer_over)+user_score>computer_score){//Chceks if the run scored by the user is greater than the computer in the second innings
                System.out.println("Your score : "+(user_score+(int)(scorer_over))+"\nComputer Score : "+computer_score);
                System.out.println("You win by "+(wickets-total_wickets)+" wickets");
                System.exit(0);
            }
            if(second_innings_checker%2==0&&checker==0&&user_score<(int)(scorer_over)+computer_score){//Chceks if the run scored by the computer is greater than the user in the second innings
                System.out.println("Your score : "+user_score+"\nComputer Score : "+computer_score+(int)(scorer_over));
                System.out.println("You lose by "+(wickets-total_wickets)+" wickets");
                System.exit(0);
            }
            if(total_wickets==wickets)//Checks if all wickets are fallen
                return scorer_over;
        }
        return scorer_over;
    }
    private void bat(){//User batting function
        System.out.println("User now batting");
        int counter_over=0,counter_wicket=0;//Counting overs and wickets
        while(counter_over<overs&&counter_wicket<wickets){
            double runs_hub=over_simulator_manager(1,counter_wicket);//Gets the score for each over
            user_score+=(int)(runs_hub);//Adds it to the user code
            String storer=String.valueOf(runs_hub);
            counter_wicket+=Integer.parseInt(storer.substring(storer.indexOf('.')+1,storer.indexOf('.')+3));//Adds the wicket
            counter_over++;//Adds the over
            System.out.println("Over no. : "+counter_over+"\nUser score : "+user_score+"\nWickets fallen : "+counter_wicket);
        }
    }

    private void ball(){//User ball for singular ball
        System.out.println("User now balling");
        int counter_over=0,counter_wicket=0;//Counting overs and wickets
        while(counter_over<overs&&counter_wicket<wickets){
            double runs_hub=over_simulator_manager(0,counter_wicket);//Gets the score for each over
            computer_score+=(int)(runs_hub);
            String storer=String.valueOf(runs_hub);
            counter_wicket+=Integer.parseInt(storer.substring(storer.indexOf('.')+1,storer.indexOf('.')+3));//Adds the wicket
            counter_over++;//Adds the over
            System.out.println("Over no. : "+counter_over+"\nComputer score : "+computer_score+"\nWickets fallen : "+counter_wicket);
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter difficulty in which you want to play \"Easy\" \"Normal\" \"Hard\" :");
        String difficulty_words=sc.next();
        System.out.println("Enter the number of overs and the number of wickets");
        int main_overs=sc.nextInt();
        int main_wickets=sc.nextInt();
        System.out.println("Choose Head or Tail");
        String toss_choose=sc.next();
        int bat_or_ball_storer=0;//0 for batting user first
        Handcricket object1=new Handcricket(main_overs,main_wickets);
        if(difficulty_words.equalsIgnoreCase("Easy"))//Difficulty sorter
            object1.difficulty=0.9;//Easy 0.9
        else if(difficulty_words.equalsIgnoreCase("Normal"))
            object1.difficulty=0.7;//Normal 0.7
        else if(difficulty_words.equalsIgnoreCase("Hard"))
            object1.difficulty=0.5;//Hard 0.5
        else{
            System.out.println("Invalid Input at difficulty");
            System.exit(0);
        }
        if(object1.toss_manager(toss_choose)==1){
            System.out.println("You win");
            System.out.println("Scores are displayed after each over");
            System.out.println("Choose to bat or to ball");
            String bat_ball_choose=sc.next();
            if(bat_ball_choose.equalsIgnoreCase("Bat")){//Toss decider user
                bat_or_ball_storer=0;
                object1.bat();
                object1.second_innings_checker++;//Checks if the function is run
                object1.ball();
                object1.second_innings_checker++;//Checks if the function is run
            }
            else if(bat_ball_choose.equalsIgnoreCase("Ball")){
                bat_or_ball_storer=1;
                object1.ball();
                object1.second_innings_checker++;//Checks if the function is run
                object1.bat();
                object1.second_innings_checker++;//Checks if the function is run
            }
            else{
                System.out.println("Wrong Input");
                System.exit(0);
            }
        }
        else{//Toss decider computer
            System.out.println("You lose");
            System.out.println("Scores are displayed after each over");
            if(((int)(Math.random()*2)+1)%2==0){
                System.out.println("Computer will ball first");
                bat_or_ball_storer=0;
                object1.bat();
                object1.second_innings_checker++;//Checks if the function is run
                object1.ball();
                object1.second_innings_checker++;//Checks if the function is run
            }
            else{
                System.out.println("Computer will bat first");
                bat_or_ball_storer=1;
                object1.ball();
                 object1.second_innings_checker++;//Checks if the function is run
                object1.bat();
                object1.second_innings_checker++;//Checks if the function is run
            }
        }
        if(object1.user_score>object1.computer_score)
            System.out.println("You win by "+(object1.user_score-object1.computer_score)+" runs");
        else if(object1.user_score<object1.computer_score)
            System.out.println("You lose by "+(object1.computer_score-object1.user_score)+" runs");
        else{
            while(object1.user_score==object1.computer_score){//Super over
                System.out.println("Match tied.Superover initiated.");
                object1.overs=1;
                object1.wickets=2;
                object1.user_score=0;
                object1.computer_score=0;
                if(bat_or_ball_storer%2==1){//User will bat first if he bowled
                    object1.bat();
                    object1.second_innings_checker++;//Checks if the function is run
                }
                object1.ball();
                object1.second_innings_checker++;//Checks if the function is run
                if(bat_or_ball_storer%2==0){//User will bat last if he bat
                    object1.bat();
                object1.second_innings_checker++;//Checks if the function is run
                }
                bat_or_ball_storer++;
            }
        }           
    }
}