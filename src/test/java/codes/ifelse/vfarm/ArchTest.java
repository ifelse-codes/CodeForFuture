package codes.ifelse.vfarm;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("codes.ifelse.vfarm");

        noClasses()
            .that()
                .resideInAnyPackage("codes.ifelse.vfarm.service..")
            .or()
                .resideInAnyPackage("codes.ifelse.vfarm.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..codes.ifelse.vfarm.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
