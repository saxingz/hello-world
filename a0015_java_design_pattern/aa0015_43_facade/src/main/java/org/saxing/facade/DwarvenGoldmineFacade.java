package org.saxing.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DwarvenGoldmineFacade provides a single interface through which users can operate the subsystems.
 *
 * This makes the goldmine easier to operate and cuts the dependencies from the goldmine user to the
 * subsystems.
 *
 * @author saxing 2019/1/7 9:06
 */
public class DwarvenGoldmineFacade {

    private final List<DwarvenMineWorker> workers;

    public DwarvenGoldmineFacade() {
        this.workers = new ArrayList<>();
        workers.add(new DwarvenGoldDigger());
        workers.add(new DwarvenCartOperator());
        workers.add(new DwarvenTunnelDigger());
    }

    public void startNewDay(){
        makeActions(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE);
    }

    public void digOutGold() {
        makeActions(workers, DwarvenMineWorker.Action.WORK);
    }

    public void endDay() {
        makeActions(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP);
    }

    private static void makeActions(Collection<DwarvenMineWorker> workers, DwarvenMineWorker.Action ... actions){
        for (DwarvenMineWorker worker : workers){
            worker.action(actions);
        }
    }
}
