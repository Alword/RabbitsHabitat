package com.company.models;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class RabbitsStorage implements Serializable {
    public Vector<AlbinoRabbit> AlbinoRabbits;
    public Vector<OrdinaryRabbit> OrdinaryRabbits;
    public Vector<BaseRabbit> BaseRabbits;
    public RabbitsStorage() {
        AlbinoRabbits = new Vector<AlbinoRabbit>();
        OrdinaryRabbits = new Vector<OrdinaryRabbit>();
        BaseRabbits = new Vector<BaseRabbit>();
    }
}


