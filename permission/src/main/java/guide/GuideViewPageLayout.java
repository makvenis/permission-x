package guide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.sirius.permission.manger.R;

import java.util.List;

/**
 * <span>布局组合</span>
 * <span>{@link ViewPager.OnPageChangeListener}监听页面的滑动变化</span>
 */

public class GuideViewPageLayout extends LinearLayout implements ViewPager.OnPageChangeListener,
        View.OnClickListener {

    /* 上下文参数 */
    private Context mContext;
    /* 获取当前的布局View */
    private View v;
    private ViewPager mViewPage;
    private Button mStartOpen;
    private GuideLinerLayout mIndicatorLayout;
    private GuideViewPageAdapter adapter;

    public GuideViewPageLayout(Context context) {
        this(context,null);
    }

    public GuideViewPageLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GuideViewPageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;

        /* 布局组合文件 */
        v = LayoutInflater.from(mContext)
                .inflate(R.layout.guide_page,this);

        /* 控件查找 */
        mViewPage = ((ViewPager) v.findViewById(R.id.mGuideViewPage));
        mStartOpen = ((Button) v.findViewById(R.id.mGuideStartOpen));
        mIndicatorLayout = ((GuideLinerLayout) v.findViewById(R.id.mGuideIndicator));
    }

    /* 赋值操作等属性 */
    public void createGuidePage(List<View> dis){
        /* 创建适配器 */
        adapter = new GuideViewPageAdapter(dis);
        /* 设置适配器 */
        mViewPage.setAdapter(adapter);
        /* 注册监听事件 */
        mViewPage.addOnPageChangeListener(this);
        /* 注册按钮点击事件 */
        mStartOpen.setOnClickListener(this);
        /* 默认选中第一个 */
        mIndicatorLayout.selectView(0);
    }


    /* 页面滑动监听 */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mIndicatorLayout.selectView(position);
        if(position == adapter.getCount()-1){
            mStartOpen.setVisibility(VISIBLE);
        }else {
            mStartOpen.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.mGuideStartOpen){
            if(callBack != null){
                callBack.open();
            }
        }
    }

    /* 回调点击事件 */
    private OnGuideStartOpenCallBack callBack;
    public void setOnGuideStartOpenCallBack(OnGuideStartOpenCallBack callBack){
        this.callBack=callBack;
    }
    public interface OnGuideStartOpenCallBack{
        void open();
    }
}
