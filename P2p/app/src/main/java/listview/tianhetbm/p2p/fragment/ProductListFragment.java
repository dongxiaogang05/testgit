package listview.tianhetbm.p2p.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import listview.tianhetbm.p2p.R;
import listview.tianhetbm.p2p.bean.Product;
import listview.tianhetbm.p2p.common.AppNetConfig;
import listview.tianhetbm.p2p.ui.RoundProgress;
import listview.tianhetbm.p2p.util.UIUtils;

/**
 * @date:2020/9/15
 * @author:dongxiaogang
 * @description:
 */
public class ProductListFragment extends Fragment {
    @Bind(R.id.lv)
    ListView lv;
    AsyncHttpClient client = new AsyncHttpClient();
    private Gson gson;
    private List<Product.Data> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        ButterKnife.bind(this, view);
        initTitle();
        initData();
        return view;
    }

    private void initData() {
        gson = new Gson();
        list = new ArrayList<>();
        client.get(AppNetConfig.PRODUCT, new RequestParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if (!TextUtils.isEmpty(content)) {
                    Product product = gson.fromJson(content, Product.class);
                    list = product.data;
                    lv.setAdapter(new MyAdapter(list));

                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    private void initTitle() {
    }

    public class MyAdapter extends BaseAdapter {
        List<Product.Data> list;

        public MyAdapter(List<Product.Data> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Product.Data product = list.get(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //设置数据
            viewHolder.pMinzouzi.setText(product.minTouMoney);
            viewHolder.pMoney.setText(product.money);
            viewHolder.pName.setText(product.name);
            viewHolder.pSuodingdays.setText(product.suodingDays);
            viewHolder.pYearlv.setText(product.yearLv);
            viewHolder.pProgresss.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }


    }
    static class ViewHolder {
        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
