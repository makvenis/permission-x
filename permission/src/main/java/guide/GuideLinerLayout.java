package guide;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

import com.sirius.permission.manger.R;

/**
 * <span>自定义一排圆点</span>
 */
public class GuideLinerLayout extends LinearLayout {

    private static final String TAG = "GuideLinerLayout";
    /* 上下文参数 */
    private Context mContext;
    /* 设置圆点的个数 */
    private int mIndex;
    /* 圆点之间的间距 */
    private int margin;
    /* 默认选中的位置 */
    private int defaultIndex;
    /* 指示器的高宽 */
    private int width,height;

    public GuideLinerLayout(Context context) {
        this(context,null);
    }

    public GuideLinerLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GuideLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        /* 加载属性 */
        TypedArray td = context.obtainStyledAttributes(attrs,R.styleable.GuideLinerLayout);
        /* 获取总共的治时期个数 */
        mIndex = td.getInteger(R.styleable.GuideLinerLayout_indicatorNumber, 4);
        /* 默认选中 */
        defaultIndex = td.getInteger(R.styleable.GuideLinerLayout_defaultSelectView,0);
        /* 指示器的高宽 */
        width = td.getInteger(R.styleable.GuideLinerLayout_indicatorWidth,30);
        height = td.getInteger(R.styleable.GuideLinerLayout_indicatorHeight,30);
        /* 获取指示器的间距 */
        margin = td.getInteger(R.styleable.GuideLinerLayout_indicatorMargin, 10);
        /* 释放内存 */
        td.recycle();
        init();
    }


    /* 设置选中的布局 */
    public void selectView(int indicator) {
        int i = indicator % mIndex;
        int count = getChildCount(); //4
        Log.e(TAG,count+"---"+i);
        for (int j = 0; j < count; j++) {
            View childAt = getChildAt(j);
            if (j == i) {
                childAt.setBackgroundResource(R.drawable.hollow_round);
            } else {
                childAt.setBackgroundResource(R.drawable.solid_round);
            }
        }
    }

    /* 添加布局 */
    private void init() {
        View view;
        for(int i=0;i<mIndex;i++){
            view=new View(mContext);
            /* 自己在Drawable文件夹目录下写的xml文件 */
            view.setBackgroundResource(R.drawable.solid_round);
            view.setEnabled(false);
            LayoutParams params = new LayoutParams(20, 20);
            params.gravity= Gravity.CENTER;
            if(i != 0){
                /* 设置间隔 */
                params.leftMargin=margin;
            }
            /* xml文件中装原点的LinearLayout的id */
            addView(view,params);
        }
    }
}
