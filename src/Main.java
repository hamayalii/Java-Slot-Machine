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
                if (balance <= 0){

                    System.out.println("GAME OVER, YOU LOSS ALL YOU MONEY");
                    break;
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

        String[] symbols = {"🔔","🔔","🔔","🔔","🔔",
                               "⭐","⭐","⭐","⭐",
                        "🍋","🍋","🍋", "🍉","🍉", "🍒"};
        String[] row = new String[3];

        Random random = new Random();

        for(int i = 0 ; i < 3 ; i++){
            row[i] = symbols[random.nextInt(symbols.length)];
        }
        return row;
    }
    static void printRow(String[] row){

        System.out.println("---------------");
        for(int i = 0 ; i < 3 ; i++) {

            System.out.print(row[i]);

            if(i < row.length - 1){
                System.out.print(" | ");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException _) {
            }
        }
        System.out.println("\n---------------");
    }
    static int getPayout(String[] row , int bet){

        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            int symbolValue = getSymbolValuer(row[0]);
            return bet * symbolValue * 2;

        }else if(row[0].equals(row[1])){
            int symbolValue = getSymbolValuer(row[0]);
            return bet * symbolValue;

        }else if(row[1].equals(row[2])){

            int symbolValue = getSymbolValuer(row[1]);
            return bet * symbolValue;
        }
        return 0;
    }
    static int getSymbolValuer(String symbol) {

        return switch (symbol) {
            case "🔔" -> 3;
            case "⭐" -> 4;
            case "🍋" -> 5;
            case "🍉" -> 6;
            case "🍒" -> 10;
            default -> 0;
        };
    }
}