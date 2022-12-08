package Project_1;

import java.util.Comparator; // Η συγκεκριμενη βιβλιοθηκη χρησιμοποιειται για να κανουμε συγκρισεις και για βεβαιωθουμε για την απολυτη ταξη των στοιχειων που χειριζεται  

public class Node 
{
    public int Row;
    public int Column;
    public String Path;
    public int Cost;
    public static int Level;

    public Node(int Row , int Column , String Path , int Cost) //Κονστρακτορας κομβου
    {
        this.Row = Row;
        this.Column = Column;
        this.Path = Path;
        this.Cost = Cost;
        Level = 0;  
    }
    public int getRow() // Επιστροφη σειρας 
    {
         return Row; 
    }
	public int getCol() // Επιστροφη στηλης 
    { 
        return Column; 
    }

    public static int getLevel() // Επιστροφη βαθους 
    { 
        return Level; 
    }

    public String toString() // Εκτυπωση σειρας και στηλης  
    { 
        return "(" + Row + "," + Column + ")"; 
    }

    public static class CostComparator implements Comparator<Node> // Συγκριση κοστους μεταξυ κομβων
    {
		public int compare(Node a, Node b)
		{
			return a.Cost - b.Cost;
		}
	}
	public static class levelComparator implements Comparator<Node> // Συγκριση βαθους μεταξυ κομβων
    {
		public int compare(Node a, Node b)
		{
			return b.Level - a.Level;
		}
	}
}