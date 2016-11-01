//Program Writen by Vincent for MS-SIS java lessons
//
import java.io.*;
import java.util.StringTokenizer;
import java.net.*;

public class UserGUI{

    protected BufferedReader inputUser = null;
    protected PrintStream outputUser = null;
    protected PrintWriter outputLog;
    protected ServerSocket calculatrice;
    protected boolean logMod = false;
    protected int quit;

    public UserGUI(){
        this.outputLog = outputLog;
        this.logMod = false;
        this.quit = 0;
    }

    public void execute(String[] args){
        inputUser = new BufferedReader( new InputStreamReader( System.in ));
        outputUser = new PrintStream (System.out);
        String ligne = "";
        int taille= 0;
        
        System.out.println("Bonjour, bienvenue dans le programme calculatrice RPL\n");
        if(args[0] != null){
            System.out.println("Souhaitez-vous rejouez un fichier de log ? ");
            try{
                ligne = inputUser.readLine();
                if(ligne.equals("oui")){
                     PileRPL pile = new PileRPL(10);
                     rejoue(args[0], pile);
                }
                else if(ligne.equals("non")){
                    System.out.println("Demarrage du programme principal");
                }
            }catch(IOException oe){
                System.out.println("Choix invalide, la reponse est non.");
                System.out.println("Demarrage du programme principal");
            }
        }
       // System.out.print("Tout d'abord, choisissez la taille de la pile: ");
       // try{
       //     ligne = inputUser.readLine();
       //     taille = Integer.parseInt(ligne);
       //     System.out.println("Vous avez choisis une taille de "+ taille +" pour votre pile.");
       // }catch(Exception ie){
       //     System.out.println("Erreur lors de l'entrée utilisateur, choix de la taille par défault (10)");
       //     taille = 10;
       // }
        taille=10;
        PileRPL pile = new PileRPL(taille);
        System.out.println("Démarrage de la calculatrice...");

        while(this.quit == 0){
            outputUser.println("\nCommandes possible: push <valeur> ; add ; div ; mult ; sous ; pop ; quit");
            outputUser.println("Pour basculer sur un usage distant, utilisez la commande \"distant\", pour revenir sur un usage local, utilisez la commande \"local\".\n");
            outputUser.print("Votre commande: ");
            try{
                ligne = inputUser.readLine();
            }
            catch(IOException ie){
                System.out.println("Erreur dans la saisie");
            }

            //Debut des choix utilisateurs
            choix_utilisateur(ligne, pile);
            try{
                calculatrice.close();
            }catch(Exception oie){

            }
            outputUser.println(pile);

        }
        try{
            outputLog.close();
        }catch(Exception oe){
        }
        System.exit(0);
    }

    public void choix_utilisateur(String st, PileRPL pile){
        String[] entree = st.split("\\s");
        for(int i=0; i<entree.length;i++){
            if(entree[i].equals("distant")){
                try{
                    //Fonction qui permet d'ouvrir un serveur et attends des connexions.
                    System.out.println("Ouverture d'un socket sur le port 12345, pour vous connecter utilisez la commande suivante nc <IP_MACHINE> 12345");
                    this.logMod = false;
                    this.calculatrice = new ServerSocket(12345);
                    Socket socket = calculatrice.accept();
                    this.inputUser = new BufferedReader ( new InputStreamReader(socket.getInputStream() ) );
                    this.outputUser = new PrintStream( socket.getOutputStream() );
                }catch(Exception oe){
                    try{
                        calculatrice.close();
                    }
                    catch(IOException ie){
                        System.out.println("Unable to close socket");
                    }
                    System.out.println("Erreur lors de l'ouvertur d'un socket") ;
                }

            }

            else if(entree[i].equals("local")){
                try{
                    //Fonction qui gère le local
                    this.logMod = true;
                    calculatrice.close();
                    this.inputUser = new BufferedReader( new InputStreamReader( System.in ));
                    this.outputUser = new PrintStream (System.out);
                }catch(Exception oe){
                    System.out.println("Erreur lors de l'initialisation des variables.");
                }
            }

            else if(entree[i].equals("log")){
                try{
                    if(entree[i+1].equals("on")){
                        try{ 
                            this.calculatrice.close();
                        }catch(Exception oe){
                        }
                        this.logMod = true;
                        this.outputLog = new PrintWriter( new BufferedWriter( new FileWriter( "fichierLog.txt" )));
                    }
                    else if(entree[i+1].equals("off")){
                        this.logMod = false;
                        this.outputLog = null;
                    }
                }catch(Exception ie){
                    System.out.println("Commande log in complete.");
                }

            }
            else if(entree[i].equals("push") || entree[i].equals("add") || entree[i].equals("pop") || entree[i].equals("mult") || entree[i].equals("sous") || entree[i].equals("div") || entree[i].equals("quit") ) {
                if(this.logMod==true){
                    outputLog.println(st);
                }
                Parse(entree, pile, i);
            }
            else
                System.out.println("Commande non implemente");
        }

    }

    public void Parse(String[] total, PileRPL pile, int x){
        //Boucle while pour parser l'entree utilisateur et dire les commandes.
        if(this.logMod == true){

        }

        ObjEmp obj = null;
        switch(total[x]){
            case "push":
                try{
                    obj = new ObjEmp(Integer.parseInt(total[x+1]));
                }
                catch(NumberFormatException oe){
                    System.out.println("Erreur lors de la saisie de l'entier");
                }
                try{
                    pile.push(obj);
                }catch(Exception ie){
                    System.out.println("Erreur lors du push, pile pleine.");
                }
                x++;
                obj = null;
                break;

            case "add":
                pile.operation('+');
                break;

            case "div":
                pile.operation('/');
                break;

            case "mult":
                pile.operation('*');
                break;

            case "sous":
                pile.operation('-');
                break;

            case "pop":
                try{
                    pile.pop();
                }catch(Exception ie){
                    System.out.println("Erreur lors du pop, pile vide.");
                }
                break;

            case "quit":
                this.quit=1;
                break;
        }
    }



    public void rejoue(String fichier, PileRPL pile) throws IOException{
        String ligne;
        BufferedReader lecteur = null;
        try{
            lecteur = new BufferedReader( new FileReader( fichier ) );
        }catch(FileNotFoundException exc){
            System.out.println("Fichier introuvable");
        }

        while( (ligne=lecteur.readLine()) != null ){
            choix_utilisateur(ligne, pile);
            System.out.println(pile);
        }
    }
}
