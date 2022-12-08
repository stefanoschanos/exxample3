package Project_1;

import java.util.Comparator;
import java.util.HashMap; //Το HashMap περιεχει εναν πινακα κομβων και ο κομβος αναπαρισταται ως κλαση.
import java.util.PriorityQueue;

public class Algorithms extends Movement
{

    public Algorithms(int Row, int Columnumn, String Path, int Cost) // Δημιουργια του υπερκονστρακτορα Algorithms για να εχουμε τα στοιχεια της Node
    {
        super(Row, Columnumn, Path, Cost);
        
    }

    static int[] startingLocation = new int[2]; // Δημιουργια μονοδιαστατου πινακα που αποθηκευουμε τις συντεταγμενες της αρχικης θεσης  
    static int[] finishLocation = new int[2]; // Δημιουργια μονοδιαστατου πινακα που αποθηκευουμε τις συντεταγμενες της τελικης θεσης (θεσης στοχου)  
    static boolean finishSearch = false; // Boolean μεταβλητη με την οποια ελεγχουμε αμα βρεθηκε ο στοχος , δηλαδη αμα τελειωσε το ψαξιμο
    static Node finishNode; // Δημιουργια του τελικου κομβου (Κομβος Στοχου)

    private static void resetGlobals() // Συναρτηση επαναφορας των αρχικων τιμων
    {
		finishSearch = false;
	    finishNode = new Node(0,0,"",0);
	}

    public static void checkNSEWandAddToQ(Node node, PriorityQueue PQ, HashMap searched, String[][] Table) // Συναρτηση ελεγχου των κινησεων και εισαγωγης του βηματος στην ουρα μας  
    {
		// Βεβαιωνομαστε οτι το ΑΡΙΣΤΕΡΟ βημα δεν βγαινει εκτως οριων και δεν ειναι στο hashmap searched 
		if(Left(node,Table) != null && searched.get(Left(node,Table).toString()) == null)
        {
			// Δημιουργια ενος προσωρινου κομβου με τα στοιχεια (σειρα , στηλη , μονοπατι , κοστος)  
			Node temp = new Node (node.Row , node.Column-1 , node.Path + "("+node.Row+","+(node.Column-1)+")" , node.Cost + getCost(node,Table)); 
			temp.Level = node.Level +1; // Αυξηση του βαθους  

			if(temp.Row == 10 && temp.Column == 12) // Αμα φτασουμε στο τελος τοτε ...
            {
				finishSearch = true; // Τελειώνουμε το ψαξιμο του στοχου
				finishNode = temp; // Θετουμε ως τελικο κομβο το τελευταιο temp
			}
			PQ.add(temp); // Και το προσθετουμε στην ουρα
		}
		
        if(Right(node,Table) != null && searched.get(Right(node,Table).toString()) == null)
        {
			Node temp = new Node(node.Row,node.Column+1, node.Path+"("+node.Row+","+(node.Column+1)+")", node.Cost + getCost(node,Table));
			temp.Level = node.Level +1;
			if(temp.Row == 10 && temp.Column == 12)
            {
				finishSearch = true;
				finishNode = temp;
			}
			PQ.add(temp);
		}
		
        if(Up(node,Table) != null && searched.get(Up(node,Table).toString()) == null)
        {
			Node temp = new Node(node.Row-1,node.Column, node.Path +"("+(node.Row-1)+","+node.Column+")", node.Cost + getCost(node,Table));
			temp.Level = node.Level +1;
			if(temp.Row == 10 && temp.Column == 12)
            {
				finishSearch = true;
				finishNode = temp;
			}
			PQ.add(temp);
		}

		if(Down(node,Table) != null && searched.get(Down(node,Table).toString()) == null)
        {
			Node temp = new Node(node.Row+1,node.Column, node.Path +"("+(node.Row+1)+","+node.Column+")", node.Cost + getCost(node,Table));
			temp.Level = node.Level +1;
			if(temp.Row == 10 && temp.Column == 12)
            {
				finishSearch = true;
				finishNode = temp;
			}
			PQ.add(temp);
		}
	}







    public static void UCS(String[][] Table) // Αλγοριθμος Αναζητησης Ενιαιου Κοστους (UCS) 
    {
		int counter=0; // Αρχικοποιηση μεταβλητης οπου αποθηκευουμε το πληθος των κομβων που δημιουργηθηκαν 
		resetGlobals(); // Επαναφορα των αρχικων τιμων
		Comparator<Node> comp = new Node.CostComparator(); // Δημιουργουμε εναν ΣΥΓΚΡΙΤΗ οπου θα συγκρινει τα ΚΟΣΤΗ μεταξυ κομβων 
		PriorityQueue PQ = new PriorityQueue<>(1000, comp); // Δημιουργουμε μια ΛΙΣΤΑ ΠΡΟΤΕΡΑΙΟΤΗΤΑΣ μεγεθους 1000 που διαταζει τα περιεχομενα της να ταθχουν με βαση τον ΣΥΓΚΡΙΤΗ μας
		HashMap<String,String> searched = new HashMap <String,String>(); // Δημιουργουμε ενα Hashmap στο οποιο θα εισαγονται οι κομβοι που εχουμε ηδη επισκευθει

		Node root = new Node(startingLocation[0],startingLocation[1], "",0); // Αρχικοποιηση της ΡΙΖΑΣ (Κομβου εναρξης)
		PQ.add(root); // Εισαγωγη της ΡΙΖΑΣ στην ΟΥΡΑ μας 
		
		// Χρησιμοποιουμε try/catch ωστε να ελεγχουμε τον κωδικα μας για τυχον ERRORS κατα την εκτελεση (Π.Χ. Out of bounds) 
		try 
        {	
			// Οσο δεν βρηκαμε τον στοχο και δεν τελειωσε η ερευνα μας ...
			while(finishSearch == false)
            {
				Node temp = (Node) PQ.peek(); // Δημιουργουμε ενα temp το οποιο κανει fetch/retrieve το πρωτο στοιχειο της ΟΥΡΑΣ μας αξιοποιοντας το PEEK ...
				PQ.remove(PQ.peek()); // Παιρνουμε το στοιχειο αυτο ...
				searched.put(temp.toString(), "searched"); // Το τοποθετουμε στο hashmap ωστε να ξερουμε οτι εχουμε περασει απο αυτον τον κομβο ...
				checkNSEWandAddToQ(temp, PQ, searched, Table); // Και κανουμε το επομενο βημα ...
				counter++; 
				//System.out.println(temp.toString());
			}
		}
		// Σε περιπτωση ERROR κατα την εκτελεση του αλγοριθμου τυπωνεται το αντιστοιχο μηνυμα 
		catch(NullPointerException e) //Το συγκεκριμενο exception χρησιμοποιειται οταν η τιμη του αντικειμενου δεν υπαρxει ή ειναι NULL
        {
			System.out.println("The program did not find a solution. Quitting");
		}
		
		// Εκτυπωση του μονοπατιου κατα την εκτελεση του UCS , του κοστους του και του πληθους των κομβων που δημιουργηθηκαν
		System.out.println("UCS:" + finishNode.Path+", Cost:" + finishNode.Cost);
		System.out.println(counter + " nodes have been created");
    }








