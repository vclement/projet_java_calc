public class ObjEmp{

    protected int val;
    
    public ObjEmp(int val)  {
        this.val = val;    
    }
    
    public int getVal(){
        return val;
    }

    public void setVal(int value){
        this.val = value;
    }

    public int add(ObjEmp oe){
        return val += oe.getVal();     
    }

    public int sous(ObjEmp oe){
        return val -= oe.getVal();
    }

    public int mult(ObjEmp oe){
        return val = val * oe.getVal();
    }
    
    public int div(ObjEmp oe){
        return val /= oe.getVal();
    }

    public String toString(){
        return ""+val;
    }
} 
