package lille.learn.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import lille.learn.room.model.Note;


/**
 * Permet de synthétiser ce qu'on aura sur la vue; ici on prépare les données pour l'UI
 * ... voir le cours sur Android sur viewmodel pour plus d'informations
 */
public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;

    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    void insert(Note note) {
        mRepository.insert(note);
    }

    void delete(Note note){
        mRepository.delete(note);
    }

    void update(Note note){
        mRepository.update(note);
    }

}