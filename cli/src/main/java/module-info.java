module rent.auto.cli{

    requires java.sql;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.aop;
    requires spring.jcl;
    requires spring.expression;

    requires rent.auto.service;
    requires rent.auto.common;

    opens ro.ubbcluj.cli to spring.core;

    exports ro.ubbcluj.cli;

}