package cn.w2site.fik.systk.conf;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInterceptor;

@Configuration
@EnableConfigurationProperties(DruidDbProperties.class)
@Import({ DruidMonitConfig.class })
public class AbstractDruidDBConfig {
	private Logger logger = LoggerFactory.getLogger(AbstractDruidDBConfig.class);

	@Resource
	private DruidDbProperties druidDbProperties;

	public DruidDataSource createDataSource(String url, String username, String password) {
		if (StringUtils.isEmpty(url)) {
			System.out.println(
					"Your database connection pool configuration is incorrect!" + " Please check your Spring profile");
			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}

		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		// datasource.setDriverClassName(
		// StringUtils.isEmpty(driverClassName) ?
		// druidDbProperties.getDriverClassName() : driverClassName);
		datasource.setInitialSize(druidDbProperties.getInitialSize());
		datasource.setMinIdle(druidDbProperties.getMinIdle());
		datasource.setMaxActive(druidDbProperties.getMaxActive());
		datasource.setMaxWait(druidDbProperties.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(druidDbProperties.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(druidDbProperties.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(druidDbProperties.getValidationQuery());
		datasource.setTestWhileIdle(druidDbProperties.isTestWhileIdle());
		datasource.setTestOnBorrow(druidDbProperties.isTestOnBorrow());
		datasource.setTestOnReturn(druidDbProperties.isTestOnReturn());
		try {
			datasource.setFilters(druidDbProperties.getFilters());
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(druidDbProperties.getConnectionProperties());
		return datasource;

	}

	/**
	 * 加载默认mybatis xml配置文件，并初始化分页插件
	 *
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource, "classpath:mybatis/mapper/*.xml");
	}

	/**
	 * 加载mybatis xml配置文件，并初始化分页插件
	 *
	 * @param dataSource
	 *            数据源
	 * @param mapperLocations
	 *            自定义xml配置路径
	 * @return
	 * @throws Exception
	 */
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, String mapperLocations) throws Exception {
		return createSqlSessionFactory(dataSource, mapperLocations);
	}

	private SqlSessionFactory createSqlSessionFactory(DataSource dataSource, String mapperLocations) throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		// mybatis分页
		PageInterceptor pageHelper = new PageInterceptor();
		Properties props = new Properties();
		// props.setProperty("dialect", "mysql"); //5.0不需要
		props.setProperty("reasonable", "true");
		props.setProperty("supportMethodsArguments", "true");
		props.setProperty("returnPageInfo", "check");
		props.setProperty("params", "count=countSql");
		pageHelper.setProperties(props); // 添加插件
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		return sqlSessionFactoryBean.getObject();

	}

}
