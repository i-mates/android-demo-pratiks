package in.jigyasacodes.totask.data;

public final class Consts {

	public static final String NET_ISSUE_AD_TITLE = "You are OFFLINE !!";
	public static final String NET_ISSUE_AD_MESSAGE = "You are OFFLINE !!\n\nPlease switch ON the internet connection.";
	
	public static final class COURSERA {

		public static final String URL_HOST = "https://api.coursera.org";

		public static final String URL_BASE = "/api/catalog.v1";

		public static final String URL_COURSES = "/courses";
		public static final String URL_INSTRUCTORS = "/courses";
		public static final String URL_UNIVERSITIES = "/courses";
		public static final String URL_SESSIONS = "/sessions";

		public static final String URL_PARAM_QUERY_ID = "id=";
		public static final String URL_PARAM_QUERY_IDS = "ids=";
		public static final String URL_PARAM_QUERY_FIELDS = "fields=";
		public static final String URL_PARAM_QUERY_INCLUDES = "includes=";

		public static final String URL_Q = "?";
		public static final String URL_AND = "&";
	}

	public static final class FOREIGN {

		public static final String COURSES = "courses.fields";
		public static final String UNIVERSITIES = "universities.fields";
		public static final String INSTRUCTORS = "instructors.fields";
		public static final String CATEGORIES = "categories.fields";
		public static final String SESSIONS = "sessions.fields";
	}

	public static final class INCLUDES {

		public static final String COURSES = "courses";
		public static final String UNIVERSITIES = "universities";
		public static final String INSTRUCTORS = "instructors";
		public static final String CATEGORIES = "categories";
		public static final String SESSIONS = "sessions";
	}

	public static final class COURSES {

		public static final String URL_COURSES_ALL = COURSERA.URL_HOST + COURSERA.URL_BASE + COURSERA.URL_COURSES + COURSERA.URL_COURSES;

		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String SMALL_ICON = "smallIcon";
		public static final String LARGE_ICON = "largeIcon";
		public static final String SHORT_DESCRIPTION = "shortDescription";
		public static final String INSTRUCTOR = "instructor";
	}

	public static final class UNIVERSITIES {

		public static final String URL_UNIVERSITIES_ALL = COURSERA.URL_HOST + COURSERA.URL_BASE + COURSERA.URL_COURSES + COURSERA.URL_UNIVERSITIES;

		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String SHORT_NAME = "shortName";
	}

	public static final class INSTRUCTORS {

		public static final String URL_INSTRUCTORS_ALL = COURSERA.URL_HOST + COURSERA.URL_BASE + COURSERA.URL_COURSES + COURSERA.URL_INSTRUCTORS;

		public static final String ID = "id";
		public static final String PHOTO_150 = "photo150";
		public static final String DEPARTMENT = "department";
		public static final String FIRST_NAME = "firstName";
		public static final String MIDDLE_NAME = "middleName";
		public static final String LAST_NAME = "lastName";
		public static final String FULL_NAME = "fullName";
	}

	public static final class JSON {

		public static final String ELEMENTS = "elements";
		public static final String LINKS = "links";

		public static final String LINKED = "linked";
	}
}