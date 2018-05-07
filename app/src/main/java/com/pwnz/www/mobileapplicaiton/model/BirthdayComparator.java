package com.pwnz.www.mobileapplicaiton.model;

import org.joda.time.DateTime;
import java.util.Comparator;

public class BirthdayComparator implements Comparator<BirthdayEntity> {
    @Override
    public int compare(BirthdayEntity o1, BirthdayEntity o2) {
        String o1Date[] = o1.getDate().split("/");
        String o2Date[] = o2.getDate().split("/");

        DateTime curr = new DateTime();

        DateTime dt1 = new DateTime(
            curr.getYear(),
            Integer.parseInt(o1Date[1]),
            Integer.parseInt(o1Date[0]),
            1,1
            );

        DateTime dt2 = new DateTime(
            curr.getYear(),
            Integer.parseInt(o2Date[1]),
            Integer.parseInt(o2Date[0]),
            1,1
            );

        int dt1Doy = dt1.getDayOfYear();
        int dt2Doy = dt2.getDayOfYear();
        int currDoy = curr.getDayOfYear();
        return chooseUpcomingBday(dt1Doy, dt2Doy, currDoy);
    }


    private int chooseUpcomingBday(int dt1Doy, int dt2Doy, int currDoy) {
        dt1Doy = normalizeDoy(dt1Doy, currDoy);
        dt2Doy = normalizeDoy(dt2Doy, currDoy);
        if(dt1Doy > dt2Doy)
            return 1;
        else
            return -1;
    }

    private int normalizeDoy(int doy, int currDoy) {
        int res;
        if(doy > currDoy)
            res = doy-currDoy;
        else
            res = doy+currDoy;
        return res;
    }
}
