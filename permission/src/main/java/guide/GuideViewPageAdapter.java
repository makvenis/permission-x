package guide;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 * <span>ViewPage的适配器<span/>
 */
public class GuideViewPageAdapter extends PagerAdapter {

    private List<View> dis;

    public GuideViewPageAdapter(List<View> dis) {
        this.dis = dis;
    }

    @Override
    public int getCount() {
        return dis.size() != 0 ? dis.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = dis.get(position);

        container.addView(view);
        return dis.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(dis.get(position));
    }
}
