package ruan.eloy.backend.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class FirebaseConfig {

    @Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }

    @Value("${firebase.database.url}")
    private String databseUrl;

    @Value("${firebase.config.path}")
    private String configPath;

    @PostConstruct
    public void init() {
        InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);
        FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(serviceAccount)
                .setDatabaseUrl(databseUrl).build();
        FirebaseApp.initializeApp(options);
    }
}
