//Program Writen by Vincent for MS-SIS java lessons
//
import java.io.*;
import java.util.StringTokenizer;

public class UserGUI{

    protected BufferedReader inputUser;
    protected PrintStream outputUser;
    protected PrintStream outputLog;
    protected boolean logMod;

    public UserGUI(){
        this.execute();
    }

//    public static void main(String[] args){
//        new UserGUI();
//        int taille=10;
//        String ligne;
//        boolean sucess = false;
//        StringTokenizer st;
//        String entree;
//        System.out.println("Veuillez entrer la taille de la pile [10 par defaut]: ");
//        BufferedReader inputUser = new BufferedReader( new InputStreamReader( System.in ));
//        try{  
//            ligne = inputUser.readLine();
//            taille = Integer.parseInt(ligne);
//        }catch(Exception oe){
//            System.out.println("Utilisation de la taille par défault");
//            taille=10;
//        }
//        PileRPL pile = new PileRPL(taille);
//
//        System.out.println(pile);
//        int i=0;
//        while(i==0){
//            try{
//                ligne = inputUser.readLine();
//                Parse(ligne);
//            }
//            catch(IOException ie){
//                System.out.println("error during parsing");
//            }
//            if(taille==2){
//                i=500;
//            }
//            System.out.println(pile);
//
//        }
//    }


    public void execute(){
        inputUser = new BufferedReader( new InputStreamReader( System.in ));
        String ligne;
        System.out.println("Bonjour, bienvenue dans le programme calculatrice RPL");
        try{
            ligne = inputUser.readLine();
        }catch(IOException ie){
            System.out.println("Erreur lors de l'entrée utilisateur.");
        }
    }

    public void Parse(String st){
        //Boucle while pour parser l'entree utilisateur et dire les commandes.
        String[] total = st.split("\\s");
        for(int x=0;x<total.length;x++){
            System.out.println(total[x]);
        }    
    
    }
}
