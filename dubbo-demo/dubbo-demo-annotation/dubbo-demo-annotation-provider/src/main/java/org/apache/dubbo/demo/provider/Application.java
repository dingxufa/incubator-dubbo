/*
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
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Application {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "org.apache.dubbo.demo.provider") //使用 @EnableDubbo 注解，配置扫描 "org.apache.dubbo.demo.provider" 目录下的 @Service 和 @Reference Bean 对象。
    @PropertySource("classpath:/spring/dubbo-provider.properties")//用 @PropertySource 注解，导入 "classpath:/spring/dubbo-provider.properties" 配置文件。
    static class ProviderConfiguration {
        @Bean //通过 @Bean 注解方法，创建 RegistryConfig Bean 对象，即注册中心
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
//            registryConfig.setAddress("multicast://224.5.6.7:1234");
            registryConfig.setAddress("zookeeper://10.200.4.75:2181");
            return registryConfig;
        }
    }
}
