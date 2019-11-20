package util;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.sirius.permission.manger.R;

import java.util.List;
import java.util.Map;

public class PermissionDialogUtils extends Dialog {

    private static final String TAG = "PermissionDialogUtils";
    private Context mContext;
    private ImageView mIcon;
    private TextView mRemark;
    private TextView mList;
    private Button mOpen;
    private TextView mDenied;
    private String pNote;
    private int pIcon;
    /* 授权失败的集合 */
    private List<String> mDeniedPermission;
    /* 授权失败权限是否取消提示 */
    private Map<String,Boolean> maps;

    public PermissionDialogUtils(@NonNull Context context,
                                 String pNote,
                                 int pIcon,
                                 List<String> mDeniedPermission,
                                 Map<String,Boolean> maps) {
        this(context,0,pNote,pIcon,mDeniedPermission,maps);
    }

    public PermissionDialogUtils(@NonNull Context context,
                                 int themeResId,
                                 String pNote,
                                 int pIcon,
                                 List<String> mDeniedPermission,
                                 Map<String,Boolean> maps) {
        super(context, R.style.permission_dialog);
        this.mContext=context;
        this.mDeniedPermission=mDeniedPermission;
        this.maps=maps;
        this.pNote=pNote;
        this.pIcon=pIcon;
        init();
    }


    /* 初始化 */
    private void init() {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.permission_error, null);
        setContentView(view);

        /* 控件查找 */
        mIcon = ((ImageView) view.findViewById(R.id.mPermissionIcon));
        mRemark = (TextView)view.findViewById(R.id.mPermissionRemark);
        mList = ((TextView) view.findViewById(R.id.mPermissionList));
        mOpen = ((Button) view.findViewById(R.id.mPermissionOpen));
        mDenied = ((TextView) view.findViewById(R.id.mPermissionDenied));

        /* 获取权限列表 */
        Map<String, String> lang = PermissionGroup.getLang(mContext);
        Log.e(TAG,lang.toString());
        /* 赋值操作 */
        mRemark.setText(pNote);
        /* icon */
        mIcon.setImageResource(pIcon);
        /* 赋值权限列表 */
        String s="";
        int index=0;
        for (int i = 0; i < mDeniedPermission.size(); i++) {

            Log.e(TAG,mDeniedPermission.get(i).trim());
            String s1 = lang.get(mDeniedPermission.get(i).trim());
            if(s1 !=null){
                s+=(++index)+"."+s1+";\n";
            }
        }

        mList.setText(s);

        /* 事件处理 */
        mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.open();
            }
        });
        /* 不开启权限 */
        mDenied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

    /**
     * <span>权限设置或者取消的回调事件</span>
     */
    private OnPermissionCallBack callBack;
    public void setOnPermissionCallBack(OnPermissionCallBack callBack){
        this.callBack=callBack;
    }
    public interface OnPermissionCallBack{
        void open();
    }

}
