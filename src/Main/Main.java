
package Main;
import Tree.Tree;
import bTree.*;
import java.util.Scanner;

public class Main {
     
    
    public static void fill(bTree arbol, int n,int m){
        
            for (int j = 0; j <40 ; j++) {
                arbol.add( (int)Math.abs( Math.floor(Math.random()*(n-m+1)+m)));
            }
        }

    public static void main(String[] args) {
          
        Scanner ent = new Scanner(System.in); 
        
        bTree<Integer> bTree = new bTree<>(); 
//        bTree.add(3);
//        bTree.add(18);
//        bTree.add(23);
//        bTree.add(21);
//        bTree.add(42);
//        bTree.add(71);
//        bTree.add(8);
//        bTree.add(113);
//        bTree.add(343);
        fill(bTree,0,15);
        bTree.add(192);
        System.out.println(bTree.toString()); //Imprime arbol con metodo toString que llama a la clase TreePrinter
        System.out.println("");
        bTree.breadthFirstTraversal(); //Imprime los numeros existentes en cada nivel
        System.out.println("Consultando cantidad de nodos entre un rango"); 
        System.out.println(bTree.between(3, 14));
        System.out.println(bTree.between(1, 20));
        
        bTree.breadthFirstTraversal();
        
    }
}
   
