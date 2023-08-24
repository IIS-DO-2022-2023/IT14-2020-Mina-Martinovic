package command;

import geometry1.Point;
import mvc.DrawingModel;

public class AddPointCmd implements Command  {
	
		private DrawingModel model;
		private Point point;

		public AddPointCmd(DrawingModel model, Point point) {
			super();
			this.model = model;
			this.point = point;
		}

		@Override
		public void execute() {
			model.add(point);

		}

		@Override
		public void unexecute() {
			model.remove(point);

		}

		@Override
		public String toString() {
			return point.toString() +"added.";
		}
}
