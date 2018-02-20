import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

val kotlinVersion: String by extra
val springBootVersion: String by extra


buildscript {
    val kotlinVersion by extra { "1.2.10" }
    val springBootVersion by extra { "2.0.0.M7" }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.spring.io/milestone")
        }
    }
}

plugins {
    val kotlinVersion = "1.2.10"
    id("org.springframework.boot").version("1.5.9.RELEASE")
    id("io.spring.dependency-management").version("1.0.3.RELEASE")
    kotlin("plugin.spring").version(kotlinVersion)
    kotlin("jvm").version(kotlinVersion)
}


repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.spring.io/milestone")
    }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    compile("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-cassandra-reactive:$springBootVersion")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.3")
    testCompile("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testCompile("io.projectreactor:reactor-test")
    testCompile("org.cassandraunit:cassandra-unit-spring:3.3.0.2")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<BootRun> {
        // Ensures IntelliJ can live reload resource files
        val sourceSets = the<JavaPluginConvention>().sourceSets
        sourceResources(sourceSets["main"])
    }
}