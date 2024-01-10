/*
 * smart-doc
 *
 * Copyright (C) 2018-2023 smart-doc
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wangshanhai.productivitydoc.plugin.mojo;


import com.thoughtworks.qdox.JavaProjectBuilder;
import com.wangshanhai.productivitydoc.builder.WordDocBuilder;
import com.wangshanhai.productivitydoc.model.ApiConfig;
import com.wangshanhai.productivitydoc.plugin.constant.MojoConstants;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * Send API documents to Word
 *
 * @author cqmike
 */
@Execute(phase = LifecyclePhase.COMPILE)
@Mojo(name = MojoConstants.WORD_REST_MOJO, requiresDependencyResolution = ResolutionScope.COMPILE)
public class WordRestMojo extends BaseDocsGeneratorMojo {

    @Override
    public void executeMojo(ApiConfig apiConfig, JavaProjectBuilder javaProjectBuilder) {
        try {
            WordDocBuilder.buildApiDoc(apiConfig, javaProjectBuilder);
        } catch (Throwable e) {
            getLog().error(e);
            if (apiConfig.isStrict()) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
