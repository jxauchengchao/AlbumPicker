package com.csdn.icoder.albumpicker;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by icoder on 15/9/15.
 */
public class AlbumApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(this)
                .diskCacheSize(20 * 1024 * 1024)
                .diskCacheFileCount(100)
                .threadPoolSize(ImageLoaderConfiguration.Builder.DEFAULT_THREAD_POOL_SIZE)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

}
