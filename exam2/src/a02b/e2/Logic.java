package a02b.e2;

import java.util.List;

import javax.swing.JButton;

public interface Logic {
    enum Direction{
        UP,
        LEFT,
        RIGHT;
    }
    int newPosition(List<JButton> cells, int position);
}
