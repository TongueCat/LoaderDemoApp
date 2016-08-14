package mwx.zhouxin.com.loaderdemoapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mwx.zhouxin.com.loaderdemoapp.R;

/**
 * Created by Administrator on 2016/8/14.
 */
public class AudioAdapter extends CursorAdapter {

    public AudioAdapter(Context context) {
        super(context, null, true);
    }

    @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_local_video,null);
        return view;
    }

    @Override public void bindView(View view, Context context, Cursor cursor) {
        TextView tvVideoName = (TextView) view.findViewById(R.id.tvVideoName);
        String videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
        tvVideoName.setText(videoName);
    }
}
