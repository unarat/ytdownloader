package dentex.youtube.downloader;

import java.util.Arrays;
import java.util.List;

import dentex.youtube.downloader.utils.Utils;
import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShareActivityListFilters {
	
	private static final String VIEW_ALL_STRING = "";
	private static final int VIEW_ALL = -1;
	
	private static final int AO_FILTER = 10;
	private static final int VO_FILTER = 9;
	private static final int _3D_FILTER = 8;
	
	private static final int SD_FILTER = 7;
	private static final int MD_FILTER = 6;
	private static final int LD_FILTER = 5;
	private static final int HD_FILTER = 4;

	private static final int _3GP_FILTER = 3;
	private static final int FLV_FILTER = 2;
	private static final int WEBM_FILTER = 1;
	private static final int MP4_FILTER = 0;

	private static final String DEBUG_TAG = "ShareActivityListFilters";
	
	private static Integer[] iMp4 = { 18, 22, 37, 38, 82, 83, 84, 133, 134, 135, 136, 137, 138, 160, 264 };
	private static Integer[] iWebm = { 43, 44, 45, 46, 100, 101, 102, 242, 243, 244, 245, 246, 247, 248 };
	private static Integer[] iFlv = { 5, 6, 34, 35 };
	private static Integer[] i3gp = { 17, 36 };
	
	private static Integer[] iHd = { 22, 37, 38, 45, 46, 84, 102, 136, 137, 138, 247, 248, 264 };
	private static Integer[] iLd = { 35, 44, 85, 135, 244, 245, 246 };
	private static Integer[] iMd = { 18, 34, 43, 82, 100, 101, 134, 243 };
	private static Integer[] iSd = { 5, 6, 17, 36, 83, 133 };
	
	private static Integer[] i3d = { 82, 83, 84, 85, 100, 101, 102 };
	
	private static Integer[] iVo = { 133, 134, 135, 136, 137, 138, 160, 242, 243, 244, 245, 246, 247, 248, 264 };
	private static Integer[] iAo = { 139, 140, 141, 171, 172 };

	public static CharSequence getListFilterConstraint(int c) {
		//0
		List<Integer> iMp4List = Arrays.asList(iMp4);
		//1
		List<Integer> iWebmList = Arrays.asList(iWebm);
		//2
		List<Integer> iFlvList = Arrays.asList(iFlv);
		//3
		List<Integer> i3gpList = Arrays.asList(i3gp);
		
		//4
		List<Integer> iHdList = Arrays.asList(iHd);
		//5
		List<Integer> iLdList = Arrays.asList(iLd);
		//6
		List<Integer> iMdList = Arrays.asList(iMd);
		//7
		List<Integer> iSdList = Arrays.asList(iSd);
		
		//8
		List<Integer> i3dList = Arrays.asList(i3d);
		
		//9
		List<Integer> iVoList = Arrays.asList(iVo);
		//10
		List<Integer> iAoList = Arrays.asList(iAo);
		
		SparseArray<List<Integer>> filtersMap = new SparseArray<List<Integer>>();
		
		filtersMap.put(MP4_FILTER, iMp4List);
		filtersMap.put(WEBM_FILTER, iWebmList);
		filtersMap.put(FLV_FILTER, iFlvList);
		filtersMap.put(_3GP_FILTER, i3gpList);
		filtersMap.put(HD_FILTER, iHdList);
		filtersMap.put(LD_FILTER, iLdList);
		filtersMap.put(MD_FILTER, iMdList);
		filtersMap.put(SD_FILTER, iSdList);
		filtersMap.put(_3D_FILTER, i3dList);
		filtersMap.put(VO_FILTER, iVoList);
		filtersMap.put(AO_FILTER, iAoList);
		
		if (c == -1) return VIEW_ALL_STRING;
		
		CharSequence constraint = null;
		List<Integer> selectedMap = filtersMap.get(c);
		
		for (int i = 0; i < selectedMap.size(); i++) {
			if (constraint == null) { 
				constraint = String.valueOf(selectedMap.get(i));
			} else {
				constraint = constraint + "/" + selectedMap.get(i);
			}
		}
		return constraint;
	}
	
	public static CharSequence getMultipleListFilterConstraints(int[] c) {
		CharSequence constraint = null;
		for (int i = 0 ; i < c.length; i++) {
			if (constraint == null) { 
				constraint = getListFilterConstraint(c[i]);
			} else {
				constraint = constraint + "/" + getListFilterConstraint(c[i]);
			}
		}
		return constraint;
	}
	
	public static void setupFilters(final Activity act, final ShareActivityAdapter a) {
		/*final int storedFilterInt = YTD.settings.getInt("list_filter", VIEW_ALL);
		final CharSequence listFilterConstraint = getListFilterConstraint(storedFilterInt);
		if (storedFilterInt != VIEW_ALL) a.getFilter().filter(listFilterConstraint);*/
		
		final View mp4 = act.findViewById(R.id.MP4);
		final View webm = act.findViewById(R.id.WEBM);
		final View flv = act.findViewById(R.id.FLV);
		final View _3gp = act.findViewById(R.id._3GP);
		final View hd = act.findViewById(R.id.HD);
		final View ld = act.findViewById(R.id.LD);
		final View md = act.findViewById(R.id.MD);
		final View sd = act.findViewById(R.id.SD);
		final View _3d = act.findViewById(R.id._3D);
		final View vo = act.findViewById(R.id.VO);
		final View ao = act.findViewById(R.id.AO);
		final View all = act.findViewById(R.id.ALL);
		
		YTD.slMenuOrigBkg = mp4.getBackground();
		
		mp4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "MP4 filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, MP4_FILTER);
			}
		});
		
		webm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "WEBM filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, WEBM_FILTER);
			}
		});
		
		flv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "FLV filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, FLV_FILTER);
			}
		});
		
		
		_3gp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "3GP filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, _3GP_FILTER);
			}
		});
		
		hd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "HD filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, HD_FILTER);
			}
		});
		
		ld.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "LD filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, LD_FILTER);
			}
		});
		
		md.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "MD filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, MD_FILTER);
			}
		});
		
		sd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "SD filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, SD_FILTER);
			}
		});
		
		_3d.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "3D filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, _3D_FILTER);
			}
		});
		
		vo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "VO filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, VO_FILTER);
			}
		});
		
		ao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "AO filter clicked", DEBUG_TAG);
				reactToViewClick(act, a, v, AO_FILTER);
			}
		});
		
		all.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.logger("d", "ALL filter clicked", DEBUG_TAG);
				a.getFilter().filter(VIEW_ALL_STRING);
				resetAllBkg(act);
				YTD.settings.edit().putInt("list_filter", VIEW_ALL).commit();
			}
		});
	}
	
	private static void reactToViewClick(final Activity act, final ShareActivityAdapter a, View v, int filterInt) {
		CharSequence constraint = getListFilterConstraint(filterInt);
		a.getFilter().filter(constraint);
		resetAllBkg(act);
		v.setBackgroundResource(R.drawable.grad_bg_sel);
		//YTD.settings.edit().putInt("list_filter", filterInt).commit();
	}

	@SuppressWarnings("deprecation")
	private static void resetAllBkg(final Activity act) {
		LinearLayout ll = (LinearLayout) act.findViewById(R.id.all_filters);
		int childCount = ll.getChildCount();
		for (int i = 0; i < childCount; i++) {
			
			final View childAt = ll.getChildAt(i);
			if (childAt instanceof TextView)
				childAt.setBackgroundDrawable(YTD.slMenuOrigBkg);
		}
	}
}
