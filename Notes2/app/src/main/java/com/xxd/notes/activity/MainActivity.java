package com.xxd.notes.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.xxd.notes.R;
import com.xxd.notes.dbHelper.NotesDB;

public class MainActivity extends ListActivity {
    private SimpleCursorAdapter adapter = null;
    private NotesDB db;
    private SQLiteDatabase dbRead;

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_EDIT_NOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new NotesDB(this);
        dbRead = db.getReadableDatabase();

        // 查询数据库并将数据显示在ListView上。
        // 建议使用CursorLoader，这个操作因为在UI线程，容易引起无响应错误
        adapter = new SimpleCursorAdapter(this,R.layout.notes_list_cell,null,
                new String[] { NotesDB.COLUMN_NAME_NOTE_NAME,
                        NotesDB.COLUMN_NAME_NOTE_DATE},new int[] { R.id.tvName, R.id.tvDate});
        setListAdapter(adapter);

        refreshNotesListView();

        findViewById(R.id.btnAddNote).setOnClickListener(btnAddNote_clickHandler);
    }

    /**
     * 实现OnClickListener接口，添加日志按钮的监听
     */
    private View.OnClickListener btnAddNote_clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 有返回结果的开启编辑日志的Activity，
            // requestCode If >= 0, this code will be returned
            // in onActivityResult() when the activity exits.
            startActivityForResult(new Intent(MainActivity.this,AtyEditNote.class),REQUEST_CODE_ADD_NOTE);
        }
    };

    /**
     * 复写方法，笔记列表中的笔记条目被点击时被调用，打开编辑笔记页面，同事传入当前笔记的信息
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //获取当前笔记条目的Cursor对象
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);

        //显示Intent开启编辑笔记页面
        Intent intent = new Intent(MainActivity.this,AtyEditNote.class);

        //传入笔记id，name，content
        intent.putExtra(AtyEditNote.EXTRA_NOTE_ID,
                    cursor.getInt(cursor.getColumnIndex(NotesDB.COLUMN_NAME_ID)));
        intent.putExtra(AtyEditNote.EXTRA_NOTE_NAME,
                    cursor.getString(cursor.getColumnIndex(NotesDB.COLUMN_NAME_NOTE_NAME)));
        intent.putExtra(AtyEditNote.EXTRA_NOTE_CONTENT,
                    cursor.getString(cursor.getColumnIndex(NotesDB.COLUMN_NAME_NOTE_CONTENT)));

        //有返回值的开启activity
        startActivityForResult(intent,REQUEST_CODE_EDIT_NOTE);

        super.onListItemClick(l, v, position, id);
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with 当被开启的Activity存在并返回结果时调用的方法
     *
     * 当从编辑笔记页面返回时调用，刷新笔记列表
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD_NOTE:
            case REQUEST_CODE_EDIT_NOTE:
                if (resultCode == Activity.RESULT_OK){
                    refreshNotesListView();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 刷新笔记列表，内容从数据库中查询
     */
    public void refreshNotesListView(){
        /**
         * Change the underlying cursor to a new cursor. If there is an existing
         * cursor it will be closed.
         *
         * Parameters: cursor The new cursor to be used
         */
        adapter.changeCursor(dbRead.query(NotesDB.TABLE_NAME_NOTES,null,null,null,null,null,null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
