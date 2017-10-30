package luoboman.www.luobo.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.MyAdapter;
import m.bean;
import okhttp3.Call;
import util.GlideImageLoader;
import util.GsonObjectCallback;
import util.OkHttp3Utils;

public class MainActivity extends AppCompatActivity {
    String  path="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0 ";

    ImageView img;
    TextView text,text2;
    RecyclerView recyclerView;
    //请求数据集合
    MyAdapter adapter;
    Banner banner;
    List<String>listbanner =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        img=(ImageView)findViewById(R.id.img);
        text=(TextView)findViewById(R.id.text3);
        text2=(TextView)findViewById(R.id.text3);
        banner=(Banner)findViewById(R.id.banner);
        getData2();
        getData();
    }
    public void getData(){
        OkHttp3Utils.doGet(path, new GsonObjectCallback<bean>() {


            @Override
            public void onUi(bean bean) {

                MyAdapter adapter = new MyAdapter(bean.getSong_list(),MainActivity.this);
                recyclerView.setAdapter(adapter);//设置适配器

                // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                //StaggeredGridLayoutManager.HORIZONTAL));

                // mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
               // LinearLayoutManager man=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                //recyclerView.setLayoutManager(man);
                //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
                recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                        DividerItemDecoration.VERTICAL));
                adapter.setOnItemClieckLinster(new MyAdapter.OnItemClieckLinster() {
                    @Override
                    public void onItemClickListener(View view, int pos) {
                          Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClickListener(View view, int pos) {
                        Toast.makeText(MainActivity.this, "long click" + pos , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });




    }
    public void getData2(){
        OkHttp3Utils.doGet(path, new GsonObjectCallback<bean>() {

            private List<bean.SongListBean> song_list;

            @Override
            public void onUi(bean bean) {
                song_list = bean.getSong_list();
                for(int i=0;i<song_list.size();i++){
                    listbanner.add(song_list.get(i).getPic_big());
                }
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(listbanner);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }


}
