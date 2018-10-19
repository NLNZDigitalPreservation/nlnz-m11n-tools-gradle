package nz.govt.natlib.m11n.tools.gradle

import org.gradle.api.GradleException

/**
 * For problems with builds.
 */
class BuildException extends GradleException {
    BuildException() {
        super()
    }

    BuildException(String message) {
        super(message)
    }

    BuildException(String message, Throwable cause) {
        super(message, cause)
    }
}
