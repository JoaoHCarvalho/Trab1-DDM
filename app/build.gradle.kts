import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.springframework.boot") version "3.1.0" // Certifique-se de usar uma versão compatível do Spring Boot
    id("io.spring.dependency-management") version "1.1.0"
    //kotlin("jvm") version "1.8.20" // Kotlin JVM para a versão desejada
    kotlin("plugin.spring") version "1.8.20" // Plugin do Kotlin para Spring
    kotlin("plugin.jpa") version "1.8.20" // Para JPA caso use persistência com Kotlin
}

android {
    namespace = "com.example.trab1_ddm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.trab1_ddm"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}
group = "com.exemplo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
repositories {
    google {
        content {
            includeGroupByRegex("com\\.android.*")
            includeGroupByRegex("com\\.google.*")
            includeGroupByRegex("androidx.*")
        }
    }
    mavenCentral()
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("org.springframework.boot:spring-boot-starter-webflux") // Para usar WebClient
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA (se necessário)
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Reflexão para Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Biblioteca padrão do Kotlin
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions") // Extensões reativas para Kotlin
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc") // Para usar banco de dados reativos, se necessário
    runtimeOnly("com.h2database:h2") // Exemplo de banco de dados
    runtimeOnly("io.r2dbc:r2dbc-h2") // Driver reativo para H2
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}
