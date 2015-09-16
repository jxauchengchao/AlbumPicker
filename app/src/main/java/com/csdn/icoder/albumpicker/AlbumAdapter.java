package com.csdn.icoder.albumpicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by icoder on 15/9/15.
 */
public class AlbumAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Album> mArrayDatas;
    private ArrayList<Album> mSelectedDatas;
    private RecyclerView mRecyclerView;
    private Context mContext;

    public AlbumAdapter(Context mContext, RecyclerView mRecyclerView, ArrayList<Album> mArrayDatas) {
        this.mContext = mContext;
        this.mRecyclerView = mRecyclerView;
        this.mArrayDatas = mArrayDatas;
        mSelectedDatas = new ArrayList<Album>(Constant.MAX_SELECTED_NUM);

        mArrayDatas.add(0,new Album(Constant.TYPE_CAMERA));
        mArrayDatas.add(1,new Album(Constant.TYPE_VIDEO));
    }

    public ArrayList<Album> getSelectedAlbum() {
        return mSelectedDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case Constant.TYPE_CAMERA:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_camera, parent, false);
                return new CameraViewHolder(view);
            case Constant.TYPE_PIC:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_pic, parent, false);
                return new PicViewHolder(view);
            case Constant.TYPE_VIDEO:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
                return new VideoViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CameraViewHolder cameraViewHolder = null;
        VideoViewHolder videoViewHolder = null;
        int type = getItemViewType(position);
        switch (type) {
            case Constant.TYPE_CAMERA:
                    Toast.makeText(mContext,"open Camera! ",Toast.LENGTH_SHORT).show();
                break;
            case Constant.TYPE_PIC:
                final Album album = mArrayDatas.get(position);
                String uri = album.getUri();
                if (!uri.startsWith("file://")) {
                    uri = "file://" + uri;
                }
                ImageLoader.getInstance().displayImage(uri, ((PicViewHolder) holder).content);
                ((PicViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                                                                     @Override
                                                                     public void onClick(View v) {
                                                                         boolean isChecked = !album.isChecked();
                                                                         if (isChecked) {
                                                                             if (mSelectedDatas.size() >= Constant.MAX_SELECTED_NUM)
                                                                                 return;
                                                                             mSelectedDatas.add(album);
                                                                         } else {
                                                                             mSelectedDatas.remove(album);
                                                                         }
                                                                         album.setChecked(isChecked);
                                                                         ((PicViewHolder) holder).cb.setChecked(isChecked);
                                                                     }
                                                                 }
                );
                break;
            case Constant.TYPE_VIDEO:
                Toast.makeText(mContext,"open video!",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mArrayDatas == null ? 0 : mArrayDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = mArrayDatas.get(position).getType();
        switch (type) {
            case Constant.TYPE_CAMERA:
                return Constant.TYPE_CAMERA;
            case Constant.TYPE_PIC:
                return Constant.TYPE_PIC;
            case Constant.TYPE_VIDEO:
                return Constant.TYPE_VIDEO;
        }
        return -1;
    }

    class PicViewHolder extends RecyclerView.ViewHolder {

        View item;
        ImageView content;
        CheckBox cb;

        public PicViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            content = (ImageView) item.findViewById(R.id.item_content);
            cb = (CheckBox) item.findViewById(R.id.item_cb);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        View item;
        ImageView content;
        CheckBox cb;

        public VideoViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            content = (ImageView) item.findViewById(R.id.item_content);
            cb = (CheckBox) item.findViewById(R.id.item_cb);
        }
    }

    class CameraViewHolder extends RecyclerView.ViewHolder {

        View item;
        ImageView content;

        public CameraViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            content = (ImageView) item.findViewById(R.id.item_content);
        }
    }
}
