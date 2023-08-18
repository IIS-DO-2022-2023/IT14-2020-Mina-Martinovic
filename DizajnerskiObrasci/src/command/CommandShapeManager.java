package command;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import geometry1.Shape;
import geometry1.ShapeManager;

/*
class ShapeManager extends Shape{
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public void bringToFront(Shape shapeIndex) {
        shapes.remove(shapeIndex);
        shapes.add(shapeIndex);
    }

    public void sendToBack(java.awt.Shape shape) {
        shapes.remove(shape);
        shapes.add(0, (Shape) shape);
    }

    // Dodajte ostale metode za manipulaciju oblicima

    public List<Shape> getShapes() {
        return shapes;
    }

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
*/
/*
class ShapeManager {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
        shape.setShapeManager(this); // Postavite referencu na ShapeManager
    }

    public void bringToFront(int shapeIndex) {
        if (shapeIndex < 0 || shapeIndex >= shapes.size()) {
            return;
        }
        Shape shape = shapes.remove(shapeIndex);
        shapes.add(shape);
    }

    public void sendToBack(int shapeIndex) {
        if (shapeIndex < 0 || shapeIndex >= shapes.size()) {
            return;
        }
        Shape shape = shapes.remove(shapeIndex);
        shapes.add(0, shape);
    }

    // Ostatak implementacije ostaje isti
}
*/

public class CommandShapeManager implements ShapeManager {
    private List<Shape> shapes = new ArrayList<>();

    // Implementirajte metode iz interfejsa ShapeManager
    // ...

    public void bringToFront(Shape shape) {
        shapes.remove(shape);
        shapes.add(shape);
    }

    public void sendToBack(Shape shape) {
        shapes.remove(shape);
        shapes.add(0, shape);
    }

	@Override
	public void bringToFront(int shapeIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendToBack(int shapeIndex) {
		// TODO Auto-generated method stub
		
	}
}
