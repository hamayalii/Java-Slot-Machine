import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double balance = 100;
        String[] row;
        int payout;
        int bet;

        System.out.println("-------------------------");
        System.out.println("    Java Slot Machine    ");
        System.out.println("Symbols: 🔔 ⭐ 🍋 🍉 🍒 ");
        System.out.println("-------------------------");

        while(balance > 0){

            System.out.println("Your current balance is: $" + balance);
            System.out.print("Enter the bet amount: ");

            try {
                 bet = scanner.nextInt();
                 scanner.nextLine();
            }catch (InputMismatchException numbersOnly){
                System.out.println("Error: Enter integers numbers only!");
                scanner.nextLine();
                continue;
            }

            if (bet > balance){
                System.out.println("Insufficient Funds!");
                continue;
            }else if(bet <= 0){
                System.out.println("The bet amount must be greater than zero!");
                continue;
            }else{

                balance -= bet;
                System.out.println("Spinning..");

                row = spinRow();
                printRow(row);
                payout = getPayout(row, bet);

                if(payout > 0){

                    System.out.println("You won: $" + payout);
                    balance += payout;
                }else{
                    System.out.println("Sorry, You loss this round..");
                }
                System.out.print("Do you want to play again? (Y/N): ");
                String playAgain = scanner.nextLine().toUpperCase();
                if(!playAgain.equals("Y")){
                    System.out.println("GAME OVER, THANK YOU FOR PLAYING");
                    break;
                }
            }
        }
    }
    static String[] spinRow(){

        String[] symbols = {"🔔", "⭐", "🍋", "🍉", "🍒"};
        String[] row = new String[3];

        Random random = new Random();

        for(int i = 0 ; i < 3 ; i++){
            row[i] = symbols[random.nextInt(symbols.length)];
        }
        return row;
    }
    static void printRow(String[] row){

        System.out.println("---------------");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("---------------");
    }
    static int getPayout(String[] row , int bet){

        if(row[0].equals(row[1]) && row[1].equals(row[2])){

            return switch(row[0]){

                case "🔔" -> bet * 3;
                case "⭐" -> bet * 4;
                case "🍋" -> bet * 5;
                case "🍉" -> bet * 6;
                case "🍒" -> bet * 10;
                default -> 0;
            };
        }else if(row[0].equals(row[1])){

            return switch(row[0]){

                case "🔔" -> bet * 2;
                case "⭐" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🍉" -> bet * 5;
                case "🍒" -> bet * 6;
                default -> 0;
            };
        }else if(row[1].equals(row[2])){

            return switch(row[1]){

                case "🔔" -> bet * 2;
                case "⭐" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🍉" -> bet * 5;
                case "🍒" -> bet * 6;
                default -> 0;
            };
        }
        return 0;
    }

}