import java.util.Scanner;
class Handcricket{
    int computer_score,user_score;//Stores the score of computer and the user rescpectively
    int overs,wickets;//Stores the wickets and the overs
    static Scanner sc=new Scanner(System.in);
    private Handcricket(int main_overs,int main_wickets) {
        computer_score=0;
        user_score=0;
        overs=main_overs;
        wickets=main_wickets;
    }

    private int random_generator_six(){//Random number generator from 1 to 6
        return (int)(Math.random()*6)+1;
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

    private double over_simulater_manager(int checker,double pre_wickets){//Function for simulating over.The use of checeker is to make sure who is balling or batting
        double scorer_over=0.001+pre_wickets/100.0;//Wickets are kept in file(.00).the last 1 is added so that the .00 does not convert into .0
        for(int loop_control=1;loop_control<=6;loop_control++){//Simulating a over
            int user_input=sc.nextInt();//User input
            int computer_output=random_generator_six();//Computer randomized output
            if(user_input<1||user_input>6){//Makes sure the user input is correct or else asks again
                System.out.println("Wrong Input");
                loop_control--;
                continue;
            }
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
            if(Integer.parseInt(score.substring(score.indexOf('.')+1,score.indexOf('.')+3))==wickets)//Checks if all wickets are fallen
                return scorer_over;
        }
        return scorer_over;
    }
    private void bat(){//User batting function
        System.out.println("User now batting");
        int counter_over=0,counter_wicket=0;//Counting overs and wickets
        while(counter_over<overs&&counter_wicket<wickets){
            double runs_hub=over_simulater_manager(1,(double)(counter_wicket));//Gets the score for each over
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
            double runs_hub=over_simulater_manager(1,(double)(counter_wicket));//Gets the score for each over
            computer_score+=(int)(runs_hub);
            String storer=String.valueOf(runs_hub);
            counter_wicket+=Integer.parseInt(storer.substring(storer.indexOf('.')+1,storer.indexOf('.')+3));//Adds the wicket
            counter_over++;//Adds the over
            System.out.println("Over no. : "+counter_over+"\nComputer score : "+computer_score+"\nWickets fallen : "+counter_wicket);
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of overs and the number of wickets");
        int main_overs=sc.nextInt();
        int main_wickets=sc.nextInt();
        System.out.println("Choose Head or Tail");
        String toss_choose=sc.next();
        Handcricket object1=new Handcricket(main_overs,main_wickets);
        if(object1.toss_manager(toss_choose)==1){
            System.out.println("You win");
            System.out.println("Scores are displayed after each over");
            System.out.println("Choose to bat or to ball");
            String bat_ball_choose=sc.next();
            if(bat_ball_choose.equalsIgnoreCase("Bat")){//Toss decider user
                object1.bat();
                object1.ball();
            }
            else if(bat_ball_choose.equalsIgnoreCase("Ball")){
                object1.ball();
                object1.bat();
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
                object1.bat();
                object1.ball();
            }
            else{
                System.out.println("Computer will bat first");
                object1.ball();
                object1.bat();
            }
        }
        if(object1.user_score>object1.computer_score)
            System.out.println("You win");
        else if(object1.user_score<object1.computer_score)
            System.out.println("You lose");
        else{
            while(object1.user_score==object1.computer_score){//Super over
                System.out.println("Match tied.Superover initiated.");
                object1.overs=1;
                object1.wickets=2;
                object1.user_score=0;
                object1.computer_score=0;
                object1.bat();
                object1.ball();
            }
        }           
    }
}