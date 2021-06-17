package UI;

public enum ModeList {
	SELECT(0),
	ASSOCIATION(1),
	GENERALIZATION(2),
	COMPOSITION(3),
	CLASS(4),
	USECASE(5);
	
	private int key;
	
	private ModeList(int key) {
		this.key= key;
	}
	
	public int getKey() {
		return key;
	}
}
