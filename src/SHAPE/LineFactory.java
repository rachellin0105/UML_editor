package SHAPE;



public class LineFactory {
	public static Line createLine( String modeType,Port pressPort,Port releasePort) {
		if ( modeType.equals("ASSOCIATION") ) {
			return new AssociationLine(pressPort,releasePort);
		}
		else if (modeType.equals("COMPOSITION")) {
			return new CompositionLine(pressPort,releasePort);
		}
		else if (modeType.equals("GENERALIZATION")) {
			return new GeneralizationLine(pressPort,releasePort);
		}
		else return null;
	}
}
