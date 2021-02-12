plugins {
    application
    id("shared-config")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":adapter-input-handler"))
    implementation(project(":adapter-persistence"))

    testImplementation("com.tngtech.archunit:archunit-junit5:0.16.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass.set("com.jdriven.app.App")
}
