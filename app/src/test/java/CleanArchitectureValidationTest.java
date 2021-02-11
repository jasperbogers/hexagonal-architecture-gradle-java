import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * ArchUnit test validates general software architecture principles of clean architecture.
 */
@AnalyzeClasses(packages = "com.jdriven", importOptions = {ImportOption.DoNotIncludeTests.class})
class CleanArchitectureValidationTest {

    private static final String[] domainModelPackages = {"..domain.models"};
    private static final String[] domainPortsPackages = {"..domain.ports"};
    private static final String[] domainServicePackages = {"..domain.services"};
    private static final String[] adapterPackages = {"..adapters.."};
    private static final String[] bootstrapPackages = {"..app"};

    private static final String DOMAIN_MODEL_LAYER = "domain model";
    private static final String DOMAIN_SERVICE_LAYER = "domain service";
    private static final String PORT_LAYER = "ports";
    private static final String ADAPTER_LAYER = "adapter";
    private static final String BOOTSTRAP_LAYER = "bootstrap";

    /**
     * TODO We should also forbid adapters from calling each other!
     */
    @ArchTest
    static final ArchRule adheresToCleanArchitecture = layeredArchitecture()
            .layer(DOMAIN_MODEL_LAYER).definedBy(domainModelPackages)
            .layer(DOMAIN_SERVICE_LAYER).definedBy(domainServicePackages)
            .layer(PORT_LAYER).definedBy(domainPortsPackages)
            .layer(ADAPTER_LAYER).definedBy(adapterPackages)
            .layer(BOOTSTRAP_LAYER).definedBy(bootstrapPackages)
            .whereLayer(BOOTSTRAP_LAYER).mayNotBeAccessedByAnyLayer()
            .whereLayer(ADAPTER_LAYER).mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
            .whereLayer(DOMAIN_MODEL_LAYER).mayOnlyBeAccessedByLayers(ADAPTER_LAYER, DOMAIN_SERVICE_LAYER, PORT_LAYER)
            .whereLayer(DOMAIN_SERVICE_LAYER).mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER)
            .whereLayer(PORT_LAYER).mayOnlyBeAccessedByLayers(BOOTSTRAP_LAYER, ADAPTER_LAYER, DOMAIN_SERVICE_LAYER)
            .because("In hexagonal architecture, dependencies should point inward.");
}
