package android.BB.util;

import android.BB.bean.User;

import java.util.Comparator;

/**
 * Created by Kalina on 2015/9/19.
 */
public class PinyinComparator implements Comparator<User>{
    public int compare(User o1, User o2) {
        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")|| o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
