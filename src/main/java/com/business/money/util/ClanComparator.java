package com.business.money.util;

import com.business.money.entities.ClanEntity;

public class ClanComparator {
    public static int compare(ClanEntity clan1, ClanEntity clan2) {
        if (clan1.getMembers().size() > clan2.getMembers().size()) return 1;
        else if (clan1.getMembers().size() == clan2.getMembers().size()) return 0;
        return -1;
    }
}
