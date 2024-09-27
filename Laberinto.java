import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Laberinto {
    static int stage=1;
    public static void main(String[] args) {
        
        Array2d arreglo= new Array2d(14, 8);
        Array2dButtons botones =new Array2dButtons(14, 8);
        arreglo.clear('p');
        
        JFrame frame = new JFrame("Laberinto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(15, 8));
         
        

        for (int i = 0; i <= 13; i++) {
            for(int j=0;j<=7;j++){
                JButton button = new JButton(String.valueOf('p'));
                button.setBackground(Color.white);
                button.addActionListener(new MiActionListener(i, j, arreglo,button));
                botones.setItem(i, j, button);
                panel.add(button);
            }
            
        }
        
        JButton go= new JButton("go");
        go.setBounds(0, 0, 30, 30);
        panel.add(go);
        go.setBackground(Color.gray);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stage++;
               resolverLaberinto(arreglo,botones);
            }
        });

        frame.add(panel);
        frame.setVisible(true);

       
    }


    public static void resolverLaberinto(Array2d arreglo,Array2dButtons botones){

        StackADT pila= new StackADT();
        int[] inicio = new int[2];
        for(int i=0;i<arreglo.getRowSize();i++){
            for(int j=0;j<arreglo.getColSize();j++){
                if(arreglo.getItem(i, j)=='e'){
                    inicio[0]=i;
                    inicio[1]=j;
                    break;
                }
            }
        }
        
        pila.push(inicio);

       

        int[] previo={arreglo.getRowSize()+1,arreglo.getColSize()+1};

        while (!pila.isEmpty()) { 

            int[] actual;
            int cantidadC=0;
            actual=pila.pop();
            System.out.println(actual[0]);
            System.out.println(actual[1]);

            JButton botonActual=botones.getItem(actual[0],actual[1]);         
            botonActual.setBackground(Color.green);

            if (arreglo.getItem(actual[0], actual[1]) == 's') {
                System.out.println("Salida encontrada en: (" + actual[0] + ", " + actual[1] + ")");
                return;
            }

            arreglo.setItem(previo[0], previo[1], 'T');
    

            if(arreglo.getItem(actual[0],actual[1]-1)=='c' || arreglo.getItem(actual[0],actual[1]-1)=='s'){
                pila.push(new int[]{actual[0],actual[1]-1});
                cantidadC++;
            }
            if(arreglo.getItem(actual[0]-1,actual[1])=='c' || arreglo.getItem(actual[0]-1,actual[1])=='s'){
                pila.push(new int[]{actual[0]-1,actual[1]});
                cantidadC++;
            }
            if(arreglo.getItem(actual[0],actual[1]+1)=='c' || arreglo.getItem(actual[0],actual[1]+1)=='s'){
                pila.push(new int[]{actual[0],actual[1]+1});
                cantidadC++;
            }
            if(arreglo.getItem(actual[0]+1,actual[1])=='c' || arreglo.getItem(actual[0]+1,actual[1])=='s'){
                pila.push(new int[]{actual[0]+1,actual[1]});
                cantidadC++;
            }
                       
            
            if(cantidadC==0){
                arreglo.setItem(actual[0], actual[1], 'X');
                arreglo.setItem(previo[0], previo[1], 'c');
                JButton botonAux=botones.getItem(actual[0],actual[1]);         
                botonAux.setBackground(Color.red);

            }else{
                arreglo.setItem(previo[0], previo[1], 'c');
                previo=actual;
            }
        }

        
    }
}

class MiActionListener implements ActionListener {
    private int x;
    private int y;
    private JButton boton;
    private Array2d arreglo;
    public MiActionListener(int x,int y,Array2d arreglo,JButton boton) {
        this.x=x;
        this.y=y;
        this.arreglo=arreglo;
        this.boton=boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String text=boton.getText();
        if (Laberinto.stage==1){
            arreglo.setItem(x, y, 'e');
            boton.setBackground(Color.green);
            boton.setText("E");
            Laberinto.stage=2;
        }else if (Laberinto.stage==2){
            arreglo.setItem(x, y, 's');
            boton.setBackground(Color.green);
            boton.setText("S");
            Laberinto.stage=3;
        }else if (Laberinto.stage==3){
            if(text.equals("p")){
            arreglo.setItem(x, y, 'c');
            boton.setBackground(Color.CYAN);
            boton.setText("c");
    
            }else if(text.equals("c")){
            arreglo.setItem(x, y, 'p');
            boton.setBackground(Color.white);
            boton.setText("p");
            }
        }
        
       
    }
}