package drawingTool;

import java.awt.event.MouseEvent;

public interface DrawingTool {
    void mouseClicked(MouseEvent e);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseEntered(MouseEvent e);
    void mouseExited(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseMoved(MouseEvent e);
}
