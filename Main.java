import java.io.*;
import java.util.*;


public class Main{
//Cette fonction main initialise un objet de type UserGUI puis execute la méthode de User GUI qui permet d'avoir une boucle infini jusqu'a ce que l'utilisateur demande l'arret de la boucle.
//
    public static void main(String[] args){
        UserGUI objet = new UserGUI();
        String[] limit = args;
        objet.execute(limit);
    }

}
