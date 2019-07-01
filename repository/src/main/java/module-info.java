module rent.auto.repository {

    requires java.sql;

    requires rent.auto.common;

    requires spring.aop;
    requires spring.jcl;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.expression;

    opens ro.ubbcluj.inmemory to spring.core;

    exports ro.ubbcluj.inmemory;
}