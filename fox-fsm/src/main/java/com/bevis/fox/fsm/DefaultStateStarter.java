package com.bevis.fox.fsm;

import com.bevis.fox.core.context.FoxContext;
import com.bevis.fox.core.context.FoxContextStarted;
import com.bevis.fox.fsm.core.StateInvoker;
import com.bevis.fox.fsm.core.StateRegister;

import java.util.List;
import java.util.Map;

/**
 * The type Default state starter.
 *
 * @author bevis
 * @version DefaultStateStarter.java, v 0.1 2018/4/1 下午3:54
 */
public class DefaultStateStarter implements StateStarter, FoxContextStarted {
    /**
     * The Fox context.
     */
    private FoxContext foxContext;
    /**
     * The State registers.
     */
    private List<StateRegister> stateRegisters;

    /**
     * Sets state registers.
     *
     * @param stateRegisters the state registers
     */
    public void setStateRegisters(List<StateRegister> stateRegisters) {
        this.stateRegisters = stateRegisters;
    }

    /**
     * On started.
     *
     * @param foxContext the fox context
     */
    @Override
    public void onStarted(FoxContext foxContext) {
        this.foxContext = foxContext;
        this.start();
    }

    /**
     * Start boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean start() {
        Map<String, StateInvoker> invokerMap = this.foxContext.getSpringContext().getBeansOfType(StateInvoker.class);
        if (null == invokerMap || 0 == invokerMap.size()) {
            return false;
        }

        for (StateInvoker invoker : invokerMap.values()) {
            this.stateRegisters.forEach(p -> p.registerInvoker(invoker));
        }

        return true;
    }

    /**
     * Order int.
     *
     * @return the int
     */
    @Override
    public int order() {
        return 0;
    }
}