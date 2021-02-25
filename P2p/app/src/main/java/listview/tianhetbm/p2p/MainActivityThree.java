package listview.tianhetbm.p2p;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import listview.tianhetbm.p2p.adapter.MyAdapter;
import listview.tianhetbm.p2p.bean.Person;
import listview.tianhetbm.p2p.ui.LetterSlideBar;

/**
 * @date:2020/12/9
 * @author:dongxiaogang
 * @description:
 */
public class MainActivityThree extends FragmentActivity implements LetterSlideBar.OnClickListener {
    @Bind(R.id.tvPop)
    TextView tvPop;
    @Bind(R.id.letter_slide_bar)
    LetterSlideBar letterSlideBar;
    @Bind(R.id.list)
    ListView list;
    private List<Person> listArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_three);
        ButterKnife.bind(this);
        letterSlideBar.setOnClick(this);
        initData();
        list.setAdapter(new MyAdapter(this,listArr));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            list.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    letterSlideBar.setTouchIndex(listArr.get(firstVisibleItem).getHeaderWord());
                }
            });
        }
    }

    @Override
    public void down(String curentLeter, boolean isShow) {
        if (isShow) {
            tvPop.setVisibility(View.VISIBLE);
            tvPop.setText(curentLeter);
            updateListView(curentLeter);
        } else {
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            tvPop.setVisibility(View.GONE);
                        }
                    }, 1000

            );

        }

    }
    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < listArr.size(); i++) {
            String headerWord = listArr.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                list.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }
    /**
     * 初始化联系人列表信息
     */
    private void initData() {
        listArr = new ArrayList<>();
        listArr.add(new Person("Dave"));
        listArr.add(new Person("阿钟"));
        //省略一些....
        listArr.add(new Person("胡继群"));
        listArr.add(new Person("隔壁老王"));
        listArr.add(new Person("姜宇航"));
        listArr.add(new Person("安"));
        listArr.add(new Person("安11"));
        listArr.add(new Person("安22"));
        listArr.add(new Person("安1122"));
        listArr.add(new Person("安333"));
        listArr.add(new Person("安1133"));
        listArr.add(new Person("安4444"));
        listArr.add(new Person("安11444"));
        listArr.add(new Person("包"));
        listArr.add(new Person("成"));
        listArr.add(new Person("嗯"));
        listArr.add(new Person("分"));
        listArr.add(new Person("i"));
        listArr.add(new Person("开"));
        listArr.add(new Person("里"));
        listArr.add(new Person("们"));

        //对集合排序
        Collections.sort(listArr, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }


}
