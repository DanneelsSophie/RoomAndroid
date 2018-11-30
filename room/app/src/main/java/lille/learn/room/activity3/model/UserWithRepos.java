package lille.learn.room.activity3.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class UserWithRepos {
    @Embedded
    public User1 user;

    @Relation(parentColumn = "id",
            entityColumn = "userId") public List<Repo1> repoList;

    public User1 getUser1() {
        return user;
    }

    public void setUser1(User1 user) {
        this.user = user;
    }

    public List<Repo1> getRepo1List() {
        return repoList;
    }

    public void setRepo1List(List<Repo1> repoList) {
        this.repoList = repoList;
    }
}
