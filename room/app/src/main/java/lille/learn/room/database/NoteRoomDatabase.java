package lille.learn.room.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import lille.learn.room.activity2.dao.CompanyDao;
import lille.learn.room.activity2.dao.EmployeeDao;
import lille.learn.room.activity2.model.Company;
import lille.learn.room.activity2.model.Employee;
import lille.learn.room.dao.NoteDao;
import lille.learn.room.model.Note;

@Database(entities = {Note.class, Company.class,Employee.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    // Les Daos
    //first activity
    public abstract NoteDao noteDao();


    //second activity
    public abstract CompanyDao companyDao();
    public abstract EmployeeDao employeeDao();

    // Un Singleton
    private static volatile NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "note_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;
        private final EmployeeDao employeeDao;
        private final CompanyDao cDao;

        PopulateDbAsync(NoteRoomDatabase db) {
            mDao = db.noteDao();
            cDao = db.companyDao();
            employeeDao = db.employeeDao();

        }

        //création des données qui sera de base dans l'application
        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Note note = new Note("Hello");
            mDao.insert(note);
            note = new Note("World");
            mDao.insert(note);
            return null;
        }
    }




}
