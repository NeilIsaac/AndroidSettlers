package com.settlers.hd;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResourceView extends LinearLayout {
	
	private static final int[] RESOURCES = {
		R.drawable.res_lumber_medium,
		R.drawable.res_wool_medium,
		R.drawable.res_grain_medium,
		R.drawable.res_brick_medium,
		R.drawable.res_ore_medium,
	};
	
	TextView[] views;

	public ResourceView(Context context) {
		super(context);

		int padding = (int) (10 * context.getResources().getDisplayMetrics().density);

		setVisibility(View.INVISIBLE);
		setGravity(Gravity.CENTER);
		
		views = new TextView[RESOURCES.length];
		
		for (int i = 0; i < RESOURCES.length; i++) {
			ImageView image = new ImageView(context);
			image.setImageResource(RESOURCES[i]);
			
			TextView text = new TextView(context);
			text.setText("");
			text.setTextColor(Color.WHITE);
			text.setTextSize(24);
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			text.setGravity(Gravity.BOTTOM);
			views[i] = text;
			
			LinearLayout row = new LinearLayout(context);
			row.setOrientation(LinearLayout.HORIZONTAL);
			row.setPadding(padding, padding, padding, padding);
			row.addView(image);
			row.addView(text);
			
			addView(row);
		}
	}

	public void setValues(Player player) {		
		if (player == null || !player.isHuman()) {
			setVisibility(View.INVISIBLE);
			return;
		}
		
		setVisibility(View.VISIBLE);
		setBackgroundColor(Color.argb(200, 0, 0, 0));
		
		int[] resources = player.getResources();
		for (int i = 0; i < Hexagon.TYPES.length; i++)
			views[i].setText(String.valueOf(resources[i]));
	}
}