	public static void DFS(String[][] Table , int depth) //Αλγοριθμος Αναζητησης Πρωτα σε Βαθος (DFS)
	{
		int counter=0; // Αρχικοποιηση μεταβλητης οπου αποθηκευουμε το πληθος των κομβων που δημιουργηθηκαν 
		resetGlobals(); // Επαναφορα των αρχικων τιμων
		Comparator<Node> comp = new Node.levelComparator(); // Δημιουργουμε εναν ΣΥΓΚΡΙΤΗ οπου θα συγκρινει τα ΒΑΘΗ μεταξυ κομβων 
		PriorityQueue PQ = new PriorityQueue<>(1000, comp); // Δημιουργουμε μια ΛΙΣΤΑ ΠΡΟΤΕΡΑΙΟΤΗΤΑΣ μεγεθους 1000 που διαταζει τα περιεχομενα της να ταθχουν με βαση τον ΣΥΓΚΡΙΤΗ μας
		HashMap<String,String> searched = new HashMap <String,String>(); // Δημιουργουμε ενα Hashmap στο οποιο θα εισαγονται οι κομβοι που εχουμε ηδη επισκευθει
		
		Node root = new Node(startingLocation[0],startingLocation[1], "",0); // Αρχικοποιηση της ΡΙΖΑΣ (Κομβου εναρξης)
		PQ.add(root); // Εισαγωγη της ΡΙΖΑΣ στην ΟΥΡΑ μας 
		
		try
		{
			while(PQ.size()>0)
			{
				Node temp = (Node) PQ.peek(); // Δημιουργουμε ενα temp το οποιο κανει fetch/retrieve το πρωτο στοιχειο της ΟΥΡΑΣ μας αξιοποιοντας το PEEK ...
				PQ.remove(PQ.peek()); // Παιρνουμε το στοιχειο αυτο ...
				searched.put(temp.toString(), "searched"); // Το τοποθετουμε στο hashmap ωστε να ξερουμε οτι εχουμε περασει απο αυτον τον κομβο ...
				checkNSEWandAddToQ(temp, PQ, searched , Table); // Και κανουμε το επομενο βημα ...
				counter++;
				//System.out.println(temp.toString());

				if(temp.Row == 10 && temp.Column == 12) // Αν βρισκομαστε στην τελευταια θεση κανε...
				{
					// Εκτυπωση του μονοπατιου κατα την εκτελεση του IDS , του κοστους του και του πληθους των κομβων που δημιουργηθηκαν
					System.out.println("IDS:" + finishNode.Path+", Cost:"+finishNode.Cost);
					System.out.println(counter + " nodes have been created");
					finishSearch = true; // Οριζουμε την μεταβλητη boolean finishSearch ως true ωστε να σταματησει η αναζητηση  
				}
				else if(temp.getLevel()<depth) // Οσο το βαθος στο οποιο βρισκομαστε ειναι μικροτερο απο το βαθος αναζητησης
				{
					checkNSEWandAddToQ(temp, PQ, searched , Table); // Κανουμε το επομενο βημα
				}
			}
		}
		// Σε περιπτωση ERROR κατα την εκτελεση του αλγοριθμου τυπωνεται το αντιστοιχο μηνυμα
		catch(NullPointerException e) //Το συγκεκριμενο exception χρησιμοποιειται οταν η τιμη του αντικειμενου δεν υπαρxει ή ειναι NULL
		{
			System.out.println("The program did not find a solution. Quitting");
		}
	}






	public static void IDS(String[][] Table) //Αλγοριθμος Αναζητησης Επαναληπτικης Εκβαθυνσης (IDS)
	{
		resetGlobals();
		while(finishSearch == false) // Οσο δεν βρηκαμε τον στοχο και δεν τελειωσε η ερευνα μας ...
		{
			DFS(Table,Level);
			Level++; // Αυξηση του βαθους αναζητησης
		}
	}





	// Δεν ηταν εφικτη η υλοποιηση 
	public static void A_Star(String[][] Table) // Αλγοριθμος Α-Αστερακι (A*)
 	{
		System.out.println("To be continued ...");
	}
		
}