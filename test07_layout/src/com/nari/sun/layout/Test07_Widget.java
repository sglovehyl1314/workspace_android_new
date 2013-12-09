package com.nari.sun.layout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;

public class Test07_Widget extends Activity implements android.widget.AdapterView.OnItemSelectedListener, android.widget.ViewSwitcher.ViewFactory {
	private Spinner spinner1;
	private Spinner spinner2;
	private ArrayAdapter<String> adapterCountries;
	private List<String> allCountries;
	private static final String[] countries = { "China", "Russia", "Germany", "Ukraine", "Belarus", "USA" };

	// 2012-03-01
	private ImageSwitcher imageSwitcher;
	private Gallery gallery;
	private Integer[] mThumbIds = { R.drawable.sample_thumb_0, R.drawable.sample_thumb_1, R.drawable.sample_thumb_2, R.drawable.sample_thumb_3, R.drawable.sample_thumb_4,
			R.drawable.sample_thumb_5, R.drawable.sample_thumb_6, R.drawable.sample_thumb_7 };
	private Integer[] mImageIds = { R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ≤ªœ‘ ætitile
		setContentView(R.layout.imageshow);
		// findAndModiView();
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
		imageSwitcher.setFactory(this);

		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

		gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemSelectedListener(this);

	}

	private void findAndModiView() {
		spinner1 = (Spinner) findViewById(R.id.spinner_1);
		allCountries = new ArrayList<String>();
		for (int i = 0; i < countries.length; i++) {
			allCountries.add(countries[i]);
		}
		adapterCountries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCountries);
		adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapterCountries);

		spinner2 = (Spinner) findViewById(R.id.spinner_2);
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(arrayAdapter);

	}

	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		return imageView;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		imageSwitcher.setImageResource(mImageIds[position]);

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(mThumbIds[position]);
			imageView.setAdjustViewBounds(true);
			imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			imageView.setBackgroundResource(R.drawable.picture_frame);
			return imageView;
		}

	}
}
