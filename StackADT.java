import java.util.LinkedList;

public class StackADT {
    private LinkedList<int[]> data;
    private long maxLength=Integer.MAX_VALUE;

    public StackADT(){
        this.data=new LinkedList<>();
    }
    public StackADT(int x){
        this.data=new LinkedList<>();
        this.maxLength=x;
    }


    public boolean isEmpty(){
        boolean res = false;
        if (this.data.size() == 0){
            res = true;
        }
        return res;
    }

    public int length(){
        return this.data.size();
    }

    public int[] pop(){
        if(this.length()==0){
            System.out.println("No hay elementos para sacar");
            return null;
        }
        
        return data.removeFirst();
        
        
    }

    public int[] peek(){
        if(this.length()==0){
            System.out.println("Ya no hay elementos");
            return null;
        }
        return data.peekFirst();
        
        
    }

    public void push(int[] valor){
        if(this.length()==this.maxLength){
            System.out.println("Maxima longitud alcanzada, no se pueden almacenar mas elementos");      
        }else{
            data.addFirst(valor);
        }
    }

    public boolean isFull(){
        if(this.length()==this.maxLength){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StackADT{" +
                "data=" + data +
                '}';
    }
}

