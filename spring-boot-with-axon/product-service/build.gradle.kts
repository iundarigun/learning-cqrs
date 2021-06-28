import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.20"
	kotlin("plugin.spring") version "1.5.20"
}

group = "br.com.devcave.cqrs.product"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

val springCloudVersion = "2020.0.3"
val swaggerVersion = "3.0.0"
val axonVersion = "4.5.2"
//val guavaVersion = "30.1.1-jre"

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")

//	implementation("com.google.guava:guava:$guavaVersion")

	implementation("io.springfox:springfox-boot-starter:$swaggerVersion")
	implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")

	implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
