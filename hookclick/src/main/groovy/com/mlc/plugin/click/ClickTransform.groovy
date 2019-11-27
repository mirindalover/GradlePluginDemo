package com.mlc.plugin.click

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils

import java.util.jar.JarFile

/**
 * Created by mulianchao on 2019/11/27.
 */
class ClickTransform extends Transform {

    @Override
    String getName() {
        //编译的文件存放在  /build/intermediates/transforms/[name]中
        return "clickHook"
    }

    @Override
     Set<QualifiedContent.ContentType> getInputTypes() {
        //处理class文件
        return TransformManager.CONTENT_CLASS
    }

    @Override
     Set<? super QualifiedContent.Scope> getScopes() {
        //主项目、子模块、lib
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
     boolean isIncremental() {
        //支持增量编译，需要处理 changed、removed、added类型
        return true
    }

    @Override
     void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        //此次是否是增量编译
        boolean incremental = transformInvocation.isIncremental()
        //改transform的输出
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider()
        //输入
        Collection<TransformInput> inputs = transformInvocation.getInputs()
        Context context = transformInvocation.getContext()

        if (!isIncremental()) {
            //不是增量编译先把原来的输出删除
            outputProvider.deleteAll()
        }

        for (TransformInput temp : inputs) {
            hookJar(temp.getJarInputs(), incremental, outputProvider, context)
            hookDirectory(temp.getDirectoryInputs(), incremental, outputProvider, context)
        }
    }

    /**
     * 处理文件夹
     */
    private void hookDirectory(Collection<DirectoryInput> directoryInputs, boolean incremental, TransformOutputProvider outputProvider, Context context) {

    }

    /**
     * 处理jar包
     */
    private void hookJar(Collection<JarInput> jarInputs, boolean incremental, TransformOutputProvider outputProvider, Context context) {
        for (JarInput temp : jarInputs) {
            //找到要处理的jar包
            File destJar = outputProvider.getContentLocation(temp.getName(), temp.getContentTypes(), temp.getScopes(), Format.JAR)
            if (incremental) {

            } else {
                transformJar(destJar, temp, context)
            }
        }
    }

    private void transformJar(File destJar, JarInput input, Context context) {
        File modifiedJar = null
        modifiedJar = modifiedJarFile(input.getFile(), context.getTemporaryDir())
        if (modifiedJar == null) {
            modifiedJar = input.getFile()
        }
        FileUtils.copyDirectory(modifiedJar, destJar)
    }

    /**
     * 修改jar
     */
    private File modifiedJarFile(File inputFile, File temporaryDir) {
        JarFile jarFile = new JarFile(inputFile)
        return null
    }
}
