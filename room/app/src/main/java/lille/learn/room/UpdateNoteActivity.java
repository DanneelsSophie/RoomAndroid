package lille.learn.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lille.learn.room.model.Note;

/**
 * Ici on a l'activité update
 */
public class UpdateNoteActivity extends AppCompatActivity {

    private EditText mEditNoteView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditNoteView = findViewById(R.id.edit_note);
        final Note note = GetIncomingIntent();

        // quand on clique sur le button update on retourne à l'activity Main, en mettant quelque chose
        // dans l'insert
        final Button button = findViewById(R.id.button_update);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                note.setNote(String.valueOf(mEditNoteView.getText()));
                Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
                intent.putExtra("note1", note);
                startActivity(intent);
            }
        });


    }

    /**
     * Cette fonction renvoie la note qu'on souhaite
     * modifier quand on a appuyé sur le boutton update avant
     * @return la note qu'on doit modifier
     */
    public Note GetIncomingIntent(){
        if (getIntent().hasExtra("note")){
            Note note = (Note) getIntent().getSerializableExtra("note");
            mEditNoteView.setText( note.getNote());
            return note;

        }
        return null;
    }

}



