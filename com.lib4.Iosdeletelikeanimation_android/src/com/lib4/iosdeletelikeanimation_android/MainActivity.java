package com.lib4.iosdeletelikeanimation_android;

import java.util.Timer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	LinearLayout container;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	
		container = (LinearLayout) findViewById(R.id.container);

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		for (int i = 0; i < 10; i++) {
			FrameLayout mItem = (FrameLayout) inflater.inflate(
					R.layout.customframe, null);

			mItem.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					animateItems();
					
					Toast.makeText(MainActivity.this, "LONG PRESS", 50).show();
					return true;
				}
			});
			container.addView(mItem);
			
		}
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void animateItems() {
		int i = 0;
		while (i < container.getChildCount()) {
			Log.e("Value i", " VAL " + i);
			Animation shakingAnim = (Animation) AnimationUtils.loadAnimation(this, R.anim.animation);
			
			final FrameLayout mFrameLayout = (FrameLayout) container.getChildAt(i);
			ImageView mImageView = (ImageView) mFrameLayout.findViewById(R.id.cross);
			mImageView.setVisibility(View.VISIBLE);
			mImageView.setTag(i);
			mImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(final View v) {
					Animation diminishAnim = (Animation) AnimationUtils.loadAnimation(MainActivity.this, R.anim.diminish);
					mFrameLayout.startAnimation(diminishAnim);
					diminishAnim.setAnimationListener(new AnimationListener() {
						
						@Override
						public void onAnimationStart(Animation arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationRepeat(Animation arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animation arg0) {
							// TODO Auto-generated method stub
							mFrameLayout.setVisibility(View.GONE);
							
							
						}
					});
					//
				}
			});
			mFrameLayout.startAnimation(shakingAnim);
			i++;
		}

	}
	
	

}
