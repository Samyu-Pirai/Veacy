/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */

package com.kaytes.veacy.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
@Configuration
public class SwaggerConfiguration {
	@Bean
    public OpenAPI api() {

        Info info = new Info();
        info.title("Swagger for InvestorInvitee Table and Status Table")
        .description("This is a RestAPI CURD Operation of InvestorInvitee and Role Table")
        .version("1.0");
        
        Server localServer = new Server();
        localServer.url("http://localhost:8090");
        localServer.description("Server URL in Local environment");

        Server productionServer = new Server();
        productionServer.setUrl("https");
//        productionServer.setDescription("Server URL in Production environment");
        
        Server devServer = new Server();
        devServer.url("https://dev.com");
        devServer.description("Server URL in dev environment");
        
        return new OpenAPI().info(info)
                .servers(List.of(localServer,productionServer,devServer));
    }
	

}
