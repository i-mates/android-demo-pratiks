package in.jigyasacodes.totask.data.deprecated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahulsdeshpande on 12/9/15.
 */
public class Coursera {

	private List<Course> courses = new ArrayList<Course>();

	/**
	 *
	 * @return
	 * The courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 *
	 * @param courses
	 * The courses
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}