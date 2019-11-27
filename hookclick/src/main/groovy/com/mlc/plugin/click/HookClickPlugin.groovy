package com.mlc.plugin.click

import com.android.build.gradle.AppExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by mulianchao on 2019/11/26.
 */
class HookClickPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        AppExtension appExtension = project.extensions.findByType(AppExtension.class)
        //添加自定义的Transform到 编译任务中
        appExtension.registerTransform(new ClickTransform())

    }
}
