package in.jigyasacodes.totask.adap.deprecated;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import in.jigyasacodes.totask.R;

/**
 * Created by rahulsdeshpande on 12/9/15.
 */

public class ViewHolderLevel1 extends RecyclerView.ViewHolder {

	protected ImageView ivCourseLogo;
	protected TextView tvCourseName, tvInstructorName, tvUnivName;

	OnCourseItemClickListener onCourseItemClickListener;

	public ViewHolderLevel1(View view,OnCourseItemClickListener listener) {

		super(view);

		this.ivCourseLogo = (ImageView) view.findViewById(R.id.ivCourseLogo);

		this.tvCourseName = (TextView) view.findViewById(R.id.tvCourseName);
		this.tvInstructorName = (TextView) view.findViewById(R.id.tvInstructorName);
		this.tvUnivName = (TextView) view.findViewById(R.id.tvUnivName);

		// ITEM LISTENER
		this.onCourseItemClickListener = listener;
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	public interface OnCourseItemClickListener
	{
		public void onCourseItemClick(View view, final int POSITION);
	}
}
