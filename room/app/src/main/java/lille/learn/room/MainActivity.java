package lille.learn.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import lille.learn.room.model.Note;

public class MainActivity extends AppCompatActivity {


    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;


    private NoteViewModel mNoteViewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        final NoteListAdapter adapter = new NoteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> notes) {
                adapter.setNotes(notes);

            }
        });

        // Ici pour aller sur NewActivitée
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lille.learn.room.MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });


        GetIncomingIntent();
    }

    /**
     * On peut savoir si on revient d'une activée delete ou update et permet d'effectuer les actions
     * nécessaires
     *
     */
    public void GetIncomingIntent() {
        if (getIntent().hasExtra("note")) {

            Note note = (Note) getIntent().getSerializableExtra("note");
            CharSequence text = "delete!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, note.getNote(), duration);
            toast.show();
            mNoteViewModel.delete(note);

        }


        if (getIntent().hasExtra("note1")) {

            Note note = (Note) getIntent().getSerializableExtra("note1");
            CharSequence text = "update!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, note.getNote(), duration);
            toast.show();
            mNoteViewModel.update(note);

        }
    }

    /**
     * Activitée pour Create
     * @param requestCode le code pour savoir on a été sur quelle page
     * @param resultCode le resultat si le message n'était pas vide
     * @param data et la donnée à modifier
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Note note = new Note(data.getStringExtra(NewNoteActivity.EXTRA_REPLY));
            mNoteViewModel.insert(note);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}



