//Program Writen by Vincent for MS-SIS java lessons
//
import java.io.*;
import java.util.StringTokenizer;

public class UserGUI{

    protected BufferedReader inputUser;
    protected PrintStream outputUser;
    protected PrintStream outputLog;
    protected boolean logMod;

    public static void main(String[] args){
        new UserGUI();
        int taille=10;
        String ligne;
        boolean sucess = false;
        StringTokenizer st;
        System.out.println("Veuillez entrer la taille de la pile [10 par defaut]: ");
        BufferedReader inputUser = new BufferedReader( new InputStreamReader( System.in ));
        try{  
            ligne = inputUser.readLine();
            taille = Integer.parseInt(ligne);
        }catch(Exception oe){
            System.out.println("Utilisation de la taille par défault");
            taille=10;
        }
        PileRPL pile = new PileRPL(taille);

        System.out.println(pile);
    }

    public void Parse(){
        //premiere boucle while pour recevoir toutes les entrees utilisateur et ecrire les commandes.
        //while(){
        //Deuxieme boucle while pour permettre de lire chaque mot dans l'entree utilisateur
        //      while(){
        //  }
        //}

    }
}
