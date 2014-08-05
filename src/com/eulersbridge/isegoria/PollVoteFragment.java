package com.eulersbridge.isegoria;

import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class PollVoteFragment extends android.support.v4.app.Fragment {
	private View rootView;
	private TableLayout pollTableLayout;
	
	private float dpWidth;
	private float dpHeight;
	
	private boolean insertedFirstRow = false;
	
	public PollVoteFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.poll_vote_fragment, container, false);
		DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		pollTableLayout = (TableLayout) rootView.findViewById(R.id.pollTableLayout);
		
		dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;  
		
        addTableRow("Consider the current federal parties if an election was held today, which party would you vote?", "Asked by Eve Menedez");
        createProgressBars("#FFFF00");
        createProgressBars("#FFFF00");
        createProgressBars("#FFFF00");
        createProgressBars("#FFFF00");
        
		return rootView;
	}
	
	public void addTableRow(String label, String caption) {
		TableRow tr = new TableRow(getActivity());
		if(!insertedFirstRow) {
			insertedFirstRow = true;
			tr.setPadding(10, 10, 0, 10);
		}
		else {
			tr.setPadding(10, 0, 0, 10);
		}
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
		tr.setLayoutParams(rowParams);
		
		ImageView view = new ImageView(getActivity());
		view.setColorFilter(Color.argb(125, 35, 35, 35));
		view.setLayoutParams(new TableRow.LayoutParams(100, (int)(100)));
		view.setScaleType(ScaleType.CENTER_CROP);
        view.setImageResource(R.drawable.news0);
		
		LinearLayout linearLayout = new LinearLayout(getActivity());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setGravity(Gravity.CENTER_VERTICAL);
		linearLayout.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
		linearLayout.setPadding(10, 0, 0, 0);
        
        TextView textViewArticle = new TextView(getActivity());
        textViewArticle.setTextColor(Color.parseColor("#000000"));
        textViewArticle.setTextSize(18.0f);
        textViewArticle.setText(label);
        textViewArticle.setGravity(Gravity.LEFT);
        
        TextView textViewArticleTime = new TextView(getActivity());
        textViewArticleTime.setTextColor(Color.parseColor("#000000"));
        textViewArticleTime.setTextSize(12.0f);
        textViewArticleTime.setText(caption);
        textViewArticleTime.setPadding(0, 0, 0, 0);
        textViewArticleTime.setGravity(Gravity.LEFT);
        
        linearLayout.addView(textViewArticle);
        linearLayout.addView(textViewArticleTime);
        
        tr.addView(view);
        tr.addView(linearLayout);	
        pollTableLayout.addView(tr);
	}
	
	public void createProgressBars(String color) {
		TableRow tr = new TableRow(getActivity());
		if(!insertedFirstRow) {
			insertedFirstRow = true;
			tr.setPadding(10, 10, 0, 10);
		}
		else {
			tr.setPadding(10, 0, 0, 10);
		}
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
		tr.setLayoutParams(rowParams);
		//tr.setGravity(Gravity.CENTER_HORIZONTAL);
		
		ProgressBar pb = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleHorizontal);
		pb.setProgress(50);
		pb.setMax(100);
		
		final float[] roundedCorners = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
		ShapeDrawable pgDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners, null, null));
		pgDrawable.getPaint().setColor(Color.parseColor(color));

		ClipDrawable progress = new ClipDrawable(pgDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
		pb.setProgressDrawable(progress);

		pb.setBackgroundDrawable(getActivity().getResources().getDrawable(android.R.drawable.progress_horizontal));
		
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setPadding(10, 0, 0, 10);
		layout.setGravity(Gravity.CENTER_VERTICAL);
		layout.addView(pb, params);
		
		ImageView view = new ImageView(getActivity());
		view.setColorFilter(Color.argb(125, 35, 35, 35));
		view.setLayoutParams(new TableRow.LayoutParams(100, (int)(100)));
		view.setScaleType(ScaleType.CENTER_CROP);
        view.setImageResource(R.drawable.news0);
		
        tr.addView(view);
		tr.addView(layout);
		pollTableLayout.addView(tr);
	}
}
