module hex.basic.core {
    exports com.jdriven.domain.models to hex.basic.adapter.input.handler, hex.basic.adapter.persistence;
    exports com.jdriven.domain.ports to hex.basic.adapter.input.handler, hex.basic.adapter.persistence, hex.basic.app;
    exports com.jdriven.domain.services to hex.basic.app;
    requires org.slf4j;
}
