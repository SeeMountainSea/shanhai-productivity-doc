/*
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
package com.wangshanhai.productivitydoc.handler;

import com.wangshanhai.productivitydoc.constants.DocAnnotationConstants;
import com.wangshanhai.productivitydoc.utils.DocUtil;
import com.wangshanhai.productivitydoc.utils.JavaClassUtil;
import com.power.common.util.CollectionUtil;
import com.power.common.util.StringUtil;
import com.wangshanhai.productivitydoc.builder.ProjectDocConfigBuilder;
import com.wangshanhai.productivitydoc.constants.Methods;
import com.wangshanhai.productivitydoc.function.RequestMappingFunc;
import com.wangshanhai.productivitydoc.model.annotation.FrameworkAnnotations;
import com.wangshanhai.productivitydoc.model.annotation.MappingAnnotation;
import com.wangshanhai.productivitydoc.model.request.RequestMapping;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.wangshanhai.productivitydoc.constants.DocTags.DEPRECATED;
import static com.wangshanhai.productivitydoc.constants.DocTags.IGNORE;

/**
 * @author yu 2019/12/22.
 */
public class SpringMVCRequestMappingHandler implements IRequestMappingHandler {

    /**
     * handle spring request mapping
     *
     * @param projectBuilder    projectBuilder
     * @param controllerBaseUrl spring mvc controller base url
     * @param method            JavaMethod
     * @return RequestMapping
     */
    @Override
    public RequestMapping handle(ProjectDocConfigBuilder projectBuilder, String controllerBaseUrl,
                                 JavaMethod method, FrameworkAnnotations frameworkAnnotations,
                                 RequestMappingFunc requestMappingFunc) {

        if (Objects.nonNull(method.getTagByName(IGNORE))) {
            return null;
        }
        List<JavaAnnotation> annotations = getAnnotations(method);
        String methodType = null;
        String shortUrl = null;
        String mediaType = null;
        boolean deprecated = Objects.nonNull(method.getTagByName(DEPRECATED));
        Map<String, MappingAnnotation> mappingAnnotationMap = frameworkAnnotations.getMappingAnnotations();
        for (JavaAnnotation annotation : annotations) {
            String annotationName = annotation.getType().getName();
            annotationName = JavaClassUtil.getClassSimpleName(annotationName);
            if (DocAnnotationConstants.DEPRECATED.equals(annotationName)) {
                deprecated = true;
            }
            MappingAnnotation mappingAnnotation = mappingAnnotationMap.get(annotationName);
            if (Objects.isNull(mappingAnnotation)) {
                continue;
            }
            // get consumes of annotation
            Object consumes = annotation.getNamedParameter("consumes");
            if (Objects.nonNull(consumes)) {
                mediaType = consumes.toString();
            }
            if (CollectionUtil.isNotEmpty(mappingAnnotation.getPathProps())) {
                shortUrl = DocUtil.getPathUrl(annotation, mappingAnnotation.getPathProps()
                        .toArray(new String[0]));
            }
            if (StringUtil.isNotEmpty(mappingAnnotation.getMethodType())) {
                methodType = mappingAnnotation.getMethodType();
            } else {
                Object nameParam = annotation.getNamedParameter(mappingAnnotation.getMethodProp());
                if (Objects.nonNull(nameParam)) {
                    methodType = nameParam.toString();
                    methodType = DocUtil.handleHttpMethod(methodType);
                } else {
                    methodType = Methods.GET.getValue();
                }
            }
        }
        RequestMapping requestMapping = RequestMapping.builder()
                .setMediaType(mediaType)
                .setMethodType(methodType)
                .setDeprecated(deprecated)
                .setShortUrl(shortUrl);
        requestMapping = formatMappingData(projectBuilder, controllerBaseUrl, requestMapping);
        requestMappingFunc.process(method.getDeclaringClass(), requestMapping);
        return requestMapping;
    }

}
