package labioopint.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;

import labioopint.controller.api.SerializableObject;
import labioopint.controller.api.SaveController;
import labioopint.model.core.impl.TurnManager;

public class SaveControllerImpl implements SaveController,Serializable{
    private TurnManager turnManager;

    public SaveControllerImpl(TurnManager tManager){
        this.turnManager = tManager;
    }

    @Override
    public void save() {
        SerializableObject newSerialization;
        if(turnManager.getEnemy().getFirst() == Boolean.TRUE){
            newSerialization = new SerializableObjectImplEnemy(turnManager);
        } else {
            newSerialization = new SerializableObjectImplNoEnemy(turnManager);
        }
        try{
            FileOutputStream fos = new FileOutputStream(new File("src/main/java/labioopint/saving/output.txt"),false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newSerialization);
            oos.close();
            fos.close();
        } catch(IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}
