package in.jigyasacodes.totask.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import in.jigyasacodes.totask.data.Consts;
import in.jigyasacodes.totask.data.redifined.Element;
import in.jigyasacodes.totask.data.redifined.Instructor;
import in.jigyasacodes.totask.data.redifined.Linked;
import in.jigyasacodes.totask.data.redifined.Links;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.data.redifined.University;

public class JSONUtils {

	// public JSONData jsonData = null;
	private String strJSONRaw = null;
	private JSONObject jsonObjMain = null;

	private List<Links> listLinksTemp;

	public JSONUtils(final String STR_RAW_JSON) {

		// jsonData = new JSONData(strJSONRaw);
		// jsonData.setStrJSONRaw(this.strJSONRaw);

		this.strJSONRaw = STR_RAW_JSON;
	}

	public JSONUtils(final JSONObject JSON_OBJ_MAIN) {

		// jsonData = new JSONData(strJSONRaw);
		// jsonData.setStrJSONRaw(this.strJSONRaw);

		this.jsonObjMain = JSON_OBJ_MAIN;
	}

	public static JSONObject verifyJSON(final String STR_RAW) {

		try {

			final JSONObject JSON_OBJ_MAIN = new JSONObject(STR_RAW);
			Log.e("JSONUtils- verifyJSON()",
					"RAW RESPONSE data Successfully Parsed to JSON ------------\n"
							+ STR_RAW + "\n------------");

			return JSON_OBJ_MAIN;

		} catch (JSONException e) {

			e.printStackTrace();
			Log.e("JSONUtils- verifyJSON()",
					"Error parsing JSON data..\n--------\n" + e.toString()
							+ "\n--------");
			return null;
		}
	}

