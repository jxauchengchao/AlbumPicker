package com.csdn.icoder.albumpicker;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by icoder on 15/9/16.
 */
public class Utils {


    public  void getAlbums(final Context mContext, final OnLoadAlbum loadAlbum) {

        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == Constant.MSG_SUCCESS) {
                        loadAlbum.onResult((ArrayList<Album>) msg.obj);
                }

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.obtainMessage(Constant.MSG_SUCCESS,getImages(mContext)).sendToTarget();
            }
        }).start();
    }
    private ArrayList<Album> getImages(Context context) {
        ArrayList<Album> images = new ArrayList<Album>();
        Album image;
        final String[] columns = { MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        Cursor imagecursor = context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
                        null, null, orderBy + " DESC");
        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor
                    .getColumnIndex(MediaStore.Images.Media.DATA);
            image = new Album();
            image.setUri(imagecursor.getString(dataColumnIndex));
            image.setType(Constant.TYPE_PIC);
            image.setChecked(false);
            images.add(image);
        }
        return images;
    }

    private static ArrayList<Album> getAlbums(Context mContext) {

        ArrayList<Album> albums=new ArrayList<Album>();
        Album album=null;
        String[]colums={MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        String orderBy= MediaStore.Images.Media.DATE_TAKEN;
        Cursor albumCursor=mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,colums,null,null,orderBy+"DESC");

        for (int i=0;i<albumCursor.getCount();i++)
        {
            albumCursor.moveToPosition(i);
            int index=albumCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            String uri=albumCursor.getString(index);
            album=new Album();
            album.setUri(uri);
            albums.add(album);
        }
        return albums;
    }


    public interface OnLoadAlbum {
        void onResult(ArrayList<Album> mAlbums);
    }

}
