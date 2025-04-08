package labioopint.model.Block.api;

public interface Block {

    BlockType getType();

    Rotation getRotation();

    void RandomRotation();

    void setRotation(Rotation blockRotation);

}