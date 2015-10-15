package in.jigyasacodes.totask.data.deprecated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahulsdeshpande on 12/9/15.
 */
public class Links {

	private List<Integer> universities = new ArrayList<Integer>();
	private List<Integer> instructors = new ArrayList<Integer>();

	/**
	 *
	 * @return
	 * The universities
	 */
	public List<Integer> getUniversities() {
		return universities;
	}

	/**
	 *
	 * @param universities
	 * The universities
	 */
	public void setUniversities(List<Integer> universities) {
		this.universities = universities;
	}

	/**
	 *
	 * @return
	 * The instructors
	 */
	public List<Integer> getInstructors() {
		return instructors;
	}

	/**
	 *
	 * @param instructors
	 * The instructors
	 */
	public void setInstructors(List<Integer> instructors) {
		this.instructors = instructors;
	}
}