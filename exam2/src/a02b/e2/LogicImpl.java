package a02b.e2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class LogicImpl implements Logic{

    private final int size;
    private List<Pair<Integer, Integer>> table = new ArrayList<>();
    private Direction direction = Direction.UP;

    public LogicImpl(final int size){
        this.size = size;
        for(int i = 0; i < size*size; i++){
            table.add(new Pair<Integer,Integer>(i/size, i%size));
        }
    }

    private boolean outOfBound(final Pair<Integer, Integer> position){
        return position.getX() < 0 || position.getX() >= this.size || position.getY() < 0 || position.getY() >= size;
    }

    private int move(final int position){
        Pair<Integer, Integer> newPosition = table.get(position);
        switch (direction) {
            case UP:
                newPosition = new Pair<Integer,Integer>(newPosition.getX()-1, newPosition.getY());
                break;
            case RIGHT:
                newPosition = new Pair<Integer,Integer>(newPosition.getX(), newPosition.getY()+1);
                break;
            case LEFT:
                newPosition = new Pair<Integer,Integer>(newPosition.getX(), newPosition.getY()-1);
                break;
        
            default:
                break;
        }
        if(outOfBound(newPosition)){
            System.exit(0);
        }
        return table.indexOf(newPosition);
    }

    @Override
    public int newPosition(final List<JButton> cells, final int position) {
        int newPosition = move(position);

        if(cells.get(newPosition).getText() == "R"){
            this.direction = Direction.RIGHT;
        }
        if(cells.get(newPosition).getText() == "L"){
            this.direction = Direction.LEFT;
        }
        return newPosition;
    }
    
}
