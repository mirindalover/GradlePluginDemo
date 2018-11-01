
package com.mlc.plugin.simple

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LogPlugin implements Plugin<Project>{


    @Override
    void apply(Project project) {
        System.out.println("==================")
        System.out.println("Hello Plugin World!")
        System.out.println("==================")
    }
}