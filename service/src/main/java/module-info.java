module rent.auto.service {


    requires rent.auto.repository;
    requires rent.auto.common;
    requires spring.context;
    requires spring.beans;

    opens ro.ubbcluj.service.service to spring.core;

    exports ro.ubbcluj.service.service;

}
