package in.jigyasacodes.totask.adap.deprecated;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.data.redifined.Element;
import in.jigyasacodes.totask.data.redifined.Instructor;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.data.redifined.University;

/**
 * Created by rahulsdeshpande on 12/9/15.
 */

public class RecyclerAdapterLevel1 extends RecyclerView.Adapter<RecyclerAdapterLevel1.ViewHolderLevel1> {

    private List<Meta> metaList;
    private Meta META;
    private List<Element> ELEMENT_LIST;
    private List<University> UNIV_LIST;
    private List<Instructor> INSTR_LIST;

    private Context mContext;

    OnCourseItemClickListener onCourseItemClickListener;

    public RecyclerAdapterLevel1(Context context, List<Element> ELEMENT_LIST/*,OnCourseItemClickListener listener*/) {

        Log.e("RecyclerAdapterLevel1()", ELEMENT_LIST.get(2).getShortDescription());
        this.ELEMENT_LIST = ELEMENT_LIST;
        this.mContext = context;

        //this.onCourseItemClickListener = listener;
    }

    public RecyclerAdapterLevel1(Context context, final Meta META, List<Element> ELEMENT_LIST/*,OnCourseItemClickListener listener*/) {

        Log.e("RecyclerAdapterLevel1()", ELEMENT_LIST.get(3).getShortDescription());
        this.META = META;
        UNIV_LIST = META.getLinked().getUniversities();
        INSTR_LIST = META.getLinked().getInstructors();
        this.ELEMENT_LIST = ELEMENT_LIST;
        this.mContext = context;

        //this.onCourseItemClickListener = listener;
    }

    @Override
    public ViewHolderLevel1 onCreateViewHolder(ViewGroup viewGroup, int i) {

        Log.e("onCreateViewHolder() > ", "1");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_rv_item_new_2, null);

        Log.e("onCreateViewHolder() > ", "2");

        //ViewHolderLevel1 viewHolder = new ViewHolderLevel1(view);
        return new ViewHolderLevel1(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderLevel1 viewHolderLevel1, int i) {

        //Meta meta = metaList.get(i);

        ////Course c = (Course) meta.getElements();

        Element elementTemp = ELEMENT_LIST.get(i);

        Log.e("onBindViewHolder() 1 > ", elementTemp.getSmallIcon());
        //	Download image using picasso library
        Picasso.with(mContext).load(elementTemp.getSmallIcon())
                .error(R.drawable.dp)
                .placeholder(R.drawable.dp)
                .into(viewHolderLevel1.ivCourseLogo);

        //Setting text view title
        Log.e("onBindViewHolder() 2 > ", elementTemp.getName());
        viewHolderLevel1.tvCourseName.setText(elementTemp.getName());

        /////////////////////////////////////////////////////////
        //  Shitty code to SORT Instructors' & Universities' data
        //  yet to be written
        /////////////////////////////////////////////////////////
        Log.e("onBindViewHolder() 2.1>", META.getLinked().getUniversities().get(i).getName());
        University universityTemp = META.getLinked().getUniversities().get(i);
        Log.e("onBindViewHolder() 3 > ", universityTemp.getName());
        viewHolderLevel1.tvUnivName.setText(universityTemp.getName());

        Instructor instructorTemp = META.getLinked().getInstructors().get(i);
        Log.e("onBindViewHolder() 4 > ", instructorTemp.getFirstName());
        viewHolderLevel1.tvInstructorName.setText(instructorTemp.getFirstName());

        //Listeners
    }

    @Override
    public int getItemCount() {
        return (null != ELEMENT_LIST ? ELEMENT_LIST.size() : 0);
    }

    ///////////////////////////////////////////////////////////////
    ////////////////////    ViewHolder  ///////////////////////////
    ///////////////////////////////////////////////////////////////

    public class ViewHolderLevel1 extends RecyclerView.ViewHolder {

        protected ImageView ivCourseLogo;
        protected TextView tvCourseName, tvInstructorName, tvUnivName;

        public ViewHolderLevel1(final View view) {

            super(view);

            this.ivCourseLogo = (ImageView) view.findViewById(R.id.ivCourseLogo);

            this.tvCourseName = (TextView) view.findViewById(R.id.tvCourseName);
            this.tvInstructorName = (TextView) view.findViewById(R.id.tvInstructorName);
            this.tvUnivName = (TextView) view.findViewById(R.id.tvUnivName);

            // ITEM LISTENER
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,
                            "Item #" + getLayoutPosition() + " Clicked !!",
                            Toast.LENGTH_LONG).show();

                    if (onCourseItemClickListener != null) {

                        onCourseItemClickListener.onCourseItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    public void setOnCourseItemClickListener(OnCourseItemClickListener onCourseItemClickListener) {
        this.onCourseItemClickListener = onCourseItemClickListener;
    }

    public interface OnCourseItemClickListener {
        public void onCourseItemClick(View view, final int POSITION);
    }
}