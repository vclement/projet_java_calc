public class PileRPL{
    protected ObjEmp tab[];
    protected int nbObj;

    public PileRPL(int taille){
        this.nbObj = 0;
        this.tab = new ObjEmp[taille];
    }

    public int getNbObj(){
        return nbObj;
    }

    public void setNbObj(int value) {
        this.nbObj = value;
    }

    public boolean estPlein(){
        return tab.length == nbObj;
    }

    public void operation(char signe){
        ObjEmp objet = null;
        ObjEmp objet2 = null;
        try {
            objet = pop();
            objet2 = pop();
            switch(signe){
                case '+':
                        objet.add(objet2);
                        break;
                case '-': 
                        objet.sous(objet2);
                        break;
                case '*': 
                        objet.mult(objet2);
                        break;
                case '/': 
                        objet.div(objet2);
                        break;
                default:
                        System.out.println("Opération impossible");
                        break;
            }
            this.push(objet);
        }
        catch(Exception oe){
            System.out.println("Pop impossible");
        }
    }

    public void swap(){
        ObjEmp temp = null;
        ObjEmp temp1 = null;
        try{
            temp = pop();
            temp1 = pop();  
        }
        catch(Exception oe){
            System.out.println("Pile Vide");
        }
        this.push(temp);
        this.push(temp1);
    }

    public void push(ObjEmp obj){
        if(estPlein()){
            System.out.println("Tableau plein");
        }
        else{
            tab[nbObj]= obj;
            nbObj++;
            //System.out.println(tab[0].getVal());
        }
    }

    public ObjEmp pop() throws Exception
    {
        ObjEmp ob;

        if(nbObj==0)
            throw new IndexOutOfBoundsException();

        ob=this.tab[nbObj-1];
        this.tab[nbObj-1] =null;
        nbObj--;

        return ob; 
    }
    public String toString(){
        String resultat=""; 
        for(int cpt=tab.length-1;cpt>=0;cpt--){
            resultat += ("|"+(tab[cpt]==null ? "   " : tab[cpt])+ "|\n+---+\n");
        }
        return resultat;
    }
}
