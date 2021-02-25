package listview.tianhetbm.p2p;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import listview.tianhetbm.p2p.common.BaseActivity;
import listview.tianhetbm.p2p.fragment.HomeFragment;
import listview.tianhetbm.p2p.fragment.MeFragment;
import listview.tianhetbm.p2p.fragment.MoreFragment;
import listview.tianhetbm.p2p.fragment.TouziFragment;
import listview.tianhetbm.p2p.util.UIUtils;

public class MainActivity extends BaseActivity {
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.ll_home)
    LinearLayout llHome;
    @Bind(R.id.iv_touzi)
    ImageView ivTouzi;
    @Bind(R.id.tv_touzi)
    TextView tvTouzi;
    @Bind(R.id.ll_touzi)
    LinearLayout llTouzi;
    @Bind(R.id.iv_me)
    ImageView ivMe;
    @Bind(R.id.tv_me)
    TextView tvMe;
    @Bind(R.id.ll_me)
    LinearLayout llMe;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.ll_more)
    LinearLayout llMore;
    private HomeFragment homeFragment;
    private TouziFragment touziFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;




    @Override
    protected void initData() {
        setSelect(0);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_home,R.id.ll_touzi,R.id.ll_me,R.id.ll_more})
    public void changeTab(View view){
        switch (view.getId()){
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_touzi:
                setSelect(1);
                break;
            case R.id.ll_me:
                setSelect(2);
                break;
            case R.id.ll_more:
                setSelect(3);
                break;
        }
    }
    public static String getSHA1()
    {

//            String[] array = new String[] { token, timestamp, nonce, encrypt };
//            StringBuffer sb = new StringBuffer();
//            // 鐎涙顑佹稉鍙夊笓鎼达拷
//            Arrays.sort(array);
//            for (int i = 0; i < 4; i++) {
//                sb.append(array[i]);
//            }
            String str = "jsapi_ticket=JSTf5f0e456917ec3be&noncestr=cftestwscde&timestamp=159985102&url=https://xacfwebservice.top:6010/login/jumppage";
            // SHA1缁涙儳鎮曢悽鐔稿灇
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();

    }
    private void setSelect(int i) {
        FragmentManager fm=getSupportFragmentManager();
        ft = fm.beginTransaction();
        reSetTab();
        hideFragment();
        switch (i){
            case 0:
                if(homeFragment==null){
                    homeFragment = new HomeFragment();
                    ft.add(R.id.content,homeFragment);
                }
                ivHome.setImageResource(R.drawable.bid01);
                tvHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(homeFragment);
                break;
            case 1:
                if(touziFragment==null){
                    touziFragment = new TouziFragment();
                    ft.add(R.id.content,touziFragment);
                }
                ivTouzi.setImageResource(R.drawable.bid03);
                tvTouzi.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(touziFragment);
                break;
            case 2:
                if(meFragment==null){
                    meFragment = new MeFragment();
                    ft.add(R.id.content,meFragment);
                }
                ivMe.setImageResource(R.drawable.bid05);
                tvMe.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(meFragment);
                break;
            case 3:
                if(moreFragment==null){
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content,moreFragment);
                }
                ivMore.setImageResource(R.drawable.bid07);
                tvMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(moreFragment);
                break;
        }
        ft.commit();
    }

    private void reSetTab() {
        ivHome.setImageResource(R.drawable.bid02);
        ivTouzi.setImageResource(R.drawable.bid04);
        ivMe.setImageResource(R.drawable.bid06);
        ivMore.setImageResource(R.drawable.bid08);
        tvHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if(homeFragment!=null){
            ft.hide(homeFragment);
        }
        if(touziFragment!=null){
            ft.hide(touziFragment);
        }
        if(meFragment!=null){
            ft.hide(meFragment);
        }
        if(moreFragment!=null){
            ft.hide(moreFragment);
        }
    }


}
