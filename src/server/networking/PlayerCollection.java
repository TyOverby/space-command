package server.networking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.main.entity.Entity;

public class PlayerCollection implements Serializable{
        private static final long serialVersionUID = -5079049404420648508L;
        
        private final String name;
        private final String password;
        
        private final Entity ship;
        
        private final List<Integer> playerIdList = new ArrayList<Integer>(10);
        
        public PlayerCollection(String name, String password, Entity ship){
                this.name = name;
                this.password = password;
                this.ship = ship;
        }
        
        public void update(long l){
                
        }
        
        public String getName(){
                return this.name;
        }
        
        public boolean testPassword(String pass){
                return this.password.equals(pass);
        }
        
        public Entity getShip(){
                return this.ship;
        }
        
        public List<Integer> getPlayerList(){
                return playerIdList;
        }
        
        public void addPlayer(int playerId){
                this.playerIdList.add(playerId);
        }
}