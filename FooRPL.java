import java.*;

public class FooRPL {

    public static void main(String[] args){
        PileRPL pile = new PileRPL(4);
        System.out.println(pile);
        ObjEmp o1 = new ObjEmp(2);
        ObjEmp o2 = new ObjEmp(2);
        ObjEmp o3 = new ObjEmp(5);
        ObjEmp o4 = new ObjEmp(7);

        pile.push(o1);
        pile.push(o2);
        pile.push(o3);
        System.out.println(pile);
        pile.operation('+');
        System.out.println(pile);
        pile.operation('*');
        System.out.println(pile);
        pile.push(o4);
        System.out.println(pile);
        pile.operation('/');
        System.out.println(pile);
    }
}
