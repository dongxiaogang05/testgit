package listview.tianhetbm.p2p;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import listview.tianhetbm.p2p.bean.TestBean;

/**
 * @date:2020/9/25
 * @author:dongxiaogang
 * @description:
 */
public class TestActivity extends Activity {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.duct_piece_track_begin_huanhao)
    EditText ductPieceTrackBeginHuanhao;
    @Bind(R.id.duct_piece_track_stop_huanhao)
    EditText ductPieceTrackStopHuanhao;
    @Bind(R.id.duct_piece_track_chaxun_btn)
    Button ductPieceTrackChaxunBtn;
    @Bind(R.id.num_one)
    TextView numOne;
    @Bind(R.id.num_two)
    TextView numTwo;
    @Bind(R.id.num_three)
    TextView numThree;
    AsyncHttpClient client = new AsyncHttpClient();
    String parmer1;
    String parmer2;
    Gson gson;
    @Bind(R.id.duct_piece_track_begin_huanhao_tishi)
    LinearLayout ductPieceTrackBeginHuanhaoTishi;
    @Bind(R.id.duct_piece_track_jies_huanhao_tishi)
    LinearLayout ductPieceTrackJiesHuanhaoTishi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        gson = new Gson();
        parmer1 = "7.7";
        parmer2 = "200";
        ductPieceTrackBeginHuanhao.setText(parmer1);
        ductPieceTrackStopHuanhao.setText(parmer2);
        getData();
    }

    @OnClick(R.id.duct_piece_track_chaxun_btn)
    public void onClick() {
        String p1=ductPieceTrackBeginHuanhao.getText().toString().trim();
        String p2=ductPieceTrackStopHuanhao.getText().toString().trim();
        if(TextUtils.isEmpty(p1)||TextUtils.isEmpty(p2)){
            Toast.makeText(getApplicationContext(),"参数不完整",Toast.LENGTH_SHORT);
            return;
        }else{
            if(Float.parseFloat(p1)<3||Float.parseFloat(p1)>20){
                ductPieceTrackBeginHuanhaoTishi.setVisibility(View.VISIBLE);
            }
            if(Float.parseFloat(p2)<50||Float.parseFloat(p2)>1200){
                ductPieceTrackJiesHuanhaoTishi.setVisibility(View.VISIBLE);
            }
            if(Float.parseFloat(p1)>=3&&Float.parseFloat(p1)<=20&&Float.parseFloat(p2)>=50&&Float.parseFloat(p2)<=1200){
                parmer1=p1;
                parmer2=p2;
                getData();
            }
        }
    }

    public void getData() {
        client.get("http://xacfwebservice.top:8080/smart/getStart/" + parmer2 + "/" + parmer1, new RequestParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if (!TextUtils.isEmpty(content)) {
                    TestBean bean = gson.fromJson(content, TestBean.class);
                    numOne.setText(bean.d_angle + "");
                    numTwo.setText(bean.t_offset + "");
                    numThree.setText(bean.state + "");
                } else {
                    numOne.setText("---");
                    numTwo.setText("---");
                    numThree.setText("---");
                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }
}
