dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
		dataSource {
			pooled = true
//			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			 dbCreate = "update"
			// url = "jdbc:h2:mem:devDb;MVCC=TRUE"
			url = "jdbc:mysql://localhost:3306/leavemanagement"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "root"
			properties {
				maxActive = 50
				maxIdle = 25
				minIdle = 5
				initialSize = 5
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "/* ping */"
			}
			}
    }
    test {
        dataSource {
            dbCreate = "update"
			//url = "jdbc:h2:mem:testDb;MVCC=TRUE"
			url = "jdbc:mysql://localhost:3306/leavemanagement"
			username = "root"
			password = "root"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost:3306/leavemanagement"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = "com.mysql.jdbc.Driver"
			pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
