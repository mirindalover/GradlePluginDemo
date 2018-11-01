package com.mlc.plugin.simple

import com.mlc.plugin.simple.builder.Person
import org.gradle.api.Plugin
import org.gradle.api.Project

public class TaskPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {

        project.extensions.add('person', Person)

        project.task('printPerson') {

            doLast {

                Person per = project.person

                println per
            }

        }
    }
}