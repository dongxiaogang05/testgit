package listview.tianhetbm.p2p.fragment;

import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import listview.tianhetbm.p2p.R;
import listview.tianhetbm.p2p.bean.Index;
import listview.tianhetbm.p2p.common.AppNetConfig;
import listview.tianhetbm.p2p.common.BaseFragment;
import listview.tianhetbm.p2p.ui.MyScrollview;
import listview.tianhetbm.p2p.ui.RoundProgress;

/**
 * @date:2020/9/7
 * @author:dongxiaogang
 * @description:
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.vp_barner)
    ViewPager vpBarner;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.myscrollview)
    MyScrollview myscrollview;
    @Bind(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @Bind(R.id.p_progresss)
    RoundProgress pProgresss;
    private Gson gson;
    private Index index;

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void initData(String content) {
        gson = new Gson();
        if (!TextUtils.isEmpty(content)) {
            index = gson.fromJson(content, Index.class);
            List<Index.ImageArr> list = index.imageArr;
            vpBarner.setAdapter(new MyAdapter(list));
            circleBarner.setViewPager(vpBarner);
            totalProgress = Integer.parseInt(index.proInfo.progress);
            new Thread(runnable).start();
        }

    }
    private int totalProgress;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int tempProgress = 0;
            try {
                while (tempProgress <= totalProgress) {
                    pProgresss.setProgress(tempProgress);
                    tempProgress++;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);


    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }




    private class MyAdapter extends PagerAdapter {
        private List<Index.ImageArr> list;

        public MyAdapter(List<Index.ImageArr> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = list.get(position).IMAURL;
            imageUrl = imageUrl.substring(22, imageUrl.length());
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load("http://w5afvq.natappfree.cc/" + imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
