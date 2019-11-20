package imp;

import java.util.List;
import java.util.Map;

/**
 * <span>Activity用户处理的回调事件</span>
 *
 */

public interface OnCallBackPermission {
    /**
     * <span>回调成功<span/>
     */
    void successful();

    /**
     * <span>客户拒绝或者申请失败的回调</span>
     */
    void failure(List<String> mDeniedPermission, Map<String, Boolean> maps);

}
