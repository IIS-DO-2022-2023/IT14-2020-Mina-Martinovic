package command;

import geometry1.ShapeManager;

class BringToFrontCommand implements Command {
    private ShapeManager shapeManager;
    private int shapeIndex;
    private boolean wasSentToBack = false;

    public BringToFrontCommand(ShapeManager shapeManager, int shapeIndex) {
        this.shapeManager = shapeManager;
        this.shapeIndex = shapeIndex;
    }

    @Override
    public void execute() {
        if (wasSentToBack) {
            shapeManager.sendToBack(shapeIndex);
            wasSentToBack = false;
        } else {
            shapeManager.bringToFront(shapeIndex);
        }
    }

    @Override
    public void unexecute() {
        shapeManager.sendToBack(shapeIndex);
        wasSentToBack = true;
    }
}
