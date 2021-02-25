package listview.tianhetbm.p2p.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import butterknife.ButterKnife;

/**
 * @date:2020/9/15
 * @author:dongxiaogang
 * @description:
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
    }

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    public void closeCurrentActivity(){
        AppManager.getInstance().removeCurrentActivity();
    }


}
