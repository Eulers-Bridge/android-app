package com.eulersbridge.isegoria;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PhotosFragment extends Fragment {
	private View rootView;
	private TableLayout photosTableLayout;
	
	private float dpWidth;
	private float dpHeight;
	
	private boolean insertedFirstRow = false;
	
	public PhotosFragment() {
		insertedFirstRow = false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.photos_fragment, container, false);
		getActivity().setTitle("Isegoria");
		
		DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		photosTableLayout = (TableLayout) rootView.findViewById(R.id.photosTableLayout);
		
		dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;  
        
        addTableRow("Baillieu Library Refurbishment University", "20 photos - 28th April 2014 10:00AM");
        addTableRow("Cocktail Party Numero Uno", "30 photos - 2nd May 2014, 10:00AM");
        addTableRow("Head of the Yarra", "41 photos - 2nd May 2014, 10:00AM");
        addTableRow("Clubs & Societies Day Rocks!", "41 photos - 2nd May 2014, 10:00AM");
        addTableRow("Clive Palmer Press Conference", "41 photos - 2nd May 2014, 10:00AM");
        addTableRow("Slam Dunk Festival 2014", "41 photos - 2nd May 2014, 10:00AM");
        addTableRow("Coffee at Lakeside Restaurant", "41 photos - 2nd May 2014, 10:00AM");
        addTableRow("Flirt", "41 photos - 2nd May 2014, 10:00AM");
        addTableRow("Polling Day", "41 photos - 2nd May 2014, 10:00AM");
		
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
        view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.news0, 100, 100));
		
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
        photosTableLayout.addView(tr);
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	
	    return inSampleSize;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
}
