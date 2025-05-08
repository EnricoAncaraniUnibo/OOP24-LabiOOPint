package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Optional;

import labioopint.controller.api.LoadController;
import labioopint.model.core.impl.TurnManager;

public class LoadControllerImpl implements LoadController{

    private Optional<TurnManager> loadedTurnManager;

    public LoadControllerImpl(){
        this.loadLastGame();
    }
    
    @Override
    public void loadLastGame() {
        try{
            FileInputStream fis = new FileInputStream(new File("src/main/java/labioopint/saving/lastGame.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadedTurnManager = Optional.of((TurnManager)ois.readObject());
            ois.close();
            fis.close();
            System.out.println("Sono fortissimo");
        }catch(Exception e){
            loadingNotPossible();
        }
    }
    
    public TurnManager getTurnManager(){
        if(loadedTurnManager.isPresent()){
            return loadedTurnManager.get();
        }
        else{
            return null;
        }
    }

    private void loadingNotPossible(){
        System.out.println("No such file found");
        
    }
}
