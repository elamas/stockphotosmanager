package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import stockphotosmanager.StockphotosmanagerApplication;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"stockphotosmanager"
		}, 
		excludeFilters={
		  @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=StockphotosmanagerApplication.class)}
		)
@EntityScan("stockphotosmanager.models")
public class TestsConfig {

}
