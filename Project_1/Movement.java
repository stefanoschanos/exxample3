package Project_1;

import javax.lang.model.util.ElementScanner14;

public class Movement extends Node
{
    //Δημιουργια του υπερκονστρακτορα Movement για να εχουμε τα στοιχεια της Node
    public Movement(int Row, int Column, String Path, int Cost) 
    {
        super (Row , Column, Path, Cost);
    }

    //Βημα Αριστερα 
    public static Node Left (Node node , String[][] Table) 
    {
        //Αμα ειμαστε στην στηλη 0 τοτε δεν κανει τιποτα γιατι θα βγει εκτος οριων
        if(node.Column == 0) 
        {
			return null;
		}
        //Αμα δεν βγαινουμε εκτος οριων με το αριστερο βημα ΚΑΙ υπαρχει ΚΕΝΟ ([ ]) Ή υπαρχει ο ΣΤΟΧΟΣ ([G]) Ή υπαρχει ΠΟΡΤΑ ([D]) ΚΑΙ ΔΕΝ υπαρχει ΤΟΙΧΟΣ ([X]) 
		else if(Table[node.Row][node.Column-1] != null && (Table[node.Row][node.Column-1].equals("[ ]") || Table[node.Row][node.Column-1].equals("[G]")) || Table[node.Row][node.Column-1].equals("[D]") && !Table[node.Row][node.Column-1].equals("[X]"))
        {
            //Τοτε δημιουργουμε εναν κομβο
			Node temp = new Node(node.Row,node.Column-1, node.Path + node.toString(),node.Cost + getCost(node,Table));
			return temp;
		}
		
		return null;
	}

    //Βημα Δεξια
    public static Node Right (Node node , String[][] Table) 
    {
        if (node.Column == 13-1) 
        {
            return null;    
        }

        else if(Table[node.Row][node.Column+1] != null && (Table[node.Row][node.Column+1].equals("[ ]") || Table[node.Row][node.Column+1].equals("[G]")) || Table[node.Row][node.Column+1].equals("[D]") && !Table[node.Row][node.Column+1].equals("[X]"))
        {
			Node temp = new Node(node.Row,node.Column+1, node.Path + node.toString(),node.Cost + getCost(node,Table));
			return temp;
		}
        return null; 
    }

    //Βημα Πανω
    public static Node Up (Node node , String[][] Table) 
    {
        if(node.Row == 0)
        {
			return null;
		}
		else if(Table[node.Row-1][node.Column] != null && (Table[node.Row-1][node.Column].equals("[ ]") || Table[node.Row-1][node.Column].equals("[G]")) || Table[node.Row-1][node.Column].equals("[D]")&& !Table[node.Row-1][node.Column].equals("[X]"))
        {
			Node temp = new Node(node.Row-1,node.Column,node.Path+ node.toString(),node.Cost+getCost(node,Table));
			return temp;
		}
        return null; 
    }

    //Βημα Κατω
    public static Node Down (Node node , String[][] Table) 
    {
        if (node.Row == 11-1) 
        {
            return null;    
        }

        else if(Table[node.Row+1][node.Column] != null && (Table[node.Row+1][node.Column].equals("[ ]") || Table[node.Row+1][node.Column].equals("[G]")) || Table[node.Row+1][node.Column].equals("[D]") && !Table[node.Row+1][node.Column].equals("[X]"))
        {
			Node temp = new Node(node.Row+1,node.Column,node.Path+ node.toString(),node.Cost+getCost(node,Table));
            return temp;
		}
        return null; 
    }

    //Επιστροφη του κοστους των κινησεων
	public static int getCost(Node N,String [][] Table)
    {
		if(Table[N.Row][N.Column].equals("[ ]")) //Το ΚΕΝΟ ([ ]) κοστιζει 1
        {
			return 1;
		}
		else if(Table[N.Row][N.Column].equals("[D]")) //Η ΠΟΡΤΑ ([D]) κοστιζει 2
        {
			return 2;
		}
        else 
        return 0;
	}
}
