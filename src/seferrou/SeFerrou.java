/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seferrou;

import java.util.Scanner;

/**
 *
 * @author victor
 */
public class SeFerrou {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game seFerrou;
        Scanner scanner = new Scanner(System.in);
        Integer playerCnt;
        Integer lifes;
        Player player;
        String playerName;
        
        System.out.println("Bem vindo ao Se Ferrou PM 2015/2!\n\n");
        
        System.out.println("Informe a quantidade de jogadores: <3 - 8> ");
        playerCnt = scanner.nextInt();
        
        System.out.println("Informe a quantidade vidas de cada jogador");
        lifes = scanner.nextInt();
        
        seFerrou = new Game(playerCnt);
        
        for(int i=1;i<playerCnt+1;i++){
            playerName = "Jogador " + i;
            player = new Player(playerName,lifes);
            seFerrou.addPlayer(player);
        }
        seFerrou.startGame();
        
        
        
    }
    
}
