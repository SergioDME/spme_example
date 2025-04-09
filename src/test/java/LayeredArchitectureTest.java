import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import project.GenerateReport;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "project")
public class LayeredArchitectureTest {
//    @ArchTest
//    public static final ArchRule boundary_dependencies_are_respected = layeredArchitecture()
//
//            .layer("Boundaries").definedBy("project.ClassiView..")
//            .layer("Controls").definedBy("project.ClassiController..")
//            .layer("Entities").definedBy("modelli..")
//            .layer("Database").definedBy("project.ClassiDatabase..")
//
//            .whereLayer("Boundaries").mayNotBeAccessedByAnyLayer();
//
//
//	@ArchTest
//    public static final ArchRule BoundaryControl_dependencies_are_respected = layeredArchitecture()
//
//            .layer("Boundaries").definedBy("project.ClassiView..")
//            .layer("Controls").definedBy("project.ClassiController..")
//            .layer("Entities").definedBy("modelli..")
//            .layer("Database").definedBy("project.ClassiDatabase..")
//
//
//            .whereLayer("Controls").mayOnlyBeAccessedByLayers("Boundaries");
//
//	@ArchTest
//    public static final ArchRule EntityControl_dependencies_are_respected = layeredArchitecture()
//            .layer("Boundaries").definedBy("project.ClassiView..")
//            .layer("Controls").definedBy("project.ClassiController..")
//            .layer("Entities").definedBy("modelli..")
//            .layer("Database").definedBy("project.ClassiDatabase..")
//
//            .whereLayer("Entities").mayOnlyBeAccessedByLayers("Controls");
//
//	@ArchTest
//    public static final ArchRule DatabaseControl_dependencies_are_respected = layeredArchitecture()
//            .layer("Boundaries").definedBy("project.ClassiView..")
//            .layer("Controls").definedBy("project.ClassiController..")
//            .layer("Entities").definedBy("modelli..")
//            .layer("Database").definedBy("project.ClassiDatabase..")
//
//            .whereLayer("Database").mayOnlyBeAccessedByLayers("Controls");


    @ArchTest
    public static final ArchRule model_dependencies_are_respected = layeredArchitecture()
            .layer("View").definedBy("project."+ GenerateReport.PACKAGE_NAME_VIEW +"..")
            .layer("Controller").definedBy("project."+GenerateReport.PACKAGE_NAME_CONTROLLER+"..")
            .layer("Model").definedBy("project."+GenerateReport.PACKAGE_NAME_MODEL+"..")
            .layer("Database").definedBy("project."+GenerateReport.PACKAGE_NAME_DATABASE+"..")
//            .whereLayer("Model").mayOnlyBeAccessedByLayers("Controller");
            .whereLayer("Model").mayNotBeAccessedByAnyLayer();
    @ArchTest

    public static final ArchRule view_dependencies_are_respected = layeredArchitecture()
            .layer("View").definedBy("project."+ GenerateReport.PACKAGE_NAME_VIEW +"..")
            .layer("Controller").definedBy("project."+GenerateReport.PACKAGE_NAME_CONTROLLER+"..")
            .layer("Model").definedBy("project."+GenerateReport.PACKAGE_NAME_MODEL+"..")
            .layer("Database").definedBy("project."+GenerateReport.PACKAGE_NAME_DATABASE+"..")
//            .whereLayer("View").mayOnlyBeAccessedByLayers("Model");
            .whereLayer("View").mayNotBeAccessedByAnyLayer();
    @ArchTest
    public static final ArchRule controller_dependencies_are_respected = layeredArchitecture()
            .layer("View").definedBy("project."+ GenerateReport.PACKAGE_NAME_VIEW +"..")
            .layer("Controller").definedBy("project."+GenerateReport.PACKAGE_NAME_CONTROLLER+"..")
            .layer("Model").definedBy("project."+GenerateReport.PACKAGE_NAME_MODEL+"..")
            .layer("Database").definedBy("project."+GenerateReport.PACKAGE_NAME_DATABASE+"..")
//            .whereLayer("Controller").mayOnlyBeAccessedByLayers("View");
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer();
    @ArchTest
    public static final ArchRule db_dependencies_are_respected = layeredArchitecture()
            .layer("View").definedBy("project."+ GenerateReport.PACKAGE_NAME_VIEW +"..")
            .layer("Controller").definedBy("project."+GenerateReport.PACKAGE_NAME_CONTROLLER+"..")
            .layer("Model").definedBy("project."+GenerateReport.PACKAGE_NAME_MODEL+"..")
            .layer("Database").definedBy("project."+GenerateReport.PACKAGE_NAME_DATABASE+"..")
//            .whereLayer("Database").mayOnlyBeAccessedByLayers("Model");
            .whereLayer("Database").mayNotBeAccessedByAnyLayer();

    @ArchTest
    static final ArchRule view_loop = classes()
            .that().resideInAPackage("project."+GenerateReport.PACKAGE_NAME_VIEW+"")
            .should().dependOnClassesThat(
                    JavaClass.Predicates.resideOutsideOfPackage("project."+GenerateReport.PACKAGE_NAME_VIEW+"")
            );
    @ArchTest
    static final ArchRule model_loop = classes()
            .that().resideInAPackage("project."+GenerateReport.PACKAGE_NAME_MODEL+"")
            .should().dependOnClassesThat(
                    JavaClass.Predicates.resideOutsideOfPackage("project."+GenerateReport.PACKAGE_NAME_MODEL+"")
            );

    @ArchTest
    static final ArchRule controller_loop = classes()
            .that().resideInAPackage("project."+GenerateReport.PACKAGE_NAME_CONTROLLER+"")
            .should().dependOnClassesThat(
                    JavaClass.Predicates.resideOutsideOfPackage("project."+GenerateReport.PACKAGE_NAME_CONTROLLER+"")
            );

    @ArchTest
    static final ArchRule database_loop = classes()
            .that().resideInAPackage("project."+GenerateReport.PACKAGE_NAME_DATABASE+"")
            .should().dependOnClassesThat(
                    JavaClass.Predicates.resideOutsideOfPackage("project."+GenerateReport.PACKAGE_NAME_DATABASE+"")
            );

}
