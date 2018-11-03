/*
 * Player.java
 * Version 0.1.5    : Setup
 * Programmer       : Adam Hennefer
 * Due Date         : 11/7/18
 * Last Modified    : 11/2/18 7:52
 *
 * This class represents a player.
 *  
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<TrainPiece> pieces;
    private ArrayList<TrainCard> carCards;
    private ArrayList<DestinationCard> destCards;
    private int points;
    private String name;
    private Board board;
    private TrainPieces.COLOR pColor;
    private boolean turn = false;
    
    // construct player object
    public Player(String n, TrainPieces.COLOR c){
        //set player's name
        name = n;
        
        //set player's colors
        pColor = c;
        
        //initialize ArrayList of forty five train pieces with player's color
        pieces = new TrainPieces().getPieces(c); 
        
        //initialize ArrayList of four traincar cards        
        carCards = new ArrayList<>();
        TrainCardDeck deck = new TrainCardDeck();
        carCards = deck.toPlayer();

        //initialize ArrayList of three destination cards    
        destCards = new ArrayList<>();
        DestinationTicketSet dCard = new DestinationTicketSet();
        destCards = dCard.Draw(destCards);
                
        //initialize points set to zero    
        points = 0;
        
    }
    
    public boolean addBoard(Board b){
        board = b;
        return board == b;
    }
   
    public TrainPiece getTrainPiece(int p){
        return pieces.get(p);
    }
    
    // insert a TrainPiece into claimed route
    public boolean insertTrainPiece(DestinationCard dc){
        // if the DestinationCard route(s) are claimed or we don't have enough train pieces then return false
        if(board.claimRoute(dc.getRouteA()) && board.claimRoute(dc.getRouteB()) || pieces.size() < dc.getPts()){
            return false; }
        // else we are going to claim the route and throw out an equivlent amount of train pieces
        // to the routes path (aka pts)
        else{
          if(board.claimRoute(dc.getRouteA()){
                
        
            board.claimRoute(dc.getRouteA());
            int i = 0;
            while(i < dc.getPts()){
                pieces.remove(i);
            }    
        }
        return true;
    }   
    //retrives a train piece of the players list of train pieces
    public void removeTrainPiece(int p){
        pieces.remove(p);
    }
    //retrieves a train car from the player's list of train cards 
    public TrainCard getTrainCar(int c){
        return carCards.get(c);
    }
    // adds a train car card to player's list of train cars
    public boolean insertTrainCar(TrainCard tc){
        return carCards.add(tc); 
    }
    // removes a train car card from players list of train cars
    public boolean removeTrainCar(int tc){
        TrainCard temp = carCards.get(tc);
        return carCards.remove(temp);
    }
    
    // returns the players current destination cards
    public ArrayList<DestinationCard> getDestTixCard(){
        return this.destCards;
    }
    
    // add a destination card to the players list of destination cards
    public boolean insertDestTixCard(DestinationCard dc){
        return destCards.add(dc);
    }
    
    // removes a destinatoin card from the plaers list of destination cards
    public boolean removeDestTixCard(int cNum){
        DestinationCard temp = destCards.get(cNum);
        return temp.returnTo(temp);
    }
    
    //add player points
    public boolean addPoints(int a){
        int oldpoints = points;
        points += a;
        return points > oldpoints;
    }
    // subtract player points
    public boolean subPoints(int s){
        int oldpoints = points;
        points -= s;
        return points < oldpoints;
    }
    // get player points
    public int getPoints(){
        return points;
    }
    // check players turn
    public boolean getTurn(){
        return turn;
    }
    // change players turn
    public void setTurn(){
        turn = !turn;
    }
    // override for testing 
    @Override
	public boolean equals(Object x){
            if(!(x instanceof Player)){ return false; }
            Player y = (Player)x;
            return this.name.equals(y.name) && this.pColor == y.pColor;
	}
}