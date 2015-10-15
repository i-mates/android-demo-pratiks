package in.jigyasacodes.totask.data.redifined;

import java.util.ArrayList;
import java.util.List;

public class Meta {

	private List<Element> elements = new ArrayList<>();

	private int courseCount = 0;

	private Linked linked;

	/**
	 * @return The elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * @param elements The elements
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}

	/**
	 * @return The linked
	 */
	public Linked getLinked() {
		return linked;
	}

	/**
	 * @param linked The linked
	 */
	public void setLinked(Linked linked) {
		this.linked = linked;
	}
}