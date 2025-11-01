dependencies {
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
}

// Use an integer for version numbers
version = 1

cloudstream {
    // All of these properties are optional, you can safely remove any of them.

    description = "Lorem ipsum"
    authors = listOf("Cloudburst", "Luna712")

    /**
    * Status int as one of the following:
    * 0: Down
    * 1: Ok
    * 2: Slow
    * 3: Beta-only
    **/
    status = 1 // Will be 3 if unspecified

    tvTypes = listOf("Movie")

    requiresResources = true
    language = "en"

    // Random CC logo I found
    iconUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Korduene_Logo.png"
}

android {
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

tasks.register<Copy>("includeViewBindingInDex") {
	group = "build"
	description = "Ensures ViewBinding-generated classes are included in dex."

	val viewBindingDir = layout.buildDirectory.dir("generated/source/viewBinding/debug")
	val kotlinOutput = layout.buildDirectory.dir("tmp/kotlin-classes/debug")
	val dexInputDir = layout.buildDirectory.dir("intermediates/dexInput")

	from(viewBindingDir)
	from(kotlinOutput)
	into(dexInputDir)

	doLast {
		println("Included ViewBinding classes from: ${viewBindingDir.get().asFile}")
	}
}

tasks.named("compileDex") {
	dependsOn("includeViewBindingInDex")
	doFirst {
		val dexInputDir = layout.buildDirectory.dir("intermediates/dexInput").get().asFile
		inputs.dir(dexInputDir)
	}
}
