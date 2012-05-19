package com.example.finagle.spring;

import scala.runtime.BoxedUnit;

import com.twitter.util.Function0;

abstract public class Ensure extends Function0<BoxedUnit> {

    public final BoxedUnit apply() {
        doApply();
        return BoxedUnit.UNIT;
    }

    abstract protected void doApply();
}
