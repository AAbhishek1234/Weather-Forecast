package dependencies

object Dependencies {

    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.contraint_layout_version}"
    val workmanager = "androidx.work:work-runtime-ktx:${Versions.workmanager}"
    val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx}"
}