package Project_1;

// Συγκουνας Ιωαννης - 1556
// Χανος Στεφανος - 1573
// Ηλιαδου Αναστασια - 1592
// Σφακιανακης Ιωαννης - 1559

import java.io.IOException;
import java.util.Scanner;

public class Project_Main 
{
    public static void main(String[] args) throws IOException 
    {
        Create_Table x = new Create_Table(); //Δημιουργια ενος αντικειμενου Create_Table()
        String [][] Labirinth = new String[11][13]; //Δημιουργια ενος δισδιαστατου πινακα οπου θα αποθηκευτουν τα στοιχεια του .txt (Ο λαβυρινθος)
        Labirinth=x.Table_Creator(); //Εισαγουμε τον λαβυρινθο απο το .txt στον πινακα Labirinth
        x.Table_Printer(Labirinth); //Εκτυπωση του πινακα
        
        //Ερωτηση επιλογης αλγοριθμου και κληση της αντιστοιχης επιλογης μεσω της switch()
        while(true)
        {
            System.out.println("Which algorithm would you like to use ?? (UCS / IDS / A*) or (EXIT to stop)");
            Scanner Input = new Scanner(System.in);
            String Choice = Input.nextLine(); 

            switch (Choice) 
            {
                case "UCS":
                    Algorithms.UCS(Labirinth);
                    break;
                case "IDS":
                    Algorithms.IDS(Labirinth);
                    break;
                case "A*":
                    Algorithms.A_Star(Labirinth);
                    break;
                case "EXIT":
                    return;
                default:
                    System.out.println("Your choice was not valid , please choose one of the following algorithms (UCS / IDS / A*)");
                    break;
            }
        }
    }
}