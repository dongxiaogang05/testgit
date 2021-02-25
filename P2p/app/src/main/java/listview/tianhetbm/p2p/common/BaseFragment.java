package listview.tianhetbm.p2p.common;



import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import listview.tianhetbm.p2p.ui.LoadingPage;
import listview.tianhetbm.p2p.util.UIUtils;

/**
 * @date:2020/9/11
 * @author:dongxiaogang
 * @description:
 */
public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            public int LayoutId() {
                return getLayoutId();
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected void OnSuccess(ResultState resultState, View successView) {
                ButterKnife.bind(BaseFragment.this, successView);
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            protected String url() {
                return getUrl();
            }
        };
        return loadingPage;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);
    }

    private void show() {
        loadingPage.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected abstract String getUrl();

    protected abstract void initData(String content);

    protected abstract void initTitle();

    protected abstract RequestParams getParams();


    protected abstract int getLayoutId();
}
