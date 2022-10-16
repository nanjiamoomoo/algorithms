package graph.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeUsers {

    public static class User {
        public String a;
        public String b;
        public String c;
        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**
     * As long as two users have at least one same property, then we consider them as the same user
     *
     * @param users
     * @return The number of unique users in the given user list
     */
    public static int mergeUsers(List<User> users) {
        UnionSet<User> uf = new UnionSet<>(users);
        Map<String, User> mapA = new HashMap<>();
        Map<String, User> mapB = new HashMap<>();
        Map<String, User> mapC = new HashMap<>();

        for (User user : users) {
            User sameUser = mapA.get(user.a);
            if (sameUser == null) {
                mapA.put(user.a, user);
            } else {
                uf.union(user, sameUser);
            }

            sameUser = mapB.get(user.b);
            if (sameUser == null) {
                mapB.put(user.b, user);
            } else {
                uf.union(user, sameUser);
            }

            sameUser = mapC.get(user.c);
            if (sameUser == null) {
                mapC.put(user.c, user);
            } else {
                uf.union(user, sameUser);
            }
        }

        return uf.sets();
    }
}
