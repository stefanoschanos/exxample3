package Project_1;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  // Η συγκεκριμενη βιβλιοθηκη χρησιμοποιειται στην περιπτωση που υπαρχει προβλημα με το διαβασμα , γραψημο ή ψαξιμο αρχειου. Στην περιπτωση μας αξιοποιειται οταν φτασουμε στο τελος του αρχειου και δεν υπαρχει κατι αλλο να διαβασουμε

public class Create_Table 
{
    public String[][] Table_Creator() throws IOException 
    {
        String line;

        List<String[]> List = new ArrayList<>(); //Δημιουργια λιστας

        File file = new File("E:/ΣΧΟΛΗ/Visual Studio Code/Τεχνητη Νοημοσυνη/Εργασια 1/Labirinth.txt"); //Δημιουργια ενος αντικειμανου file στο οποιο αποθηκευεται ενα αρχειο .txt (ΕΝΝΟΕΙΤΑΙ ΠΩΣ ΤΟ PATH ΕΙΝΑΙ ΔΙΑΦΟΡΕΤΙΚΟ ΣΕ ΚΑΘΕ ΥΠΟΛΟΓΙΣΤΗ)
        BufferedReader scan = new BufferedReader(new FileReader(file)); //Διαβασμα του αρχειου μεσω Buffer για μεγαλυτερη αποτελεσματικοτητα

        while ((line = scan.readLine()) != null) //Λουπα που εκτελειται οσο υπαρχουν γραμμες στο αρχειο
        {
            List.add(line.split(",")); //Προσθηκη της καθε γραμμης στην λιστα 
        }
        scan.close();
        
        String[][] Table = List.toArray(new String[][] {}); //Μετατροπη της λιστας σε δισδιαστατο πινακα

        return Table;
    }

    public void Table_Printer(String[][] Table)
    {
        int i;
        System.out.println("This is your maze: ");
        //Εκτυπωση του δισδιαστατου πινακα
        for (String[] t : Table)
        {
            for (i = 0; i < t.length; i++) //Λουπα που εκτελειται για οσες γραμμες εχει ο πινακας 
                System.out.print(t[i]); //Εκτυπωση ολοκληρης της γραμμης
            System.out.println(" "); //Αλλαγη γραμμης
        }   
    }
}