import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class MainMenu {
    /**
     * Function displaying the menu at the start of the game and scan the user's input to interact with the menu
     */
    public static void displayMainMenu(){
        System.out.println(TextColor.ANSI_GREEN +" ____________________________\n" +
                "|  ________________________  |\n" +
                "| |"+TextColor.ANSI_RESET +TextColor.ANSI_BLUE+" Welcome to ChessSpleef:"+TextColor.ANSI_RESET+ TextColor.ANSI_GREEN +"| |\n" +
                "| |                        | | \n"+
                "| |"+TextColor.ANSI_RESET +TextColor.ANSI_BLUE+" Choose an option:      "+TextColor.ANSI_RESET+ TextColor.ANSI_GREEN +"| |\n"+
                "| |"+TextColor.ANSI_RESET +TextColor.ANSI_BLUE+" 1-Play                 "+TextColor.ANSI_RESET+TextColor.ANSI_GREEN + "| |\n"+
                "| |"+TextColor.ANSI_RESET +TextColor.ANSI_BLUE+" 2-Scores               "+TextColor.ANSI_RESET+TextColor.ANSI_GREEN + "| |\n"+
                "| |"+TextColor.ANSI_RESET +TextColor.ANSI_BLUE+" 3-Game Rules           "+TextColor.ANSI_RESET+TextColor.ANSI_GREEN + "| |\n"+
                "| |"+TextColor.ANSI_RESET +TextColor.ANSI_BLUE+" 4-Leave Game           "+TextColor.ANSI_RESET+TextColor.ANSI_GREEN + "| |\n"+
                "| |                        | |\n"+
                "| |________________________| |\n"+
               "|____________________________|"+TextColor.ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
          //////////////////////////////////////////////////////////////////
         //start an action that correspond to the option choose          //
        // and return an error message if the scanner is not a int value//
       //////////////////////////////////////////////////////////////////
        try {
            int response = scanner.nextInt();
                    switch (response){
                        case 1:

                            Main.pseudo1 = GameSystem.choosePseudo();
                            Main.pseudo2 = GameSystem.choosePseudo();
                            //Display the rick roll (Secret input)
                            if ((Main.pseudo1 + Main.pseudo2).equals("Rick")){
                                EasterEgg.rickRolled();
                                break;
                            //Forbid the two players from having same name to make the score table accurate
                            } else if (Objects.equals(Main.pseudo1, Main.pseudo2)) {
                                System.out.println("Start again and choose 2 differents names");
                                displayMainMenu();
                            } else {
                                Main.isgame = true;
                                int width = 13;
                                int height = 12;
                                byte Player = GameSystem.randomStart();
                                int[][] loadingMap = new int[width][height];
                                GameBoard map = new GameBoard(loadingMap, width, height);
                                map.matriceGenerator();
                                map.mouvment(Player);
                            }
                        case 2:
                            //Display scores table
                            GameSystem.printPseudosAndScores();

                            break;
                        case 3:
                            //Show the rules
                            System.out.println(TextColor.ANSI_BLUE+"During his turn, a player can move his pawn one square (vertically or horizontally), then destroy a square on the board.\n" +
                                    "The last player still able to move wins.\n" +
                                    "Constraints :\n" +
                                    "- A player cannot destroy an occupied square.\n" +
                                    "- A player cannot occupy a destroyed square or an already-occupied square.\n" +
                                    "- A player blocked during a turn is declared the loser."+TextColor.ANSI_RESET);
                            try {
                                AudioPlayer.playAudio("sound/collision.wav",250);
                                // Attendre que la lecture audio se termine
                                Thread.sleep(7000);
                                // Afficher le menu principal après le délai
                                displayMainMenu();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        case 4:
                            break;
                        default:
                            System.out.println("Enter a VALID number!!!");
                            displayMainMenu();
                            break;
                    }
        }catch (InputMismatchException e){
            System.out.println("Enter a VALID number!!!");
            displayMainMenu();
        }

    }
    /*Test function if we have no score registered

                            GameSystem.addOrUpdatePseudoScore(Main.pseudos, Main.scores, "Sllappo", 200);
                            GameSystem.addOrUpdatePseudoScore(Main.pseudos, Main.scores, "Bwalexius", -22);
                            GameSystem.addOrUpdatePseudoScore(Main.pseudos, Main.scores, "Kramptax", 56);
                            GameSystem.addOrUpdatePseudoScore(Main.pseudos, Main.scores, "Noroi", -1);
                            GameSystem.addOrUpdatePseudoScore(Main.pseudos, Main.scores, "Astolfo", 34);
                            GameSystem.saveScores("score.txt");*/
}
