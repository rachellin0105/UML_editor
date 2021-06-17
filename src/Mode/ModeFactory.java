package Mode;

public class ModeFactory {
	public Mode createMode(String type) {
		if ( type.equals("SELECT") ) {
			return new SelectMode();
		}else if (type.equals("ASSOCIATION")) {
			return new LineMode("ASSOCIATION");
		}else if (type.equals("GENERALIZATION")) {
			return new LineMode("GENERALIZATION");
		}else if (type.equals("COMPOSITION")) {
			return new LineMode("COMPOSITION");
		}else if (type.equals("CLASS")) {
			return new ClassMode();
		}else if (type.equals("USECASE")) {
			return new UseCaseMode();
		} else return null;
	}
}
