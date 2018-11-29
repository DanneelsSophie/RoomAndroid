package lille.learn.room;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


import lille.learn.room.dao.NoteDao;
import lille.learn.room.database.NoteRoomDatabase;
import lille.learn.room.model.Note;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class NoteDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private NoteDao mNoteDao;
    private NoteRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, NoteRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        mNoteDao = mDb.noteDao();

    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetNote() throws Exception {
        Note note = new Note("note");
        mNoteDao.insert(note);
        List<Note> allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        assertEquals(allNotes.get(0).getNote(), note.getNote());
    }

    @Test
    public void getAllNotes() throws Exception {
        Note note = new Note("aaa");
        mNoteDao.insert(note);
        Note note2 = new Note("bbb");
        mNoteDao.insert(note2);
        List<Note> allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        assertEquals(allNotes.get(0).getNote(), note.getNote());
        assertEquals(allNotes.get(1).getNote(), note2.getNote());
    }

    @Test
    public void deleteAll() throws Exception {
        Note note = new Note("note");
        mNoteDao.insert(note);
        Note note2 = new Note("note2");
        mNoteDao.insert(note2);
        mNoteDao.deleteAll();
        List<Note> allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        assertTrue(allNotes.isEmpty());
    }

    @Test
    public void update() throws Exception {
        Note note = new Note("note");
        mNoteDao.insert(note);
        List<Note> allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        Note note1 = allNotes.get(0);
        note1.setNote("test");
        mNoteDao.update(note1);
        allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        assertEquals("test", allNotes.get(0).getNote());

    }

    @Test
    public void delete() throws Exception {
        Note note = new Note("note");
        mNoteDao.insert(note);
        List<Note> allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        Note note1 = allNotes.get(0);
        mNoteDao.delete(note1);
        allNotes = LiveDataTestUtil.getValue(mNoteDao.getAlphabetizedNotes());
        assertTrue(allNotes.isEmpty());

    }


}
