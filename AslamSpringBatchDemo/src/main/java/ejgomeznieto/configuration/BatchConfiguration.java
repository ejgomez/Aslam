package ejgomeznieto.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import ejgomeznieto.model.database.Lectoc1;
import ejgomeznieto.model.file.Lectoc1Footer;
import ejgomeznieto.model.file.Lectoc1Header;
import ejgomeznieto.model.file.Lectoc1Line;
import ejgomeznieto.processor.Lectoc1FooterItemProcessor;
import ejgomeznieto.processor.Lectoc1HeaderItemProcessor;
import ejgomeznieto.processor.Lectoc1LineItemProcessor;
import ejgomeznieto.tokenizer.Lectoc1LineTokenizer;

@Configuration
@EnableBatchProcessing
@ComponentScan
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class BatchConfiguration {

	@Value("${database.driver}")
	private String databaseDriver;
	@Value("${database.url}")
	private String databaseUrl;
	@Value("${database.username}")
	private String databaseUsername;
	@Value("${database.password}")
	private String databasePassword;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public ItemReader<Lectoc1Header> headerReader() {
		FlatFileItemReader<Lectoc1Header> reader = new FlatFileItemReader<Lectoc1Header>();
		reader.setResource(new ClassPathResource("LECTOC1.txt"));
		DefaultLineMapper<Lectoc1Header> lineMapper = new DefaultLineMapper<Lectoc1Header>();
		LineTokenizer lineTokenizer = new Lectoc1LineTokenizer();
		lineMapper.setLineTokenizer(lineTokenizer);
		BeanWrapperFieldSetMapper<Lectoc1Header> fieldSetMapper = new BeanWrapperFieldSetMapper<Lectoc1Header>();
		fieldSetMapper.setTargetType(Lectoc1Header.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	public ItemReader<Lectoc1Line> lineReader() {
		FlatFileItemReader<Lectoc1Line> reader = new FlatFileItemReader<Lectoc1Line>();
		reader.setResource(new ClassPathResource("LECTOC1.txt"));
		DefaultLineMapper<Lectoc1Line> lineMapper = new DefaultLineMapper<Lectoc1Line>();
		LineTokenizer lineTokenizer = new Lectoc1LineTokenizer();
		lineMapper.setLineTokenizer(lineTokenizer);
		BeanWrapperFieldSetMapper<Lectoc1Line> fieldSetMapper = new BeanWrapperFieldSetMapper<Lectoc1Line>();
		fieldSetMapper.setTargetType(Lectoc1Line.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	public ItemReader<Lectoc1Footer> footerReader() {
		FlatFileItemReader<Lectoc1Footer> reader = new FlatFileItemReader<Lectoc1Footer>();
		reader.setResource(new ClassPathResource("LECTOC1.txt"));
		DefaultLineMapper<Lectoc1Footer> lineMapper = new DefaultLineMapper<Lectoc1Footer>();
		LineTokenizer lineTokenizer = new Lectoc1LineTokenizer();
		lineMapper.setLineTokenizer(lineTokenizer);
		BeanWrapperFieldSetMapper<Lectoc1Footer> fieldSetMapper = new BeanWrapperFieldSetMapper<Lectoc1Footer>();
		fieldSetMapper.setTargetType(Lectoc1Footer.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	public ItemProcessor<Lectoc1Header, Lectoc1> headerProcessor() {
		return new Lectoc1HeaderItemProcessor();
	}

	@Bean
	public ItemProcessor<Lectoc1Line, Lectoc1> lineProcessor() {
		return new Lectoc1LineItemProcessor();
	}

	@Bean
	public ItemProcessor<Lectoc1Footer, Lectoc1> footerProcessor() {
		return new Lectoc1FooterItemProcessor();
	}

	@Bean
	public ItemWriter<Lectoc1> writer() {
		final JpaItemWriter<Lectoc1> writer = new JpaItemWriter<Lectoc1>();
		writer.setEntityManagerFactory(entityManagerFactory().getObject());
		return writer;
	}

	@Bean
	public Job importData(JobBuilderFactory jobs) {
		return jobs.get("importData").incrementer(new RunIdIncrementer()).start(step1()).next(step2()).next(step3())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Lectoc1Header, Lectoc1>chunk(40).reader(headerReader())
				.processor(headerProcessor()).writer(writer()).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<Lectoc1Line, Lectoc1>chunk(40).reader(lineReader())
				.processor(lineProcessor()).writer(writer()).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").<Lectoc1Footer, Lectoc1>chunk(40).reader(footerReader())
				.processor(footerProcessor()).writer(writer()).build();
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriver);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		return new LocalContainerEntityManagerFactoryBean();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
}
