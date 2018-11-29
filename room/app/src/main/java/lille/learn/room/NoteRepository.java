package lille.learn.room;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import lille.learn.room.dao.NoteDao;
import lille.learn.room.database.NoteRoomDatabase;
import lille.learn.room.model.Note;

/**
 * Ici permet d'executer les actions sur le Dao
 */
class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;


    NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAlphabetizedNotes();
    }


    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }


    void insert(Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    void delete(Note note) {
        new deleteAsyncTask(mNoteDao).execute(note);
    }

    void update(Note note) {
        new updateAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        deleteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        updateAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }


}
