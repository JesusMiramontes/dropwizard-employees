//import com.google.inject.Guice;
//import com.hubspot.dropwizard.guice.GuiceBundle;
//import com.miramontes.config.DatabaseConfig;
//import com.miramontes.controller.EmployeeController;
//import com.miramontes.dao.EmployeeDao;
//import io.dropwizard.Application;
//import io.dropwizard.db.DataSourceFactory;
//import io.dropwizard.hibernate.HibernateBundle;
//import io.dropwizard.setup.Bootstrap;
//import io.dropwizard.setup.Environment;
//import com.miramontes.model.EmployeeModel;
//import com.miramontes.modules.EmployeeModule;
//
//public class EmployeeAppRunner2 extends Application<DatabaseConfig> {
//
//    private GuiceBundle<DatabaseConfig> guiceBundle;
//
//    public static void main(String[] args) throws Exception {
//        Guice.createInjector(new EmployeeModule());
//        new EmployeeAppRunner2().run(args);
//    }
//
//    public void run(DatabaseConfig myConfiguration, Environment environment) throws Exception {
//        System.out.println("Value from dev.yml is "+myConfiguration.getDatabase().getUser());
//        EmployeeDao infoDao = new EmployeeDao(hibernate.getSessionFactory());
//        final EmployeeController resource = new EmployeeController(infoDao, environment.getValidator());
//        environment.jersey().register(resource);
//
//    }
//
//    private HibernateBundle<DatabaseConfig> hibernate = new HibernateBundle<DatabaseConfig>(EmployeeModel.class) {
//        @Override
//        public DataSourceFactory getDataSourceFactory(DatabaseConfig configuration) {
//            return configuration.getDatabase();
//        }
//    };
//
//    @Override
//    public String getName() {
//        return "dropwizard-hibernate";
//    }
//
//    @Override
//    public void initialize(Bootstrap<DatabaseConfig> bootstrap) {
//        guiceBundle = GuiceBundle.<DatabaseConfig>newBuilder()
//                .addModule(new EmployeeModule())
//                .enableAutoConfig(getClass().getPackage().getName())
//                .setConfigClass(DatabaseConfig.class)
//                .build();
//        bootstrap.addBundle(guiceBundle);
//        bootstrap.addBundle(hibernate);
//    }
//}
