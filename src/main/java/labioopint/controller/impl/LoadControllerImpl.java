package labioopint.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Optional;

import labioopint.controller.api.LoadController;
import labioopint.model.core.impl.TurnManager;

public class LoadControllerImpl implements LoadController{

    private Optional<TurnManager> loadedTurnManager;
    private boolean isLoaded;

    public LoadControllerImpl(){
        this.loadLastGame();
        isLoaded = false;
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
            isLoaded = true;
        }catch(Exception e){
            System.out.println(e);
            loadingNotPossible();
        }
    }
    
    @Override
    public boolean isLoaded(){
        if(isLoaded){return true;}
        return false;
    }

    @Override
    public TurnManager getTurnManager(){
        if(loadedTurnManager.isPresent()){
            return loadedTurnManager.get();
        }else{
            return null;
        }
    }

    private void loadingNotPossible(){
        System.out.println("No such file found");
        
    }
}
