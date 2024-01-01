rootProject.name = "com.sslukess.ideabank-v1"

plugins {
    id("com.gradle.enterprise") version("3.16.1")
}

gradleEnterprise {
    buildScan {
        tag("TEST-TAG")
    }
}
