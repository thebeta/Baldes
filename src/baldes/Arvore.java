/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baldes;

import java.util.ArrayList;

/**
 *
 * @author Convidado
 */
public class Arvore {
    
    public Arvore pai;
    public Balde A, B;
    ArrayList<Arvore> filhos = new ArrayList();    
    
    // Construtor
    public Arvore(Arvore father, Balde A, Balde B){
        this.pai = father;
        this.A = new Balde(A);
        this.B = new Balde(B);
    }
    
    // Visita este nó, imprimindo os baldes na tela
    public void visita(){
        System.out.println(A.getQuantidade()+","+B.getQuantidade());
    }
    
    void mostraCaminho(){
        System.out.println("MOSTRANDO O CAMINHO (de baixo para cima) :"); 
        Arvore pointer = pai;
        while (pointer != null){     
            pointer.visita();
            pointer = pointer.pai;
        }
        System.out.println("CHEGOU À RAIZ.");
        System.out.println("");  
    }
                           
    public void geraFilhos(){
        Arvore filho = new Arvore(this, A, B);
        filho.A.enche();
        filhos.add(filho);
        
        filho = new Arvore(this, A, B);
        filho.B.enche();
        filhos.add(filho);
        
        filho = new Arvore(this, A, B);
        filho.A.esvazia();
        filhos.add(filho);
        
        filho = new Arvore(this, A, B);
        filho.B.esvazia();
        filhos.add(filho);
        
        filho = new Arvore(this, A, B);
        filho.A.transfere(filho.B);
        filhos.add(filho);
        
        filho = new Arvore(this, A, B);
        filho.B.transfere(filho.A);
        filhos.add(filho);
    }

    public int possuiCiclo(){
        Arvore ponteiro = pai;
        while (ponteiro != null){
            if (ponteiro.A.getQuantidade() == A.getQuantidade() && 
                ponteiro.B.getQuantidade() == B.getQuantidade()) 
            {
                return 1;
            }
            ponteiro = ponteiro.pai;
        }
        return 0;
    }
    
    public void removeCiclo(){
        for (int i = 0; i < filhos.size(); i++) {
            if (filhos.get(i).possuiCiclo() == 1) {
                filhos.remove(i);                
                i--;
            }            
        }
    }
       
    

}
