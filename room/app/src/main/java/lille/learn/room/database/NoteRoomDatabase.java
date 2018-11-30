package lille.learn.room.database;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import lille.learn.room.activity2.dao.CompanyDao;
import lille.learn.room.activity2.dao.CompanyDepartmentsDao;
import lille.learn.room.activity2.dao.DepartmentDao;
import lille.learn.room.activity2.dao.EmployeeDao;
import lille.learn.room.activity2.model.Company;
import lille.learn.room.activity2.model.Department;
import lille.learn.room.activity2.model.Employee;
import lille.learn.room.activity3.dao.RepoDao;
import lille.learn.room.activity3.dao.RepoDao1;
import lille.learn.room.activity3.dao.UserDao;
import lille.learn.room.activity3.dao.UserDao1;
import lille.learn.room.activity3.dao.UserRepoJoinDao;
import lille.learn.room.activity3.dao.UserWithReposDao;
import lille.learn.room.activity3.model.Repo;
import lille.learn.room.activity3.model.Repo1;
import lille.learn.room.activity3.model.User;
import lille.learn.room.activity3.model.User1;
import lille.learn.room.activity3.model.UserRepoJoin;
import lille.learn.room.dao.NoteDao;
import lille.learn.room.model.Note;


/**
 * Ce fichier consiste a initialisé la bdd
 */
@Database(entities = {Note.class, Company.class, Employee.class, Department.class, Repo.class,User.class,Repo1.class,User1.class,UserRepoJoin.class}, version = 5, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {


    // Les Daos
    //first activity
    public abstract NoteDao noteDao();
    //second activity
    public abstract CompanyDao companyDao();
    public abstract EmployeeDao employeeDao();
    public abstract DepartmentDao departmentDao();
    public abstract CompanyDepartmentsDao companyAndAllDepartmentsDao();

    // third activity
    public abstract RepoDao repoDao();
    public abstract UserDao userDao();
    public abstract UserRepoJoinDao userRepoJoinDao();

    public abstract RepoDao1 repo1Dao();
    public abstract UserDao1 user1Dao();
    public abstract UserWithReposDao userWithReposDao();

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



    // Ici, c'est comme un create, on dit ce qu'on souhaite lorsque la bdd
    // vient d'être crée
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;
        private final CompanyDao cDao;
        private final EmployeeDao eDao;
        private final DepartmentDao dDao;
        private final CompanyDepartmentsDao cdDao;
        private final RepoDao repoDao;
        private final UserDao usrDao;
        private final RepoDao1 repo1Dao;
        private final UserDao1 usr1Dao;
        private final UserRepoJoinDao userRepoDao;
        private final UserWithReposDao userwithRepo;



        PopulateDbAsync(NoteRoomDatabase db) {
            cDao = db.companyDao();
            mDao = db.noteDao();
            eDao = db.employeeDao();
            dDao = db.departmentDao();
            cdDao = db.companyAndAllDepartmentsDao();
            repoDao = db.repoDao();
            usrDao = db.userDao();
            userRepoDao = db.userRepoJoinDao();
            userwithRepo = db.userWithReposDao();
            repo1Dao = db.repo1Dao();
            usr1Dao = db.user1Dao();


        }

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
