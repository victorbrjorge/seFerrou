/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seferrou;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author victor
 */
public class Game {

    private final ArrayList<Player> players;
    private final Integer numOfPlayers;
    private final Integer maxCardsPerHand;
    private final Deck deck;
    private final Hand board; //cartas na mesa

    public Game(Integer numOfPlayers) {
        deck = new Deck();
        players = new ArrayList<>();
        this.numOfPlayers = numOfPlayers;
        maxCardsPerHand = 40 / numOfPlayers;
        board = new Hand();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void startGame() {
        Integer cardsCnt=2;
        Integer firstPlayer=0;
        do{
            deck.shuffle();
            round(cardsCnt,firstPlayer);
            if(cardsCnt<maxCardsPerHand)
                cardsCnt++;
            
            firstPlayer = (firstPlayer+1) % players.size();
            
            for(int i=0;i<players.size();i++){
                if(players.get(i).getLifes()<1){
                    System.out.println(players.get(i).getName() + ", você foi eliminado!");
                    players.remove(i); //se o jogador ficar sem vidas é removido
                    cardsCnt=2;
                }
            }
        }while(players.size()>2);
        
        if(players.size()>1){ //caso sobre 2 players
            if(players.get(0).getLifes()>players.get(1).getLifes()){
                System.out.print("Parabéns, " + players.get(0).getName() + "! Você ganhou.");
            }else{
                if(players.get(0).getLifes()<players.get(1).getLifes()){
                    System.out.print("Parabéns, " + players.get(1).getName() + "! Você ganhou.");
                }else{
                    System.out.print("Empate!");
                }
            }
        }else{ //caso sobre somente um player
            System.out.print("Parabéns, " + players.get(0).getName() + "! Você ganhou.");
        }
    }

    public void dealHand(Integer cardsCount) {
        System.out.println("Distribuindo cartas...\n");
        for (int i = 0; i < numOfPlayers; i++) {
            for (int j = 0; j < cardsCount; j++) {
                players.get(i).getCurrentHand().addCard(deck.dealCard());
            }
        }
    }

    /**
     * Inicia um turno e determina o vencedor
     *

     * @param cardsCount qtd de cartas por mão naquele turno
     */
    public void round(Integer cardsCount, Integer firstPlayer) { //logica dos for's, quem começa jogando
        Scanner scanner = new Scanner(System.in);
        Integer totalPredictions = 0;
        Integer tmp;
        Integer posCarta;

        //Da as cartas para os players
        this.dealHand(cardsCount);

        //previsão de cada player
        
        int i=firstPlayer;
        do{
            System.out.println(players.get(i).getName() + ": ");
            players.get(i).getCurrentHand().printHand();
            System.out.println("Qual sua previsão para sua mão?");
            if(i==((firstPlayer-1)%players.size())){ //ultimo player
                System.out.println("Você não pode falar " + (cardsCount - totalPredictions));
            }
            tmp = scanner.nextInt();
            totalPredictions += tmp;
            players.get(i).getCurrentHand().setPredicton(tmp);
            i=(i+1)%players.size();
        }while(i!=firstPlayer);
        
        //IMPLEMENTAR LÓGICA DIFERENTE PARA PRIMEIRA MÃO
        
        //jogadas
        for (int j = 0; j < cardsCount; j++) {
            int k=firstPlayer;
            do{
                System.out.println(players.get(k).getName() + ", é a sua vez de jogar.");

                System.out.println("Cartas na mesa: ");
                board.printHand();

                System.out.println("Sua mão: ");
                players.get(k).getCurrentHand().printHand();

                System.out.println("Escolha a carta que você deseja jogar: ");
                posCarta = scanner.nextInt();

                players.get(k).getCurrentHand().getCard(posCarta).setPlayerId(k); //identifica de quem é a carta jogada
                board.addCard(players.get(k).getCurrentHand().getCard(posCarta)); //coloca a carta na mesa
                players.get(k).getCurrentHand().removeCard(posCarta); //retira da mão

                k=(k+1)%players.size();
            }while(k!=firstPlayer);
            board.sortByRealValue();
            //em caso de empate, ninguém vence a mão
            if(board.getCard(0).getRealValue()!=board.getCard(1).getRealValue()){ //não há empate
                players.get(board.getCard(0).getPlayerId()).getCurrentHand().wonRound();
            }
            // -------------- //
            board.clear();
        }
        
        for(int index=0;index<players.size();index++){
            tmp=players.get(index).getCurrentHand().getRoundsWon()- players.get(index).getCurrentHand().getPredicton();
            if(tmp!=0){
                tmp = Math.abs(tmp);
                for(int l=0;l<tmp;l++){
                    players.get(index).lostLife();
                }
                System.out.println(players.get(index).getName() + ", você perdeu " + tmp + " vidas esse round.");
            }
        }
    }

}
