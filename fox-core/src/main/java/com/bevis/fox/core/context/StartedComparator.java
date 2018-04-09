package com.bevis.fox.core.context;

import java.util.Comparator;

/**
 * The type Started comparator.
 *
 * @author bevis
 * @version StartedComparator.java, v 0.1 2018/4/1 下午4:03
 */
public class StartedComparator implements Comparator<FoxContextStarted> {

    /**
     * Compare int.
     *
     * @param o1 the o 1
     * @param o2 the o 2
     * @return the int
     */
    @Override
    public int compare(FoxContextStarted o1, FoxContextStarted o2) {
        return o1.order() - o2.order();
    }
}
