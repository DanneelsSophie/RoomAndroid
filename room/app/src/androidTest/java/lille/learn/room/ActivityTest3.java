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
import lille.learn.room.database.NoteRoomDatabase;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ActivityTest3 {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserDao userDao;
    private RepoDao repoDao;
    private UserRepoJoinDao userRepoDao;
    private NoteRoomDatabase mDb;

    private UserWithReposDao userWithRepoDao;
    private UserDao1 userDao1;
    private RepoDao1 repoDao1;



    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, NoteRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        userDao = mDb.userDao();
        repoDao = mDb.repoDao();
        userRepoDao = mDb.userRepoJoinDao();
        userWithRepoDao = mDb.userWithReposDao();
        repoDao1 = mDb.repo1Dao();
        userDao1 = mDb.user1Dao();


    }

    @After
    public void closeDb() {
        mDb.close();
    }


    @Test
    public void testRelationManytoMany() throws Exception {
        userDao.insert(new User(1, "login", "lienurl"));
        repoDao.insert(new Repo(1,"repo1","url"));
        repoDao.insert(new Repo(2,"repo2","url"));
        userDao.insert(new User(2, "login1", "lienurl"));
        userRepoDao.insert(new UserRepoJoin(1,1));
        userRepoDao.insert(new UserRepoJoin(1,2));
        userRepoDao.insert(new UserRepoJoin(2,2));
        assertEquals("login",userRepoDao.getUsersForRepository(2).get(0).getLogin());
        assertEquals("login1",userRepoDao.getUsersForRepository(2).get(1).getLogin());

        assertEquals("login",userRepoDao.getUsersForRepository(1).get(0).getLogin());

        assertEquals("repo1",userRepoDao.getRepositoriesForUsers(1).get(0).getName());
        assertEquals("repo2",userRepoDao.getRepositoriesForUsers(1).get(1).getName());

        assertEquals("repo2",userRepoDao.getRepositoriesForUsers(2).get(0).getName());

    }



    @Test
    public void testRelationAnnotation() throws Exception {
        userDao1.insert(new User1(1, "login", "lienurl"));
        repoDao1.insert(new Repo1(1,"repo1","url",1));
        repoDao1.insert(new Repo1(2,"repo2","url",1));
        userDao.insert(new User(2, "login1", "lienurl"));
        assertEquals("login",userWithRepoDao.getUsersWithRepos().get(0).user.getLogin());

    }
}
