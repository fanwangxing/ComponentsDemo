package fan.componentsdemo.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fan.componentsdemo.R;

public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText input;
    private Button insert,query;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initView();
        setLisener();

    }

    private void initView() {
        input = (EditText)findViewById(R.id.input);
        insert = (Button)findViewById(R.id.insert);
        query = (Button)findViewById(R.id.query);
        show = (TextView) findViewById(R.id.show);
    }

    private void setLisener() {
        insert.setOnClickListener(this);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.insert:
                insert(input.getText().toString());
                break;
            case R.id.query:
                show.setText(query());
                break;
        }

    }

    private void insert(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookname",name);
        getContentResolver().insert(MyContentProvider.BOOK_URI,contentValues);
    }
    private String query(){
        Cursor cursor = getContentResolver().query(MyContentProvider.BOOK_URI, null, null, null, null);
        cursor.moveToLast();
        String value = cursor.getString(2);
        cursor.close();
        return value;
    }
}
