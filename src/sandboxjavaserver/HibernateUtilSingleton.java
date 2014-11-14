package sandboxjavaserver;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilSingleton {
	private static final SessionFactory sessionFactory;
	
	static{
		Configuration config = new Configuration();
		
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		
		config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		
		//change the next line of code to match your MySQL URL and port
		
		config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sandbox");
		
		//change the next two lines of code to match your MySQL username and password
		
		config.setProperty("hibernate.connection.username", "mark");
		
		config.setProperty("hibernate.connection.password", "a251991a");
		
		//change the pool size to reflect how many users you expect your application to have initially
		
		config.setProperty("hibernate.connection.pool_size", "1");
		
		config.setProperty("hibernate.connection.autocommit", "true");
		
		config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		
		config.setProperty("hibernate.show_sql", "true");

		config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

		config.setProperty("hibernate.current_session_context_class", "thread");
		
		config.addAnnotatedClass(UserBean.class);
		config.addAnnotatedClass(PhoneNumberBean.class);
		config.addAnnotatedClass(AddressBean.class);
		config.addAnnotatedClass(AccountBean.class);

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();        

		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory(){

		return sessionFactory;

	}

	//make a private default constructor so that no other HibernateUtil can be created.

	private HibernateUtilSingleton(){

	}

}

