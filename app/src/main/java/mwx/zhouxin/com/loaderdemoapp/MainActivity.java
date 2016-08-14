package mwx.zhouxin.com.loaderdemoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import mwx.zhouxin.com.loaderdemoapp.adapter.AudioAdapter;

// 使用Loader
// 获取显示手机所有音频文件
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private GridView gridView;
    private AudioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new AudioAdapter(this);
        gridView.setAdapter(adapter);
        // 初始Loader(1. id为0的loader已存在，拿出来直接重用， id为0的loader不存在,触发onCreateLoader方法 )
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                // id
                MediaStore.Audio.Media._ID,
                // 音乐名称
                MediaStore.Audio.Media.DISPLAY_NAME,
                // data
                MediaStore.Audio.Media.DATA,
                // 音乐专辑
                MediaStore.Audio.Media.ALBUM,
                // 谁唱的
                MediaStore.Audio.Media.ARTIST,
                // 时长
                MediaStore.Audio.Media.DURATION,
                // 大小
                MediaStore.Audio.Media.SIZE
        };
        // 视频数据的URI
        return new CursorLoader(
                this,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // 将Loader异步加载完成的Cursor，给adapter
        adapter.swapCursor(cursor);
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}