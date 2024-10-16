package eu.profinit.education.flightlog;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;

@SpringBootTest(classes = IntegrationTestConfig.class)
@Transactional
@ActiveProfiles("inttest")
public abstract class AbstractIntegrationTest {
}