plugins {
    kotlin("multiplatform") version "2.4.20"
}

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(npm("nanoid", "5.1.6"))
        }
    }
}
