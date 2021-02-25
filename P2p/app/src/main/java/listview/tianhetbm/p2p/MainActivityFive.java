package listview.tianhetbm.p2p;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import listview.tianhetbm.p2p.ui.MySurfaceView;

/**
 * @date:2021/1/27
 * @author:dongxiaogang
 * @description:
 */
public class MainActivityFive extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 把Activity的标题去掉
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置布局
        this.setContentView(new MySurfaceView(this));
    }
}
