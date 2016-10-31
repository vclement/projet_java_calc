//Program Writen by Vincent for MS-SIS java lessons
//
import java.io.*;
import java.util.StringTokenizer;

public class UserGUI{

    protected BufferedReader inputUser = null;
    protected PrintStream outputUser = null;
    protected PrintStream outputLog;
    protected boolean logMod;
    protected int quit;

    public UserGUI(){
        this.outputLog = outputLog;
        this.logMod = logMod;
        this.quit = 0;
        this.execute();
    }

    public void execute(){
        inputUser = new BufferedReader( new InputStreamReader( System.in ));
        outputUser = new PrintStream (System.out);
        String ligne = "";
        int taille= 0;

        System.out.println("Bonjour, bienvenue dans le programme calculatrice RPL\n");
        System.out.print("Tout d'abord, choisissez la taille de la pile: ");
        try{
            ligne = inputUser.readLine();
            taille = Integer.parseInt(ligne);
            System.out.println("Vous avez choisis une taille de "+ taille +" pour votre pile.");
        }catch(IOException ie){
            System.out.println("Erreur lors de l'entrée utilisateur.");
        }
        PileRPL pile = new PileRPL(taille);
        System.out.println("Démarrage de la calculatrice...");

        while(this.quit == 0){
            outputUser.println("\nCommandes possible: push <valeur> ; add ; div ; mult ; sous ; pop ; quit");
            outputUser.println("Pour basculer sur un usage distant, utilisez la commande \"distant\", pour revenir sur un usage local, utilisez la commande \"local\".\n");
            outputUser.println("Pour utiliser les logs en local, il faut utiliser la commande \"log\" ");
            outputUser.print("Votre commande: ");
            try{
                ligne = inputUser.readLine();
            }
            catch(IOException ie){
                System.out.println("Erreur dans la saisie");
            }

            choix_utilisateur(ligne, pile);
            System.out.println(pile);

        }
        System.exit(0);
    }

    public void choix_utilisateur(String st, PileRPL pile){
        String[] entree = st.split("\\s");
        for(int i=0; i<entree.length;i++){
            if(entree[i].equals("distant")){
                try{
                    //Fonction qui permet d'ouvrir un serveur et attends des connexions.
                    this.logMod = false;
                }catch(Exception oe){
                
                }
            
            }

            else if(entree[i].equals("local")){
                try{
                    //Fonction qui gère le local
                    this.logMod = true;
                }catch(Exception oe){
                
                }
            }

            else if(entree[i].equals("log"));
            else
               Parse(entree, pile, i);

        }

    }

    public void Parse(String[] total, PileRPL pile, int x){
        //Boucle while pour parser l'entree utilisateur et dire les commandes.
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

            case "multi":
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
}
