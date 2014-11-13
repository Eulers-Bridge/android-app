package com.eulersbridge.isegoria;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;

import android.content.res.Resources;
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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ScaleDrawable;

public class EventsDetailFragment extends SherlockFragment {
	private View rootView;
	private float dpWidth;
	private float dpHeight;
	private DisplayMetrics displayMetrics;
	private Isegoria isegoria;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.events_detail_fragment, container, false);
		this.isegoria = (Isegoria) getActivity().getApplication();
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getActivity().getActionBar().removeAllTabs();
		Bundle bundle = this.getArguments();

		displayMetrics = getActivity().getResources().getDisplayMetrics();
		dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;  
        
        isegoria.getNetwork().getEventDetails(this, bundle.getInt("EventId"));
		

		return rootView;
	}
	
	public void populateContent(final String title, final String content, final String location, final String likes, final Bitmap picture) {
		try {
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					LinearLayout backgroundLinearLayout = (LinearLayout) rootView.findViewById(R.id.topBackgroundNews);
					backgroundLinearLayout.getLayoutParams().height = (int) (displayMetrics.heightPixels / 2.7);
					//Bitmap original = BitmapFactory.decodeResource(getActivity().getResources(), backgroundDrawableResource);
					//Bitmap b = Bitmap.createScaledBitmap(original, (int)dpWidth, (int)dpHeight/2, false);
					Drawable d = new BitmapDrawable(getActivity().getResources(), picture);
					d.setColorFilter(Color.argb(125, 35, 35, 35), Mode.DARKEN);
					backgroundLinearLayout.setBackgroundDrawable(d);

					TextView eventTitle = (TextView) rootView.findViewById(R.id.eventTitle);
					eventTitle.setText(title);
					
					TextView eventLocationLine1 = (TextView) rootView.findViewById(R.id.eventLocationLine1);
					eventLocationLine1.setText(location);
					
					TextView eventsText = (TextView) rootView.findViewById(R.id.eventDetails);
					eventsText.setText(content);
				}
			});
		} catch(Exception e) {
			
		}
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