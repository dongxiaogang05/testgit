package listview.tianhetbm.p2p.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @date:2020/9/9
 * @author:dongxiaogang
 * @description:
 */
public class P2pApplication extends Application {
    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread = null;
    public static int mainThreadId = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        //CrashHandler.getInstance().init(this);
    }
}
