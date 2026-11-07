package app.android.buildlogic.config

import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

abstract class ApkBuildTimeValueSource : ValueSource<String, ValueSourceParameters.None> {
    override fun obtain(): String {
        return getApkBuildTime()
    }
}

abstract class AppBuildTimeValueSource : ValueSource<String, ValueSourceParameters.None> {
    override fun obtain(): String {
        return getAppBuildTime()
    }
}

fun getApkBuildTime(): String {
    return getFormattedTime(pattern = "yyyyMMdd_HHmmss")
}

fun getAppBuildTime(): String {
    return getFormattedTime(pattern = "yyyy-MM-dd HH:mm:ss")
}

private fun getFormattedTime(pattern: String): String {
    val now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"))
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return now.format(formatter)
}
