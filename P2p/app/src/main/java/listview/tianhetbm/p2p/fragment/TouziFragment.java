package listview.tianhetbm.p2p.fragment;





import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import listview.tianhetbm.p2p.R;
import listview.tianhetbm.p2p.common.BaseFragment;
import listview.tianhetbm.p2p.util.UIUtils;

/**
 * @date:2020/9/7
 * @author:dongxiaogang
 * @description:
 */
public class TouziFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.tab_indictor)
    TabPageIndicator tabIndictor;
    @Bind(R.id.pager)
    ViewPager pager;
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected void initData(String content) {
        initFragment();
        pager.setAdapter(new MyAdapter(getFragmentManager()));
        tabIndictor.setViewPager(pager);
    }

    @Override
    protected void initTitle() {
        titleTv.setText("我要投资");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);

    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_touzi;
    }
    private void initFragment() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommendFragment productRecommendFragment = new ProductRecommendFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        fragmentList.add(productListFragment);
        fragmentList.add(productRecommendFragment);
        fragmentList.add(productHotFragment);
    }
    private class MyAdapter extends FragmentPagerAdapter {



        public MyAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArr(R.array.touzi_tab)[position];
        }
    }
}
