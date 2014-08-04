package com.eulersbridge.isegoria;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class NewsFragment extends Fragment {
	private View rootView;
	private TableLayout newsTableLayout;
	
	private float dpWidth;
	private float dpHeight;
	
	public NewsFragment() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		rootView = inflater.inflate(R.layout.news_fragment, container, false);
		newsTableLayout = (TableLayout) rootView.findViewById(R.id.newsTableLayout);
		
		dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;  
		
		addTableRow(R.drawable.news0, -1, false, false, "Labor pulls ahead in Nielson poll", "Yesterday, 9:00 AM", "", "");
		addTableRow(R.drawable.news1, R.drawable.news2, true, false, "MELBOURNE RANKED #1 IN STUDENT VOTING", "Friday, 1:00 PM", "NEW BALLOT BOXES OPEN", "Friday, 1:00 PM");
		addTableRow(R.drawable.news3, -1, false, false, "ANON PROTEST ON CAMPUS", "Wednesday, 2:00 PM", "", "");
		addTableRow(R.drawable.news4, R.drawable.news5, true, false, "Fem Protest", "Wednesday, 2:00 PM", "UNI FUNDING CUTS", "Friday, 1:00 PM");
		addTableRow(R.drawable.news6, -1, false, false, "Protesting against Russia's anti-gay laws", "Wednesday, 2:00 PM", "", "");
		addTableRow(R.drawable.news7, R.drawable.news8, true, false, "Student fees set to double", "Wednesday, 2:00 PM", "SSA Fee Increase", "Friday, 1:00 PM");
		addTableRow(R.drawable.news9, -1, false, false, "UNIMELB IS NOW SMOKE FREE", "Wednesday, 2:00 PM", "", "");
		addTableRow(R.drawable.news10, R.drawable.news11, true, false, "MORE ACTIVITIES TO CHANGE PREFERENCES", "Wednesday, 2:00 PM", "It's Good to Be White...", "Friday, 1:00 PM");
		addTableRow(R.drawable.news12, -1, false, false, "MURDOCH TO BE DEMOLISHED", "Wednesday, 2:00 PM", "", "");
		addTableRow(R.drawable.news13, R.drawable.news14, true, true, "EULERS BRIDGE INCREASES TURNOUT 400%", "Wednesday, 2:00 PM", "ELECTION DATE", "Friday, 1:00 PM");

		return rootView;
	}
	
	public void addTableRow(int drawable1, int drawable2, boolean doubleCell, boolean lastCell, String articleTitle1, String articleTime1, 
			String articleTitle2, String articleTime2) {
		TableRow tr;
		
		if(doubleCell) {
			tr = new TableRow(getActivity());
			TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
			tr.setLayoutParams(rowParams);
			
			RelativeLayout relativeLayout = new RelativeLayout(getActivity());
			relativeLayout.setLayoutParams(new TableRow.LayoutParams((int)(dpWidth / 2), (int)(dpHeight / 2.3)));
			if(lastCell)
				((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(5, 5, 5, 5);
			else
				((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(5, 5, 5, 0);

	        TextView textViewArticle = new TextView(getActivity());
	        textViewArticle.setTextColor(Color.parseColor("#F8F8F8"));
	        textViewArticle.setTextSize(16.0f);
	        textViewArticle.setText(articleTitle1);
	        textViewArticle.setPadding(10, 0, 10, 0);
	        textViewArticle.setGravity(Gravity.CENTER);
	        
	        TextView textViewArticleTime = new TextView(getActivity());
	        textViewArticleTime.setTextColor(Color.parseColor("#F8F8F8"));
	        textViewArticleTime.setTextSize(12.0f);
	        textViewArticleTime.setText(articleTime1);
	        textViewArticleTime.setPadding(0, 135, 0, 0);
	        textViewArticleTime.setGravity(Gravity.CENTER);
	        
	        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	        params1.addRule(RelativeLayout.CENTER_HORIZONTAL, textViewArticle.getId());
	        params1.addRule(RelativeLayout.CENTER_VERTICAL, textViewArticle.getId());
	        
	        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	        params2.addRule(RelativeLayout.CENTER_HORIZONTAL, textViewArticle.getId());
	        params2.addRule(RelativeLayout.CENTER_VERTICAL, textViewArticle.getId());
			
			ImageView view = new ImageView(getActivity());
			view.setColorFilter(Color.argb(125, 35, 35, 35));
			view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
			view.setScaleType(ScaleType.CENTER_CROP);
	        view.setImageResource(drawable1);
	        relativeLayout.addView(view);
	        relativeLayout.addView(textViewArticle, params1);
	        relativeLayout.addView(textViewArticleTime, params2);
	        tr.addView(relativeLayout);
	        
			relativeLayout = new RelativeLayout(getActivity());
			relativeLayout.setLayoutParams(new TableRow.LayoutParams((int)(dpWidth / 2), (int)(dpHeight / 2.3)));
			if(lastCell)
				((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(0, 5, 5, 5);
			else
				((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(0, 5, 5, 0);
			
	        textViewArticle = new TextView(getActivity());
	        textViewArticle.setTextColor(Color.parseColor("#F8F8F8"));
	        textViewArticle.setTextSize(16.0f);
	        textViewArticle.setText(articleTitle2);
	        textViewArticle.setPadding(10, 0, 10, 0);
	        textViewArticle.setGravity(Gravity.CENTER);
	        
	        textViewArticleTime = new TextView(getActivity());
	        textViewArticleTime.setTextColor(Color.parseColor("#F8F8F8"));
	        textViewArticleTime.setTextSize(12.0f);
	        textViewArticleTime.setText(articleTime2);
	        textViewArticleTime.setPadding(0, 135, 0, 0);
	        textViewArticleTime.setGravity(Gravity.CENTER);
	        
	        params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	        params1.addRule(RelativeLayout.CENTER_HORIZONTAL, textViewArticle.getId());
	        params1.addRule(RelativeLayout.CENTER_VERTICAL, textViewArticle.getId());
	        
	        params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	        params2.addRule(RelativeLayout.CENTER_HORIZONTAL, textViewArticle.getId());
	        params2.addRule(RelativeLayout.CENTER_VERTICAL, textViewArticle.getId());
			
			view = new ImageView(getActivity());
			view.setColorFilter(Color.argb(125, 35, 35, 35));
			view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
			view.setScaleType(ScaleType.CENTER_CROP);
	        view.setImageResource(drawable2);
	        relativeLayout.addView(view);
	        relativeLayout.addView(textViewArticle, params1);
	        relativeLayout.addView(textViewArticleTime, params2);
	        tr.addView(relativeLayout);
	        
	        newsTableLayout.addView(tr);
		}
		else {
			tr = new TableRow(getActivity());
			TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
			tr.setLayoutParams(rowParams);
			
			RelativeLayout relativeLayout = new RelativeLayout(getActivity());
			relativeLayout.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, (int)(dpHeight / 2.3)));
			((TableRow.LayoutParams) relativeLayout.getLayoutParams()).span = 2;
			if(lastCell)
				((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(5, 5, 5, 5);
			else
				((ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams()).setMargins(5, 5, 5, 0);
			
			ImageView view = new ImageView(getActivity());
			view.setColorFilter(Color.argb(125, 35, 35, 35));
			view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, (int)(dpHeight / 2.3)));
			view.setScaleType(ScaleType.CENTER_CROP);
	        view.setImageResource(drawable1);
	        
	        TextView textViewArticle = new TextView(getActivity());
	        textViewArticle.setTextColor(Color.parseColor("#F8F8F8"));
	        textViewArticle.setTextSize(20.0f);
	        textViewArticle.setText(articleTitle1);
	        textViewArticle.setGravity(Gravity.CENTER);
	        
	        TextView textViewArticleTime = new TextView(getActivity());
	        textViewArticleTime.setTextColor(Color.parseColor("#F8F8F8"));
	        textViewArticleTime.setTextSize(12.0f);
	        textViewArticleTime.setText(articleTime1);
	        textViewArticleTime.setPadding(0, 100, 0, 0);
	        textViewArticleTime.setGravity(Gravity.CENTER);
	        
	        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	        params1.addRule(RelativeLayout.CENTER_HORIZONTAL, textViewArticle.getId());
	        params1.addRule(RelativeLayout.CENTER_VERTICAL, textViewArticle.getId());
	        
	        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
	        params2.addRule(RelativeLayout.CENTER_HORIZONTAL, textViewArticle.getId());
	        params2.addRule(RelativeLayout.CENTER_VERTICAL, textViewArticle.getId());
	        
	        relativeLayout.addView(view);
	        relativeLayout.addView(textViewArticle, params1);
	        relativeLayout.addView(textViewArticleTime, params2);
	        
	        tr.addView(relativeLayout);	
	        newsTableLayout.addView(tr);
		}
	}
}