	public Meta processAndStoreRawJSONData() {

		Meta meta = new Meta();

		try {

			JSONObject JSON_OBJ_MAIN = null;

			if (jsonObjMain.equals(null)/* && !strJSONRaw.equals(null) */) {

				JSON_OBJ_MAIN = new JSONObject(strJSONRaw);

			} else {

				JSON_OBJ_MAIN = jsonObjMain;
			}

			// /////////////////////////////////////////////////////////////////////
			// REDINE REVOLUIONARIZE REGENERATE REFINE REWIND RESET RESTART
			// REPLAY
			// /////////////////////////////////////////////////////////////////////

			meta.setElements(this.parseElements(JSON_OBJ_MAIN
					.getJSONArray(Consts.JSON.ELEMENTS)));
			final int COURSE_COUNT = meta.getElements().size();

			meta.setCourseCount(COURSE_COUNT);

			// meta.setLinked(this.parseLinked(JSON_OBJ_MAIN
			// .getJSONObject(Consts.JSON.LINKED)));
			meta.setLinked(this.parseLinkedTM(
					(JSON_OBJ_MAIN.getJSONObject(Consts.JSON.LINKED)),
					COURSE_COUNT));

			// Log.e("IS meta NULL ?",
			// meta.getLinked().getUniversities().get(0).getName());

			/*
			 * meta.getLinked() .setUniversities( this.parseUniversities(
			 * JSON_OBJ_MAIN.getJSONObject( Consts.JSON.LINKED).getJSONArray(
			 * Consts.INCLUDES.UNIVERSITIES), COURSE_COUNT));
			 * 
			 * 
			 * meta.getLinked().setInstructors( this.parseInstructors(
			 * JSON_OBJ_MAIN.getJSONObject(Consts.JSON.LINKED)
			 * .getJSONArray(Consts.INCLUDES.INSTRUCTORS), COURSE_COUNT));
			 */
			// //////////////////////////////////////////////////////////////

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return meta;
	}

	private List<Element> parseElements(final JSONArray JSON_ARR_ELEMENTS) {

		listLinksTemp = new ArrayList<>();

		List<Element> elementListTemp = new ArrayList<>();

		// Element elementTemp = new Element();

		final int ELEMENT_CNT = JSON_ARR_ELEMENTS.length();

		try {

			for (int i = 0; i < ELEMENT_CNT; ++i) {

				Element elementTemp = new Element();

				JSONObject jsonObjectTemp = JSON_ARR_ELEMENTS.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding Element POJO
				// ////////////////////////////
				// ///////////////////////////////////////////////////////////////////////////
				elementTemp.setId(jsonObjectTemp.getInt(Consts.COURSES.ID));
				// Unnecessary boxing of 'Integer.valueOf()' :O Thus, removed !!
				elementTemp.setName(jsonObjectTemp
						.getString(Consts.COURSES.NAME));
				elementTemp.setSmallIcon(jsonObjectTemp
						.getString(Consts.COURSES.SMALL_ICON));
				elementTemp.setLargeIcon(jsonObjectTemp
						.getString(Consts.COURSES.LARGE_ICON));
				elementTemp.setShortDescription(jsonObjectTemp
						.getString(Consts.COURSES.SHORT_DESCRIPTION));

				Log.e("INSTRUCTOR ERROR -> ",
						"ID - " + jsonObjectTemp.getInt(Consts.COURSES.ID));
				// elementTemp.setInstructor(jsonObjectTemp.getString(Consts.COURSES.INSTRUCTOR));
				// ///////////////////////////////////////////////////////////////////////////

				// ///////////////// Feeding Element -> Links POJO
				// //////////////////////
				elementTemp.setLinks(this.parseElementLinks(jsonObjectTemp
						.getJSONObject(Consts.JSON.LINKS)));
				// ///////////////////////////////////////////////////////////////////////////

				elementListTemp.add(i, elementTemp);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return elementListTemp;
	}

	private Links parseElementLinks(final JSONObject JSON_OBJ_LINKS) {

		Links linksTemp = new Links();
		try {

			final JSONArray JSON_ARR_LINKS_UNIV = JSON_OBJ_LINKS
					.getJSONArray(Consts.INCLUDES.UNIVERSITIES);
			linksTemp.setUniversities(this
					.parseElementLinksUniversity(JSON_ARR_LINKS_UNIV));

			// Log.e("INSTRUCTORS ERROR -> ","ID - "+jsonObjectTemp.getInt(Consts.COURSES.ID));
			final JSONArray JSON_ARR_LINKS_INSTR = JSON_OBJ_LINKS
					.getJSONArray(Consts.INCLUDES.INSTRUCTORS);
			linksTemp.setInstructors(this
					.parseElementLinksInstructor(JSON_ARR_LINKS_INSTR));

		} catch (JSONException e) {

			e.printStackTrace();
		}

		// !!!!!!!!!!!!!!!!!!!!!!!
		listLinksTemp.add(linksTemp);

		return linksTemp;
	}

	private List<Integer> parseElementLinksUniversity(
			final JSONArray JSON_ARR_ELEMENTS_UNIVERSITY) {

		List<Integer> linksUnivListTemp = new ArrayList<>();

		final int LINKS_UNIV_COUNT = JSON_ARR_ELEMENTS_UNIVERSITY.length();

		for (int i = 0; i < LINKS_UNIV_COUNT; ++i) {

			try {

				linksUnivListTemp
						.add(i, JSON_ARR_ELEMENTS_UNIVERSITY.getInt(i));

				Log.e("parseElementLinksUniversity() 1", "ID - "
						+ JSON_ARR_ELEMENTS_UNIVERSITY.getInt(i));

			} catch (JSONException e) {

				e.printStackTrace();
			}
		}

		return linksUnivListTemp;
	}

	private List<Integer> parseElementLinksInstructor(
			final JSONArray JSON_ARR_ELEMENTS_INSTRUCTOR) {

		List<Integer> linksInstrListTemp = new ArrayList<>();

		final int LINKS_UNIV_COUNT = JSON_ARR_ELEMENTS_INSTRUCTOR.length();

		for (int i = 0; i < LINKS_UNIV_COUNT; ++i) {

			try {

				linksInstrListTemp.add(i,
						JSON_ARR_ELEMENTS_INSTRUCTOR.getInt(i));

				Log.e("parseElementLinksInstructor() 1", "ID - "
						+ JSON_ARR_ELEMENTS_INSTRUCTOR.getInt(i));

				// TRIAL //

			} catch (JSONException e) {

				Log.e("parseElementLinksInstructor() 1", "ID - " + "catch()");
				e.printStackTrace();
			}
		}

		return linksInstrListTemp;
	}

	@Deprecated
	private Linked parseLinked(final JSONObject JSON_OBJ_LINKED) {

		Linked linked = new Linked();

		try {

			linked.setUniversities(this.parseUniversities(JSON_OBJ_LINKED
					.getJSONArray(Consts.INCLUDES.UNIVERSITIES)));

			linked.setInstructors(this.parseInstructors(JSON_OBJ_LINKED
					.getJSONArray(Consts.INCLUDES.INSTRUCTORS)));

		} catch (JSONException e) {

			e.printStackTrace();
		}

		// KCUF !! This li'l piece of shit, got me almost drowned for 2 hrs..
		// ):O
		return linked;
	}

	private Linked parseLinkedTM(final JSONObject JSON_OBJ_LINKED,
			final int COURSE_COUNT) {

		try {

			Log.e("parseLinkedTM() 1", "COURSE_COUNT - " + COURSE_COUNT);

			// KKKKCUF !! This li'l piece of shit, got me almost drowned for 3
			// hrs.. ):O
			return parseLinkedUnivInstrTM(
					JSON_OBJ_LINKED.getJSONArray(Consts.INCLUDES.UNIVERSITIES),
					JSON_OBJ_LINKED.getJSONArray(Consts.INCLUDES.INSTRUCTORS),
					COURSE_COUNT);

		} catch (JSONException e1) {

			Log.e("parseLinkedTM() - catch() 2", "e1 - " + e1.getMessage());

			e1.printStackTrace();
		}

		Log.e("parseLinkedTM() 3", "!!!!!!!!!!!!!!!");

		return null;
	}

	@Deprecated
	private List<University> parseUniversities(
			final JSONArray JSON_ARR_UNIVERSITIES/* , final int COURSE_COUNT */) {

		List<University> universityListTemp = new ArrayList<>();

		final int UNIVERSITY_CNT = JSON_ARR_UNIVERSITIES.length();

		try {

			for (int i = 0; i < UNIVERSITY_CNT; ++i) {

				University universityTemp = new University();

				JSONObject jsonObjectTemp = JSON_ARR_UNIVERSITIES
						.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding University POJO
				// /////////////////////////
				// ///////////////////////////////////////////////////////////////////////////
				universityTemp.setId(jsonObjectTemp
						.getInt(Consts.UNIVERSITIES.ID));

				if (!jsonObjectTemp.isNull(Consts.UNIVERSITIES.NAME)) {
					Log.e("UNIVERSITIES.NAME !ERROR ------> ",
							"UNIV_ID - "
									+ jsonObjectTemp
											.getString(Consts.UNIVERSITIES.NAME));
					universityTemp.setName(jsonObjectTemp
							.getString(Consts.UNIVERSITIES.NAME));
				} else {
					universityTemp.setName("N/A");
				}

				// ///////////////////////////////////////////////////////////////////////////

				// universityListTemp.add(i, universityTemp);
				universityListTemp.add(universityTemp);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return universityListTemp;
	}

	@Deprecated
	private List<Instructor> parseInstructors(
			final JSONArray JSON_ARR_INSTRUCTORS/* , final int COURSE_COUNT */) {

		List<Instructor> instructorListTemp = new ArrayList<>();

		final int INSTRUCTOR_CNT = JSON_ARR_INSTRUCTORS.length();

		try {

			for (int i = 0; i < INSTRUCTOR_CNT; ++i) {

				Instructor instructorTemp = new Instructor();

				JSONObject jsonObjectTemp = JSON_ARR_INSTRUCTORS
						.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding Instructor POJO
				// ////////////////////////////
				// ///////////////////////////////////////////////////////////////////////////
				instructorTemp.setId(jsonObjectTemp
						.getInt(Consts.INSTRUCTORS.ID));
				instructorTemp.setPhoto150(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.PHOTO_150));
				instructorTemp.setFirstName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.FIRST_NAME));
				instructorTemp.setMiddleName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.MIDDLE_NAME));
				instructorTemp.setLastName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.LAST_NAME));
				instructorTemp.setFullName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.FULL_NAME));
				instructorTemp.setDepartment(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.DEPARTMENT));
				// ///////////////////////////////////////////////////////////////////////////

				// instructorListTemp.add(i, instructorTemp);
				instructorListTemp.add(instructorTemp);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return instructorListTemp;
	}

	private List<University> parseUniversitiesTM(
			final JSONArray JSON_ARR_UNIVERSITIES/* , final int COURSE_COUNT */) {

		List<University> universityListTemp = new ArrayList<>();

		final int UNIVERSITY_CNT = JSON_ARR_UNIVERSITIES.length();

		try {

			for (int i = 0; i < UNIVERSITY_CNT; ++i) {

				University universityTemp = new University();

				JSONObject jsonObjectTemp = JSON_ARR_UNIVERSITIES
						.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding University POJO
				// /////////////////////////
				// ///////////////////////////////////////////////////////////////////////////
				universityTemp.setId(jsonObjectTemp
						.getInt(Consts.UNIVERSITIES.ID));

				if (!jsonObjectTemp.isNull(Consts.UNIVERSITIES.NAME)) {
					Log.e("UNIVERSITIES.NAME !ERROR ------> ",
							"UNIV_ID - "
									+ jsonObjectTemp
											.getString(Consts.UNIVERSITIES.NAME));
					universityTemp.setName(jsonObjectTemp
							.getString(Consts.UNIVERSITIES.NAME));
				} else {
					universityTemp.setName("N/A");
				}

				// ///////////////////////////////////////////////////////////////////////////

				// universityListTemp.add(i, universityTemp);
				universityListTemp.add(universityTemp);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return universityListTemp;
	}

	private List<Instructor> parseInstructorsTM(
			final JSONArray JSON_ARR_INSTRUCTORS/* , final int COURSE_COUNT */) {

		List<Instructor> instructorListTemp = new ArrayList<>();

		final int INSTRUCTOR_CNT = JSON_ARR_INSTRUCTORS.length();

		try {

			for (int i = 0; i < INSTRUCTOR_CNT; ++i) {

				Instructor instructorTemp = new Instructor();

				JSONObject jsonObjectTemp = JSON_ARR_INSTRUCTORS
						.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding Instructor POJO
				// ////////////////////////////
				// ///////////////////////////////////////////////////////////////////////////
				instructorTemp.setId(jsonObjectTemp
						.getInt(Consts.INSTRUCTORS.ID));
				instructorTemp.setPhoto150(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.PHOTO_150));
				instructorTemp.setFirstName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.FIRST_NAME));
				instructorTemp.setMiddleName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.MIDDLE_NAME));
				instructorTemp.setLastName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.LAST_NAME));
				instructorTemp.setFullName(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.FULL_NAME));
				instructorTemp.setDepartment(jsonObjectTemp
						.getString(Consts.INSTRUCTORS.DEPARTMENT));
				// ///////////////////////////////////////////////////////////////////////////

				// instructorListTemp.add(i, instructorTemp);
				instructorListTemp.add(instructorTemp);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return instructorListTemp;
	}

	private Linked parseLinkedUnivInstrTM(
			final JSONArray JSON_ARR_UNIVERSITIES,
			final JSONArray JSON_ARR_INSTRUCTORS, final int COURSE_COUNT) {

		Linked linkedTemp = new Linked();

		List<University> univListTemp = new ArrayList<>();
		List<Instructor> instrListTemp = new ArrayList<>();

		final int UNIVERSITY_CNT = JSON_ARR_UNIVERSITIES.length();
		final int INSTRUCTOR_CNT = JSON_ARR_INSTRUCTORS.length();

		final TreeMap<Integer, String> TM_IS_UNIV = this.sortUniversities(
				JSON_ARR_UNIVERSITIES, UNIVERSITY_CNT);
		final TreeMap<Integer, String[]> TM_ISa_INSTR = this.sortInstructors(
				JSON_ARR_INSTRUCTORS, INSTRUCTOR_CNT);

		try {

			for (int i = 0; i < COURSE_COUNT; ++i) {

				University univTemp = new University();
				Instructor instrTemp = new Instructor();

				// Log.e("parseLinkedUnivInstrTM() - for()",
				// listLinksTemp.get(i)
				// .getUniversities().get(i).toString());

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding Linked POJO
				// //////////////////////////////////
				// ///////////////////////////////////////////////////////////////////////////
				if (TM_IS_UNIV.containsKey(listLinksTemp.get(i)
						.getUniversities().get(0))) {

					Log.e("parseLinkedUnivInstrTM() - for() - if()", "i: " + i);

					try {

						univTemp.setId(listLinksTemp.get(i).getUniversities()
								.get(0));
						univTemp.setName(TM_IS_UNIV.get(listLinksTemp.get(i)
								.getUniversities().get(0)));

					}
					catch (Exception e)
					{
						Log.e("pLUITM() - for() - if() - catch()1", "e: "+e.getMessage());
						univTemp.setId(0000);
						univTemp.setName("N/A");
					}
					listLinksTemp.get(i);
					
					// INSTR
					try {

						if (listLinksTemp.get(i).getInstructors().get(0) != null//.equals(null)
							/*&& listLinksTemp.get(i).getInstructors().size() == 0*/
								) {

							instrTemp.setId(listLinksTemp.get(i).getInstructors()
									.get(0));
							instrTemp.setPhoto150(TM_ISa_INSTR.get(listLinksTemp
									.get(i).getInstructors().get(0))[0]);
							instrTemp.setFirstName(TM_ISa_INSTR.get(listLinksTemp
									.get(i).getInstructors().get(0))[1]);
							instrTemp.setMiddleName(TM_ISa_INSTR.get(listLinksTemp
									.get(i).getInstructors().get(0))[2]);
							instrTemp.setLastName(TM_ISa_INSTR.get(listLinksTemp
									.get(i).getInstructors().get(0))[3]);
							instrTemp.setFullName(TM_ISa_INSTR.get(listLinksTemp
									.get(i).getInstructors().get(0))[4]);
							instrTemp.setDepartment(TM_ISa_INSTR.get(listLinksTemp
									.get(i).getInstructors().get(0))[5]);

						} else {

							Log.e("parseLinkedUnivInstrTM() - for() - if() - else()", "i: " + i);

							instrTemp.setId(0000);
							instrTemp.setPhoto150("N/A");
							instrTemp.setFirstName("N/A");
							instrTemp.setMiddleName("N/A");
							instrTemp.setLastName("N/A");
							instrTemp.setFullName("N/A");
							instrTemp.setDepartment("N/A");
						}
					} catch (Exception e) {
						//Log.e("pLUITM() - for() - if() - catch()2", "e: "+e.getMessage());
						instrTemp.setId(0000);
						instrTemp.setPhoto150("N/A");
						instrTemp.setFirstName("N/A");
						instrTemp.setMiddleName("N/A");
						instrTemp.setLastName("N/A");
						instrTemp.setFullName("N/A");
						instrTemp.setDepartment("N/A");
					}

				} else {

					Log.e("parseLinkedUnivInstrTM() - for() - else()", "i: "
							+ i);
					univTemp.setName("N/A");
				}

				// ///////////////////////////////////////////////////////////////////////////

				// universityListTemp.add(i, universityTemp);
				univListTemp.add(univTemp);
				instrListTemp.add(instrTemp);
			}

			linkedTemp.setUniversities(univListTemp);
			linkedTemp.setInstructors(instrListTemp);

		} catch (Exception e) {
			//e.printStackTrace();
		}
		return linkedTemp;
	}

	private TreeMap<Integer, String> sortUniversities(
			final JSONArray JSON_ARR_UNIVERSITIES, final int LINKED_UNIV_COUNT) {

		// HashMap<Integer,String> hmIS = new HashMap<>();
		// SortedMap<Integer,String> smIS = new

		TreeMap<Integer, String> tmISUniv = new TreeMap<>();

		try {

			for (int i = 0; i < LINKED_UNIV_COUNT; ++i) {

				final JSONObject JSON_OBJ_TEMP = JSON_ARR_UNIVERSITIES
						.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding University TreeMap
				// //////////////////////////
				// ///////////////////////////////////////////////////////////////////////////

				Log.e("sortUniversities() - for() - if()",
						"--------\ni: "
								+ i
								+ "\nKEY: "
								+ JSON_OBJ_TEMP.getInt(Consts.UNIVERSITIES.ID)
								+ "\nVALUE: "
								+ (!JSON_OBJ_TEMP
										.isNull(Consts.UNIVERSITIES.NAME) ? JSON_OBJ_TEMP
										.getString(Consts.UNIVERSITIES.NAME)
										: "N/A"));
				tmISUniv.put(
						JSON_OBJ_TEMP.getInt(Consts.UNIVERSITIES.ID),
						!JSON_OBJ_TEMP.isNull(Consts.UNIVERSITIES.NAME) ? JSON_OBJ_TEMP
								.getString(Consts.UNIVERSITIES.NAME) : "N/A");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Log.e("sortUniversities()", "-----");

		return tmISUniv;
	}

	private TreeMap<Integer, String[]> sortInstructors(
			final JSONArray JSON_ARR_INSTRUCTORS, final int LINKED_INSTR_COUNT) {
		// HashMap<Integer,String> hmIS = new HashMap<>();
		// SortedMap<Integer,String> smIS = new

		TreeMap<Integer, String[]> tmISaInstr = new TreeMap<>();

		try {

			for (int i = 0; i < LINKED_INSTR_COUNT; ++i) {

				final JSONObject JSON_OBJ_TEMP = JSON_ARR_INSTRUCTORS
						.getJSONObject(i);

				// ///////////////////////////////////////////////////////////////////////////
				// //////////////////// Feeding Instructor TreeMap
				// //////////////////////////
				// ///////////////////////////////////////////////////////////////////////////

				final String[] STR_ARR_TEMP = {
						JSON_OBJ_TEMP.getString(Consts.INSTRUCTORS.PHOTO_150),
						JSON_OBJ_TEMP.getString(Consts.INSTRUCTORS.FIRST_NAME),
						JSON_OBJ_TEMP.getString(Consts.INSTRUCTORS.MIDDLE_NAME),
						JSON_OBJ_TEMP.getString(Consts.INSTRUCTORS.LAST_NAME),
						JSON_OBJ_TEMP.getString(Consts.INSTRUCTORS.FULL_NAME),
						JSON_OBJ_TEMP.getString(Consts.INSTRUCTORS.DEPARTMENT) };

				tmISaInstr.put(JSON_OBJ_TEMP.getInt(Consts.INSTRUCTORS.ID),
						STR_ARR_TEMP);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return tmISaInstr;
	}

	private String[] getStrinsgArr(final JSONArray JSON_ARR)
			throws JSONException {

		String[] strArrSkillsTemp = new String[JSON_ARR.length()];

		for (int j = 0; j < JSON_ARR.length(); ++j) {

			strArrSkillsTemp[j] = JSON_ARR.getString(j);
		}

		return strArrSkillsTemp;
	}
}