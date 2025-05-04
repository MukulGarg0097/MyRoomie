//package com.myRoomie.config;
//
//import org.hibernate.SessionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
//
//@Configuration
//public class HibernateConfig {
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactory() {
//		return new HibernateJpaSessionFactoryBean();
//	}
//
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		return new HibernateTransactionManager(sessionFactory);
//	}
//}
