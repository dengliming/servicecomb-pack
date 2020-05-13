/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.pack.integration.tests.explicitcontext;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.servicecomb.pack.omega.transport.resttemplate.RestTemplateConfig;
import org.apache.servicecomb.pack.omega.transport.resttemplate.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {WebConfig.class, RestTemplateConfig.class})
@EntityScan(basePackages = "org.apache.servicecomb.pack.alpha")
public class GreetingApplication {
  public static void main(String[] args) {
    SpringApplication.run(GreetingApplication.class, args);
  }

  @Bean
  Queue<String> compensated() {
    return new ConcurrentLinkedQueue<>();
  }

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.rootUri("http://localhost:8080").build();
  }
}
