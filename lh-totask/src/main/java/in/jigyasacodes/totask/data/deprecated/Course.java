package in.jigyasacodes.totask.data.deprecated;

/**
 * Created by rahulsdeshpande on 12/9/15.
 */
public class Course {

	private Integer id;
	private String shortName;
	private String name;
	private String largeIcon;
	private String shortDescription;
	private String smallIcon;
	private Links links;
	private String instructor;

	/**
	 * @return The id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return The shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName The shortName
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The largeIcon
	 */
	public String getLargeIcon() {
		return largeIcon;
	}

	/**
	 * @param largeIcon The largeIcon
	 */
	public void setLargeIcon(String largeIcon) {
		this.largeIcon = largeIcon;
	}

	/**
	 * @return The shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription The shortDescription
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return The smallIcon
	 */
	public String getSmallIcon() {
		return smallIcon;
	}

	/**
	 * @param smallIcon The smallIcon
	 */
	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	/**
	 * @return The links
	 */
	public Links getLinks() {
		return links;
	}

	/**
	 * @param links The links
	 */
	public void setLinks(Links links) {
		this.links = links;
	}

	/**
	 * @return The instructor
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor The instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
}