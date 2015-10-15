package in.jigyasacodes.totask.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.data.Consts;
import in.jigyasacodes.totask.util.FontUtils;

/**
 * Created by Rahul S Deshpande on 13/9/15.
 */
public class InfoFragment extends Fragment {

    private Context ctx;
    private TextView tvCourseName,tvInstrName,tvShortDesc,tvDeptName,tvUnivName;
    private ImageView ivCourseLogo,ivInstrPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        if (initUI(view)) {
            ;
        } else {

            Toast.makeText(ctx,
                    "Failed to bind the UI elements..\n\nExiting..",
                    Toast.LENGTH_LONG).show();
        }

        Log.e("!!!!!!!!!!!!",getArguments().getString("course_" + Consts.COURSES.NAME));
        
        this.initUI(view);
        this.floodDataOntoViews();
        
        Log.e("!!!!!!!!!!!!",getArguments().getString("course_" + Consts.COURSES.NAME));

        return view;
    }

    private boolean floodDataOntoViews() {

        tvCourseName.setText(getArguments().getString("course_" + Consts.COURSES.NAME));

        Picasso.with(ctx).load(getArguments().getString(Consts.COURSES.LARGE_ICON))
                .error(R.drawable.dp)
                .placeholder(R.drawable.dp)
                .into(ivCourseLogo);

        tvInstrName.setText(getArguments().getString(Consts.INSTRUCTORS.FIRST_NAME));

        Picasso.with(ctx).load(getArguments().getString(Consts.INSTRUCTORS.PHOTO_150))
                .error(R.drawable.dp)
                .placeholder(R.drawable.dp)
                .into(ivInstrPic);

        tvShortDesc.setText(getArguments().getString(Consts.COURSES.SHORT_DESCRIPTION));
        tvDeptName.setText(getArguments().getString(Consts.INSTRUCTORS.DEPARTMENT));
        tvUnivName.setText(getArguments().getString("univ_" + Consts.UNIVERSITIES.NAME));


        return true;
    }

    private boolean initUI(View view) {

        tvCourseName= (TextView) view.findViewById(R.id.tvCourseName);
        tvCourseName =  (TextView) FontUtils.typeFaceApplier(this.ctx, tvCourseName, FontUtils.JCTO_FONT_DOSIS_SEMIBOLD);        tvInstrName= (TextView) view.findViewById(R.id.tvInstrName);

        tvShortDesc= (TextView) view.findViewById(R.id.tvShortDesc);
        tvShortDesc =  (TextView) FontUtils.typeFaceApplier(this.ctx, tvShortDesc, FontUtils.JCTO_FONT_DOSIS_SEMIBOLD);

        tvDeptName= (TextView) view.findViewById(R.id.tvDeptName);
        tvDeptName =  (TextView) FontUtils.typeFaceApplier(this.ctx, tvDeptName, FontUtils.JCTO_FONT_DOSIS_SEMIBOLD);

        tvUnivName= (TextView) view.findViewById(R.id.tvUnivName);
        tvUnivName =  (TextView) FontUtils.typeFaceApplier(this.ctx, tvUnivName, FontUtils.JCTO_FONT_PACIFICO);

        ivCourseLogo = (ImageView) view.findViewById(R.id.ivCourseLogo);
        ivInstrPic = (ImageView) view.findViewById(R.id.ivInstrPic);

        return true;
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        this.ctx = activity.getApplicationContext();
    }
}