package br.com.avaliacao.cursos.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.avaliacao.cursos.WLogger;
import liquibase.integration.spring.SpringLiquibase;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Configuration
public class LiquibaseConfiguration {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SpringLiquibase liquibase() {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    try {
		    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
		    liquibase.setDataSource(dataSource);
	    } catch(Exception e) {
	    	WLogger.error(e);
	    }
	    return liquibase;
	}
}

