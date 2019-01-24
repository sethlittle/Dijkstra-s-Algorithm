package A5_Dijkstra;

public class EntryPair implements EntryPair_Interface {
	public String label;
	public long priority;

	/*
	 * an adaption of the A3 Entry pair - it stores the weight as a priority and
	 * the string label as data
	 */

	public EntryPair(long aPriority, String label) {
		this.label = label;
		priority = aPriority;
	}

	public String getLabel() {
		return label;
	}

	public long getPriority() {
		return priority;
	}
}
