package util;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import luoboman.www.luobo.com.myapplication.R;

/**
 * Created by TA on 2017/10/14.
 */

public class MyApp extends Application{
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImg();
    }
    //缓存图片
    private void initImg() {
        DisplayImageOptions options  = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//允许内存缓存
                .cacheOnDisk(true)//允许磁盘缓存
                .showImageForEmptyUri(R.mipmap.ic_launcher)//uri为空的时候，设置图片
                .showImageOnFail(R.mipmap.ic_launcher)//失败的时候，设置图片
                .showImageOnLoading(R.mipmap.ic_launcher)//加载过程中需要显示的图片，设置图片
                .build();
        ImageLoaderConfiguration configuration  = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }


    public static Context getInstance() {
        return instance;
    }

}
