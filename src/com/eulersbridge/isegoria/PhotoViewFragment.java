package com.eulersbridge.isegoria;

import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class PhotoViewFragment extends Fragment {
	private View rootView;
	
	private float dpWidth;
	private float dpHeight;
	
	private String photoPath;

	public PhotoViewFragment() {

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.photo_view_fragment, container, false);
		getActivity().setTitle("Isegoria");
		Bundle bundle = this.getArguments();
		photoPath = (String) bundle.getString("PhotoName");
		
		DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;  

		AssetManager assetManager = getActivity().getAssets();
		ImageView photoImageView = (ImageView) rootView.findViewById(R.id.photoImageView);
		try {
			Bitmap bitmap = decodeSampledBitmapFromBitmap(assetManager.open(photoPath), 150, 150);
			photoImageView.setScaleType(ScaleType.CENTER_CROP);
			photoImageView.setImageBitmap(bitmap);
			photoImageView.getLayoutParams().width = (int) displayMetrics.widthPixels;
			photoImageView.getLayoutParams().height = (int) (displayMetrics.heightPixels / 2.5);
			photoImageView.setPadding(0, 0, 0, (displayMetrics.heightPixels / 20));
		} catch (IOException e) {
			e.printStackTrace();
		}   
        
		return rootView;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;

	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	
	    return inSampleSize;
	}
	
	public static Bitmap decodeSampledBitmapFromBitmap(InputStream is,
	        int reqWidth, int reqHeight) {
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeStream(is);
	}
}
