﻿# aop-library

    implementation "io.github.arthurstrokov:aop-library:0.0.1"

    repositories {
        maven {
            url "https://maven.pkg.jetbrains.space/avistate/p/aop-library/aop"
            credentials {
                username = spaceUsername // located in gradle.properties
                password = spacePassword // located in gradle.properties
            }
        }
    }
