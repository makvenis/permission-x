package util;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PermissionGroup {

    public static final String[] CALENDAR;
    public static final String[] CAMERA;
    public static final String[] CONTACTS;
    public static final String[] LOCATION;
    public static final String[] MICROPHONE;
    public static final String[] PHONE;
    public static final String[] SENSORS;
    public static final String[] SMS;
    public static final String[] STORAGE;

    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            CALENDAR = new String[]{};
            CAMERA = new String[]{};
            CONTACTS = new String[]{};
            LOCATION = new String[]{};
            MICROPHONE = new String[]{};
            PHONE = new String[]{};
            SENSORS = new String[]{};
            SMS = new String[]{};
            STORAGE = new String[]{};

        } else {
            CALENDAR = new String[]{
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR};

            CAMERA = new String[]{
                    Manifest.permission.CAMERA};

            CONTACTS = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS};

            LOCATION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            MICROPHONE = new String[]{
                    Manifest.permission.RECORD_AUDIO};

            PHONE = new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG,
                    Manifest.permission.USE_SIP,
                    Manifest.permission.PROCESS_OUTGOING_CALLS};

            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS};

            SMS = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_WAP_PUSH,
                    Manifest.permission.RECEIVE_MMS};

            STORAGE = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
    }

    /* 解析权限列表JSON格式的字符串 */
    public static final Map<String,String> getLang(Context mContext){
//        CharSequence text = mContext.getResources().getText(R.string.android_permission);
//        String s = text.toString();
        Map<String,String> permissionRemark=new HashMap<>();
        if(PermissionGroup.PERMISSION_TXT != null){
            try {
                JSONArray array=new JSONArray(PermissionGroup.PERMISSION_TXT.trim());
                if(array != null && array.length() != 0){
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject emp = array.getJSONObject(i);
                        permissionRemark.put(emp.optString("permission"),
                                emp.optString("mode"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return permissionRemark;
    }

    private static final String PERMISSION_TXT="" +
            "[{\"mode\":\"访问登记属性\",\"permission\":\"android.permission.ACCESS_CHECKIN_PROPERTIES\",\"remark\":\"读取或写入登记check-in数据库属性表的权限\"},{\"mode\":\"获取错略位置\",\"permission\":\"android.permission.ACCESS_COARSE_LOCATION\",\"remark\":\"通过WiFi或移动基站的方式获取用户错略的经纬度信息,定位精度大概误差在30~1500米\"},{\"mode\":\"获取精确位置\",\"permission\":\"android.permission.ACCESS_FINE_LOCATION\",\"remark\":\"通过GPS芯片接收卫星的定位信息,定位精度达10米以内\"},{\"mode\":\"访问定位额外命令\",\"permission\":\"android.permission.ACCESS_LOCATION_EXTRA_COMMANDS\",\"remark\":\"允许程序访问额外的定位提供者指令\"},{\"mode\":\"获取模拟定位信息\",\"permission\":\"android.permission.ACCESS_MOCK_LOCATION\",\"remark\":\"获取模拟定位信息,一般用于帮助开发者调试应用\"},{\"mode\":\"获取网络状态\",\"permission\":\"android.permission.ACCESS_NETWORK_STATE\",\"remark\":\"获取网络信息状态,如当前的网络连接是否有效\"},{\"mode\":\"访问Surface Flinger\",\"permission\":\"android.permission.ACCESS_SURFACE_FLINGER\",\"remark\":\"Android平台上底层的图形显示支持,一般用于游戏或照相机预览界面和底层模式的屏幕截图\"},{\"mode\":\"获取WiFi状态\",\"permission\":\"android.permission.ACCESS_WIFI_STATE\",\"remark\":\"获取当前WiFi接入的状态以及WLAN热点的信息\"},{\"mode\":\"账户管理\",\"permission\":\"android.permission.ACCOUNT_MANAGER\",\"remark\":\"获取账户验证信息,主要为GMail账户信息,只有系统级进程才能访问的权限\"},{\"mode\":\"验证账户\",\"permission\":\"android.permission.AUTHENTICATE_ACCOUNTS\",\"remark\":\"允许一个程序通过账户验证方式访问账户管理ACCOUNT_MANAGER相关信息\"},{\"mode\":\"电量统计\",\"permission\":\"android.permission.BATTERY_STATS\",\"remark\":\"获取电池电量统计信息\"},{\"mode\":\"绑定小插件\",\"permission\":\"android.permission.BIND_APPWIDGET\",\"remark\":\"允许一个程序告诉appWidget服务需要访问小插件的数据库,只有非常少的应用才用到此权限\"},{\"mode\":\"绑定设备管理\",\"permission\":\"android.permission.BIND_DEVICE_ADMIN\",\"remark\":\"请求系统管理员接收者receiver,只有系统才能使用\"},{\"mode\":\"绑定输入法\",\"permission\":\"android.permission.BIND_INPUT_METHOD\",\"remark\":\"请求InputMethodService服务,只有系统才能使用\"},{\"mode\":\"绑定RemoteView\",\"permission\":\"android.permission.BIND_REMOTEVIEWS\",\"remark\":\"必须通过RemoteViewsService服务来请求,只有系统才能用\"},{\"mode\":\"绑定壁纸\",\"permission\":\"android.permission.BIND_WALLPAPER\",\"remark\":\"必须通过WallpaperService服务来请求,只有系统才能用\"},{\"mode\":\"使用蓝牙\",\"permission\":\"android.permission.BLUETOOTH\",\"remark\":\"允许程序连接配对过的蓝牙设备\"},{\"mode\":\"蓝牙管理\",\"permission\":\"android.permission.BLUETOOTH_ADMIN\",\"remark\":\"允许程序进行发现和配对新的蓝牙设备\"},{\"mode\":\"变成砖头\",\"permission\":\"android.permission.BRICK\",\"remark\":\"能够禁用手机,非常危险,顾名思义就是让手机变成砖头\"},{\"mode\":\"应用删除时广播\",\"permission\":\"android.permission.BROADCAST_PACKAGE_REMOVED\",\"remark\":\"当一个应用在删除时触发一个广播\"},{\"mode\":\"收到短信时广播\",\"permission\":\"android.permission.BROADCAST_SMS\",\"remark\":\"当收到短信时触发一个广播\"},{\"mode\":\"连续广播\",\"permission\":\"android.permission.BROADCAST_STICKY\",\"remark\":\"允许一个程序收到广播后快速收到下一个广播\"},{\"mode\":\"WAP PUSH广播\",\"permission\":\"android.permission.BROADCAST_WAP_PUSH\",\"remark\":\"WAP PUSH服务收到后触发一个广播\"},{\"mode\":\"拨打电话\",\"permission\":\"android.permission.CALL_PHONE\",\"remark\":\"允许程序从非系统拨号器里输入电话号码\"},{\"mode\":\"通话权限\",\"permission\":\"android.permission.CALL_PRIVILEGED\",\"remark\":\"允许程序拨打电话,替换系统的拨号器界面\"},{\"mode\":\"拍照权限\",\"permission\":\"android.permission.CAMERA\",\"remark\":\"允许访问摄像头进行拍照\"},{\"mode\":\"改变组件状态\",\"permission\":\"android.permission.CHANGE_COMPONENT_ENABLED_STATE\",\"remark\":\"改变组件是否启用状态\"},{\"mode\":\"改变配置\",\"permission\":\"android.permission.CHANGE_CONFIGURATION\",\"remark\":\"允许当前应用改变配置,如定位\"},{\"mode\":\"改变网络状态\",\"permission\":\"android.permission.CHANGE_NETWORK_STATE\",\"remark\":\"改变网络状态如是否能联网\"},{\"mode\":\"改变WiFi多播状态\",\"permission\":\"android.permission.CHANGE_WIFI_MULTICAST_STATE\",\"remark\":\"改变WiFi多播状态\"},{\"mode\":\"改变WiFi状态\",\"permission\":\"android.permission.CHANGE_WIFI_STATE\",\"remark\":\"改变WiFi状态\"},{\"mode\":\"清除应用缓存\",\"permission\":\"android.permission.CLEAR_APP_CACHE\",\"remark\":\"清除应用缓存\"},{\"mode\":\"清除用户数据\",\"permission\":\"android.permission.CLEAR_APP_USER_DATA\",\"remark\":\"清除应用的用户数据\"},{\"mode\":\"底层访问权限\",\"permission\":\"android.permission.CWJ_GROUP\",\"remark\":\"允许CWJ账户组访问底层信息\"},{\"mode\":\"手机优化大师扩展权限\",\"permission\":\"android.permission.CELL_PHONE_MASTER_EX\",\"remark\":\"手机优化大师扩展权限\"},{\"mode\":\"控制定位更新\",\"permission\":\"android.permission.CONTROL_LOCATION_UPDATES\",\"remark\":\"允许获得移动网络定位信息改变\"},{\"mode\":\"删除缓存文件\",\"permission\":\"android.permission.DELETE_CACHE_FILES\",\"remark\":\"允许应用删除缓存文件\"},{\"mode\":\"删除应用\",\"permission\":\"android.permission.DELETE_PACKAGES\",\"remark\":\"允许程序删除应用\"},{\"mode\":\"电源管理\",\"permission\":\"android.permission.DEVICE_POWER\",\"remark\":\"允许访问底层电源管理\"},{\"mode\":\"应用诊断\",\"permission\":\"android.permission.DIAGNOSTIC\",\"remark\":\"允许程序到RW到诊断资源\"},{\"mode\":\"禁用键盘锁\",\"permission\":\"android.permission.DISABLE_KEYGUARD\",\"remark\":\"允许程序禁用键盘锁\"},{\"mode\":\"转存系统信息\",\"permission\":\"android.permission.DUMP\",\"remark\":\"允许程序获取系统dump信息从系统服务\"},{\"mode\":\"状态栏控制\",\"permission\":\"android.permission.EXPAND_STATUS_BAR\",\"remark\":\"允许程序扩展或收缩状态栏\"},{\"mode\":\"工厂测试模式\",\"permission\":\"android.permission.FACTORY_TEST\",\"remark\":\"允许程序运行工厂测试模式\"},{\"mode\":\"使用闪光灯\",\"permission\":\"android.permission.FLASHLIGHT\",\"remark\":\"允许访问闪光灯\"},{\"mode\":\"强制后退\",\"permission\":\"android.permission.FORCE_BACK\",\"remark\":\"允许程序强制使用back后退按键,无论Activity是否在顶层\"},{\"mode\":\"访问账户Gmail列表\",\"permission\":\"android.permission.GET_ACCOUNTS\",\"remark\":\"访问GMail账户列表\"},{\"mode\":\"获取应用大小\",\"permission\":\"android.permission.GET_PACKAGE_SIZE\",\"remark\":\"获取应用的文件大小\"},{\"mode\":\"获取任务信息\",\"permission\":\"android.permission.GET_TASKS\",\"remark\":\"允许程序获取当前或最近运行的应用\"},{\"mode\":\"允许全局搜索\",\"permission\":\"android.permission.GLOBAL_SEARCH\",\"remark\":\"允许程序使用全局搜索功能\"},{\"mode\":\"硬件测试\",\"permission\":\"android.permission.HARDWARE_TEST\",\"remark\":\"访问硬件辅助设备,用于硬件测试\"},{\"mode\":\"注射事件\",\"permission\":\"android.permission.INJECT_EVENTS\",\"remark\":\"允许访问本程序的底层事件,获取按键、轨迹球的事件流\"},{\"mode\":\"安装定位提供\",\"permission\":\"android.permission.INSTALL_LOCATION_PROVIDER\",\"remark\":\"安装定位提供\"},{\"mode\":\"安装应用程序\",\"permission\":\"android.permission.INSTALL_PACKAGES\",\"remark\":\"允许程序安装应用\"},{\"mode\":\"内部系统窗口\",\"permission\":\"android.permission.INTERNAL_SYSTEM_WINDOW\",\"remark\":\"允许程序打开内部窗口,不对第三方应用程序开放此权限\"},{\"mode\":\"访问网络\",\"permission\":\"android.permission.INTERNET\",\"remark\":\"访问网络连接,可能产生GPRS流量\"},{\"mode\":\"结束后台进程\",\"permission\":\"android.permission.KILL_BACKGROUND_PROCESSES\",\"remark\":\"允许程序调用killBackgroundProcesses(String).方法结束后台进程\"},{\"mode\":\"管理账户\",\"permission\":\"android.permission.MANAGE_ACCOUNTS\",\"remark\":\"允许程序管理AccountManager中的账户列表\"},{\"mode\":\"管理程序引用\",\"permission\":\"android.permission.MANAGE_APP_TOKENS\",\"remark\":\"管理创建、摧毁、Z轴顺序,仅用于系统\"},{\"mode\":\"高级权限\",\"permission\":\"android.permission.MTWEAK_USER\",\"remark\":\"允许mTweak用户访问高级系统权限\"},{\"mode\":\"社区权限\",\"permission\":\"android.permission.MTWEAK_FORUM\",\"remark\":\"允许使用mTweak社区权限\"},{\"mode\":\"软格式化\",\"permission\":\"android.permission.MASTER_CLEAR\",\"remark\":\"允许程序执行软格式化,删除系统配置信息\"},{\"mode\":\"修改声音设置\",\"permission\":\"android.permission.MODIFY_AUDIO_SETTINGS\",\"remark\":\"修改声音设置信息\"},{\"mode\":\"修改电话状态\",\"permission\":\"android.permission.MODIFY_PHONE_STATE\",\"remark\":\"修改电话状态,如飞行模式,但不包含替换系统拨号器界面\"},{\"mode\":\"格式化文件系统\",\"permission\":\"android.permission.MOUNT_FORMAT_FILESYSTEMS\",\"remark\":\"格式化可移动文件系统,比如格式化清空SD卡\"},{\"mode\":\"挂载文件系统\",\"permission\":\"android.permission.MOUNT_UNMOUNT_FILESYSTEMS\",\"remark\":\"挂载、反挂载外部文件系统\"},{\"mode\":\"允许NFC通讯\",\"permission\":\"android.permission.NFC\",\"remark\":\"允许程序执行NFC近距离通讯操作,用于移动支持\"},{\"mode\":\"永久Activity\",\"permission\":\"android.permission.PERSISTENT_ACTIVITY\",\"remark\":\"创建一个永久的Activity,该功能标记为将来将被移除\"},{\"mode\":\"处理拨出电话\",\"permission\":\"android.permission.PROCESS_OUTGOING_CALLS\",\"remark\":\"允许程序监视,修改或放弃播出电话\"},{\"mode\":\"读取日程提醒\",\"permission\":\"android.permission.READ_CALENDAR\",\"remark\":\"允许程序读取用户的日程信息\"},{\"mode\":\"读取联系人\",\"permission\":\"android.permission.READ_CONTACTS\",\"remark\":\"允许应用访问联系人通讯录信息\"},{\"mode\":\"屏幕截图\",\"permission\":\"android.permission.READ_FRAME_BUFFER\",\"remark\":\"读取帧缓存用于屏幕截图\"},{\"mode\":\"读取收藏夹和历史记录\",\"permission\":\"com.android.browser.permission.READ_HISTORY_BOOKMARKS\",\"remark\":\"读取浏览器收藏夹和历史记录\"},{\"mode\":\"读取输入状态\",\"permission\":\"android.permission.READ_INPUT_STATE\",\"remark\":\"读取当前键的输入状态,仅用于系统\"},{\"mode\":\"读取系统日志\",\"permission\":\"android.permission.READ_LOGS\",\"remark\":\"读取系统底层日志\"},{\"mode\":\"读取电话状态\",\"permission\":\"android.permission.READ_PHONE_STATE\",\"remark\":\"访问电话状态\"},{\"mode\":\"读取短信内容\",\"permission\":\"android.permission.READ_SMS\",\"remark\":\"读取短信内容\"},{\"mode\":\"读取同步设置\",\"permission\":\"android.permission.READ_SYNC_SETTINGS\",\"remark\":\"读取同步设置,读取Google在线同步设置\"},{\"mode\":\"读取同步状态\",\"permission\":\"android.permission.READ_SYNC_STATS\",\"remark\":\"读取同步状态,获得Google在线同步状态\"},{\"mode\":\"重启设备\",\"permission\":\"android.permission.REBOOT\",\"remark\":\"允许程序重新启动设备\"},{\"mode\":\"开机自动允许\",\"permission\":\"android.permission.RECEIVE_BOOT_COMPLETED\",\"remark\":\"允许程序开机自动运行\"},{\"mode\":\"接收彩信\",\"permission\":\"android.permission.RECEIVE_MMS\",\"remark\":\"接收彩信\"},{\"mode\":\"接收短信\",\"permission\":\"android.permission.RECEIVE_SMS\",\"remark\":\"接收短信\"},{\"mode\":\"接收Wap Push\",\"permission\":\"android.permission.RECEIVE_WAP_PUSH\",\"remark\":\"接收WAP PUSH信息\"},{\"mode\":\"录音\",\"permission\":\"android.permission.RECORD_AUDIO\",\"remark\":\"录制声音通过手机或耳机的麦克\"},{\"mode\":\"排序系统任务\",\"permission\":\"android.permission.REORDER_TASKS\",\"remark\":\"重新排序系统Z轴运行中的任务\"},{\"mode\":\"结束系统任务\",\"permission\":\"android.permission.RESTART_PACKAGES\",\"remark\":\"结束任务通过restartPackage(String)方法,该方式将在外来放弃\"},{\"mode\":\"发送短信\",\"permission\":\"android.permission.SEND_SMS\",\"remark\":\"发送短信\"},{\"mode\":\"设置Activity观察其\",\"permission\":\"android.permission.SET_ACTIVITY_WATCHER\",\"remark\":\"设置Activity观察器一般用于monkey测试\"},{\"mode\":\"设置闹铃提醒\",\"permission\":\"com.android.alarm.permission.SET_ALARM\",\"remark\":\"设置闹铃提醒\"},{\"mode\":\"设置总是退出\",\"permission\":\"android.permission.SET_ALWAYS_FINISH\",\"remark\":\"设置程序在后台是否总是退出\"},{\"mode\":\"设置动画缩放\",\"permission\":\"android.permission.SET_ANIMATION_SCALE\",\"remark\":\"设置全局动画缩放\"},{\"mode\":\"设置调试程序\",\"permission\":\"android.permission.SET_DEBUG_APP\",\"remark\":\"设置调试程序,一般用于开发\"},{\"mode\":\"设置屏幕方向\",\"permission\":\"android.permission.SET_ORIENTATION\",\"remark\":\"设置屏幕方向为横屏或标准方式显示,不用于普通应用\"},{\"mode\":\"设置应用参数\",\"permission\":\"android.permission.SET_PREFERRED_APPLICATIONS\",\"remark\":\"设置应用的参数,已不再工作具体查看addPackageToPreferred(String)介绍\"},{\"mode\":\"设置进程限制\",\"permission\":\"android.permission.SET_PROCESS_LIMIT\",\"remark\":\"允许程序设置最大的进程数量的限制\"},{\"mode\":\"设置系统时间\",\"permission\":\"android.permission.SET_TIME\",\"remark\":\"设置系统时间\"},{\"mode\":\"设置系统时区\",\"permission\":\"android.permission.SET_TIME_ZONE\",\"remark\":\"设置系统时区\"},{\"mode\":\"设置桌面壁纸\",\"permission\":\"android.permission.SET_WALLPAPER\",\"remark\":\"设置桌面壁纸\"},{\"mode\":\"设置壁纸建议\",\"permission\":\"android.permission.SET_WALLPAPER_HINTS\",\"remark\":\"设置壁纸建议\"},{\"mode\":\"发送永久进程信号\",\"permission\":\"android.permission.SIGNAL_PERSISTENT_PROCESSES\",\"remark\":\"发送一个永久的进程信号\"},{\"mode\":\"状态栏控制\",\"permission\":\"android.permission.STATUS_BAR\",\"remark\":\"允许程序打开、关闭、禁用状态栏\"},{\"mode\":\"访问订阅内容\",\"permission\":\"android.permission.SUBSCRIBED_FEEDS_READ\",\"remark\":\"访问订阅信息的数据库\"},{\"mode\":\"写入订阅内容\",\"permission\":\"android.permission.SUBSCRIBED_FEEDS_WRITE\",\"remark\":\"写入或修改订阅内容的数据库\"},{\"mode\":\"显示系统窗口\",\"permission\":\"android.permission.SYSTEM_ALERT_WINDOW\",\"remark\":\"显示系统窗口\"},{\"mode\":\"更新设备状态\",\"permission\":\"android.permission.UPDATE_DEVICE_STATS\",\"remark\":\"更新设备状态\"},{\"mode\":\"使用证书\",\"permission\":\"android.permission.USE_CREDENTIALS\",\"remark\":\"允许程序请求验证从AccountManager\"},{\"mode\":\"使用SIP视频\",\"permission\":\"android.permission.USE_SIP\",\"remark\":\"允许程序使用SIP视频服务\"},{\"mode\":\"使用振动\",\"permission\":\"android.permission.VIBRATE\",\"remark\":\"允许振动\"},{\"mode\":\"唤醒锁定\",\"permission\":\"android.permission.WAKE_LOCK\",\"remark\":\"允许程序在手机屏幕关闭后后台进程仍然运行\"},{\"mode\":\"写入GPRS接入点设置\",\"permission\":\"android.permission.WRITE_APN_SETTINGS\",\"remark\":\"写入网络GPRS接入点设置\"},{\"mode\":\"写入日程提醒\",\"permission\":\"android.permission.WRITE_CALENDAR\",\"remark\":\"写入日程,但不可读取\"},{\"mode\":\"写**系人\",\"permission\":\"android.permission.WRITE_CONTACTS\",\"remark\":\"写**系人,但不可读取\"},{\"mode\":\"写入外部存储\",\"permission\":\"android.permission.WRITE_EXTERNAL_STORAGE\",\"remark\":\"允许程序写入外部存储,如SD卡上写文件\"},{\"mode\":\"写入Google地图数据\",\"permission\":\"android.permission.WRITE_GSERVICES\",\"remark\":\"允许程序写入Google Map服务数据\"},{\"mode\":\"写入收藏夹和历史记录\",\"permission\":\"com.android.browser.permission.WRITE_HISTORY_BOOKMARKS\",\"remark\":\"写入浏览器历史记录或收藏夹,但不可读取\"},{\"mode\":\"读写系统敏感设置\",\"permission\":\"android.permission.WRITE_SECURE_SETTINGS\",\"remark\":\"允许程序读写系统安全敏感的设置项\"},{\"mode\":\"读写系统设置\",\"permission\":\"android.permission.WRITE_SETTINGS\",\"remark\":\"允许读写系统设置项\"},{\"mode\":\"编写短信\",\"permission\":\"android.permission.WRITE_SMS\",\"remark\":\"允许编写短信\"}]\n";
}
