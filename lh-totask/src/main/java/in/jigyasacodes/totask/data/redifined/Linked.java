package in.jigyasacodes.totask.data.redifined;

import java.util.ArrayList;
import java.util.List;

public class Linked {

	private List<University> universities = new ArrayList<University>();
	private List<Instructor> instructors = new ArrayList<Instructor>();

	/**
	 * @return The universities
	 */
	public List<University> getUniversities() {
		return universities;
	}

	/**
	 * @param universities The universities
	 */
	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}

	/**
	 * @return The instructors
	 */
	public List<Instructor> getInstructors() {
		return instructors;
	}

	/**
	 * @param instructors The instructors
	 */
	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}
}