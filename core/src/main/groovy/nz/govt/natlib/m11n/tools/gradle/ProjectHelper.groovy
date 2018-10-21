package nz.govt.natlib.m11n.tools.gradle

import org.gradle.TaskExecutionRequest
import org.gradle.api.Project

class ProjectHelper {

    /**
     * Indicates if the given project (or its parent if it is a subproject) has any tasks with the given name.
     * Subproject tasks have names starting with {@code <subproject-name>:<task-name>}.
     */
    static boolean hasAnyTasks(Project project, List<String> taskNames) {
        boolean isSubproject = (project != project.rootProject)
        String subprojectPrefix = isSubproject ? "${project.name}:" : ""
        List<TaskExecutionRequest> taskExecutionRequestList = project.gradle.getStartParameter().getTaskRequests()
        return taskExecutionRequestList.any { TaskExecutionRequest taskExecutionRequest ->
            taskExecutionRequest.args.any { argument ->
                // it's either a global task (root project and all subprojects (no ':')) or
                // it's a subproject and it starts with the subproject name
                taskNames.any { String candidateTaskName ->
                    (argument == candidateTaskName) || (argument == "${subprojectPrefix}${candidateTaskName}")
                }
            }
        }
    }
}
