/**
 * Copyright (C) 2015 Rahul S Deshpande & Jigyasa Codes
 *
 * RECIPROCAL PUBLIC LICENSE
 * 
 * Unless explicitly acquired and licensed from Licensor under
 * another license, the contents of this file are subject to the
 * Reciprocal Public License ("RPL") Version 1.3, or subsequent
 * versions as allowed by the RPL, and You may not copy or use this
 * file in either source code or executable form, except in
 * compliance with the terms and conditions of the RPL.
 *
 * http://www.technicalpursuit.com/licenses/RPL_1.3.html
 * 
 * All software distributed under the RPL is provided strictly on an
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH WARRANTIES,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, QUIET ENJOYMENT, OR
 * NON-INFRINGEMENT. See the RPL for specific language governing
 * rights and limitations under the RPL.
 **/

package in.jigyasacodes.totask.adap;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.data.redifined.Element;
import in.jigyasacodes.totask.data.redifined.Instructor;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.data.redifined.University;
import in.jigyasacodes.totask.util.FontUtils;

public class CustomArrAdapCourses extends ArrayAdapter<String> {

	private Context ctx;

	private Meta META;
	private List<String> listStrElements;
	private List<Element> ELEMENT_LIST;
	private List<University> UNIV_LIST;
	private List<Instructor> INSTR_LIST;

	// private String STR_CATEGORY;

	public CustomArrAdapCourses(Context ctx, int resource,
			int textViewResourceId,/* String[] strArrElements, */
			final Meta META, List<Element> ELEMENT_LIST) {

		super(ctx, resource, textViewResourceId/* , strArrElements */);

		Log.e(this.getClass().getSimpleName(), "6");

		this.ctx = ctx;

		// this.strArrElements = strArrElements;
		this.META = META;
		this.ELEMENT_LIST = ELEMENT_LIST;
		UNIV_LIST = META.getLinked().getUniversities();
		INSTR_LIST = META.getLinked().getInstructors();
		// this.prods = prods;
		// this.STR_CATEGORY = STR_CATEGORY;
	}

	public CustomArrAdapCourses(Context ctx, int resource,
			int textViewResourceId, List<String> listStrElements,
			final Meta META, List<Element> ELEMENT_LIST) {

		super(ctx, resource, textViewResourceId, listStrElements);

		Log.e(this.getClass().getSimpleName(), "6");

		this.ctx = ctx;

		this.listStrElements = listStrElements;
		this.META = META;
		this.ELEMENT_LIST = ELEMENT_LIST;
		UNIV_LIST = META.getLinked().getUniversities();
		INSTR_LIST = META.getLinked().getInstructors();
		// this.prods = prods;
		// this.STR_CATEGORY = STR_CATEGORY;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Log.e(this.getClass().getSimpleName(), position + "----------");

		LayoutInflater inflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		Log.e(this.getClass().getSimpleName(), "7");

		convertView = inflater.inflate(R.layout.course_rv_item_new_2, parent,
				false);

		convertView = floodDataOnItem(convertView, position);

		return super.getView(position, convertView, parent);
	}

	private View floodDataOnItem(View convertView, final int POSITION) {

		ImageView ivCourseLogo;
		TextView tvCourseName, tvInstructorName, tvUnivName;

		ivCourseLogo = (ImageView) convertView.findViewById(R.id.ivCourseLogo);

		tvCourseName = (TextView) convertView.findViewById(R.id.tvCourseName);
		tvCourseName = (TextView) FontUtils.typeFaceApplier(this.ctx,
				tvCourseName, FontUtils.JCTO_FONT_DOSIS_SEMIBOLD);

		tvInstructorName = (TextView) convertView
				.findViewById(R.id.tvInstructorName);
		tvInstructorName = (TextView) FontUtils.typeFaceApplier(this.ctx,
				tvInstructorName, FontUtils.JCTO_FONT_DOSIS_SEMIBOLD);

		tvUnivName = (TextView) convertView.findViewById(R.id.tvUnivName);
		tvUnivName = (TextView) FontUtils.typeFaceApplier(this.ctx, tvUnivName,
				FontUtils.JCTO_FONT_DOSIS_SEMIBOLD);

		Element elementTemp = ELEMENT_LIST.get(POSITION);

		Log.e("floodDataOnItem() 1 > ", elementTemp.getSmallIcon());
		// Download image using picasso library
		Picasso.with(ctx).load(elementTemp.getSmallIcon()).error(R.drawable.dp)
				.placeholder(R.drawable.dp).into(ivCourseLogo);

		// Setting text view title
		Log.e("floodDataOnItem() 2 > ", elementTemp.getName());
		tvCourseName.setText(elementTemp.getName());

		// ///////////////////////////////////////////////////////
		// Shitty code to SORT Instructors' & Universities' data
		// yet to be written
		// ///////////////////////////////////////////////////////
		Log.e("floodDataOnItem() 2.1>",
				META.getLinked().getUniversities().get(POSITION).getName());
		University universityTemp = META.getLinked().getUniversities()
				.get(POSITION);
		Log.e("floodDataOnItem() 3 > ", universityTemp.getName());
		tvUnivName.setText(universityTemp.getName());

		Instructor instructorTemp = META.getLinked().getInstructors()
				.get(POSITION);

		tvInstructorName.setText(instructorTemp.getFirstName());

		Log.e("floodDataOnItem() 4 > ", "---------");

		return convertView;
	}

	@Deprecated
	private View typeFaceApplier1(View view, String strFontName) {

		Typeface tf = Typeface.createFromAsset(ctx.getAssets(), strFontName);

		if (view instanceof Button) {

			((Button) view).setTypeface(tf);

		} else {

			((TextView) view).setTypeface(tf);
		}

		return view;
	}
}